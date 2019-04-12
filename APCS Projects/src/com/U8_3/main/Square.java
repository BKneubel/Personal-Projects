package com.U8_3.main;

import java.awt.Graphics;

public class Square extends AbstractShape{
	public Square(){
		super(4,"Square",50,50,100,275);
	}
	@Override
	public void drawShape(Graphics g){
		g.fillRect(100, 50, 200, 200);
	}
}
