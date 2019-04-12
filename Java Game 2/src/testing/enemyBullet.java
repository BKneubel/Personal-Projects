package testing;

import java.awt.Color;
import java.awt.Graphics;

public class enemyBullet {

	public static int enemyProjX; public static int enemyProjY;
	public static int enemyProjWidth; public static int enemyProjHeight;
	public static Color color;
	
	
	public enemyBullet(int projX, int projY, int width, int height, Color col){
		enemyProjX = projX;
		enemyProjY = projY;
		enemyProjWidth = width;
		enemyProjHeight = height;
		color = col;
	}
	public static void drawShape(Graphics g){
    	g.setColor(color);
        g.fillOval(enemyProjX, enemyProjY, enemyProjWidth, enemyProjHeight);
    }

}


