import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame implements ActionListener{
	private JTextField jtf = new JTextField();
	private JTextArea jta = new JTextArea();
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	private Socket socket;
	
	public static void main(String[] args){
		new Client();
	}
	public Client(){
		super();
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Enter Message"), BorderLayout.WEST);
		p.add(jtf, BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.LEFT);

		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		jta.setEditable(false);
		add(new JScrollPane(jta), BorderLayout.CENTER);

		jtf.addActionListener(this);

		setTitle("Client");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		try{
			socket = new Socket("localhost", 8000);
			jta.append("I've been on-line\n");
			fromServer = new DataInputStream(
					socket.getInputStream());
			toServer = new DataOutputStream(
					socket.getOutputStream());
			new Thread(new HandleMessage(socket)).start();
		}
		catch(IOException ex){
			jta.append(ex.toString()+"\n");
		}

	}

	class HandleMessage implements Runnable{
		private Socket socket;
		public HandleMessage(Socket socket){
			this.socket = socket;
		}
		public void run(){
			try {
				while(true){
					String messege = fromServer.readUTF();
					jta.append(messege + '\n');
					jta.setCaretPosition(jta.getText().length());
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
				System.out.println("Connection Closed");
				System.exit(0);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			String message = jtf.getText().trim();
			if(message.equalsIgnoreCase("leave")){
				jta.append("I've been off-line\n");
				jta.setCaretPosition(jta.getText().length());
				socket.close();
				System.exit(0);
			}
			else{
				jta.append("Me said:"+message+"\n");
				jta.setCaretPosition(jta.getText().length());
				toServer.writeUTF(message);
				toServer.flush();
				jtf.setText("");
			}
		}
		catch(IOException ex){
			System.err.println(ex);
		}

	}

}
