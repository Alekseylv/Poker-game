package poker.GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import client.ClientModel;
import poker.arturka.Card;

@SuppressWarnings("serial")
public class ClientView extends JFrame implements ChangeListener, ActionListener{
    private ClientModel model;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Methods' variables START

    String userCardOne;
    String userCardTwo;

    // Methods' variables END

    // LoginWindow variables
    JFrame LoginWindow = new JFrame();
    String PlayerName;
    JLabel labelName = new JLabel();
    JLabel warning = new JLabel();
    JTextField textName = new JTextField();
    // LoginWindow variables end

    // TableWindow variables
    JFrame TableWindow = new JFrame();
    JLabel displayNick = new JLabel();
    JLabel displayCash = new JLabel();
    JButton foldButton = new JButton();
    JButton raiseButton = new JButton();
    JButton checkButton = new JButton();
    JButton callButton = new JButton();
    JButton potSizeSlider = new JButton();
    JButton TwoxSizeSlider = new JButton();
    JButton ThreexSizeSlider = new JButton();
    JSlider CashSlider = new JSlider();
    JLabel displayCashSlider = new JLabel();
    JLabel[][] arrayPlayersCards = new JLabel[8][2];
    JLabel[] arrayPlayersNickCash = new JLabel[8];
    JLabel[] showFlop = new JLabel[3];

    int Cash = 1000;
    int CashCurrent = 30;
    // TableWindow variables end

    public ClientView(ClientModel model) {

        this.model = model;

        // LoginWindow appearance
        LoginWindow.setLayout(new FlowLayout());
        LoginWindow.setSize(240, 100);
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


        // TableWindow appearance
        TableWindow.setSize(900, 630);
        TableWindow.setLocation(((screenSize.width / 2) - (TableWindow.getSize().width / 2)), screenSize.height / 2 - TableWindow.getSize().height / 2);
        TableWindow.setResizable(false);
        TableWindow.setVisible(false);
        TableWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        TableWindow.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/poker/GUI/img/pokerTableNew.jpg"))));
        TableWindow.setTitle("Poker Client");

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

        showFlop[0] = showFlop("queen_of_hearts", 265, 180);
        showFlop[1] = showFlop("ace_of_clubs", 340, 180);
        showFlop[2] = showFlop("jack_of_hearts", 415, 180);

        TableWindow.add(displayNick(), null);
        TableWindow.add(displayCash(), null);
        TableWindow.add(foldButton(), null);
        TableWindow.add(checkButton(), null);
        TableWindow.add(callButton(), null);
        TableWindow.add(raiseButton(), null);
        TableWindow.add(CashSlider(), null);
        TableWindow.add(displayCashSlider(), null);
        TableWindow.add(potSizeSlider(), null);
        TableWindow.add(TwoxSizeSlider(), null);
        TableWindow.add(ThreexSizeSlider(), null);
        TableWindow.add(userCard1(), null);
        TableWindow.add(userCard2(), null);

        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 2; k++){
                TableWindow.add(arrayPlayersCards[i][k], null);
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
            TableWindow.add(arrayPlayersNickCash[i], null);
        }

        for(int i = 0; i < 3; i++){
            TableWindow.add(showFlop[i], null);
        }

        TableWindow.add(showTurn("3_of_diamonds"), null);
        TableWindow.add(showRiver("7_of_clubs"), null);

        TableWindow.add(Dealer(450, 330), null);
//        this.add(arrayPlayersNickCash[0], null);
//        this.add(arrayPlayersNickCash[1], null);
//        this.add(arrayPlayersNickCash[2], null);
//        this.add(arrayPlayersNickCash[3], null);
//        this.add(arrayPlayersNickCash[4], null);
//        this.add(arrayPlayersNickCash[5], null);
//        this.add(arrayPlayersNickCash[6], null);
//        this.add(arrayPlayersNickCash[7], null);
    }

    // Methods for CONTROLLER

    public void stateReady(){
         displayNick.setEnabled(true);
    }    // to write
    public void stateInputCheck(){
        checkButton.setVisible(true);
        callButton.setVisible(false);
        foldButton.setEnabled(true);
        checkButton.setEnabled(true);
        callButton.setEnabled(true);
        raiseButton.setEnabled(true);
        CashSlider.setEnabled(true);
        potSizeSlider.setEnabled(true);
        TwoxSizeSlider.setEnabled(true);
        ThreexSizeSlider.setEnabled(true);
    }
    public void stateInputCall(){
        callButton.setVisible(true);
        checkButton.setVisible(false);
        foldButton.setEnabled(true);
        checkButton.setEnabled(true);
        callButton.setEnabled(true);
        raiseButton.setEnabled(true);
        CashSlider.setEnabled(true);
        potSizeSlider.setEnabled(true);
        TwoxSizeSlider.setEnabled(true);
        ThreexSizeSlider.setEnabled(true);
    }
    public void statePlaying(){
        foldButton.setEnabled(false);
        checkButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        CashSlider.setEnabled(false);
        potSizeSlider.setEnabled(false);
        TwoxSizeSlider.setEnabled(false);
        ThreexSizeSlider.setEnabled(false);       
    }
    public void stateEnded(){

    }  // label + id + cash + [combination]

//    TableWindow.add(displayNick(), null);
//    TableWindow.add(displayCash(), null);
//    TableWindow.add(foldButton(), null);
//    TableWindow.add(checkButton(), null);
//    TableWindow.add(callButton(), null);
//    TableWindow.add(raiseButton(), null);
//    TableWindow.add(CashSlider(), null);
//    TableWindow.add(displayCashSlider(), null);
//    TableWindow.add(potSizeSlider(), null);
//    TableWindow.add(TwoxSizeSlider(), null);
//    TableWindow.add(ThreexSizeSlider(), null);
//    TableWindow.add(userCard1(), null);
//    TableWindow.add(userCard2(), null);
//
//    }
    public ArrayList<String> fromCardToString(Card[] cards ){
        ArrayList<String> output=new ArrayList<String>();
        String fileName="";
        for(Card card:cards){
            switch (card.getRank()){
                case TWO:
                    fileName+="2_of_";
                    break;
                case THREE:
                    fileName+="3_of_";
                    break;
                case FOUR:
                    fileName+="4_of_";
                    break;
                case FIVE:
                    fileName+="5_of_";
                    break;
                case SIX:
                    fileName+="6_of_";
                    break;
                case SEVEN:
                    fileName+="7_of_";
                    break;
                case EIGHT:
                    fileName+="8_of_";
                    break;
                case NINE:
                    fileName+="9_of_";
                    break;
                case TEN:
                    fileName+="10_of_";
                    break;
                case JACK:
                    fileName+="jack_of_";
                    break;
                case QUEEN:
                    fileName+="queen_of_";
                    break;
                case KING:
                    fileName+="king_of_";
                    break;
                case ACE:
                    fileName+="ace_of_";
                    break;
            }
            switch (card.getSuit()){
                case DIAMONDS:
                    fileName+="diamonds.png";
                    break;
                case HEARTS:
                    fileName+="hearts.png";
                    break;
                case CLUBS:
                    fileName+="clubs.png";
                    break;
                case SPADES:
                    fileName+="spades.png";
                    break;
            }
            output.add(fileName);
        }
        return output;
    }

    // Methods for CONTROLLER ENDs


    // TableWindow variables description STARTs

    public JLabel displayNick(){
        displayNick.setBounds(403, 515, 100, 20);
        displayNick.setForeground(Color.WHITE);
        displayNick.setHorizontalAlignment( SwingConstants.CENTER );
        displayNick.setText("aaa");
        return displayNick;
    }
    public JLabel displayCash(){
        displayCash.setBounds(428, 530, 50, 25);
        displayCash.setForeground(Color.WHITE);
        displayCash.setHorizontalAlignment( SwingConstants.CENTER );
        displayCash.setText("(" + String.valueOf(Cash) + ")");
        return displayCash;
    }

    public JButton foldButton(){
        foldButton.setActionCommand("fold");
        foldButton.setBounds(15, 562, 85, 30);
        foldButton.setText("FOLD");
        foldButton.setForeground(Color.WHITE);
        foldButton.setOpaque(false);
        foldButton.setContentAreaFilled(false);
        return foldButton;
    }
    public JButton checkButton(){
        checkButton.setActionCommand("check");
        checkButton.setBounds(105, 562, 85, 30);
        checkButton.setText("CHECK");
        checkButton.setForeground(Color.WHITE);
        checkButton.setOpaque(false);
        checkButton.setContentAreaFilled(false);
        checkButton.addActionListener(this);
        return checkButton;
    }
    public JButton callButton(){
        callButton.setActionCommand("call");
        callButton.setBounds(105, 562, 85, 30);
        callButton.setText("CALL");
        callButton.setForeground(Color.WHITE);
        callButton.setOpaque(false);
        callButton.setContentAreaFilled(false);
        callButton.addActionListener(this);
        return callButton;
    }
    public JButton raiseButton(){
        raiseButton.setActionCommand("raise");
        raiseButton.setBounds(195, 562, 85, 30);
        raiseButton.setText("RAISE");
        raiseButton.setForeground(Color.WHITE);
        raiseButton.setEnabled(true);
        raiseButton.setOpaque(false);
        raiseButton.setContentAreaFilled(false);
        raiseButton.addActionListener(this);
        return raiseButton;
    }

    public JSlider CashSlider(){
        int tick = (Cash - 30) / 2;

        CashSlider.setBounds(675, 550, 210, 50);
        CashSlider.setMaximum(Cash);
        CashSlider.setMinimum(30);
        CashSlider.setValue(30);
        CashSlider.addChangeListener(this);
        CashSlider.setMajorTickSpacing(tick);
        CashSlider.setMinorTickSpacing((int)(Math.round((Cash / 20)/ 10.0) * 10)); // FORMULA
        CashSlider.setPaintTicks(true);
        CashSlider.setPaintLabels(true);
        CashSlider.setBackground(Color.GRAY);
        CashSlider.setForeground(Color.WHITE);
        CashSlider.setSnapToTicks(false);
        CashSlider.setOpaque(false);


        return CashSlider;
    }
    public JLabel displayCashSlider(){

        displayCashSlider.setBounds(820, 525, 50, 25);
        displayCashSlider.setForeground(Color.WHITE);
        displayCashSlider.setHorizontalAlignment( SwingConstants.RIGHT );

        displayCashSlider.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        displayCashSlider.setText("" + CashSlider.getValue());
        return displayCashSlider;
    }
    public JButton potSizeSlider(){
        potSizeSlider.setActionCommand("pot");
        potSizeSlider.setBounds(705, 525, 40, 25);
        potSizeSlider.setText("POT");
        potSizeSlider.setForeground(Color.WHITE);
        potSizeSlider.setOpaque(false);
        potSizeSlider.setContentAreaFilled(false);
        potSizeSlider.setMargin(new Insets(0,0,0,0));
        return potSizeSlider;
    }
    public JButton TwoxSizeSlider(){
        TwoxSizeSlider.setActionCommand("2X");
        TwoxSizeSlider.setBounds(750, 525, 25, 25);
        TwoxSizeSlider.setText("2X");
        TwoxSizeSlider.setForeground(Color.WHITE);
        TwoxSizeSlider.setOpaque(false);
        TwoxSizeSlider.setContentAreaFilled(false);
        TwoxSizeSlider.setMargin(new Insets(0,0,0,0));
        return TwoxSizeSlider;
    }
    public JButton ThreexSizeSlider(){
        ThreexSizeSlider.setActionCommand("3X");
        ThreexSizeSlider.setBounds(780, 525, 25, 25);
        ThreexSizeSlider.setText("3X");
        ThreexSizeSlider.setForeground(Color.WHITE);
        ThreexSizeSlider.setOpaque(false);
        ThreexSizeSlider.setContentAreaFilled(false);
        ThreexSizeSlider.setMargin(new Insets(0,0,0,0));
        return ThreexSizeSlider;
    }

    public JLabel userCard1(){
        ImageIcon cardImg1 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/3_of_spades.png"));
        JLabel userCard1 = new JLabel(cardImg1);
        userCard1.setBounds(425,415,70,100);
        return userCard1;
    }
    public JLabel userCard2(){
        ImageIcon cardImg2 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/6_of_clubs.png"));
        JLabel userCard2 = new JLabel(cardImg2);
        userCard2.setBounds(411,408,70,100);
        return userCard2;
    }
    public JLabel backCard(int x, int y){

        ImageIcon back = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/back.png"));
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
    public JLabel Dealer(int x, int y){
        ImageIcon back = new ImageIcon(getClass().getResource("/poker/GUI/img/Dealer.png"));
        JLabel backCard = new JLabel(back);
        backCard.setBounds(x,y,25,20);
        return backCard;
    }

    public JLabel showFlop(String card, int x, int y){
        ImageIcon cardImg1 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/" + card + ".png"));
        JLabel flopCard = new JLabel(cardImg1);
        flopCard.setBounds(x,y,70,100);
        return flopCard;
    }
    public JLabel showTurn(String card){
        ImageIcon cardImg1 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/" + card + ".png"));
        JLabel turnCard = new JLabel(cardImg1);
        turnCard.setBounds(490, 180, 70, 100);
        return turnCard;
    }
    public JLabel showRiver(String card){
        ImageIcon cardImg1 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/" + card + ".png"));
        JLabel riverCard = new JLabel(cardImg1);
        riverCard.setBounds(565, 180, 70, 100);
        return riverCard;
    }
    // TableWindow variables description ENDs

    // LoginWindow variables description STARTs

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
        buttonConnect.setActionCommand("connect");
        buttonConnect.setBounds(70, 50, 100, 25);
        buttonConnect.setText("Connect");
        buttonConnect.addActionListener(this);
        return buttonConnect;
    }
    // LoginWindow variables description ENDs

    public void actionPerformed(ActionEvent e) {
        if("raise".equals(e.getActionCommand())){
            Cash-= CashCurrent;
            displayCash.setText("$" + String.valueOf(Cash));

        } else if("check".equals(e.getActionCommand())){
            // check

        } else if("connect".equals(e.getActionCommand())){
            if(textName.getText().length() >= 3 && textName.getText().length() <= 15){
                PlayerName = textName.getText();
                displayNick.setText(PlayerName);
                TableWindow.setVisible(true);
                LoginWindow.dispose();
            } else {
                LoginWindow.setSize(240, 150);
                warning.setBounds(5, 85, 220, 30);
                warning.setVisible(true);
            }
        }

    }
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider CashSlider = (JSlider) e.getSource();
        CashCurrent = (int)(Math.round(CashSlider.getValue() / 10.0) * 10); // FORMULA
        if(CashCurrent > Cash){
            CashCurrent = Cash;
        }
        displayCashSlider.setText(String.valueOf("" + CashCurrent));

    }


}
