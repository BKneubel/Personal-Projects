package earlyBuilds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import main.Contact;
import main.Message;

public class MultiThreadClient{

	private static Socket socket;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static String hostName;
	private static int port;
	private static BufferedReader in;
	private static final DateFormat df = new SimpleDateFormat("dd/MM HH:mm:ss");
	private static ReceptionHandler RHandler;
	private static Contact myContact;

	public static void main(String[] args) throws IOException{

		if(args.length != 1){
			System.err.println("Usage: java MultiThreadClient <host name:port number>");
			System.exit(1);
		}
		String fullName = args[0];

		try{
			initialize(fullName);
			Message messageOut;
			String userMessage = null;
			RHandler.start();
			for(;;){
				userMessage = in.readLine();
				if(userMessage != null && userMessage != "EXIT"){
					messageOut = new Message(userMessage, myContact);
					oos.writeObject(messageOut);
				} else if(userMessage == "EXIT"){
					break;
				}
			}
			finish();
		} catch(UnknownHostException e){
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch(IOException e){
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}

	public static void printMessage(Message m){
		System.out.println("[" + m.getSender().getName() + "][" + df.format(m.getTime()) + "]");
		System.out.println(m.getText());
	}

	public static void initialize(String s) throws IOException{
		int index = s.indexOf(':');
		hostName = s.substring(0, index);
		port = Integer.parseInt(s.substring(index + 1));
		in = new BufferedReader(new InputStreamReader(System.in));
		try{
			socket = new Socket(hostName, port);
		} catch(Exception e){
			e.printStackTrace();
			System.err.println("Accept failed.");
			System.exit(1);
		}
		System.out.println("What is your contact name?");
		myContact = new Contact(in.readLine(), socket.getLocalAddress(), null);
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		RHandler = new ReceptionHandler(ois);
		System.out.println("Initialized");

	}

	public static void pullMessage(Message m){

	}

	public static void finish() throws IOException{
		socket.close();
		ois.close();
		oos.close();
		in.close();
	}

}