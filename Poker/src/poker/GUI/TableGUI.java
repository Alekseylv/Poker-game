package poker.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
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
    FlowLayout layout = new FlowLayout();
    JLabel[][] arrayPlayersCards = new JLabel[8][2];
    JLabel[] arrayPlayersNickCash = new JLabel[8];

    int Cash = 950;
    int CashCurrent = 30;

    public TableGUI(){
        super();
        arrayPlayersCards[0][0] = backCard(260,435);
        arrayPlayersCards[0][1] = backCard(250,430);
        arrayPlayersCards[1][0] = backCard(60,345);
        arrayPlayersCards[1][1] = backCard(50,340);
        arrayPlayersCards[2][0] = backCard(60,155);
        arrayPlayersCards[2][1] = backCard(50,150);
        arrayPlayersCards[3][0] = backCard(280,65);
        arrayPlayersCards[3][1] = backCard(270,60);
        arrayPlayersCards[4][0] = backCard(570,65);
        arrayPlayersCards[4][1] = backCard(560,60);
        arrayPlayersCards[5][0] = backCard(790,155);
        arrayPlayersCards[5][1] = backCard(780,150);
        arrayPlayersCards[6][0] = backCard(790,345);
        arrayPlayersCards[6][1] = backCard(780,340);
        arrayPlayersCards[7][0] = backCard(600,435);
        arrayPlayersCards[7][1] = backCard(590,430);

        arrayPlayersNickCash[0] = clientNameCash("Player0", 300, 230, 510);
        arrayPlayersNickCash[1] = clientNameCash("Player1", 300, 30, 420);
        arrayPlayersNickCash[2] = clientNameCash("Player2", 300, 30, 110);
        arrayPlayersNickCash[3] = clientNameCash("Player3", 300, 250, 20);
        arrayPlayersNickCash[4] = clientNameCash("Player4", 300, 540, 20);
        arrayPlayersNickCash[5] = clientNameCash("Player5", 300, 760, 110);
        arrayPlayersNickCash[6] = clientNameCash("Player6", 300, 760, 420);
        arrayPlayersNickCash[7] = clientNameCash("Player7", 300, 570, 510);

        this.setLayout(layout);
        this.setContentPane(new JLabel(new ImageIcon(getClass().getResource("poker/GUI/img/pokerTable.jpg"))));
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

        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 2; k++){
                this.add(arrayPlayersCards[i][k], null);
            }
        }

//        this.add(arrayPlayersCards[0][0], null);
//        this.add(arrayPlayersCards[0][1], null);
//        this.add(arrayPlayersCards[1][0], null);
//        this.add(arrayPlayersCards[1][1], null);
//        this.add(arrayPlayersCards[2][0], null);
//        this.add(arrayPlayersCards[2][1], null);
//        this.add(arrayPlayersCards[3][0], null);
//        this.add(arrayPlayersCards[3][1], null);
//        this.add(arrayPlayersCards[4][0], null);
//        this.add(arrayPlayersCards[4][1], null);
//        this.add(arrayPlayersCards[5][0], null);
//        this.add(arrayPlayersCards[5][1], null);
//        this.add(arrayPlayersCards[6][0], null);
//        this.add(arrayPlayersCards[6][1], null);
//        this.add(arrayPlayersCards[7][0], null);
//        this.add(arrayPlayersCards[7][1], null);

        for(int i = 0; i < 8; i++){
            this.add(arrayPlayersNickCash[i], null);
        }

//        this.add(arrayPlayersNickCash[0], null);
//        this.add(arrayPlayersNickCash[1], null);
//        this.add(arrayPlayersNickCash[2], null);
//        this.add(arrayPlayersNickCash[3], null);
//        this.add(arrayPlayersNickCash[4], null);
//        this.add(arrayPlayersNickCash[5], null);
//        this.add(arrayPlayersNickCash[6], null);
//        this.add(arrayPlayersNickCash[7], null);
    }

    public JLabel displayNick(){
        displayNick.setBounds(400, 515, 100, 20);
        displayNick.setForeground(Color.WHITE);
        displayNick.setHorizontalAlignment( SwingConstants.CENTER );
        return displayNick;
    }
    public JLabel displayCash(){
        displayCash.setBounds(425, 530, 50, 25);
        displayCash.setForeground(Color.WHITE);
        displayCash.setHorizontalAlignment( SwingConstants.CENTER );
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
        raiseButton.setEnabled(false);
        raiseButton.addActionListener(this);
        return raiseButton;
    }

    public JSlider CashSlider(){
        int tick =  (int)(Math.round(((Cash - 30)/3)/ 10.0) * 10);


        CashSlider.setBounds(675, 540, 210, 50);
        CashSlider.setMaximum(Cash);
        CashSlider.setMinimum(30);
        CashSlider.setValue(CashCurrent);
        CashSlider.addChangeListener(this);
        CashSlider.setMajorTickSpacing(tick);
        CashSlider.setMinorTickSpacing((int)(Math.round((Cash / 30)/ 10.0) * 10));
        CashSlider.setPaintTicks(true);
        CashSlider.setPaintLabels(true);
        CashSlider.setSnapToTicks(true);
        return CashSlider;
    }
    public JLabel displayCashSlider(){
        displayCashSlider.setBounds(840, 520, 45, 25);
        displayCashSlider.setHorizontalAlignment( SwingConstants.RIGHT );
        displayCashSlider.setForeground(Color.WHITE);
        displayCashSlider.setText("$" + CashSlider.getValue());
        return displayCashSlider;
    }

    public JLabel userCard1(){
        ImageIcon cardImg1 = new ImageIcon(getClass().getResource("poker/GUI/img/cards/3_of_clubs.png"));
        JLabel userCard1 = new JLabel(cardImg1);
        userCard1.setBounds(425,415,70,100);
        return userCard1;
    }
    public JLabel userCard2(){
        ImageIcon cardImg2 = new ImageIcon(getClass().getResource("poker/GUI/img/cards/king_of_hearts.png"));
        JLabel userCard2 = new JLabel(cardImg2);
        userCard2.setBounds(411,408,70,100);
        return userCard2;
    }

    public JLabel backCard(int x, int y){

        ImageIcon back = new ImageIcon(getClass().getResource("poker/GUI/img/cards/back.png"));
        JLabel backCard = new JLabel(back);
        backCard.setBounds(x,y,50,70);

        return backCard;
    }

    public JLabel clientNameCash(String ClientName, int ClientCash, int x, int y){
        JLabel clientNameCash = new JLabel();
        clientNameCash.setBounds(x,y,100,30);
        clientNameCash.setForeground(Color.WHITE);
        clientNameCash.setHorizontalAlignment( SwingConstants.CENTER );
        clientNameCash.setText("<html><body align='center'>" + ClientName + "<br />(" + ClientCash +")</body></html>");
        return clientNameCash;
    }

    public void showFlop(){

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