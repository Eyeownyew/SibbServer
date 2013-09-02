package org.ncs.world.client;

import org.jboss.netty.channel.Channel;
import org.ncs.world.World;

public class Client {
	public String username = "";
	public Channel channel = null;
	public boolean loggedIn = false;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Client(Channel channel) {
		this.channel = channel;
		lastMessage = World.getWorld().getMessages().size();
	}
	public byte updateFlags = 0;
	
	public void chatUpdate() {
		updateFlags |= 0x1;
	}
	
	public int lastMessage = 0;
}
