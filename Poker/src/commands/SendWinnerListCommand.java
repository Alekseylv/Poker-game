package commands;

import java.io.Serializable;
import java.util.List;

import client.ClientController;
import client.ClientGame;
import client.ClientModel;
import client.State;
import poker.arturka.Hand;

/**
 * Sends information about winners and the amount of cash the have won
 * @author Aleksey
 *
 */
@SuppressWarnings("serial")
public class SendWinnerListCommand implements Command {

    /**
     *
     */
    private static final long serialVersionUID = 5577005935334322458L;
    private final List<Tuple> winnerList;
    /**
     * Creates Command
     * @param list - info about winners (id's and cash)
     */
    public SendWinnerListCommand(List<Tuple> list) {
        this.winnerList = list;
    }
    /**
     * Used to represent relevant winner information
     * @author Aleksey
     *
     */

    public static class Tuple implements Serializable{
        /**
         *
         */
        private static final long serialVersionUID = -7765856579326354766L;
        public final int id;
        public final int cash;
        public final Hand hand;

        public Tuple(int id, int cash,Hand hand) {
            this.id = id;
            this.cash = cash;
            this.hand=hand;
        }
    }


    public void execute(ClientGame game) {
        game.controller.sendViewWinners(winnerList);
    }
}
