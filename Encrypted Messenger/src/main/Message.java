package main;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable{

	private static final long serialVersionUID = -3583192461807198282L;
	private String text;
	private Contact contact;
	private Date time;

	public Message(String m, Contact c, Date d){
		text = m;
		contact = c;
		time = d;
	}

	public Message(String m, Contact c){
		text = m;
		contact = c;
		time = new Date();
	}

	public String getText(){
		return text;
	}

	public Contact getSender(){
		return contact;
	}

	public Date getTime(){
		return time;
	}

	@Override
	public String toString(){
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		return "$IP:" + contact.getName() + "T:" + df.format(time) + "$" + text;
	}

}
