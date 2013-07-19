package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import commands.SendWinnerListCommand;
import commands.SendWinnerListCommand.Tuple;

import poker.GUI.ClientView;
import poker.arturka.Card;

/**
 * Monitors changes in the {@link ClientModel} and toggles associated
 * changes in {@link ClientView}
 * @author Aleksey
 *
 */

public class ClientController implements Observer {

    /**Associated ClientModel and ClientView to send and receive messages */
    private ClientModel model;
    private ClientView view;

    /**
     * Constructs a Controller
     *
     * @param model
     * The associated ClientModel
     * @param view
     * The associated ClientView
     */
    public ClientController(ClientModel model, ClientView view) {
        this.model = model;
        this.view = view;
    }


    /**
     * Sends the view information about cash won by players
     *
     * @param list
     * The list of player id's and cash they have won
     */


    public void sendViewWinners(List<Tuple> list) {
        view.getWinners((ArrayList<Tuple>) list);
    }

    /**
     * ClientModel change handler
     *
     * @param model
     * 	The model that changed
     * @param arg
     * 	The argument that changed
     */

    public void update(ClientModel model, Object arg) {
        if(arg instanceof Card[] ) {
            view.tableCards(model.getFieldCards());

        } else if(arg instanceof State) {
            if(model.getState() == State.READY){
                view.stateReady();
            }
            if(model.getState() == State.INPUTCHECK){
                view.stateInputCheck();
            }
            if(model.getState() == State.INPUTCALL){
                view.stateInputCall();
            }
            if(model.getState() == State.PLAYING){
                view.statePlaying();
            }
            if(model.getState() == State.ENDED){
                view.stateEnded();
            }
        } else if(arg instanceof Integer) {
            // we just got an id
        } else if(arg instanceof List) {
            // we just got player list
        }
			
			
	/*	private Card fieldcards[];
		private State state;
		private List<ClientSidePlayer> players;	
		private int id;
	*/
    }
    /**
     * Player change handler
     *
     * @param player
     * 	The changed Player
     * @param arg
     * 	The argument that changed
     */

    public void update(ClientSidePlayer player, Object arg) {
        // rewrite concrete player on screen
    }


    /**
     * Dispatches changes to appropriate handlers
     */
    public void update(Observable obj, Object arg) {
        if(obj instanceof ClientSidePlayer) {
            this.update((ClientSidePlayer) obj, arg);
        } else if(obj instanceof ClientModel) {
            this.update((ClientModel) obj, arg);
        } else {
            System.out.println("Not a valid object" + obj);
        }
    }
}