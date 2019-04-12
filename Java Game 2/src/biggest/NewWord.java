package biggest;

import java.awt.Graphics;
import java.util.ArrayList;

public class NewWord {
	public static ArrayList<String> word = new ArrayList<String>();
	public static ArrayList<Integer> height = new ArrayList<Integer>();
	
	public NewWord(String string, int h){
		word.add(string);
		height.add(h);
	}
	public static void drawShape(Graphics g){
		g.setFont(Typer.font);
		for(String s:word){
			g.drawString(s,0, height.get(word.indexOf(s)));
		}
			
	}
}
