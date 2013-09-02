package org.ncs.net.packet;

import org.ncs.world.client.Client;

public interface PacketType {
	public void processPacket(Client c, Packet p);
}
