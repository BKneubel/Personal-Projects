package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{

	
	
	private Handler handler;
	Random r = new Random();
	
	private int timer = 100;
	private int timer2 = 50;
	
	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = -2;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,96,96);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) velY = 0;//stop after 100 ticks
		else timer--;//count down from 100
		
		if(timer <= 0) timer2 --;//once timer is done, start timer 2
		if(timer2 <= 0){//once timer 2 is done, start moving back and forth horizontally
			if(velX == 0) velX = 2;
			if(velX > 0) velX += 0.005f;//boss acceleration per tick
			else if(velX < 0) velX -= 0.005f;
			
			velX = Game.clamp(velX, -10, 10);//clamp boss velocity
			
			int spawn = r.nextInt(10);//higher the number, less frequent bullets
			if(spawn == 0) handler.addObject(new EnemyBossBullet((int)x+48,(int)y+48,ID.BasicEnemy,handler));
		}
		
		//if(y <= 0 || y >= Game.HEIGHT - 48)velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 100)velX *= -1;//X bounce back
		
		//handler.addObject(new Trail(x,y,ID.Trail,Color.red,96,96,0.09f,handler));
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x,(int)y, 96, 96);
		g.setColor(new Color(96,96,96));
		g.fillRect((int)x+4,(int)y+20,10,36);
		g.fillRect((int)x+50,(int)y+20,10,36);
		
		g.setColor(Color.red);
		Polygon triangle = new Polygon();
		triangle.addPoint((int)x+32,(int)y+0);
		triangle.addPoint((int)x+64,(int)y+56);
		triangle.addPoint((int)x+0,(int)y+56);
		g.fillPolygon(triangle);
		
		g.setColor(new Color(96,96,96));
		g.fillRect((int)x+6,(int)y+56,8,8);
		g.fillRect((int)x+28,(int)y+56,8,8);
		g.fillRect((int)x+50,(int)y+56,8,8);
		
		g.setColor(new Color(0,255,255));
		Polygon t = new Polygon();
		t.addPoint((int)x+32,(int)y+10);
		t.addPoint((int)x+24,(int)y+32);
		t.addPoint((int)x+40,(int)y+32);
		g.fillPolygon(t);
		
		g.setColor(new Color(96,96,96));
		g.fillRect((int)x+26,(int)y+36,4,16);
		g.fillRect((int)x+34,(int)y+36,4,16);
	}
	
}
