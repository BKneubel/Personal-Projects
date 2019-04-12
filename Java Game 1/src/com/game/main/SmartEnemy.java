package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class SmartEnemy extends GameObject{

	
	
	private Handler handler;
	private GameObject player;
	
	Random r = new Random();
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public static boolean SmartDead = false;
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		
		
		//Find player object, set to player variable so we can use in this class
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		
		
		
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8; //subtract 8 so doesn't go to far corner
		float diffY = y - player.getY() - 8;
		//distance from player
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		
		velX = (-2/distance)*diffX;//larger the value, faster the enemy
		velY = (-2/distance)*diffY;//larger the value, faster the enemy
		
		//if(y <= 0 || y >= Game.HEIGHT - 48)velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16)velX *= -1;
		
		//handler.addObject(new Trail(x,y,ID.Trail,Color.green,16,16,0.03f,handler));
		collision2();
		
		
		if(HUD.enemyHEALTH <= 0){
			handler.removeObject(this);
			SmartDead = true;
		}
		
		
		int spawn = r.nextInt(20);//higher the number, less frequent bullets
		if(spawn == 0) handler.addObject(new SmartEnemyBullet((int)x+16,(int)y+16,ID.BasicEnemy,handler));
	}

	private void collision2() {
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.PlayerBullet){//temp object is now player bullet
				if(getBounds().intersects(tempObject.getBounds())){
					//collision code
					HUD.enemyHEALTH -= 4;//damage taken
				}
			}
		}
				
	}

	public void render(Graphics g) {
		//g.setColor(Color.green);
		//g.fillRect((int)x,(int)y, 16, 16);
		g.setColor(new Color(96,96,96));
		g.fillRect((int)x+2,(int)y+10,5,18);
		g.fillRect((int)x+25,(int)y+10,5,18);
		
		g.setColor(Color.red);
		Polygon triangle = new Polygon();
		triangle.addPoint((int)x + 16,(int)y);
		triangle.addPoint((int)x + 32,(int)y + 28);
		triangle.addPoint((int)x,(int)y + 28);
		g.fillPolygon(triangle);
		
		g.setColor(new Color(96,96,96));
		g.fillRect((int)x+3,(int)y+28,4,4);
		g.fillRect((int)x+14,(int)y+28,4,4);
		g.fillRect((int)x+25,(int)y+28,4,4);
		
		g.setColor(new Color(0,255,255));
		Polygon t = new Polygon();
		t.addPoint((int)x+16,(int)y+5);
		t.addPoint((int)x+12,(int)y+16);
		t.addPoint((int)x+20,(int)y+16);
		g.fillPolygon(t);
		
		g.setColor(new Color(96,96,96));
		g.fillRect((int)x+13,(int)y+18,2,8);
		g.fillRect((int)x+17,(int)y+18,2,8);
		
	}
	
}
