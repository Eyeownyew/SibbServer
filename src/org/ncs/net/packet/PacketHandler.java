package org.ncs.net.packet;

import org.ncs.net.packet.impl.LoginPacket;
import org.ncs.net.packet.impl.MessagePacket;
import org.ncs.world.client.Client;

public class PacketHandler {

	private static PacketType packetHandlers[] = new PacketType[255];
	static {
		packetHandlers[0] = new LoginPacket();
		packetHandlers[1] = new MessagePacket();
	}
	
	public static void processPacket(Client c, Packet p) {
		byte opcode = p.getOpcode();
		if(packetHandlers[opcode] != null) {
			packetHandlers[opcode].processPacket(c, p);
		} else {
			System.out.println("Unhandled packet -- Opcode: "+opcode+", Length: "+(p.getData().capacity()-1));
		}
	}
}
