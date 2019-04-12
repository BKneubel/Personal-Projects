package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin extends GameObject{

	
	
	private Handler handler;
	
	public Coin(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 0;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGHT - 48)velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16)velX *= -1;
		
		//handler.addObject(new Trail(x,y,ID.Trail,new Color(153,76,0),16,16,0.03f,handler));
		if(Player.money == true){
			handler.removeObject(this);
			Player.money = false;
		}
		if(Player2.P2money == true){
			handler.removeObject(this);
			Player2.P2money = false;
		}
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillOval((int)x,(int)y, 16, 16);
		g.setColor(Color.yellow);
		g.fillOval((int)x+0,(int)y+0,16,16);
		
		
		
	}
	
}
