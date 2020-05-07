package _02_Chat_Application;

import javax.swing.JOptionPane;

 // Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.


public class ChatApp {
	Server server;
	Client client;
	public static void main(String[] args) {
		new ChatApp();
	}
	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat", JOptionPane.YES_NO_OPTION);
		 server = new Server();
		if(response==JOptionPane.YES_OPTION) {
			 System.out.println(server.getIPAddress());
			 server.start();
		}
		else {
			 System.out.println(server.getIPAddress());
			 client = new Client(server.getIPAddress(),8080);
			 client.start();
			
		}
	}
}
