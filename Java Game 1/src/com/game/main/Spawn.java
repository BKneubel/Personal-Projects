package com.game.main;

import java.util.Random;

import com.game.main.Game.STATE;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	public static int killEnemies = 0;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	public void tick(){
		scoreKeep++;
		killEnemies++;
		if (killEnemies == 1){
			handler.clearEnemies();
		}
		if(scoreKeep >= 250){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			if(SmartEnemy.SmartDead == true){
				HUD.level2++;
			}
			if(hud.getLevel() == 1){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
			}
			if(hud.getLevel() == 2){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.Coin,handler));
			}
			if(hud.getLevel() == 3){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.Coin,handler));
			}
			if(hud.getLevel() == 4){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.Coin,handler));
			}
			if(hud.getLevel() == 5){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.Coin,handler));
			}
			if(hud.getLevel() == 6){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.Coin,handler));
			}
			if(hud.getLevel() == 7){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.Coin,handler));
			}
			if(hud.getLevel() == 8){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.Coin,handler));
			}
			if(hud.getLevel() == 9){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
				handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.Coin,handler));
			}
			if(hud.getLevel() == 10){
				handler.clearEnemies();
				handler.addObject(new EnemyBoss((Game.WIDTH/2)-48,520,ID.EnemyBoss,handler));
			}
			if(hud.getLevel() == 20){
				handler.clearEnemies();
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.SmartEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 1){
				handler.clearEnemies();
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 2){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 3){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 4){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 5){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 6){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 7){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 8){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			if(HUD.level > 20 && SmartEnemy.SmartDead == true && HUD.level2 == 9){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.FastEnemy,handler));
			}
			
		}
		
	}
}
