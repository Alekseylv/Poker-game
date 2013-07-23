package poker.GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import client.ClientModel;
import client.ClientSidePlayer;
import commands.SendWinnerListCommand;
import message.data.Card;


@SuppressWarnings("serial")
public class ClientView extends JFrame implements ChangeListener, ActionListener{

    private ClientModel model;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    // LoginWindow variables
    private JFrame LoginWindow = new JFrame();
    private String PlayerName;
    private JLabel labelName = new JLabel();
    private JLabel warning = new JLabel();
    private JTextField textName = new JTextField();
    private JButton buttonConnect = new JButton();
    // LoginWindow variables end

    // TableWindow variables
    private JFrame TableWindow = new JFrame();
    private JLabel displayNick = new JLabel();
    private JLabel displayCash = new JLabel();
    private JButton foldButton = new JButton();
    private JButton raiseButton = new JButton();
    private JButton checkButton = new JButton();
    private JButton callButton = new JButton();
    private JButton potSizeSlider = new JButton();
    private JButton OneThirdSizeSlider = new JButton();
    private JButton ThreexSizeSlider = new JButton();
    private JButton AllInSizeSlider = new JButton();
    private JButton PlusSizeSlider = new JButton();
    private JButton MinusSizeSlider = new JButton();
    private JSlider CashSlider = new JSlider();
    private JLabel displayCashSlider = new JLabel();
    private JLabel[][] arrayPlayersCards = new JLabel[8][2];
    private JLabel[] arrayPlayersNickCash = new JLabel[9];
    private JLabel[] showTableCards = new JLabel[5];
    private JLabel Broadcast = new JLabel();
    private JLabel Dealer = new JLabel();
    private JLabel showPot = new JLabel();

    // *** getLocation() method vars
    private String Card1 = "CardOne";
    private String Card2 = "CardTwo";
    private String PlayerBar = "PlayerBar";
    private String Deal = "Dealer";
    private char x = 'x';
    private char y = 'y';

    // TableWindow variables end

    public ClientView(ClientModel model) {

        this.model = model;

        // LoginWindow appearance
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


        // TableWindow appearance
        TableWindow.setSize(900, 630);
        TableWindow.setLocation(((screenSize.width / 2) - (TableWindow.getSize().width / 2)), screenSize.height / 2 - TableWindow.getSize().height / 2);
        TableWindow.setResizable(false);
        TableWindow.setVisible(false);
        TableWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        TableWindow.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/poker/GUI/img/pokerTableNew.jpg"))));
        TableWindow.setTitle("Poker Client");



//        TableWindow.add(displayNick(), null);
//        TableWindow.add(displayCash(), null);
        TableWindow.add(foldButton(), null);
        TableWindow.add(checkButton(), null);
        TableWindow.add(callButton(), null);
        TableWindow.add(raiseButton(), null);
        TableWindow.add(CashSlider(), null);
        TableWindow.add(displayCashSlider(), null);
        TableWindow.add(potSizeSlider(), null);
        TableWindow.add(OneThirdSizeSlider(), null);
        TableWindow.add(ThreexSizeSlider(), null);
        TableWindow.add(AllInSizeSlider(), null);
        TableWindow.add(PlusSizeSlider(), null);
        TableWindow.add(MinusSizeSlider(), null);
        TableWindow.add(displayBroadcast(), null);
        TableWindow.add(showPot(), null);
    }

    // Methods for CONTROLLER
    public void updateView() {
        TableWindow.invalidate();
        TableWindow.validate();
        TableWindow.repaint();
    }
    public void stateReady(){
        Broadcast.setVisible(true);
        foldButton.setEnabled(false);
        raiseButton.setEnabled(false);
        checkButton.setEnabled(false);
        callButton.setEnabled(false);
        potSizeSlider.setEnabled(false);
        OneThirdSizeSlider.setEnabled(false);
        ThreexSizeSlider.setEnabled(false);
        CashSlider.setEnabled(false);
        AllInSizeSlider.setEnabled(false);
        PlusSizeSlider.setEnabled(false);
        MinusSizeSlider.setEnabled(false);
        displayCashSlider.setVisible(false);
    }
    public void stateInputCheck(){
        checkButton.setVisible(true);
        callButton.setVisible(false);
        foldButton.setEnabled(true);
        checkButton.setEnabled(true);
        callButton.setEnabled(true);
        raiseButton.setEnabled(true);
        CashSlider.setEnabled(true);
        potSizeSlider.setEnabled(true);
        OneThirdSizeSlider.setEnabled(true);
        ThreexSizeSlider.setEnabled(true);
        AllInSizeSlider.setEnabled(true);
        PlusSizeSlider.setEnabled(true);
        MinusSizeSlider.setEnabled(true);
        displayCashSlider.setVisible(true);
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
        OneThirdSizeSlider.setEnabled(true);
        ThreexSizeSlider.setEnabled(true);
        AllInSizeSlider.setEnabled(true);
        PlusSizeSlider.setEnabled(true);
        MinusSizeSlider.setEnabled(true);
        displayCashSlider.setVisible(true);
    }
    public void statePlaying(){
        foldButton.setEnabled(false);
        checkButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        CashSlider.setEnabled(false);
        potSizeSlider.setEnabled(false);
        OneThirdSizeSlider.setEnabled(false);
        ThreexSizeSlider.setEnabled(false);
        AllInSizeSlider.setEnabled(false);
        PlusSizeSlider.setEnabled(false);
        MinusSizeSlider.setEnabled(false);
        callButton.setVisible(false);
        displayCashSlider.setVisible(false);
    }
    public void stateEnded(){
        Broadcast.setVisible(true);
        Broadcast.setText("|PLAYER| has won |CASH||");
    }

    public void tableCards(){
        int count = -1;
        int x = 265;
        int offSetX = 75;
        int newOffSetX;
        ArrayList<String> tableCards = fromCardToString(model.getFieldCards());
        
        for(String card : tableCards){
        
            if(card != null){
                count++;
                newOffSetX = x + (count * offSetX);
                showTableCards[count] = showTable(tableCards.get(count), newOffSetX, 180);
                    for(int i = 0; i < showTableCards.length; i++){
                        if(showTableCards[i] != null){
                            TableWindow.add(showTableCards[i], null);
                        }
                    }

            }
        }
    }
    public void getWinners(ArrayList<SendWinnerListCommand.Tuple> list){
        int id;
        int newCash;

        for(SendWinnerListCommand.Tuple player : list){
            if(player != null){

                id = player.id;
                newCash = player.cash;

                switch (id){
                    case 0:
                        arrayPlayersNickCash[id] = clientNameCash("Player0", newCash, 230, 510);
                        break;
                    case 1:
                        arrayPlayersNickCash[id] = clientNameCash("Player1", newCash, 30, 420);
                        break;
                    case 2:
                        arrayPlayersNickCash[id] = clientNameCash("Player2", newCash, 30, 110);
                        break;
                    case 3:
                        arrayPlayersNickCash[id] = clientNameCash("Player3", newCash, 250, 20);
                        break;
                    case 4:
                        arrayPlayersNickCash[id] = clientNameCash("Player4", newCash, 540, 20);
                        break;
                    case 5:
                        arrayPlayersNickCash[id] = clientNameCash("Player5", newCash, 760, 110);
                        break;
                    case 6:
                        arrayPlayersNickCash[id] = clientNameCash("Player6", newCash, 760, 420);
                        break;
                    case 7:
                        arrayPlayersNickCash[id] = clientNameCash("Player7", newCash, 570, 510);
                        break;
                    case 8:
                        arrayPlayersNickCash[id] = clientNameCash("Player8", newCash, 403, 515);
                        break;

                }
            }
        }
    }
    
    private int getLocation(int id, String what, char axis){
    	
    	ArrayList<Coordinates> PlayerLocation = new ArrayList<Coordinates>();
    	ArrayList<Coordinates> CardOneLocation = new ArrayList<Coordinates>();
    	ArrayList<Coordinates> CardTwoLocation = new ArrayList<Coordinates>();
    	ArrayList<Coordinates> DealerLocation = new ArrayList<Coordinates>();
    	
	    	PlayerLocation.add(0, new Coordinates(230, 510));
			PlayerLocation.add(1, new Coordinates(30, 420));
			PlayerLocation.add(2, new Coordinates(30, 110));
			PlayerLocation.add(3, new Coordinates(250, 20));
			PlayerLocation.add(4, new Coordinates(540, 20));
			PlayerLocation.add(5, new Coordinates(760, 110));
			PlayerLocation.add(6, new Coordinates(760, 420));
			PlayerLocation.add(7, new Coordinates(570, 510));
			PlayerLocation.add(8, new Coordinates(400, 515));
			
			CardOneLocation.add(0, new Coordinates(260,435));
			CardOneLocation.add(1, new Coordinates(60,345));
			CardOneLocation.add(2, new Coordinates(60,155));
			CardOneLocation.add(3, new Coordinates(280,65));
			CardOneLocation.add(4, new Coordinates(570,65));
			CardOneLocation.add(5, new Coordinates(790,165));
			CardOneLocation.add(6, new Coordinates(790,345));
			CardOneLocation.add(7, new Coordinates(600,435));
			CardOneLocation.add(8, new Coordinates(420,410));
			
			CardTwoLocation.add(0, new Coordinates(250,430));
			CardTwoLocation.add(1, new Coordinates(50,340));
			CardTwoLocation.add(2, new Coordinates(50,150));
			CardTwoLocation.add(3, new Coordinates(270,60));
			CardTwoLocation.add(4, new Coordinates(560,60));
			CardTwoLocation.add(5, new Coordinates(780,160));
			CardTwoLocation.add(6, new Coordinates(780,340));
			CardTwoLocation.add(7, new Coordinates(590,430));
			CardTwoLocation.add(8, new Coordinates(410,405));
			
			DealerLocation.add(0, new Coordinates(280, 390));
			DealerLocation.add(1, new Coordinates(130, 340));
			DealerLocation.add(2, new Coordinates(130, 210));
			DealerLocation.add(3, new Coordinates(300, 150));
			DealerLocation.add(4, new Coordinates(555, 150));
			DealerLocation.add(5, new Coordinates(740, 210));
			DealerLocation.add(6, new Coordinates(740, 340));
			DealerLocation.add(7, new Coordinates(575, 390));
			DealerLocation.add(8, new Coordinates(455, 370));
			
		if(what == PlayerBar){
			if(axis == x){
			return PlayerLocation.get(id).axisX;
			} else if (axis == y){
			return PlayerLocation.get(id).axisY;	
			}
    	} else if(what == Card1){
    		if(axis == x){
    			return CardOneLocation.get(id).axisX;
    			} else if (axis == y){
    			return CardOneLocation.get(id).axisY;	
    			}
    	} else if(what == Card2){
    		if(axis == x){
    			return CardTwoLocation.get(id).axisX;
    			} else if (axis == y){
    			return CardTwoLocation.get(id).axisY;	
    			}
    	} else if(what == Deal){
    		if(axis == x){
    			return DealerLocation.get(id).axisX;
    			} else if (axis == y){
    			return DealerLocation.get(id).axisY;	
    			}
    	}
		return 0;
	
    }
    public void setNums(){
        CashSlider.setMaximum(model.getCash(model.getID()));
        CashSlider.setValue(model.getMaxBet() * 2);
        CashSlider.setMinimum(model.getMaxBet() * 2);

        CashSlider.setMajorTickSpacing(model.getCash(model.getID()) / 2);
    }
    public void setNewPot(){   
    	showPot.setText("POT: " + model.getPot());
    }
    public void setNewCash(ArrayList<ClientSidePlayer> list){
        int id = 0;
        int offSet = 0;

            for(ClientSidePlayer player : list){
                if(player != null){

                	offSet = 9 - model.getID();
                    id = player.getId() - 1;
                    switch (id){
                    case 0:
                    	if(arrayPlayersNickCash[id] != null){
                    	TableWindow.remove(arrayPlayersNickCash[id]);
                    	}
                        arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 1:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                        arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 2:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 3:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 4:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 5:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 6:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 7:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 8:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                }
            }
        }
    }

	public void placePlayers(ArrayList<ClientSidePlayer> list){
        int id = 0;
        int offSet = 0;

            for(ClientSidePlayer player : list){
                if(player != null){

                	offSet = 9 - model.getID();
                    id = player.getId() - 1;
                    switch (id){
                    case 0:
                    	if(arrayPlayersNickCash[id] != null){
                    	TableWindow.remove(arrayPlayersNickCash[id]);
                    	}
                        arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 1:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                        arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 2:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 3:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 4:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 5:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 6:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 7:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                    case 8:
                    	if(arrayPlayersNickCash[id] != null){
                        	TableWindow.remove(arrayPlayersNickCash[id]);
                        	}
                    	arrayPlayersNickCash[id] = clientNameCash("Player" + id, model.getCash(id + 1), getLocation((id + offSet)%9,PlayerBar,x),getLocation((id + offSet)%9,PlayerBar,y));
                        TableWindow.add(arrayPlayersNickCash[id]);
                        break;
                }
                    TableWindow.add(Dealer(getLocation((model.getDealer() + 1 + offSet)%9,Deal,x),getLocation((model.getDealer() + 1 + offSet)%9,Deal,y)));
                }
            }
    }

    public void giveCards(ArrayList<ClientSidePlayer> list){
        int id = 0;
        int offSet = 0;
        ArrayList<String> myCards;
        
        for(ClientSidePlayer player : list){
            if(player != null){

                offSet = 8 - model.getID();
                id = player.getId();
                System.out.println("modelid= "+model.getID()+" id= "+player.getId());
                switch (id){
                    case 0:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));

                        }

                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);
                        break;
                    case 1:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));

                        }

                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);
                        break;
                    case 2:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));

                        }

                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);
                        break;
                    case 3:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));

                        }

                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);
                        break;
                    case 4:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));

                        }

                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);
                        break;
                    case 5:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));

                        }

                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);
                        break;
                    case 6:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));
                        }
                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);

                        break;
                    case 7:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));

                        }

                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);
                        break;
                    case 8:

                        if(model.getID()  == id){
                            myCards = fromCardToString(model.getCards(id));
                            arrayPlayersCards[id][0] = userCard1(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y), myCards.get(0));
                            arrayPlayersCards[id][1] = userCard2(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y), myCards.get(1));
                        } else {
                            arrayPlayersCards[id][0] = backCard(getLocation((id + offSet)%9,Card1,x),getLocation((id + offSet)%9,Card1,y));
                            arrayPlayersCards[id][1] = backCard(getLocation((id + offSet)%9,Card2,x),getLocation((id + offSet)%9,Card2,y));

                        }

                        TableWindow.add(arrayPlayersCards[id][0]);
                        TableWindow.add(arrayPlayersCards[id][1]);
                        break;

                }

            }
        }
    }

//    public void showMyCards(){
//        ClientSidePlayer player = new ClientSidePlayer();
//        fromCardToString(model.getMyCards());
//        if(player.getId() - 1 == id){
//            arrayPlayersCards[id][0] = userCard1(260,435, myCards.get(0));
//            arrayPlayersCards[id][1] = userCard1(250,430, myCards.get(1));
//        } else {
//            arrayPlayersCards[id][0] = backCard(260,435);
//            arrayPlayersCards[id][1] = backCard(250,430);
//        }
//    }

    private ArrayList<String> fromCardToString(Card[] cards ){
        ArrayList<String> output=new ArrayList<String>();
        String fileName="";
        for(Card card:cards){
            if(card == null){
            	fileName = "invisibleCard";
            	output.add(fileName);
            	continue;
            }
            switch (card.getRank()){
                case TWO:
                    fileName = "2_of_";
                    break;
                case THREE:
                    fileName = "3_of_";
                    break;
                case FOUR:
                    fileName = "4_of_";
                    break;
                case FIVE:
                    fileName = "5_of_";
                    break;
                case SIX:
                    fileName = "6_of_";
                    break;
                case SEVEN:
                    fileName = "7_of_";
                    break;
                case EIGHT:
                    fileName = "8_of_";
                    break;
                case NINE:
                    fileName = "9_of_";
                    break;
                case TEN:
                    fileName = "10_of_";
                    break;
                case JACK:
                    fileName = "jack_of_";
                    break;
                case QUEEN:
                    fileName = "queen_of_";
                    break;
                case KING:
                    fileName = "king_of_";
                    break;
                case ACE:
                    fileName = "ace_of_";
                    break;
            }
            switch (card.getSuit()){
                case DIAMONDS:
                    fileName+="diamonds";
                    break;
                case HEARTS:
                    fileName+="hearts";
                    break;
                case CLUBS:
                    fileName+="clubs";
                    break;
                case SPADES:
                    fileName+="spades";
                    break;
            }
            output.add(fileName);
        }
        return output;
    }

    // Methods for CONTROLLER ENDs


    // TableWindow variables description STARTs

    public JLabel displayBroadcast(){
        
        Broadcast.setText("Waiting for actions");
        Broadcast.setBounds(350, 565, 200, 20);
        Broadcast.setForeground(Color.WHITE);
        Broadcast.setHorizontalAlignment( SwingConstants.CENTER );
        Broadcast.setVisible(true);
        return Broadcast;
    }
    private JButton foldButton(){
        foldButton.setActionCommand("fold");
        foldButton.setBounds(15, 562, 85, 30);
        foldButton.setText("FOLD");
        foldButton.setForeground(Color.WHITE);
        foldButton.setOpaque(false);
        foldButton.setContentAreaFilled(false);
        foldButton.addActionListener(this);
        return foldButton;
    }
    private JButton checkButton(){
        checkButton.setActionCommand("check");
        checkButton.setBounds(105, 562, 85, 30);
        checkButton.setText("CHECK");
        checkButton.setForeground(Color.WHITE);
        checkButton.setOpaque(false);
        checkButton.setContentAreaFilled(false);
        checkButton.addActionListener(this);
        return checkButton;
    }
    private JButton callButton(){
        callButton.setActionCommand("call");
        callButton.setBounds(105, 562, 85, 30);
        callButton.setText("CALL");
        callButton.setForeground(Color.WHITE);
        callButton.setOpaque(false);
        callButton.setContentAreaFilled(false);
        callButton.addActionListener(this);
        callButton.setVisible(false);
        return callButton;
    }
    private JButton raiseButton(){
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

    private JSlider CashSlider(){
        CashSlider.setBounds(720, 550, 155, 50);
        CashSlider.setMaximum(0);
        CashSlider.setMinimum(0);
        CashSlider.setValue(0);
        CashSlider.addChangeListener(this);
        CashSlider.setMajorTickSpacing(0);
//		CashSlider.setMinorTickSpacing((int)(Math.round((Cash / 20)/ 10.0) * 10)); // FORMULA
        CashSlider.setPaintTicks(true);
        
        CashSlider.setBackground(Color.GRAY);
        CashSlider.setForeground(Color.WHITE);
        CashSlider.setSnapToTicks(false);
        CashSlider.setOpaque(false);


        return CashSlider;
    }
    private JLabel displayCashSlider(){

        displayCashSlider.setBounds(650, 555, 50, 25);
        displayCashSlider.setForeground(Color.WHITE);
        displayCashSlider.setHorizontalAlignment( SwingConstants.RIGHT );

        displayCashSlider.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        displayCashSlider.setText("" + CashSlider.getValue());
        return displayCashSlider;
    }
    private JButton potSizeSlider(){
        potSizeSlider.setActionCommand("pot");
        potSizeSlider.setBounds(750, 525, 40, 25);
        potSizeSlider.setText("POT");
        potSizeSlider.setForeground(Color.WHITE);
        potSizeSlider.setOpaque(false);
        potSizeSlider.setContentAreaFilled(false);
        potSizeSlider.setMargin(new Insets(0,0,0,0));
        potSizeSlider.addActionListener(this);
        return potSizeSlider;
    }
    private JButton OneThirdSizeSlider(){
        OneThirdSizeSlider.setActionCommand("1/3x");
        OneThirdSizeSlider.setBounds(705, 525, 40, 25);
        OneThirdSizeSlider.setText("1/3X");
        OneThirdSizeSlider.setForeground(Color.WHITE);
        OneThirdSizeSlider.setOpaque(false);
        OneThirdSizeSlider.setContentAreaFilled(false);
        OneThirdSizeSlider.setMargin(new Insets(0,0,0,0));
        OneThirdSizeSlider.addActionListener(this);
        return OneThirdSizeSlider;
    }
    private JButton ThreexSizeSlider(){
        ThreexSizeSlider.setActionCommand("3x");
        ThreexSizeSlider.setBounds(795, 525, 40, 25);
        ThreexSizeSlider.setText("3X");
        ThreexSizeSlider.setForeground(Color.WHITE);
        ThreexSizeSlider.setOpaque(false);
        ThreexSizeSlider.setContentAreaFilled(false);
        ThreexSizeSlider.setMargin(new Insets(0,0,0,0));
        ThreexSizeSlider.addActionListener(this);
        return ThreexSizeSlider;
    }
    private JButton AllInSizeSlider(){
        AllInSizeSlider.setActionCommand("AllIn");
        AllInSizeSlider.setBounds(840, 525, 50, 25);
        AllInSizeSlider.setText("ALL-IN");
        AllInSizeSlider.setForeground(Color.WHITE);
        AllInSizeSlider.setOpaque(false);
        AllInSizeSlider.setContentAreaFilled(false);
        AllInSizeSlider.setMargin(new Insets(0,0,0,0));
        AllInSizeSlider.addActionListener(this);
        return AllInSizeSlider;
    }

    private JButton PlusSizeSlider(){
    	PlusSizeSlider.setActionCommand("PlusSlider");
        PlusSizeSlider.setBounds(875, 560, 15, 15);
        PlusSizeSlider.setText("+");
        PlusSizeSlider.setForeground(Color.WHITE);
        PlusSizeSlider.setFont(new Font("Times New Roman", Font.BOLD, 14));
        PlusSizeSlider.setBorderPainted(false);
        PlusSizeSlider.setOpaque(false);
        PlusSizeSlider.setContentAreaFilled(false);
        PlusSizeSlider.setMargin(new Insets(0,0,0,0));
        PlusSizeSlider.addActionListener(this);
        return PlusSizeSlider;	
    }
    private JButton MinusSizeSlider(){
    	MinusSizeSlider.setActionCommand("MinusSlider");
        MinusSizeSlider.setBounds(705, 560, 15, 15);
        MinusSizeSlider.setText("-");
        MinusSizeSlider.setForeground(Color.WHITE);
        MinusSizeSlider.setFont(new Font("Times New Roman", Font.BOLD, 14));
        MinusSizeSlider.setBorderPainted(false);
        MinusSizeSlider.setOpaque(false);
        MinusSizeSlider.setContentAreaFilled(false);
        MinusSizeSlider.setMargin(new Insets(0,0,0,0));
        MinusSizeSlider.addActionListener(this);
        return MinusSizeSlider;	
    }

    private JLabel userCard1(int x, int y, String userCardOne){
        ImageIcon cardImg1 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/" + userCardOne + ".png"));
        JLabel userCard1 = new JLabel(cardImg1);
        userCard1.setBounds(x, y, 70, 100);
        return userCard1;
    }
    private JLabel userCard2(int x, int y, String userCardTwo){
        ImageIcon cardImg2 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/" + userCardTwo + ".png"));
        JLabel userCard2 = new JLabel(cardImg2);
        userCard2.setBounds(x, y, 70, 100);
        return userCard2;
    }
    private JLabel backCard(int x, int y){

        ImageIcon back = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/back.png"));
        JLabel backCard = new JLabel(back);
        backCard.setBounds(x,y,50,70);
        return backCard;
    }

    private JLabel clientNameCash(String ClientName, int ClientCash, int x, int y){
        JLabel clientNameCash = new JLabel();
        clientNameCash.setBounds(x,y,100,30);
        clientNameCash.setForeground(Color.WHITE);
        clientNameCash.setHorizontalAlignment( SwingConstants.CENTER );
        clientNameCash.setText("<html><body align='center'>" + ClientName + "<br />(" + ClientCash +")</body></html>");
        return clientNameCash;
    }
    private JLabel Dealer(int x, int y){
        ImageIcon back = new ImageIcon(getClass().getResource("/poker/GUI/img/Dealer.png"));
        Dealer = new JLabel(back);
        Dealer.setBounds(x,y,25,20);
        return Dealer;
    }
    private JLabel showPot(){
    	showPot.setForeground(Color.WHITE);
    	showPot.setHorizontalAlignment( SwingConstants.CENTER );
		showPot.setText("POT: " + model.getPot());
		showPot.setBounds(415,300,70,20);
    	return showPot;
    }

    private JLabel showTable(String card, int x, int y){
    	
        ImageIcon cardImg1 = new ImageIcon(getClass().getResource("/poker/GUI/img/cards/" + card + ".png"));
        JLabel showTable = new JLabel(cardImg1);
        showTable.setBounds(x,y,70,100);
        showTable.setVisible(true);
        return showTable;
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
    // LoginWindow variables description ENDs

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if("raise".equals(e.getActionCommand())){
           model.pressedRaise(CashSlider.getValue());
        } else if("check".equals(e.getActionCommand())){
            model.pressedCheck();

        } else if("call".equals(e.getActionCommand())){
            model.pressedCall(model.getMaxBet() - model.getPlayerBet(model.getID()));

        } else if("fold".equals(e.getActionCommand())){
            model.pressedFold();
        } else if("1/3x".equals(e.getActionCommand())){
        	CashSlider.setValue(model.getPot() / 3);
        } else if("3x".equals(e.getActionCommand())){
        	CashSlider.setValue(model.getPot() * 3);
        } else if("pot".equals(e.getActionCommand())){
        	CashSlider.setValue(model.getPot());
        } else if("AllIn".equals(e.getActionCommand())){
        	CashSlider.setValue(model.getCash(model.getID()));
        } else if("PlusSlider".equals(e.getActionCommand())){
        	CashSlider.setValue(CashSlider.getValue() + model.getMaxBet());
        } else if("MinusSlider".equals(e.getActionCommand())){
        	CashSlider.setValue(CashSlider.getValue() - model.getMaxBet());
        } else if("connect".equals(e.getActionCommand())){
            if(textName.getText().length() >= 3 && textName.getText().length() <= 15){
                // SERVER CONNECTION IMPLEMENTATION
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
        int CashCurrent;
        CashCurrent = CashSlider.getValue(); // FORMULA
        if(CashCurrent > model.getCash(model.getID())){
            CashCurrent = model.getCash(model.getID());
        }
        displayCashSlider.setText(String.valueOf("" + CashCurrent));
        
    }
}