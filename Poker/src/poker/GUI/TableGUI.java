package poker.GUI;

import javax.swing.*;

@SuppressWarnings("serial")
public class TableGUI extends JFrame{

    LoginGUI loginWindow = new LoginGUI();
    JLabel displayNick = new javax.swing.JLabel();
    JToolBar menu = new javax.swing.JToolBar();

    public TableGUI(){
        super();
        this.setTitle("Login");
        this.getContentPane().setLayout(null);
        this.setSize(900, 600);
        this.add(displayNick(), null);
    }

    public JLabel displayNick(){
        displayNick.setBounds(100, 15, 190, 25);
        return displayNick;
    }

    public JToolBar menu(){


        return menu;
    }

}
