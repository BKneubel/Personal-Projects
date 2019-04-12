package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

public class Player2 extends GameObject{
	

	Random r = new Random();
	Handler handler;
	public static boolean P2money = false;
	public static int P2timer = 0;
	
	public Player2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);//x border
		y = Game.clamp(y, 0, Game.HEIGHT - 67);//y borders
		
		collision();
		
		if(KeyInput.P2spaceDown == true && HUD.level >= 20){
			handler.addObject(new PlayerBullet((int)x+25,(int)y+8,ID.PlayerBullet,handler));
			handler.addObject(new PlayerBullet((int)x+2,(int)y+8,ID.PlayerBullet,handler));
		}
		//handler.addObject(new Trail(x,y,ID.Trail,Color.white,32,32,0.05f,handler));
		if(KeyInput.P2invinc == true){
			P2timer++;
		}
		if(P2timer > 500){
			KeyInput.P2invinc = false;
			P2timer = 0;
		}
	}

	private void collision() {
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(KeyInput.P2invinc == false && KeyInput.invincibility == false){
				if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss){//temp object is now basic enemy
					if(getBounds().intersects(tempObject.getBounds())){
						//collision code
						HUD.P2HEALTH -= 4;//damage taken
						AudioPlayer.getSound("hit").play();
					}
				}
			}
			else if(KeyInput.P2invinc == true || KeyInput.invincibility == true){
				if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss){//temp object is now basic enemy
					if(getBounds().intersects(tempObject.getBounds())){
						//collision code
						HUD.P2HEALTH -= 0;//damage taken
					}
				}
			}
			if(tempObject.getId() == ID.Coin){//temp object is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())){
					//collision code
					HUD.P2money++;
					AudioPlayer.getSound("coin").play();
					P2money = true;
				}
			}
		}
	}
	
		
		
	

	public void render(Graphics g) {
		
		//Graphics2D g2d = (Graphics2D) g;
		
		//g.setColor(Color.red); 
		//g2d.draw(getBounds()); shows boundaries
		
		g.setColor(Color.white);
		//g.fillRect((int)x,(int)y,32,32);
		
		
		
		g.setColor(new Color(96,96,96));
		g.fillRect((int)x+2,(int)y+10,5,18);
		g.fillRect((int)x+25,(int)y+10,5,18);
		
		g.setColor(Color.blue);
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
