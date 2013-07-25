package poker.server;

public class StartServer implements Runnable {

	private int count;
	
	public StartServer(int count) {
		this.count = count;
	}
	
	@Override
	public void run() {
		Server.start(count, null, -1);
	}

	
}
