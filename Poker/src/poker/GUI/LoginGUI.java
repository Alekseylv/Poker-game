package poker.GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class LoginGUI extends JFrame implements ActionListener{

    static String PlayerName;
    static TableGUI tableWindow = new TableGUI();
    JLabel labelName = new JLabel();
    JLabel warning = new JLabel();
    JTextField textName = new JTextField();
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginGUI(){
        super();
        this.setTitle("Login");
        this.getContentPane().setLayout(null);
        this.setSize(240, 100);
        this.add(labelName(), null);

        this.add(textName(), null);
        this.add(buttonConnect(), null);
        this.add(warning(), null);
    }

    private JLabel labelName(){
        labelName.setBounds(10, 15, 90, 25);
        labelName.setText("Your name: ");
        return labelName;
    }

    private JLabel warning(){
        warning.setHorizontalAlignment( SwingConstants.CENTER );
        warning.setText("<html><body align='center'>Name must be between 3 and 15 characters long!</body></html>");
        warning.setVisible(false);
        return warning;
    }

    private JTextField textName(){
        textName.setBounds(100, 15, 120, 25);
        return textName;
    }
    private JButton buttonConnect(){
        JButton buttonConnect = new JButton();

        buttonConnect.setBounds(70, 50, 100, 25);
        buttonConnect.setText("Connect");
        buttonConnect.addActionListener(this);
        return buttonConnect;
    }

    public void actionPerformed(ActionEvent buttonConnect) {
        if(textName.getText().length() >= 3 && textName.getText().length() <= 15){
            PlayerName = textName.getText();
            showTable();
            this.dispose();
        } else {
            this.setSize(240, 150);
            warning.setBounds(5, 85, 220, 30);
            warning.setVisible(true);
        }
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