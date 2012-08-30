import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame{
	private JTextArea jta = new JTextArea();
	public static void main(String[] args){
		new Server();
	}
	ArrayList<Socket> sockets = new ArrayList<Socket>();
	public Server(){
		setResizable(false);
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);
		jta.setEditable(false);
		setTitle("Server");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try{
			ServerSocket serverSocket = new ServerSocket(8000);
			jta.append("Server started at "+new Date()+"\n");
			int clientNo = 1;
			while(true){
				Socket socket = serverSocket.accept();
				sockets.add(socket);
				jta.append("Starting thread for client "+ clientNo+"\n");
				InetAddress inetAddress = socket.getInetAddress();
				jta.append("Client "+ clientNo +"'s host name is "
						+inetAddress.getHostName()+"\n");
				jta.append("Client "+ clientNo +"'s IP address is "
						+inetAddress.getHostAddress()+"\n");
				jta.setCaretPosition(jta.getText().length());
				Thread thread = new Thread(new HandleAClient(clientNo, socket));
				thread.start();
				clientNo++;
			}
		}
		catch(IOException ex){
			System.err.println(ex);
		}
	}

	class HandleAClient implements Runnable{
		private Socket socket;
		private int clientNo;
		public HandleAClient(int clientNo, Socket socket){
			this.socket = socket;
			this.clientNo = clientNo;
		}

		public void run(){
			try {
				DataInputStream inputFromClient = new DataInputStream(
						socket.getInputStream());

				DataOutputStream outputToClient;

				while(true){
					String messege="";
					try{
						messege = inputFromClient.readUTF();
						System.out.println("Size is " + sockets.size());
						for(int i = 0 ; i < sockets.size(); i++){
							if(sockets.get(i) != null && !sockets.get(i).isClosed() && !sockets.get(i).equals(socket)){
								System.out.println(i);
								System.out.println("IsClosed is " + sockets.get(i).isClosed());
								outputToClient = new DataOutputStream(
										sockets.get(i).getOutputStream());
								outputToClient.writeUTF("Client"+clientNo+" says: "+messege);
							}
						}
						jta.append("messege received from client"+clientNo + ": "+
								messege + '\n');
						jta.setCaretPosition(jta.getText().length());

					}catch (EOFException e){
					//	e.printStackTrace();
						for(int i = 0 ; i < sockets.size(); i++){
							if(sockets.get(i) != null && !sockets.get(i).isClosed() && !sockets.get(i).equals(socket)){
								outputToClient = new DataOutputStream(
										sockets.get(i).getOutputStream());
								outputToClient.writeUTF("Client"+clientNo+" leaves");
							}
							else{
								sockets.get(i).close();
							}
						}
						jta.append("Client"+clientNo+" leaves\n");
						jta.setCaretPosition(jta.getText().length());

						break;
					}
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
