package org.ncs;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.ncs.net.ChannelHandler;
import org.ncs.net.codec.PacketDecoder;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Server().start();
	}

	private void start() {
		ChannelFactory factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap(factory);
		ChannelPipelineFactory channelPipeline = new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new PacketDecoder());
				pipeline.addLast("handler", new ChannelHandler());
				return pipeline;
			}
		};

		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		bootstrap.setPipelineFactory(channelPipeline);
		bootstrap.bind(new InetSocketAddress(43599));
		new Engine().init().run();
	}
}
