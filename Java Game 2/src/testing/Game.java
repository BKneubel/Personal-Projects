package testing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Game extends JPanel implements KeyListener{
	
	private static final long serialVersionUID = 1L;

	public static int frameWidth = 1280; public static int frameHeight = 720;
	
	public static int playerX = frameWidth/2; public static int playerY = frameHeight/2 + 200;
	public static int playerWidth = 50; public static int playerHeight = 50;
	public static double playerXvelocity; public static double playerYvelocity;
	public static boolean playerJumped = false;
	public static boolean playerPowerUp = false; public static int playerSizeVelocity;
	public static Rectangle r; public static boolean playerShot = false;
	public static boolean playerRightFace = true;
	
	
	public static int enemyX = frameWidth/2 + 100; public static int enemyY = frameHeight/2 + 200;
	public static int enemyWidth = 50; public static int enemyHeight = 50;
	public static double enemyXvelocity = 0; public static double enemyYvelocity = 0;
	public static boolean enemyJumped = false;
	public static Rectangle p;
	
	
	public static int playerHealth = 400;
	public static int enemyHealth = 400;
	public static double playerSpeed; public static double enemySpeed;
	
	
	
	public static int playerProjX= playerX + playerWidth/2; public static int playerProjY = playerY + playerHeight/2;;
	public static int playerProjXvelocity; public static int playerProjYvelocity;
	
	public static double backgroundR = 10,backgroundG = 130,backgroundB = 250;
	public static boolean red = true, green = true, blue = true;
	
	
	
	public Game(){
		addKeyListener(this);
	}
	public void moveBall(){
		if(playerRightFace) playerProjXvelocity = 10;
		else playerProjXvelocity = -10;
		
		
		if(playerX < 0){ playerX = 0; playerXvelocity = 0; playerHealth -= 50;}
		if(playerY < 0){ playerY = 0; playerYvelocity = 0;}
		if(playerX > 1280){ playerX = 1280 - 100; playerXvelocity = 0; playerHealth -= 50;} //REMOVE 100 FROM HERE
		if(playerY > 720){ playerY = 720; playerYvelocity = 0;}
		playerX+=  playerXvelocity;
		playerY+=  playerYvelocity;
		playerWidth += playerSizeVelocity;
		playerHeight += playerSizeVelocity;
		if(playerWidth >= 100){ playerSizeVelocity = 0; playerWidth = 100; playerHeight = 100;}
		if(playerWidth <= 50){ playerSizeVelocity = 0; playerWidth = 50; playerHeight = 50;}
		//****************PLAYER OVER PLATFORM?***********************************//
		int playerPlatformHeight = frameHeight/2 - playerWidth + 50;
		int playerGroundHeight = frameHeight/2 - playerWidth + 50 + 200;
		boolean playerOverPlatform;
		if(playerX >= frameWidth/6 * 2 && playerX <= frameWidth/6 * 4) playerOverPlatform = true;
		else playerOverPlatform = false;
		
		if(playerY < playerGroundHeight && !playerOverPlatform) playerYvelocity++;
		else if(!playerOverPlatform) {playerYvelocity = 0; playerY = playerGroundHeight;}

		if(playerY > playerPlatformHeight && playerY < playerGroundHeight && playerOverPlatform) playerYvelocity++;
		else if(playerY >= playerGroundHeight && playerOverPlatform){playerYvelocity = 0; playerY = playerGroundHeight;}
		
		if(playerY < playerPlatformHeight && playerOverPlatform) playerYvelocity++;
		else if(playerY >= playerPlatformHeight && playerOverPlatform && playerY < playerPlatformHeight + 20){playerYvelocity = 0; playerY = playerPlatformHeight;}
		
		
		//******************ENEMY OVER PLATFORM?***********************************//
		int enemyPlatformHeight = frameHeight/2;
		int enemyGroundHeight = frameHeight/2 + 200;
		if(enemyX < 0){ enemyX = 0; enemyXvelocity = 0; enemyHealth -= 50;}
		if(enemyY < 0){ enemyY = 0; enemyYvelocity = 0;}
		if(enemyX > 1280){ enemyX = 1280 - 100 ; enemyXvelocity = 0; enemyHealth -= 50;}// REMOVE 100 FROM HERE
		if(enemyY > 720){ enemyY = 720; enemyYvelocity = 0;}
		enemyX+=  enemyXvelocity;
		enemyY+=  enemyYvelocity;
		boolean enemyOverPlatform;
		if(enemyX >= frameWidth/6 * 2 && enemyX <= frameWidth/6 * 4) enemyOverPlatform = true;
		else enemyOverPlatform = false;
		
		if(enemyY < enemyGroundHeight && !enemyOverPlatform) enemyYvelocity++;
		else if(!enemyOverPlatform) {enemyYvelocity = 0; enemyY = enemyGroundHeight;}

		if(enemyY > enemyPlatformHeight && enemyY < enemyGroundHeight && enemyOverPlatform) enemyYvelocity++;
		else if(enemyY >= enemyGroundHeight && enemyOverPlatform){enemyYvelocity = 0; enemyY = enemyGroundHeight;}
		
		if(enemyY < enemyPlatformHeight && enemyOverPlatform) enemyYvelocity++;
		else if(enemyY >= enemyPlatformHeight && enemyOverPlatform && enemyY < enemyPlatformHeight + 25){enemyYvelocity = 0; enemyY = enemyPlatformHeight;}
		
		//**********************CALCULATE SPEEDS***************************************//
		playerSpeed = Math.sqrt(Math.pow((double)playerXvelocity, 2) + Math.pow((double)playerYvelocity, 2));
		enemySpeed = Math.sqrt(Math.pow((double)enemyXvelocity, 2) + Math.pow((double)enemyYvelocity, 2));
		
		r = new Rectangle(playerX, playerY, playerWidth, playerHeight);
		p = new Rectangle(enemyX, enemyY, enemyWidth, enemyHeight);
		
		playerProjX += playerProjXvelocity;
		playerProjY += playerProjYvelocity;
		
		
		
	}
	
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, frameWidth, frameHeight);
		g.setColor(Color.BLACK);
	      
		
		g.setColor(new Color((int) backgroundR,(int) backgroundG,(int) backgroundB));
		g.fillRect(frameWidth/6 * 2, frameHeight/2 + 50, frameWidth/6 * 2, 20);
		g.fillRect(0, frameHeight/2 + 50 + 200, frameWidth, 200);
		g.setColor(Color.BLACK);
		g.drawRect(frameWidth/6 * 2, frameHeight/2 + 50, frameWidth/6 * 2, 20);
		g.drawRect(0, frameHeight/2 + 50 + 200, frameWidth, 200);
		
		Font font = new Font("Verdana",Font.BOLD, 20); g.setFont(font);
		g.drawString(playerX + ", " + playerY + " | Velocity: " + playerXvelocity + ", " + playerYvelocity + ", " + playerSpeed, 10, 30);
		g.setColor(Color.RED);
		g.drawString(enemyX + ", " + enemyY + " | Velocity: " + enemyXvelocity + ", " + enemyYvelocity + ", " + enemySpeed, 10, 80);
		g.drawString("R " + (int) backgroundR + " G " + (int) backgroundG + " B " + (int) backgroundB, 50, 120);
		
		Font font2 = new Font("Verdana",Font.BOLD, 50); g.setFont(font2);
		if(r.intersects(p)){
			g.drawString("COLLISION OCCURRED!", frameWidth/2 - 300, 160);
			if(enemyX - playerX < 50 && enemyX - playerX > 0){
				playerX = enemyX - 50;
			}
			else if(enemyX - playerX > -50 && enemyX - playerX < 0){
				playerX = enemyX + 50;
			}
			
			if(playerSpeed > enemySpeed){ 
				enemyHealth -= playerSpeed;
				enemyXvelocity = playerXvelocity;
				playerXvelocity = 0;
			}
			if(enemySpeed > playerSpeed){
				playerHealth -= enemySpeed;
				playerXvelocity = enemyXvelocity; 
				enemyXvelocity = 0;
			}
			
		
		
		}
		
		g.fillRect(frameWidth/2 - 300, 45, enemyHealth, 30);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(frameWidth/2 - 300, 10, playerHealth, 30);
		
		g.setColor(Color.BLACK);
		g.drawRect(frameWidth/2 - 300, 10, 400, 30);
		g.drawRect(frameWidth/2 - 300, 45, 400, 30);
		
		
		
		
		
		
		
		Background.drawShape(g);
		Bullet.drawShape(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK); g2.fillOval(playerX, playerY, playerWidth, playerHeight);
		
		playerBullet.drawShape(g);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D){ playerXvelocity = 5; playerRightFace = true;}
		if(key == KeyEvent.VK_A){ playerXvelocity = -5; playerRightFace = false;}
		if(key == KeyEvent.VK_SPACE){ playerJumped = true;}
		if(key == KeyEvent.VK_0){ playerPowerUp = true;}
		if(key == KeyEvent.VK_W){playerShot = true;new playerBullet(playerProjX, playerProjY, 10,10, Color.BLUE);}
		
		if(key == KeyEvent.VK_RIGHT){ enemyXvelocity = 5;}
		if(key == KeyEvent.VK_LEFT){ enemyXvelocity = -5;}
		if(key == KeyEvent.VK_UP){ enemyJumped = true;}
		
	}
	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D){ playerXvelocity = 0;playerRightFace = true;}
		if(key == KeyEvent.VK_A){ playerXvelocity = 0;playerRightFace = false;}
		if(key == KeyEvent.VK_SPACE){ playerJumped = false;}
		if(key == KeyEvent.VK_0){ playerPowerUp = false;}
		if(key == KeyEvent.VK_W){ playerShot = false;}
		
		
		
		if(key == KeyEvent.VK_RIGHT){ enemyXvelocity = 0;}
		if(key == KeyEvent.VK_LEFT){ enemyXvelocity = 0;}
		if(key == KeyEvent.VK_UP){ enemyJumped = false;}
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Sample");
		Bullet bullet = new Bullet(enemyX,enemyY, Color.RED, enemyWidth, enemyHeight);
		frame.add(game);
		frame.setSize(1280, 720); frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); frame.setVisible(true);
		
		
		int playerJumpCounter = 12;
		int enemyJumpCounter = 12;
		
		//int i = 500;
		//new Background(frameWidth,frameHeight, new Color((int) (Math.random()*256),(int) (Math.random()*256),(int) (Math.random()*256)));
		
		while(true){
			/*i--;
			if(i <= 0){
				new Background(frameWidth,frameHeight, new Color((int) (Math.random()*256),(int) (Math.random()*256),(int) (Math.random()*256)));
				i=500;
			}*/
			//Background.update(frameWidth/500, frameHeight/500);
			
			
			if(backgroundR <= 254 && red){backgroundR+= 1;}
			else{red = false; backgroundR-= 1;}
			if(backgroundR < 1) red = true;
			
			if(backgroundG <= 254 && green){backgroundG+= 1;}
			else{green = false; backgroundG-= 1;}
			if(backgroundG < 1) green = true;
			
			if(backgroundB <= 254 && blue){backgroundB+= 1;}
			else{blue = false; backgroundB-= 1;}
			if(backgroundB < 1) blue = true;
			
			game.moveBall();
			game.repaint();
			game.addNotify();
			Bullet.update(enemyX,enemyY);
			playerBullet.update(playerProjX, playerProjY);
			
			
			
			
			if(playerJumped){playerJumpCounter--; playerYvelocity = -20;}
			if(playerJumpCounter <= 0)playerJumped = false;
			if(!playerJumped && playerJumpCounter <= 0){playerJumpCounter = 12;  playerYvelocity = 0;}
			
			if(enemyJumped){enemyJumpCounter--; enemyYvelocity = -20;}
			if(enemyJumpCounter <= 0)enemyJumped = false;
			if(!enemyJumped && enemyJumpCounter <= 0){enemyJumpCounter = 12;  enemyYvelocity = 0;}
			
			
			
			
			//if(playerJumped) playerYvelocity = -4;
			if(playerPowerUp) playerSizeVelocity = 1;
			else playerSizeVelocity = -2;
			//if(enemyJumped) enemyYvelocity = -4;
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}


