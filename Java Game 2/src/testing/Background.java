package testing;

import java.awt.Color;
import java.awt.Graphics;

public class Background {
	public static int width;
	public static int height;
	public static Color color;
	public Background(int wid, int hei, Color col) {
		width = wid;
		height = hei;
		color = col;
	}

	public static void update(int velX, int velY){
		width -= velX;
		width -= velY;
	}
	public static void drawShape(Graphics g){
		g.setColor(color);
        g.fillRect(0, 0, width, height);
	}

}
