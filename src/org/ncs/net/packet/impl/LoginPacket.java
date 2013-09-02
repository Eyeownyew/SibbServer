package org.ncs.net.packet.impl;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.ncs.net.packet.Packet;
import org.ncs.net.packet.PacketType;
import org.ncs.world.client.Client;

public class LoginPacket implements PacketType {

	@Override
	public void processPacket(Client c, Packet p) {
		ChannelBuffer data = p.getData();
		String username = "";
		String password = "";

		byte b = data.readByte();
		for (int i = 0; i < b; i++)
			username += "" + (char) data.readByte();

		System.out.println("");

		byte b2 = data.readByte();
		for (int i = 0; i < b2; i++)
			password += (char) data.readByte();

		System.out.println("Username: " + username + ", MD5-Pass: " + password);
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		c.username = username;
		buffer.writeByte(0);
		buffer.writeByte(1);
		c.loggedIn = true;
		c.getChannel().write(buffer);
	}

}
