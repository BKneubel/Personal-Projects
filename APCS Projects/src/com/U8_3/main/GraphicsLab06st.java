package com.U8_3.main;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GraphicsLab06st {
	public static void main(String args[]){
		Windows win = new Windows();
		win.setSize(800,600);
		win.addWindowListener(new WindowAdapter() {public void
		windowClosing(WindowEvent e) {System.exit(0);}});
		win.show();
	}
}

class Windows extends Frame{
	public void paint(Graphics g){
		ArrayList<AbstractShape> shapes = new ArrayList<AbstractShape>();
		shapes.add(new Square());
		shapes.add(new Triangle());
		shapes.add(new Octagon());
		shapes.add(new Circle());

		drawGrid(g);

		for(AbstractShape shape: shapes){
			shape.drawShape(g);
			shape.displayName(g);
			shape.displayNumSides(g);
		}
	}
	public void drawGrid(Graphics g){
		g.drawLine(0,300,800,300);
		g.drawLine(400,0,400,600);
	}
}

