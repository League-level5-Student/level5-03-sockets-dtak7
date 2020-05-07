package _02_Chat_Application;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client {
	private String ip;
	Socket socket;
	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port) {
		this.ip = ip;
		port = 8080;
	}

	public void start() {
		try {
			socket = new Socket(ip, 8080);

			os = new ObjectOutputStream(socket.getOutputStream());
			is = new ObjectInputStream(socket.getInputStream());
			JFrame frame = new JFrame("Client GUI");
			JButton sendMessage = new JButton("Send a Message");
			frame.add(sendMessage);
			frame.setVisible(true);
			frame.setSize(250, 150);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			sendMessage.addActionListener((ActionEvent e) -> {
				String message=JOptionPane.showInputDialog("Send a message");
				try {
					os.writeObject(message);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		} catch (Exception e) {

		}

		while (socket.isConnected()) {
			try {
				JOptionPane.showMessageDialog(null, is.readObject(),"Server",JOptionPane.INFORMATION_MESSAGE);
				System.out.println(is.readObject());
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
