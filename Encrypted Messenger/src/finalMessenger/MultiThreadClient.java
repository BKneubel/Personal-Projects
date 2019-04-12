package finalMessenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiThreadClient{

	private static Socket socket;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static String hostName;
	private static int port;
	private static BufferedReader in;
	private static ReceptionHandler RHandler;
	private static Contact myContact;

	public static void startClient(String s) throws IOException{

		String fullName = s;
		try{
			initialize(fullName);
			RHandler.start();
			for(;;){

			}
		} catch(UnknownHostException e){
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch(IOException e){
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}

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
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		RHandler = new ReceptionHandler(ois);
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run(){
				finish();
			}
		});
		System.out.println("Initialized");

	}

	public static void pullMessage(Message m){
		try{
			oos.writeObject(m);
		} catch(IOException e){
			System.err.println("Error:");
			e.printStackTrace();
		}
	}

	public static void finish(){
		try{
			socket.close();
			ois.close();
			oos.close();
			in.close();
		} catch(IOException e){

		}
	}

}