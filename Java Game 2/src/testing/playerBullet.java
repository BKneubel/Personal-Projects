package testing;

import java.awt.Color;
import java.awt.Graphics;

public class playerBullet {

	public static int playerProjX; public static int playerProjY;
	public static int playerProjWidth; public static int playerProjHeight;
	public static Color color;
	
	
	public playerBullet(int projX, int projY, int width, int height, Color col){
		playerProjX = projX;
		playerProjY = projY;
		playerProjWidth = width;
		playerProjHeight = height;
		color = col;
	}
	public static void update(int Xpos, int Ypos){
		playerProjX = Xpos;
		playerProjY = Ypos;
	}
	public static void drawShape(Graphics g){
    	g.setColor(color);
        g.fillOval(playerProjX, playerProjY, playerProjWidth, playerProjHeight);
    }

}
