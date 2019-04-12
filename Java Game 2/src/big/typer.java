package big;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class typer extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;

	public static int frameWidth = 1280, frameHeight = 720, x = 80;
	public static boolean space = false, keyTyped = false;
	public static File file = new File("C:\\Users\\bkneu\\Desktop\\textFolder\\text.txt");
	public static File file2 = new File("C:\\Users\\bkneu\\Desktop\\textFolder\\out.txt");
	public static char currentKey;
	public static String currentWord;
	
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
		g.fillRect(0,0,40,40);
		Font font = new Font("Verdana",Font.BOLD, 80); g.setFont(font);
		g.drawString(currentKey + " ", x, 300);
		g.drawString(currentWord, 100, 100);
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		char currentKey = arg0.getKeyChar();
		saveKey(currentKey);
	}
	public void saveKey(char key){
		currentKey = key;
		keyTyped = true;
	}
	/*public String getNewWord() throws FileNotFoundException{
		
		
		else return null;
	}*/
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException,IOException{
		typer game = new typer();
		JFrame frame = new JFrame("Typer");
		frame.add(game);
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		
		Scanner sc = new Scanner(file);
		if(sc.hasNextLine()) currentWord = sc.nextLine();
		
		PrintWriter writer = new PrintWriter(file2, "UTF-8");
		
		while(true){
			game.update();
			if(keyTyped){ writer.print(currentKey); x += 30; keyTyped = false;}
			game.repaint();
			game.addNotify();
			if(space && sc.hasNextLine()){
				currentWord = sc.nextLine();
				writer.println();
				x = 80;
				try {Thread.sleep(150);}catch (InterruptedException e) {e.printStackTrace();}
			}
			else if(!sc.hasNextLine()) writer.close();
			try {Thread.sleep(10);}catch (InterruptedException e) {e.printStackTrace();}
			if(!sc.hasNextLine()){
				break;
			}
		}
		writer.close();

		frame.setVisible(false);
		Scanner reader1 = new Scanner(file);
		Scanner reader2 = new Scanner(file2);
		String correctWord, enteredWord;
		int mistakes = 0;
		while(reader1.hasNextLine() && reader2.hasNextLine()){
			correctWord = reader1.nextLine();
			enteredWord = reader2.nextLine().replaceAll("\\s+","");;     
			if(correctWord != enteredWord){
				mistakes++;
				System.out.println("You should have typed: \"" + correctWord + "\", but instead you typed \"" + enteredWord + "\"");
				
			}
		}
        System.out.println("You made " + mistakes + " mistakes.");
        reader1.close();
        reader2.close();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			//System.out.println("space pressed");
			space = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			//System.out.println("space released");
			space = false;
		}
	}

}
