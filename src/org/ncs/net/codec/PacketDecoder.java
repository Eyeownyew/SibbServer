package org.ncs.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.ncs.net.packet.Packet;

public class PacketDecoder extends ReplayingDecoder<DecoderState> {

	private byte opcode = 0;

	public PacketDecoder() {
		super(DecoderState.READ_OPCODE);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer, DecoderState state) throws Exception {
		switch (state) {
		case READ_OPCODE:
			opcode = buffer.readByte();
			checkpoint(DecoderState.READ_DATA);
		case READ_DATA:
			checkpoint(DecoderState.READ_OPCODE);
			return new Packet(opcode, buffer);
		default:
			throw new Error("Shouldn't reach here.");
		}
	}

}
