package org.ncs.world;

import org.ncs.world.client.Client;

public class Message {
	private Client client;

	public Client getClient() {
		return client;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	
	private int type;

	public int getType() {
		return type;
	}

	public Message(Client client, String message, int type) {
		this.client = client;
		this.message = message;
		this.type = type;
	}
}
