package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	
	
	private Handler handler;
	
	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 5;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 48)velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16)velX *= -1;
		
		//handler.addObject(new Trail(x,y,ID.Trail,new Color(153,76,0),16,16,0.03f,handler));
		handler.addObject(new Trail(x,y,ID.Trail,Color.white,16,16,0.03f,handler));
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillOval((int)x,(int)y, 16, 16);
		//g.setColor(new Color(153,76,0));
		g.setColor(Color.white);
		g.fillOval((int)x+0,(int)y+0,16,16);
		
		g.setColor(new Color(204,102,0));
		g.fillOval((int)x+6,(int)y+6,2,2);
		g.fillOval((int)x+5,(int)y+12,2,2);
		g.fillOval((int)x+11,(int)y+7,2,2);
		g.fillOval((int)x+12,(int)y+8,2,2);
		g.fillOval((int)x+8,(int)y+3,2,2);
		g.fillOval((int)x+3,(int)y+10,2,2);
		
	}
	
}
