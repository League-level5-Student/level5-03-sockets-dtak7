package _02_Chat_Application;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class Server {
	ServerSocket server;
	Socket socket;
	ObjectOutputStream os;
	ObjectInputStream is;

	public void start() {
		try {
			server = new ServerSocket(8080);
			socket = server.accept();
			os = new ObjectOutputStream(socket.getOutputStream());
			is = new ObjectInputStream(socket.getInputStream());
			while (socket.isConnected()) {
				JOptionPane.showMessageDialog(null, is.readObject());
				System.out.println(is.readObject());
			}
		} catch (Exception e) {
			System.exit(0);
		}
	}

	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}
}
