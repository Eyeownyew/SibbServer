package org.ncs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.ncs.world.World;
import org.ncs.world.client.ClientHandler;

public class Engine {
	private static Engine instance = null;

	public static Engine getInstance() {
		return instance;
	}

	private ClientHandler clientHandler = new ClientHandler();

	public ClientHandler getClientHandler() {
		return clientHandler;
	}

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public Engine init() {
		new World();
		instance = this;
		return instance;
	}

	public void run() {
		scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				loop();
			}
		}, 100, 100, TimeUnit.MILLISECONDS);
	}

	public void loop() {
		clientHandler.updatePlayers();
	}

}
