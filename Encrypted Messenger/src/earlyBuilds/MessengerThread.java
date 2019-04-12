package earlyBuilds;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import main.Contact;
import main.Message;

public class MessengerThread extends Thread{

	private Socket socket = null;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private int serial;
	private Contact serverContact;

	public MessengerThread(Socket socket, int s){
		super("MessengerThread");
		serial = s;
		this.socket = socket;
		serverContact = new Contact("The Server", socket.getLocalAddress(), null);
	}

	@Override
	public void run(){
		try{
			initialize();
			Message in;
			while((in = readMessage()) != null){
				pushUpdate(in);
			}
			finish();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public void initialize() throws IOException{
		System.out.println(2);
		oos = new ObjectOutputStream(socket.getOutputStream());
		System.out.println(3);
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
		socket.close();
		ois.close();
		oos.close();
	}
}
