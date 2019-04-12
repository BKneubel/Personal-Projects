package com.testing.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Racquet {

	int x = 0, y = 500, xVel = 0, yVel = 0;
	private Main main;

	public Racquet(Main main) {
		this.main = main;
	}

	public static void main(String[] args) {
		

	}
	
	public void paint(Graphics g) {
		if(Main.s.equals("blue"))g.setColor(Color.blue);
		if(Main.s.equals("green"))g.setColor(Color.green);
		if(Main.s.equals("red"))g.setColor(Color.red);
		g.fillRect(x, y, 50, 5);
	}

	public void keyReleased(KeyEvent e) {
		
		
	}

	public void keyPressed(KeyEvent e) {
		
		
	}

}
