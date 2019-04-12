package testing;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet{
	public static int bulletX; public static int bulletY;
	public static Color color;
	public static int enemyWidth; public static int enemyHeight;
	public Bullet(int startX, int startY, Color Color, int width, int height){
        bulletX = startX;
        bulletY = startY;
        color = Color;
        enemyWidth = width;
        enemyHeight = height;
    }
	public static void update(int Xpos, int Ypos){
		bulletX = Xpos;
		bulletY = Ypos;
	}
    public static void drawShape(Graphics g){
    	g.setColor(color);
        g.fillOval(bulletX, bulletY, enemyWidth, enemyHeight);
    }
}
