package com.game.main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];//array (made to avoid sticky keys)
	private boolean[] P2keyDown = new boolean[4];
	public static boolean spaceDown = false;
	public static boolean coolDown = false;
	
	public static boolean P2spaceDown = false;
	public static boolean P2coolDown = false;
	
	
	public static int timer = 0;
	public static int timer2 = 0;
	
	public static int P2timer = 0;
	public static int P2timer2 = 0;
	
	
	
	public static boolean invinc = false;
	public static boolean invincibility = false;
	
	public static boolean P2invinc = false;
	Game game;
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		
		
		this.game = game;
		
		keyDown[0]=false;
		keyDown[1]=false;
		keyDown[2]=false;
		keyDown[3]=false;
		
		P2keyDown[0]=false;
		P2keyDown[1]=false;
		P2keyDown[2]=false;
		P2keyDown[3]=false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player2){
				if(key == KeyEvent.VK_UP){tempObject.setVelY(-5); P2keyDown[0]=true;}
				if(key == KeyEvent.VK_DOWN){tempObject.setVelY(5); P2keyDown[1]=true;}
				if(key == KeyEvent.VK_RIGHT){tempObject.setVelX(5); P2keyDown[2]=true;}
				if(key == KeyEvent.VK_LEFT){tempObject.setVelX(-5); P2keyDown[3]=true;}
			}
			if(tempObject.getId() == ID.Player){//if player1
				//key events for player 1
				if(key == KeyEvent.VK_W){tempObject.setVelY(-5); keyDown[0]=true;}
				if(key == KeyEvent.VK_S){tempObject.setVelY(5); keyDown[1]=true;}
				if(key == KeyEvent.VK_D){tempObject.setVelX(5); keyDown[2]=true;}
				if(key == KeyEvent.VK_A){tempObject.setVelX(-5); keyDown[3]=true;}
				
				if(key == KeyEvent.VK_SPACE && timer <= 100){spaceDown = true; coolDown = false;}
				
				if(key == KeyEvent.VK_INSERT && P2timer <= 100){P2spaceDown = true; P2coolDown = false;}
				
				if(timer > 100 || (100-KeyInput.timer) <= 0){//if you shoot weapon for 100 ticks
					coolDown = true;//issue cool down
				}
				if(timer2 > 400){//once cool down surpasses 400 ticks
					timer = 0;//reset weapon timer
					coolDown = false;//end cool down
				}
				if(coolDown == false){//once cool down ends
					timer2 = 0;//reset cool down timer
				}
				if((100-KeyInput.timer) < 0){//if timer passes 100
					spaceDown = false;//spaceDown must be false
				}
				
				
				
				
				if(P2timer > 100 || (100-KeyInput.P2timer) <= 0){//if you shoot weapon for 100 ticks
					P2coolDown = true;//issue cool down
				}
				if(P2timer2 > 400){//once cool down surpasses 400 ticks
					P2timer = 0;//reset weapon timer
					P2coolDown = false;//end cool down
				}
				if(P2coolDown == false){//once cool down ends
					P2timer2 = 0;//reset cool down timer
				}
				if((100-KeyInput.P2timer) < 0){//if timer passes 100
					P2spaceDown = false;//spaceDown must be false
				}
				
				
				
				
				
				
				
				
				if(key == KeyEvent.VK_F && HUD.money > 0){//when f is pressed
					HUD.HEALTH = 200;//heal
					AudioPlayer.getSound("heal").play();
					HUD.money -= 1;//remove 1 coin
				}
				if(key == KeyEvent.VK_E && HUD.money > 1){//when e is pressed
					HUD.money -= 2;//subtract 2 coins
					AudioPlayer.getSound("shields").play();
					invinc = true;//activate temporary invincibility
				}
				
				
				if(key == KeyEvent.VK_DELETE && HUD.P2money > 0){//when f is pressed
					HUD.P2HEALTH = 200;//heal
					AudioPlayer.getSound("heal").play();
					HUD.P2money -= 1;//remove 1 coin
				}
				if(key == KeyEvent.VK_ENTER && HUD.P2money > 1){//when e is pressed
					HUD.P2money -= 2;//subtract 2 coins
					AudioPlayer.getSound("shields").play();
					P2invinc = true;//activate temporary invincibility
				}
				
				
				
				
				if (spaceDown == true && HUD.level >= 20){
					AudioPlayer.getSound("laser").play();
				}
				if (P2spaceDown == true && HUD.level >= 20){
					AudioPlayer.getSound("laser").play();
				}
			}
			
		}
		if(key == KeyEvent.VK_P){
			if(game.gameState == Game.STATE.Game){
				
				if(Game.paused) Game.paused = false;
				else Game.paused = true;
			}
		}
		
		if(key == KeyEvent.VK_SHIFT){invincibility = true;}//if shift is pressed activate permanent invincibility
		if(key == KeyEvent.VK_CONTROL){invincibility = false;}//if control is pressed disable permanent invincibility
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);//if esc is pressed exit the system
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player2){
				if(key == KeyEvent.VK_UP) P2keyDown[0]=false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN) P2keyDown[1]=false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_RIGHT) P2keyDown[2]=false; //tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT) P2keyDown[3]=false; //tempObject.setVelX(0);
				
				if(!P2keyDown[0] && !P2keyDown[1]) tempObject.setVelY(0);
				if(!P2keyDown[2] && !P2keyDown[3]) tempObject.setVelX(0);
				
				if(key == KeyEvent.VK_SPACE){spaceDown = false;}
			}
			if(tempObject.getId() == ID.Player){
				//key events for player 1
				if(key == KeyEvent.VK_W) keyDown[0]=false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1]=false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) keyDown[2]=false; //tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) keyDown[3]=false; //tempObject.setVelX(0);
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
				
				if(key == KeyEvent.VK_SPACE){spaceDown = false;}
				if(key == KeyEvent.VK_INSERT){P2spaceDown = false;}
			}
			
		}
		
		
		
	}
}
