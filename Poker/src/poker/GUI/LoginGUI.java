package poker.GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class LoginGUI extends JFrame implements ActionListener{

    static String PlayerName;
    static TableGUI tableWindow = new TableGUI();
    JLabel labelName = new javax.swing.JLabel();
    JTextField textName = new javax.swing.JTextField();
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginGUI(){
        super();
        this.setTitle("Login");
        this.getContentPane().setLayout(null);
        this.setSize(240, 100);
        this.add(labelName(), null);

        this.add(textName(), null);
        this.add(buttonConnect(), null);

    }

    private JLabel labelName(){
        labelName.setBounds(10, 15, 90, 25);
        labelName.setText("Your name: ");
        return labelName;
    }

    private JTextField textName(){
        textName.setBounds(100, 15, 120, 25);
        return textName;
    }

    private JButton buttonConnect(){
        JButton buttonConnect = new javax.swing.JButton();

        buttonConnect.setBounds(70, 50, 100, 25);
        buttonConnect.setText("Connect");
        buttonConnect.addActionListener(this);
        return buttonConnect;
    }

    @Override
    public void actionPerformed(ActionEvent buttonConnect) {
        PlayerName = textName.getText();
        this.setVisible(false);
        showTable();
    }

    public static TableGUI showTable(){

        tableWindow.setLocation(screenSize.width / 2 - tableWindow.getSize().width / 2, screenSize.height / 2 - tableWindow.getSize().height / 2);
        tableWindow.setResizable(false);
        tableWindow.setVisible(true);
        tableWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tableWindow.displayNick().setText(PlayerName);
        return tableWindow;
    }


}