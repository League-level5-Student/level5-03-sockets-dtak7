package _02_Chat_Application;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
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
			JFrame frame = new JFrame("Server GUI");
			JButton sendMessage = new JButton("Send a Message");
			frame.add(sendMessage);
			frame.setVisible(true);
			frame.setSize(250, 150);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			while (socket.isConnected()) {
				sendMessage.addActionListener((ActionEvent e) -> {
					String message=JOptionPane.showInputDialog("Send a message");
					try {
						os.writeObject(message);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				});
				JOptionPane.showMessageDialog(null, is.readObject(),"Client",JOptionPane.INFORMATION_MESSAGE);
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
