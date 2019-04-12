package finalMessenger;

import java.awt.Color;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessengerThread extends Thread{

	private Socket socket = null;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private int serial;
	private boolean running;

	public MessengerThread(Socket socket, int s){
		super("MessengerThread");
		serial = s;
		running = true;
		this.socket = socket;
	}

	@Override
	public void run(){
		try{
			if(running)
				initialize();
			if(running){
				System.out.println();
				oos.writeObject(new Message("Connected to Server",
						new Contact("The Server", socket.getLocalAddress(), new Color(207, 83, 0))));
				Message in;
				while(((in = readMessage()) != null) && running){
					System.out.println(in.getText());
					pushUpdate(in);
				}
			}
		} catch(java.net.SocketException e){
			System.out.println("Server was pinged by: ");
			System.out.println(socket.getInetAddress().getHostName());
			System.out.println(socket.getInetAddress().getHostAddress());
			MessengerMultiServer.markUseless(serial);
			MessengerMultiServer.killThread(serial);
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public void initialize() throws IOException{
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		System.out.println("Finished");
	}

	public Message readMessage(){
		Message m;
		try{
			m = (Message) ois.readObject();
		} catch(EOFException e){
			m = null; //this could happen and is not an error
		} catch(Exception e){
			m = null;
			System.out.println("Error: ");
			e.printStackTrace();
		}
		return m;
	}

	public void pullMessage(Message m){
		try{
			oos.writeObject(m);
		} catch(IOException e){
			System.out.println("Error:");
			e.printStackTrace();
		}
	}

	public void pushUpdate(Message m){
		MessengerMultiServer.update(this.serial, m);
	}

	public void finish() throws IOException{
		running = false;
		socket.close();
		if(ois != null)
			ois.close();
		if(oos != null)
			oos.close();
	}
}
