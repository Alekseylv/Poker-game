package message.data;

public class PlayerMove {
	
	public final int id;
	public final ClientTurn turn;
	public final int currentBet;
	public final int moneyLeft;
	
	public PlayerMove(int id, ClientTurn turn, int currentBet, int moneyLeft) {
		this.id = id;
		this.turn = turn;
		this.currentBet = currentBet;
		this.moneyLeft = moneyLeft;
	}
}
