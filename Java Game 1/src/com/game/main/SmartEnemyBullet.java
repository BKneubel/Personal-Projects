package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

public class SmartEnemyBullet extends GameObject{
	
	private Handler handler;
	
	Random r = new Random();
	
	public SmartEnemyBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = (r.nextInt(5 - -5) + -5);
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,10,10);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGHT - 48)velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16)velX *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		if(x >= Game.WIDTH) handler.removeObject(this);
		if(y < 0) handler.removeObject(this);
		if(x < 0) handler.removeObject(this);
		//handler.addObject(new Trail(x,y,ID.Trail,Color.gray,16,16,0.03f,handler));
		
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval((int)x,(int)y, 10, 10);
		
		
	}
	
}
