package tests;

import java.util.Observable;

import poker.GUI.ClientView;
import client.ClientController;
import client.ClientModel;
import client.ClientSidePlayer;

public class EmptyController extends ClientController {

	public EmptyController(ClientModel model, ClientView view) {
		super(model, view);
		// TODO Auto-generated constructor stub
	}
	
		/**
		 * Dispatches changes to appropriate handlers
		 */
		public void update(Observable obj, Object arg) {
			
		}
	

}
