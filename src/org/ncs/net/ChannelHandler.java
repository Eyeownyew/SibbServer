package org.ncs.net;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.ncs.Engine;
import org.ncs.net.packet.Packet;
import org.ncs.net.packet.PacketHandler;
import org.ncs.world.client.Client;

public class ChannelHandler extends SimpleChannelHandler {
	private Client client = null;

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		Packet p = (Packet) e.getMessage();
		PacketHandler.processPacket(client, p);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		client = new Client(ctx.getChannel());
		Engine.getInstance().getClientHandler().addClient(client);
		System.out.println("New connection made.");
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		Engine.getInstance().getClientHandler().removeClient(client);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		System.out.println(e.getCause().getMessage());
	}
}