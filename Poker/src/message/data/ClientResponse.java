package message.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientResponse implements Serializable {

	public final ClientTurn turn;
	private int optional;
	
	public ClientResponse(ClientTurn turn, int optional) {
		this.turn = turn;
		this.optional = optional;
	}
	
	public int getBet() {
		return optional;
	}
}
