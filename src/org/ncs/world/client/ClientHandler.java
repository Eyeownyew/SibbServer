package org.ncs.world.client;

import java.util.ArrayList;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.ncs.world.Message;
import org.ncs.world.World;

public class ClientHandler {
	private List<Client> clients = new ArrayList<Client>();

	public List<Client> getClients() {
		return clients;
	}

	public ClientHandler() {

	}

	public void addClient(Client c) {
		clients.add(c);
	}

	public void removeClient(Client c) {
		clients.remove(c);
	}

	public void updatePlayers() {
		for (Client c : clients) {
			if (c.loggedIn) {
				ChannelBuffer updateBlock = ChannelBuffers.dynamicBuffer();
				updateBlock.writeByte(1);
				updateBlock.writeByte(c.updateFlags);

				if ((c.updateFlags & 0x1) == 0x1) {
					List<Message> messages = World.getWorld().getMessages();
					updateBlock.writeByte(messages.size() - c.lastMessage);
					for (int i = c.lastMessage; i < messages.size(); i++) {
						Message message = messages.get(i);
						byte[] usernameBytes = message.getClient().username.getBytes();
						updateBlock.writeByte(usernameBytes.length);
						updateBlock.writeBytes(usernameBytes);
						byte[] messageBytes = (message.getMessage()).getBytes();
						updateBlock.writeInt(messageBytes.length);
						updateBlock.writeBytes(messageBytes);
						updateBlock.writeByte(message.getType());
					}
					c.lastMessage = messages.size();
				}
				c.getChannel().write(updateBlock);
				c.updateFlags = 0;
			}
		}
	}
}
