package _02_Chat_Application;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket server;
	Socket socket;
	public void start() {
		try {
			server = new ServerSocket(8080);
			socket = server.accept();
			boolean connection = true;
			while(connection) {
				
			}
		}
		catch(Exception e) {
			
		}
	}
}
