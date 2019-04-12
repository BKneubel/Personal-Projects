package main;

import java.awt.Color;
import java.io.Serializable;
import java.net.InetAddress;

public class Contact implements Serializable{

	private static final long serialVersionUID = 5326503325979129164L;
	private Color color;
	private InetAddress address;
	private String name;

	public Contact(String n, InetAddress i, Color c){
		name = n;
		address = i;
		color = c;
	}

	public String getName(){
		return name;
	}

	public Color getColor(){
		return color;
	}

	public InetAddress getAddress(){
		return address;
	}

}
