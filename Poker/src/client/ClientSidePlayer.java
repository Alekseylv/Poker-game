package client;

import message.data.ClientTurn;
import poker.arturka.Card;
import poker.arturka.Player;



public class ClientSidePlayer extends Player {

	private ClientTurn lastTurn;
	
	public ClientSidePlayer(Player player) {
		super(player.getId());
		this.lastTurn = null;
	}
	
	private void notifyObs(Object o) {
		setChanged();
        notifyObservers(o);
	}
	
	public void setLastTurn(ClientTurn turn) {
		this.lastTurn = turn;
		
		notifyObs(turn);
	}
	
	public ClientTurn getLastTurn() {
		return this.lastTurn;
	}
	
	public void giveCash(int cash){
        super.giveCash(cash);
        
        notifyObs(cash);
    }
	
	public void giveCards(Card card1, Card card2){
       super.giveCards(card1, card2);
       
       notifyObs(card1);
    }
	
	public void setCash(int cash) {
        super.setCash(cash);
        
        notifyObs(cash);
    }
	
	public void toggleDealer() {
        super.toggleDealer();
        
        notifyObs(true);
    }

	public void Fold() {
		super.Fold();
	    notifyObs(true);
	}
	
	public void unFold() {
		super.unFold();
	    notifyObs(true);
	}

    public void setBet(int bet){
        super.setBet(bet);
        
        notifyObs(bet);
    }

	public void setBetTurnCash(int currentBet, ClientTurn turn, int moneyLeft) {
		super.setBet(currentBet);
		super.setCash(moneyLeft);
		this.setLastTurn(turn);	
	}
	
}
