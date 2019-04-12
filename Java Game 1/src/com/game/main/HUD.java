package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH = 200;
	public static int P2HEALTH = 200;
	public static int enemyHEALTH = 5000;
	private int greenValue = 255;
	private int P2greenValue = 255;
	
	private int score = 0;
	public static int level = 1;
	public static int level2 = 0;
	public static int money = 0;
	public static int P2money = 0;

	//******************************TICK METHOD********************************//
	public void tick(){
		if(Menu.players == false){//IF SINGLE PLAYER<<<<<<<<<<<<<<<<<<<<<<<<<
			HEALTH = (int) Game.clamp(HEALTH, 0, 200);//CLAMP HEALTH
			greenValue = (int) Game.clamp(greenValue,0,255);
			
			greenValue = HEALTH;
			
			score++;//CONTINUE TO RAISE SCORE
			
			if(KeyInput.spaceDown == true){//IF GUN ACTIVE
				KeyInput.timer++;//ADD TO GUN TIMER
			}
			if(KeyInput.coolDown == true){//IF COOLDOWN
				KeyInput.timer2++;//ADD TO COOLDOWN TIMER
			}
		}
		if(Menu.players == true){//IF MULTI PLAYER<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
			//------------------------------------------------------------//
			HEALTH = (int) Game.clamp(HEALTH, 0, 200);//CLAMP P1 HEALTH
			greenValue = (int) Game.clamp(greenValue,0,255);
			
			greenValue = HEALTH;
			
			score++;//CONTINUE TO RAISE SCORE
			
			if(KeyInput.spaceDown == true){//IF P1 GUN ACTIVE
				KeyInput.timer++;//ADD TO P1 TIMER
			}
			if(KeyInput.coolDown == true){//IF P1 COOLDOWN
				KeyInput.timer2++;//ADD TO COOLDOWN TIMER
			}
		
			//------------------------------------------------------------//
			P2HEALTH = (int) Game.clamp(P2HEALTH, 0, 200);
			P2greenValue = (int) Game.clamp(P2greenValue,0,255);
			
			P2greenValue = P2HEALTH;
			
			
			if(KeyInput.P2spaceDown == true){
				KeyInput.P2timer++;
			}
			if(KeyInput.P2coolDown == true){
				KeyInput.P2timer2++;
			}
			
			//------------------------------------------------------------//
			P2HEALTH = (int) Game.clamp(P2HEALTH, 0, 200);
			P2greenValue = (int) Game.clamp(P2greenValue,0,255);
			P2greenValue = P2HEALTH;	
		}
	}
	
	//******************************RENDER METHOD********************************//
	public void render(Graphics g){
		if(Menu.players == true){//IF MULTI PLAYER<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
			//---------------------------------P1 HEALTH BAR-------------------------------//
			g.setColor(Color.gray);
			g.fillRect(5, 15, 200, 32);
			
			g.setColor(new Color(75,greenValue,0));
			if(HUD.level < 20){
				g.fillRect(5, 15, HEALTH, 32);
			}
			
			if(HUD.level >= 20){//ADD ENERGY BAR AT LEVEL 20
				
				g.fillRect(5, 15, HEALTH, 16);//SHORTTEN HEALTH BAR'S HEIGHT
				
				g.setColor(Color.cyan);//COLOR OF ENERGY BAR
				if(KeyInput.timer2 != 0){//IF COOLDOWN ACTIVE
					if(KeyInput.timer2 <= 400){//AND COOLDOWN IS STILL IN PROGRESS
						g.fillRect(5, 31,(int)(KeyInput.timer2)/2, 16);//FILL BAR
					}else{
						g.fillRect(5, 31,200, 16);//OTHERWISE FILL ENERGY BAR
					}
				}else{//IF COOLDOWN INACTIVE
					g.fillRect(5, 31,2*(100-KeyInput.timer),16);//DECREASE ENERGY BAR ACCORDING TO ENERGY LEFT
				}
			}
			
			g.setColor(Color.white);//HEALTH BAR BORDER
			g.drawRect(5, 15, 200, 32);
			
			//---------------------------------LEVEL 20 INSTRUCTIONS-------------------------------//
			if(level == 20){
				Font f = new Font("Verdana",1,30);
				Font f5 = new Font("Verdana",1,28);
				g.setFont(f5);
				g.drawString("P1 Shoot: \"space\"    P2 Shoot: \"Insert\"",15,400);
			}
			
			//_________________________________SCORE_____________________________//
			g.setColor(Color.MAGENTA);
			Font f = new Font("Verdana",1,16);
			g.setFont(f);
			g.drawString("Score: " + score, 15+4, 64+6+28);
			
			//_________________________________LEVEL_____________________________//
			g.setColor(Color.GREEN);
			if(level < 10){
				g.drawString("Level: " + level, 15+4, 80+6+28);
			}
			if(level >= 20 && SmartEnemy.SmartDead == false){
				g.drawString("Level: 11", 15+4, 80+6+28);
			}
			if(SmartEnemy.SmartDead == true){
				g.drawString("Level: " + (11 + level2), 15+4, 80+6+28);
			}
			else if(level >= 10 && level < 20){
				g.drawString("Level: 10", 15+4, 80+6+28);
			}
			
			//_________________________________FPS_____________________________//
			g.setColor(Color.yellow);
			g.drawString("FPS: " + Game.frames2, 15+4, 440);
			
			//_________________________________ENEMY HEALTH_____________________________//
			g.setColor(Color.red);
			Font f3 = new Font("Verdana",1,24);
			g.setFont(f3);
			if(level >= 20 && enemyHEALTH > 0) g.drawString("Enemy Health: " + enemyHEALTH, 180-40, 100);
			
			//_________________________________P1 COINS_____________________________//
			g.setFont(f);
			g.setColor(Color.yellow);
			g.drawString("P1 Coins: " + money, 15+4, 96+6+28);
			
			//_________________________________SHOP_____________________________//
			Font f2 = new Font("Verdana",1,11);
			g.setFont(f2);
			g.drawString("P1 Heal (\"f\"):                 1 Coin", 422, 30);
			g.drawString("P1 Invincibility (\"e\"):    2 Coins", 422, 48);
			g.drawString("P2 Heal (\"Del\"):             1 Coin", 422, 66);
			g.drawString("P2 Invincibility (\"Ent\"): 2 Coins", 422, 84);
			
			
			//_________________________________PLAYER 1 CHARGE %_____________________________//
			if(level >= 20){
				g.setColor(Color.cyan);
				g.setFont(f);
				if((100-KeyInput.timer) >= 0 && KeyInput.coolDown == false){
					g.drawString("P1 Charge: " + (100-KeyInput.timer) + "%", 150-140, 70);
				}
				else if((100-KeyInput.timer) < 0 && KeyInput.coolDown == false){
					g.drawString("P1 Charge: 0%", 150-140, 70);
				}
				else if(KeyInput.coolDown == true){
					g.drawString("P1 Charge: Reloading", 150-140, 70);
				}
			}
			
			//_________________________________BORDERS_____________________________//
			g.setColor(Color.white);
			g.drawRect(420, 15, 210, 36+36);//SHOP BORDER
			g.drawRect(15, 54+28, 124, 54+20);//SCORE, LEVEL, COINS BORDER
			
			//_________________________________P1 INVINCIBILITY BAR_____________________________//
			if(KeyInput.invinc == true){//IF P1 PURCHASED INVINCIBILITY
				g.setColor(Color.gray);//BAR BACKGROUND
				g.fillRect(128, 400-5, 500, 16);
				
				g.setColor(Color.pink);//BAR
				g.fillRect(128, 400-5, (500-Player.timer), 16);
				
				g.setColor(Color.white);//BORDER
				g.drawRect(128, 400-5, 500, 16);
				
				Font f4 = new Font("Helvetica",1,14);//STRING "P1 INVINCIBILITY"
				g.setFont(f4);
				g.setColor(Color.white);
				g.drawString("P1 Invincibility", 280+220, 424-15);
			}
			
			//_________________________________DEVELOPER MODE_____________________________//
			if(KeyInput.invincibility == true){
				g.setColor(Color.yellow);
				g.setFont(f);
				g.drawString("Dev Mode", 545, 440);
			}
			
			//_________________________________P1 INVINCIBILITY BAR_____________________________//
			if(KeyInput.P2invinc == true){//IF P2 PURCHASED INVINCIBILITY
				g.setColor(Color.gray);//BAR BACKGROUND
				g.fillRect(128, 400+20, 500, 16);
				
				g.setColor(Color.pink);//BAR
				g.fillRect(128, 400+20, (500-Player2.P2timer), 16);
				
				g.setColor(Color.white);//BORDER
				g.drawRect(128, 400+20, 500, 16);
				
				Font f4 = new Font("Helvetica",1,14);//STRING "P2 INVINCIBILITY"
				g.setFont(f4);
				g.setColor(Color.white);
				g.drawString("P2 Invincibility", 280+220, 424+10);
			}
			
			//_________________________________P2 HEALTH BAR_____________________________//
			g.setColor(Color.gray);//BACKGROUND
			g.fillRect(215, 15, 200, 32);
			
			g.setColor(new Color(75,P2greenValue,0));
			
			if(HUD.level < 20){//NORMAL SIZE
				g.fillRect(215, 15, P2HEALTH, 32);
			}
			
			if(HUD.level >= 20){//SHORTENED HEIGHT AFTER LEVEL 20 TO ACCOUNT FOR ENERGY BAR
				g.fillRect(215, 15, P2HEALTH, 16);
				g.setColor(Color.cyan);
				if(KeyInput.P2timer2 != 0){
					if(KeyInput.P2timer2 <= 400){
						g.fillRect(215, 31,(int)(KeyInput.P2timer2)/2, 16);
					}else{
						g.fillRect(215, 31,200, 16);
					}
				}else{
					g.fillRect(215, 31,2*(100-KeyInput.P2timer),16);
				}
			}
			
			g.setColor(Color.white);//HEALTH BAR BORDER
			g.drawRect(215, 15, 200, 32);
			
			//_________________________________P2 COINS_____________________________//
			g.setFont(f);
			g.setColor(Color.yellow);
			g.drawString("P2 Coins: " + P2money, 15+4, 112+6+28);
			
			//_________________________________P2 CHARGE %_____________________________//
			if(level >= 20){
				g.setColor(Color.cyan);
				g.setFont(f);
				if((100-KeyInput.P2timer) >= 0 && KeyInput.P2coolDown == false){
					g.drawString("P2 Charge: " + (100-KeyInput.P2timer) + "%", 150+70, 70);
				}else if((100-KeyInput.P2timer) < 0 && KeyInput.P2coolDown == false){
					g.drawString("P2 Charge: 0%", 150+70, 70);
				}else if(KeyInput.P2coolDown == true){
					g.drawString("P2 Charge: Reloading", 150+70, 70);
				}
			}
			
			
		}
		if(Menu.players == false){//IF SINGLE PLAYER<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			
			//---------------------------------PLAYER HEALTH BAR-------------------------------//
			g.setColor(Color.gray);//BACKGROUND
			g.fillRect(15, 15, 400, 32);
			
			g.setColor(new Color(75,greenValue,0));
			
			if(HUD.level < 20){//NORMAL HEALTH BAR
				g.fillRect(15, 15, HEALTH*2, 32);
			}
			
			if(HUD.level >= 20){//SHORTENED HEALTH BAR
				g.fillRect(15, 15, HEALTH*2, 16);
				g.setColor(Color.cyan);
				if(KeyInput.timer2 != 0){
					if(KeyInput.timer2 <= 400){
						g.fillRect(15, 31,(KeyInput.timer2), 16);
					}else{
						g.fillRect(15, 31,400, 16);
					}
				}else{
					g.fillRect(15, 31,4*(100-KeyInput.timer),16);
				}
			}
			
			g.setColor(Color.white);//BORDER
			g.drawRect(15, 15, 400, 32);
			
			//---------------------------------LEVEL 20 INSTRUCTIONS-------------------------------//
			if(level == 20){
				Font f = new Font("Verdana",1,30);
				g.setFont(f);
				g.drawString("You can now shoot using space bar",15,400);
			}
			
			//_________________________________SCORE_____________________________//
			g.setColor(Color.MAGENTA);
			Font f = new Font("Verdana",1,16);
			g.setFont(f);
			g.drawString("Score: " + score, 15+4, 64+6);
			
			//_________________________________LEVEL_____________________________//
			g.setColor(Color.GREEN);
			if(level < 10){
				g.drawString("Level: " + level, 15+4, 80+6);
			}
			if(level >= 20 && SmartEnemy.SmartDead == false){
				g.drawString("Level: 11", 15+4, 80+6);
			}
			if(SmartEnemy.SmartDead == true){
				g.drawString("Level: " + (11 + level2), 15+4, 80+6);
			}
			else if(level >= 10 && level < 20){
				g.drawString("Level: 10", 15+4, 80+6);
			}
			
			//_________________________________FPS_____________________________//
			g.setColor(Color.yellow);
			g.drawString("FPS: " + Game.frames2, 15+4, 440);
			
			//_________________________________ENEMY HEALTH_____________________________//
			g.setColor(Color.red);
			Font f3 = new Font("Verdana",1,24);
			g.setFont(f3);
			if(level >= 20 && enemyHEALTH > 0) g.drawString("Enemy Health: " + enemyHEALTH, 180, 100);
			
			//_________________________________PLAYER COINS_____________________________//
			g.setFont(f);
			g.setColor(Color.yellow);
			g.drawString("Coins: " + money, 15+4, 96+6);
			
			//_________________________________SHOP_____________________________//
			Font f2 = new Font("Verdana",1,14);
			g.setFont(f2);
			g.drawString("Heal (\"f\"):          1 Coin", 422, 30);
			g.drawString("Invincibility (\"e\"): 2 Coins", 422, 48);
			
			//_________________________________PLAYER CHARGE %_____________________________//
			if(level >= 20){
				g.setColor(Color.cyan);
				g.setFont(f);
				if((100-KeyInput.timer) >= 0 && KeyInput.coolDown == false){
					g.drawString("Charge: " + (100-KeyInput.timer) + "%", 150, 70);
				}
				else if((100-KeyInput.timer) < 0 && KeyInput.coolDown == false){
					g.drawString("Charge: 0%", 150, 70);
				}
				else if(KeyInput.coolDown == true){
					g.drawString("Charge: Reloading", 150, 70);
				}
			}
			
			//_________________________________BORDERS_____________________________//
			g.setColor(Color.white);
			g.drawRect(420, 15, 210, 36);//SHOP BORDER
			g.drawRect(15, 54, 124, 54);//SCORE, LEVEL, AND COINS BORDER
			
			//_________________________________PLAYER INVINCIBILITY BAR_____________________________//
			if(KeyInput.invinc == true){
				g.setColor(Color.gray);
				g.fillRect(128, 400, 500, 32);
				g.setColor(Color.pink);
				g.fillRect(128, 400, (500-Player.timer), 32);
				g.setColor(Color.white);
				g.drawRect(128, 400, 500, 32);
				Font f4 = new Font("Helvetica",1,28);
				g.setFont(f4);
				g.setColor(Color.white);
				g.drawString("Invincibility", 280, 424);
			}
			
			//_________________________________DEVELOPER MODE_____________________________//
			if(KeyInput.invincibility == true){
				g.setColor(Color.yellow);
				g.setFont(f);
				g.drawString("Dev Mode", 545, 440);
			}
		}
	}
	
	//******************************GETTER METHODS********************************//
	public void setScore(int score){
		this.score = score;	
	}
	public int getScore(){
		return score;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int level){
		this.level = level;
	}
}
	