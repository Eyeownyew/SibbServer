package org.ncs.net.packet.impl;

import org.jboss.netty.buffer.ChannelBuffer;
import org.ncs.Engine;
import org.ncs.net.packet.Packet;
import org.ncs.net.packet.PacketType;
import org.ncs.world.Message;
import org.ncs.world.World;
import org.ncs.world.client.Client;

public class MessagePacket implements PacketType {

	@Override
	public void processPacket(Client c, Packet p) {
		ChannelBuffer data = p.getData();
		String message = "";

		int b = data.readInt();
		for (int i = 0; i < b; i++)
			message += "" + (char) data.readByte();
		World.getWorld().getMessages().add(new Message(c, message, 0));
		for(Client other : Engine.getInstance().getClientHandler().getClients())
			other.chatUpdate();
	}

}
