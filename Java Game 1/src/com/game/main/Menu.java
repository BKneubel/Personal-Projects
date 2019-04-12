package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	public static boolean players = false;
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		//play button
		if(game.gameState == STATE.Menu && mouseOver(mx,my,220+200,160-90,200,64)){
			game.gameState = STATE.Game;
			handler.addObject(new Player((Game.WIDTH-32)/2, (Game.HEIGHT-32)/2, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
			
			AudioPlayer.getSound("menu_sound").play();
		}
		//player 2 button
				if(game.gameState == STATE.Menu && mouseOver(mx,my,220+200,160,200,64)){
					game.gameState = STATE.Game;
					players = true;
					handler.addObject(new Player(100, 200, ID.Player, handler));
					handler.addObject(new Player2(200, 200, ID.Player2, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
					
					AudioPlayer.getSound("menu_sound").play();
				}
		//help button
		if(game.gameState == STATE.Menu && mouseOver(mx,my,220+200,250,200,64)){
			game.gameState = STATE.Help;
			
			AudioPlayer.getSound("menu_sound").play();
					
		}
		//back button for help
		if(game.gameState == STATE.Help && mouseOver(mx,my,220,340,200,64)){
			game.gameState = STATE.Menu;
			AudioPlayer.getSound("menu_sound").play();
			return;
			
			
		}
				
		//quit button
		if(game.gameState == STATE.Menu && mouseOver(mx,my,220+200,340,200,64)){
			System.exit(1);//exits, (1 and 0 both work)
		}
		//back button for help
		if(game.gameState == STATE.Help && mouseOver(mx,my,220,340,200,64)){
			game.gameState = STATE.Menu;
			AudioPlayer.getSound("menu_sound").play();
			return;
			
			
		}
		//back button for end
		if(game.gameState == STATE.End && mouseOver(mx,my,220,340,200,64)){
			game.gameState = STATE.Game;
			hud.setLevel(1);//reset level
			hud.setScore(0);//reset score
			HUD.money = 0;
			HUD.P2money = 0;
			HUD.enemyHEALTH = 5000;//reset enemy health
			HUD.level2 = 0;
			Spawn.killEnemies = 0;//reset killEnemies counter
			handler.addObject(new Player((Game.WIDTH-32)/2, (Game.HEIGHT-32)/2, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
			AudioPlayer.getSound("menu_sound").play();
			return;
			
			
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
		
		
		
	}
	
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void tick(){
		
		
		
	}
	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
			Font f = new Font("Verdana", Font.BOLD,30);
			Font f2 = new Font("Verdana", Font.BOLD,30);
			Font f4 = new Font("Verdana", Font.BOLD,24);
			g.setColor(new Color(0,0,60));
			g.fillRect(Game.WIDTH-218,0, 300, Game.HEIGHT);
			
			g.setFont(f);
			g.setColor(Color.white);
			g.drawString("No Man's Sky", 215-120, 40);
			g.drawString("LE 1.1.0", 255-120, 80);
		
			g.setFont(f2);
			
			g.drawRect(200+220,160-90,200,64);
			
			g.drawString("Play",200+290,200-90);
			
			g.setFont(f4);
			g.drawRect(200+220,160,200,64);
			g.drawString("2-Player(WIP)",132+290,200);
		
			g.setFont(f2);
			
			g.drawRect(200+220,250,200,64);
			g.drawString("Help",200+290,290);
		
			g.drawRect(200+220,340,200,64);
			g.drawString("Quit",200+290,380);
			
			g.fillRect(Game.WIDTH-225, 0, 7, Game.HEIGHT);
			
			//spaceship 1
			
			g.setColor(new Color(96,96,96));
			g.fillRect(156+4-120,32+20,10,36);
			g.fillRect(156+50-120,32+20,10,36);
			
			g.setColor(new Color(192,192,192));
			Polygon triangle = new Polygon();
			triangle.addPoint(156+32-120,32+0);
			triangle.addPoint(156+64-120,32+56);
			triangle.addPoint(156+0-120,32+56);
			g.fillPolygon(triangle);
			
			g.setColor(new Color(96,96,96));
			g.fillRect(156+6-120,32+56,8,8);
			g.fillRect(156+28-120,32+56,8,8);
			g.fillRect(156+50-120,32+56,8,8);
			
			g.setColor(new Color(0,255,255));
			Polygon t = new Polygon();
			t.addPoint(156+32-120,32+10);
			t.addPoint(156+24-120,32+32);
			t.addPoint(156+40-120,32+32);
			g.fillPolygon(t);
			
			g.setColor(new Color(96,96,96));
			g.fillRect(156+26-120,32+36,4,16);
			g.fillRect(156+34-120,32+36,4,16);
			
			//spaceship 2
			
			g.setColor(new Color(96,96,96));
			g.fillRect(420+4-120,32+20,10,36);
			g.fillRect(420+50-120,32+20,10,36);
			
			g.setColor(Color.red);
			Polygon tri = new Polygon();
			tri.addPoint(420+32-120,32+0);
			tri.addPoint(420+64-120,32+56);
			tri.addPoint(420+0-120,32+56);
			g.fillPolygon(tri);
			
			g.setColor(new Color(96,96,96));
			g.fillRect(420+6-120,32+56,8,8);
			g.fillRect(420+28-120,32+56,8,8);
			g.fillRect(420+50-120,32+56,8,8);
			
			g.setColor(new Color(0,255,255));
			Polygon tria = new Polygon();
			tria.addPoint(420+32-120,32+10);
			tria.addPoint(420+24-120,32+32);
			tria.addPoint(420+40-120,32+32);
			g.fillPolygon(tria);
			
			g.setColor(new Color(96,96,96));
			g.fillRect(420+26-120,32+36,4,16);
			g.fillRect(420+34-120,32+36,4,16);
			
		}
		else if(game.gameState == STATE.Help){
			Font f = new Font("Verdana", Font.BOLD,50);
		
			g.setFont(f);
			g.setColor(Color.white);
			g.drawString("Help", 250, 80);
			
			Font f2 = new Font("Verdana", Font.BOLD,30);
			g.setFont(f2);
			g.drawRect(220,340,200,64);
			g.drawString("Back",290,380);
			
			Font f3 = new Font("Verdana", Font.BOLD,22);
			g.setFont(f3);
			g.drawString("Use WASD keys to move player and avoid enemies", 2, 200);
			
		}
		else if(game.gameState == STATE.End && Menu.players == false){
			Font f = new Font("Verdana", Font.BOLD,50);
		
			g.setFont(f);
			g.setColor(Color.white);
			g.drawString("Game Over",162, 80);
			
			Font f2 = new Font("Verdana", Font.BOLD,30);
			g.setFont(f2);
			g.drawRect(220,340,200,64);
			g.drawString("Try Again",242,380);
			
			Font f3 = new Font("Verdana", Font.BOLD,22);
			g.setFont(f3);
			g.drawString("You Lost with a score of: " + hud.getScore(),125, 200);
			
		}
		else if(game.gameState == STATE.End && Menu.players == true){
			Font f = new Font("Verdana", Font.BOLD,50);
		
			g.setFont(f);
			g.setColor(Color.white);
			if(Game.playerOneWins == true){
				g.drawString("Player 1 Wins",140, 80);
			}
			else if(Game.playerOneWins == false){
				g.drawString("Player 2 Wins",140, 80);
			}
			
			Font f2 = new Font("Verdana", Font.BOLD,30);
			g.setFont(f2);
			g.drawRect(220,340,200,64);
			g.drawString("Try Again",242,380);
			
			Font f3 = new Font("Verdana", Font.BOLD,22);
			g.setFont(f3);
			g.drawString("Game Ended with a Score of: " + hud.getScore(),125, 200);
			
		}
	}
		

}
