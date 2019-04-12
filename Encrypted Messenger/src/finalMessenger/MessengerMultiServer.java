package finalMessenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class MessengerMultiServer{
	//This is the class that receives connections from clients and assigns them their very own thread

	private static ArrayList<Integer> useless;
	private static final int port = 46290;//the port the server will be hosted on
	private static int serial;//thread serial number
	private static ArrayList<MessengerThread> threads;

	public static void startServer() throws IOException{
		serial = 0;
		threads = new ArrayList<MessengerThread>();
		boolean listening = true;
		useless = new ArrayList<Integer>();
		System.out.println("Server now hosting on port number " + port);
		try(ServerSocket serverSocket = new ServerSocket(port)){//try with the server socket as a resource
			while(listening){
				threads.add(new MessengerThread(serverSocket.accept(), serial));//iterates on this line, will wait for connection
				threads.get(serial).start();//start thread
				serial++;
			}
		} catch(IOException e){
			System.err.println("Could not listen on port " + port);
			System.exit(-1);
		}
	}

	public static void killThread(int s){
		try{
			threads.get(s).finish();
		} catch(IOException e){
			System.err.println("There was an error with closing a thread.");
		}
	}

	public static void markUseless(int i){
		useless.add(i);
	}

	public static void update(int s, Message m){//called by a thread that got a message from the client
		for(int i = 0; i < threads.size(); i++){//this method will push each other thread the message so they may update their client
			if(s != i && !useless.contains(i)){
				threads.get(i).pullMessage(m);
			}
		}
	}
}
