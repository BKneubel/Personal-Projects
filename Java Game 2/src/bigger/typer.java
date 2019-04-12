package bigger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class typer extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static int frameWidth = 1280, frameHeight = 720;
	public static File file = new File("C:\\Users\\bkneu\\Desktop\\textFolder\\1.txt");
	public static File file2 = new File("C:\\Users\\bkneu\\Desktop\\textFolder\\2.txt");
	public static String[] words = {"testing","out","a","random","word","generator","add","more","list","please","keep","additional","century","plant","based","ruby","eclipse","solar","system"
			,"typing","test","quick","the","brown","fox","jumped","over","the","moon","run","far","without","within","through","millenial","cake","snake","bake","vocabulary","skills","important"
			,"printer","desktop","laptop","for","foreshadow","shadow","bat","cat","dog","frog","elephant","seaturtle"};
	public static char currentKey;
	public static String currentWord;
	public static boolean keyTyped = false, space = false, timerStart = false;
	public static double numWords = 40,numWordsAttempted = 1;
	public static double timer = 20000;
	
	public typer(){
		addKeyListener(this);
	}
	public void update(){
		
	}
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	public void paint(Graphics g){
		super.paint(g);
		//g.fillRect(0,0,40,40);
		Font font = new Font("Verdana",Font.BOLD, 80); g.setFont(font);
		g.drawString(currentKey + " ", 100, 500);
		g.drawString(currentWord, 100, 300);
		g.setColor(Color.RED);
		g.drawString("Time: " + timer/1000 + "." + timer%1000, 100, 100);
		if((((20000-timer)/1000)/60) != 0) g.drawString("Pace: " + (numWordsAttempted)/(((20000-timer)/1000)/60), 800, 100);
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		char currentKey = arg0.getKeyChar();
		if(currentKey != ' ')saveKey(currentKey);
	}
	public void saveKey(char key){
		currentKey = key;
		keyTyped = true;
	}
	public static void main(String[] args) throws IOException{
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		for(int i = 0; i < numWords; i++)
			writer.println(words[(int) (Math.random()*words.length)]);
		writer.close();
		
		typer game = new typer();
		JFrame frame = new JFrame("Typer");
		frame.add(game);
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		
		Scanner sc = new Scanner(file);
		if(sc.hasNextLine()) currentWord = sc.nextLine();
		
		PrintWriter rewriter = new PrintWriter(file2, "UTF-8");
		
		while(true){
			if(keyTyped){rewriter.print(currentKey); timerStart = true; keyTyped = false;}
			if(space && sc.hasNextLine()){
				currentWord = sc.nextLine();
				numWordsAttempted++;
				rewriter.println();
				try {Thread.sleep(150);}catch (InterruptedException e) {e.printStackTrace();}
			}
			if(timerStart){
				timer-= 10;
			}
			game.update();
			game.repaint();
			game.addNotify();
			try {
				Thread.sleep(10);
			}catch(InterruptedException e){e.printStackTrace();}
			if(!sc.hasNextLine() || timer <= 0) break;
		}
		sc.close();
		rewriter.close();
		
		frame.setVisible(false);
		
		BufferedReader b1 = new BufferedReader(new FileReader(file));
		BufferedReader b2 = new BufferedReader(new FileReader(file2));
		ArrayList<String> list_file1 = new ArrayList<String>();
		ArrayList<String> list_file2 = new ArrayList<String>();
		String lineText = null;
		while ((lineText = b1.readLine()) != null) {
            list_file1.add(lineText);
        }
        while ((lineText = b2.readLine()) != null) {
            list_file2.add(lineText);
        }
        int mistakes = compareFile(list_file1,list_file2, (int) numWordsAttempted)-1;
        System.out.println("You made " + mistakes + " mistakes when typing " + (numWordsAttempted-1) + " words. Your typing speed was " + 3*((numWordsAttempted-1) - mistakes) + " WPM.");
		    
	}
	private static int compareFile(ArrayList<String> list_file1, ArrayList<String> list_file2, int mistakes) {
	    String content1 = null,content2 = null;
		for(int i = 0; i < list_file1.size(); i++){
			content1 = list_file1.get(i);
	        for(int j = 0; j < list_file2.size(); j ++){
	        	content2 = list_file2.get(j);
	            if(i == j && content1.equals(content2) && content1 != null && content2 != null){
	                //System.out.println("Match Found:-"+content1);
	                mistakes--;
	            } 
	        }
	    }
		return mistakes;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) space = true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) space = false;	
	}

}
