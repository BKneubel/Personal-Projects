package com.U8_3.main;

import java.awt.Graphics;

public class AbstractShape implements Shape {
	int numSides;
	String shapeName;
	int titleX;
	int titleY;
	int messageX;
	int messageY;
	
	public AbstractShape(int numSides, String shapeName, int titleX, int titleY, int messageX, int messageY){
		this.numSides = numSides;
		this.shapeName = shapeName;
		this.titleX = titleX;
		this.titleY = titleY;
		this.messageX = messageX;
		this.messageY = messageY;
	}
	public AbstractShape(){}
	
	public void drawShape(Graphics g) {}
	
	public void displayName(Graphics g) {
		g.drawString(shapeName, titleX, titleY);
	}
	public void displayNumSides(Graphics g) {
		g.drawString("A " + shapeName + " has " + numSides + " sides.",messageX, messageY);
	}
}
