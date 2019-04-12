package com.U8_3.main;

import java.awt.Graphics;
import java.awt.Polygon;

public class Octagon extends AbstractShape{
	public Octagon(){
		super(8,"Octagon",50,325,100,575);
	}
	@Override
	public void drawShape(Graphics g){
		Polygon p = new Polygon();
		p.addPoint(166, 350);
		p.addPoint(234, 350);
		p.addPoint(300, 416);
		p.addPoint(300, 484);
		p.addPoint(234, 550);
		p.addPoint(166, 550);
		p.addPoint(100, 484);
		p.addPoint(100, 416);
		g.fillPolygon(p);
	}
}
