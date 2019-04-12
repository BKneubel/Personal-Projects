package earlyBuilds;

import java.io.EOFException;
import java.io.ObjectInputStream;

import main.Message;

public class ReceptionHandler extends Thread{

	private ObjectInputStream ois = null;

	public ReceptionHandler(ObjectInputStream Objectis){
		super("ReceptionHandler");
		ois = Objectis;
	}

	@Override
	public void run(){
		Message in = null;
		Object temp = null;
		while((temp = readObject()) != null){
			if(temp instanceof Message){
				in = (Message) temp;
				MultiThreadClient.printMessage(in);
			}
		}
	}

	public Object readObject(){
		Object o;
		try{
			o = ois.readObject();
		} catch(EOFException e){
			o = null; //this could happen and is not an error
		} catch(Exception e){
			o = null;
			System.out.println("Error: ");
			e.printStackTrace();
		}
		return o;
	}

}
