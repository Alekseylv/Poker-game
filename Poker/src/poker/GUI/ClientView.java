package poker.GUI;

import javax.swing.JFrame;

import client.ClientModel;

/**
 * Created with IntelliJ IDEA.
 * User: student
 * Date: 7/16/13
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientView extends JFrame {
	private ClientModel model;
	
	public ClientView(ClientModel model) {
		this.model = model;
	}
}
