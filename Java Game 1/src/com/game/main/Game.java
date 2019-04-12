package com.game.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = -473349850293143017L;
	public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9;//height is 480
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	public static boolean playerOneWins = false;
	private Random r = new Random();
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public static int frames2;//declares variable to display in HUD
	
	//******************************DECLARING GAME STATES********************************//
	public enum STATE{//Declaring game states
		Menu,
		Help,
		End,
		Game2,
		Game
	};
	
	//******************************STARTING STATE IS MENU********************************//
	public static STATE gameState = STATE.Menu;
	
	//******************************GAME METHOD********************************//
	public Game(){
		handler = new Handler();//ADDS HANDLER
		hud = new HUD();//ADDS HUD
		menu = new Menu(this, handler,hud);//ADDS MENU
		this.addKeyListener(new KeyInput(handler,this));//ADDS KEY LISTENER
		this.addMouseListener(menu);//ADDS MOUSE LISTENER
		
		AudioPlayer.load();//LOADS AUDIO PLAYER CLASS
		
		AudioPlayer.getMusic("music").loop();//LOOPS BACKGROUND MUSIC
		
		new Window(WIDTH,HEIGHT,"No Man's Sky LE 1.1.0",this);//ADDS WINDOW
		
		spawner = new Spawn(handler,hud);//ADDS SPAWNER
		r = new Random();
		if(gameState == STATE.Game){//SPAWNS ONCE GAME STARTS
			//SPAWNS PLAYER AND ENEMY
			handler.addObject(new Player((WIDTH-32)/2, (HEIGHT-32)/2, ID.Player, handler));//INDICATE STARTING P1 POS
		
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.BasicEnemy,handler));
		}else{
			for(int i = 0; i <10; i++){//SPAWNS 10 MENU PARTICLES IN ALL STATES BUT MENU
				handler.addObject(new MenuParticle(r.nextInt(WIDTH-240),r.nextInt(HEIGHT-48),ID.MenuParticle,handler));
			}
		}
	}
	
	//******************************START METHOD********************************//
	public synchronized void start(){
		thread = new Thread(this);//ADDS THREAD, DECLARED PRIVATE ABOVE
		thread.start();//STARTS THREAD
		running = true;//BOOLEAN RUNNING EQUAL TO TRUE
	}
	
	//******************************STOP METHOD********************************//
	public synchronized void stop(){
		try{//TRY CATCH
			thread.join();//STOPS THREAD
			running = false;//BOOL RUNNING FALSE
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//******************************FRAME RATE********************************//
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now  = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS:" + frames);
				frames2 = frames;//VARIABLE TO DISPLAY ON HUD
				frames = 0;
			}
		}
		stop();
	}
	
	//******************************TICK METHOD********************************//
	private void tick(){
		if(gameState == STATE.Game){//DISPLAY HUD AND BEGIN SPAWNING WHEN GAME STARTS
			if(!paused){//NOT PAUSED
				hud.tick();
				spawner.tick();
				handler.tick();
				
				if(HUD.HEALTH <= 0 && Menu.players == false){//END OF GAME (IF ONE PLAYER MODE)
					HUD.HEALTH = 200;//RESET P1 HEALTH
					gameState = STATE.End;//DISPLAY END STATE
					handler.clearEnemies();//CLEAR ENEMIES
					for(int i = 0; i <20; i++){//SPAWNS 10 PARTICLES ON MENU SCREEN
						handler.addObject(new MenuParticle(r.nextInt(WIDTH-16),r.nextInt(HEIGHT-48),ID.MenuParticle,handler));
					}
				}
				if(HUD.HEALTH <= 0 && Menu.players == true){//END OF GAME (TWO PLAYER MODE, P1 DIES FIRST)
					HUD.HEALTH = 200;//RESET P1 HEALTH
					HUD.P2HEALTH = 200;//RESET P2 HEALTH
					gameState = STATE.End;//DISPLAY END MENU
					handler.clearEnemies();//CLEAR ENEMIES
					playerOneWins = false;//PLAYER ONE LOSES, BOOL TBA IN MENU
					for(int i = 0; i <20; i++){//SPAWNS 10 PARTICLES ON MENU SCREEN
						handler.addObject(new MenuParticle(r.nextInt(WIDTH-16),r.nextInt(HEIGHT-48),ID.MenuParticle,handler));
					}
				}
				if(HUD.P2HEALTH <= 0 && Menu.players == true){//end of game(health at zero
					HUD.HEALTH = 200;//RESET P1 HEALTH
					HUD.P2HEALTH = 200;//RESET P2 HEALTH
					gameState = STATE.End;//DISPLAY END MENU
					handler.clearEnemies();//CLEAR ENEMIES
					playerOneWins = true;//PLAYER ONE WINS, BOOL TBA IN MENU
					for(int i = 0; i <20; i++){//SPAWNS 10 PARTICLES ON MENU SCREEN
						handler.addObject(new MenuParticle(r.nextInt(WIDTH-16),r.nextInt(HEIGHT-48),ID.MenuParticle,handler));
					}
				}
			}
		}
		else if(gameState == STATE.Menu || gameState == STATE.End){//PAUSE MENU
			menu.tick();//STILL RUN MENU
			handler.tick();//STILL RUN HANDLER
		}
	}
	
	//******************************GRAPHICS METHOD********************************//
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();//BUFFER STRATEGY CLASS
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//---------------------------------BACKGROUND COLOR-------------------------------//
		g.setColor(new Color(0,0,60));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//---------------------------------STARS-------------------------------//
		g.setColor(Color.white);
		g.fillOval(34,76,5,5);g.fillOval(56,325,5,5);g.fillOval(124,23,5,5);g.fillOval(437,463,5,5);
		g.fillOval(264,125,5,5);g.fillOval(500,321,5,5);g.fillOval(476,69,5,5);g.fillOval(43,278,5,5);
		g.fillOval(100,215,5,5);g.fillOval(300,286,5,5);g.fillOval(413,215,5,5);g.fillOval(14,363,5,5);
		g.fillOval(214,31,5,5);g.fillOval(251,380,5,5);g.fillOval(485,186,5,5);g.fillOval(590,120,5,5);
		g.fillOval(125,467,5,5);g.fillOval(80,300,5,5);
		//g.fillOval(r.nextInt(WIDTH),r.nextInt(HEIGHT),5,5); -- blinking stars
		
		//---------------------------------PLANETS-------------------------------//
		g.setColor(new Color(102,102,0));//SATURN
		g.fillOval(100,100,100,100);
		g.setColor(new Color(51,51,0));//RINGS
		g.drawArc(75,140,150,20,135,270);g.drawArc(70,140,160,21,135,270);g.drawArc(65,140,170,22,135,270);
		g.drawArc(60,140,180,23,135,270);g.drawArc(55,140,190,24,135,270);g.drawArc(50,140,200,25,135,270);
		g.setColor(new Color(153,76,0));//PLANET 1
		g.fillOval(500,150,64,64);
		
		handler.render(g);
	
		//---------------------------------PAUSE SCREEN-------------------------------//
		if(paused){
			Font f = new Font("Verdana", Font.BOLD,50);
			g.setFont(f);
			g.setColor(Color.white);
			g.drawString("Paused", 220, 200);
		}
		
		if(gameState == STATE.Game){//ONLY RENDER HUD IF IN GAME
			hud.render(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){//ONLY RENDER MENU IF IN MENU, HELP, OR END
			menu.render(g);
		}
		g.dispose();
		bs.show();
		
	}
	
	//******************************CLAMP METHOD********************************//
	public static float clamp(float var, float min, float max){
		if(var >= max) return var = max;
		else if(var <= min) return var = min;
		else return var;
	}
	
	//******************************MAIN METHOD********************************//
	public static void main(String[]args){
		new Game();
	}
}
