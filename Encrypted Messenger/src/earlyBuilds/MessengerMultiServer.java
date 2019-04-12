package earlyBuilds;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import main.Message;

public class MessengerMultiServer{
	//This is the class that receives connections from clients and assigns them their very own thread

	private static final int port = 46290;//the port the server will be hosted on
	private static int serial;//thread serial number
	private static ArrayList<MessengerThread> threads;

	public static void main(String[] args) throws IOException{
		serial = 0;
		threads = new ArrayList<MessengerThread>();
		boolean listening = true;
		System.out.println("Listening on port " + port);
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

	public static void update(int s, Message m){//called by a thread that got a message from the client
		for(int i = 0; i < threads.size(); i++){//this method will push each other thread the message so they may update their client
			if(s != i){
				threads.get(i).pullMessage(m);
			}
		}
	}
}
