package tests;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import message.data.ClientTurn;
import message.data.PlayerMove;
import poker.arturka.Card;
import poker.arturka.Player;
import poker.arturka.Card.Rank;
import poker.arturka.Card.Suit;
import commands.ChangeCashCommand;
import commands.ChangeDealersCommand;
import commands.Command;
import commands.PlayerMoveCommand;
import commands.SendCardsCommand;
import commands.SendPlayerListCommand;
import commands.SetIDCommand;

public class FakeServer implements Runnable {

	private static final int PORT = 9998;
	private static final int CONNECTION_LIMIT = 50;
	
	private ServerSocket server;
	private ObjectOutputStream out;
	public FakeServer() {
		
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(PORT, CONNECTION_LIMIT, InetAddress.getLocalHost());
			Socket client = server.accept();
			this.out = new ObjectOutputStream(client.getOutputStream());
			out.flush();
			
			List <Command>que = new ArrayList<Command>();
			List <Player>someList = new ArrayList<Player>();
			Player player = new Player(1);
			player.toggleDealer();
			
			someList.add(player);
			someList.add(new Player(2));
			
			que.add(new SetIDCommand(1));
			que.add(new SendPlayerListCommand(someList));
			que.add(new ChangeCashCommand(2, 1200));
			que.add(new SendCardsCommand(1, 
									new Card(Suit.CLUBS, Rank.ACE),
									new Card(Suit.DIAMONDS, Rank.ACE)));
			que.add(new PlayerMoveCommand( 
					new PlayerMove(1, ClientTurn.FOLD, 30, 970)));
			que.add(new ChangeDealersCommand(1,2));
			
			for(Command i: que) {
				out.writeObject(i);
				out.flush();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}