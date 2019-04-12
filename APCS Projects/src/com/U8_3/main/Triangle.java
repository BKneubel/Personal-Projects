package com.U8_3.main;

import java.awt.Graphics;
import java.awt.Polygon;

public class Triangle extends AbstractShape {
	public Triangle(){
		super(3,"Triangle",425,50,525,275);
	}
	@Override
	public void drawShape(Graphics g){
		Polygon p = new Polygon();
		p.addPoint(475, 250);
		p.addPoint(675, 250);
		p.addPoint(575, 50);
		g.fillPolygon(p);
	}
}
