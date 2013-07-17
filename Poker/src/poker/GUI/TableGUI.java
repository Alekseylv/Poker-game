package poker.GUI;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class TableGUI extends JFrame implements ChangeListener, ActionListener{

    LoginGUI loginWindow = new LoginGUI();
    JLabel displayNick = new JLabel();
    JLabel displayCash = new JLabel();
    JButton foldButton = new JButton();
    JButton raiseButton = new JButton();
    JButton checkButton = new JButton();
    JSlider CashSlider = new JSlider();
    JLabel displayCashSlider = new JLabel();

    int Cash = 300;
    int CashCurrent = 30;

    public TableGUI(){
        super();

        this.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/poker/GUI/img/pokerTable.jpg"))));
        this.setTitle("Poker Client");
        this.getContentPane().setLayout(null);
        this.setSize(900, 600);
        this.add(displayNick(), null);
        this.add(displayCash(), null);
        this.add(foldButton(), null);
        this.add(checkButton(), null);
        this.add(raiseButton(), null);
        this.add(CashSlider(), null);
        this.add(displayCashSlider(), null);
        this.add(userCard1(), null);
        this.add(userCard2(), null);
        this.add(backCard(), null);
    }

    public JLabel displayNick(){
        displayNick.setBounds(400, 500, 100, 20);
        displayNick.setForeground(Color.WHITE);
        return displayNick;
    }
    public JLabel displayCash(){
        displayCash.setBounds(400, 520, 100, 25);
        displayCash.setForeground(Color.WHITE);
        displayCash.setText("$" + String.valueOf(Cash));
        return displayCash;
    }

    public JButton foldButton(){
        foldButton.setBounds(15, 555, 85, 30);
        foldButton.setText("FOLD");
        return foldButton;
    }
    public JButton checkButton(){
        checkButton.setBounds(105, 555, 85, 30);
        checkButton.setText("CHECK");
        return checkButton;
    }
    public JButton raiseButton(){
        raiseButton.setBounds(195, 555, 85, 30);
        raiseButton.setText("RAISE");
        raiseButton.addActionListener(this);
        return raiseButton;
    }

    public JSlider CashSlider(){
        CashSlider.setBounds(685, 540, 200, 50);
        CashSlider.setMaximum(Cash);
        CashSlider.setMinimum(0);
        CashSlider.setValue(CashCurrent);
        CashSlider.addChangeListener(this);
        CashSlider.setMajorTickSpacing(100);
        CashSlider.setMinorTickSpacing(10);
        CashSlider.setPaintTicks(true);
        CashSlider.setPaintLabels(true);
        CashSlider.setSnapToTicks(true);
        return CashSlider;
    }
    public JLabel displayCashSlider(){
        displayCashSlider.setBounds(850, 520, 35, 25);
        displayCashSlider.setForeground(Color.WHITE);
        displayCashSlider.setText("$" + CashSlider.getValue());
        return displayCashSlider;
    }

    public JLabel userCard1(){
        ImageIcon cardImg1 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/3_of_clubs.png"));
        JLabel userCard1 = new JLabel(cardImg1);
        userCard1.setBounds(400, 400, 70, 100);
        return userCard1;
    }
    public JLabel userCard2(){
        ImageIcon cardImg2 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/king_of_hearts.png"));
        JLabel userCard2 = new JLabel(cardImg2);
        userCard2.setBounds(380,400,70,100);
        return userCard2;
    }
    public JLabel backCard(){
        ImageIcon back = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/back.png"));
        JLabel backCard = new JLabel(back);
        backCard.setBounds(480,400,49,70);
        return backCard;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider CashSlider = (JSlider) e.getSource();
        CashCurrent = (int)(Math.round(CashSlider.getValue() / 10.0) * 10);

        displayCashSlider.setText(String.valueOf("$" + CashCurrent));
    }
    public void actionPerformed(ActionEvent raiseButton) {
        Cash-= CashCurrent;
        displayCash.setText("$" + String.valueOf(Cash));
    }
}