package com.U8_3.main;

import java.awt.Graphics;

public class Circle extends AbstractShape{
	public Circle(){
		super(0,"Circle",425,325,525,575);
	}
	@Override
	public void drawShape(Graphics g){
		g.fillOval(475, 350, 200, 200);
	}
}
