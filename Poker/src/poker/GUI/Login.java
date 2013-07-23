package poker.GUI;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import client.Client;

public class Login extends JFrame implements ActionListener {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public Client client;

    public Login(){

        LoginWindow.setLayout(new FlowLayout());
        LoginWindow.setSize(240, 120);
        LoginWindow.setLocation(screenSize.width / 2 - LoginWindow.getSize().width / 2, screenSize.height / 2 - LoginWindow.getSize().height / 2);
        LoginWindow.setResizable(false);
        LoginWindow.setVisible(true);
        LoginWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        LoginWindow.setTitle("Login");
        LoginWindow.getContentPane().setLayout(null);

        LoginWindow.add(labelName(), null);
        LoginWindow.add(textName(), null);
        LoginWindow.add(buttonConnect(), null);
        LoginWindow.add(warning(), null);
    }


    // LoginWindow variables
    private JFrame LoginWindow = new JFrame();
    private String PlayerName;
    private JLabel labelName = new JLabel();
    private JLabel warning = new JLabel();
    private JTextField textName = new JTextField();
    public JButton buttonConnect = new JButton();

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
        textName.addKeyListener(new KeyAdapter() {public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                buttonConnect.doClick();
            }
        }
        }
        );
        return textName;
    }
    private JButton buttonConnect(){
        buttonConnect = new JButton();
        buttonConnect.setActionCommand("connect");
        buttonConnect.setBounds(70, 50, 100, 25);
        buttonConnect.setText("Connect");
        buttonConnect.addActionListener(this);
        return buttonConnect;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if("connect".equals(e.getActionCommand())){
            if(textName.getText().length() >= 3 && textName.getText().length() <= 15){
                // SERVER CONNECTION IMPLEMENTATION

                PlayerName = textName.getText();
                client.start(PlayerName);
                LoginWindow.dispose();

            } else {
                LoginWindow.setSize(240, 150);
                warning.setBounds(5, 85, 220, 30);
                warning.setVisible(true);
            }
        }
    }

}
