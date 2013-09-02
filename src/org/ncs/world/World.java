package org.ncs.world;

import java.util.ArrayList;
import java.util.List;

public class World {
	private static World world = null;

	public static World getWorld() {
		return world;
	}

	private List<Message> messages = new ArrayList<Message>();

	public List<Message> getMessages() {
		return messages;
	}
	
	public World() {
		world = this;
	}
}
