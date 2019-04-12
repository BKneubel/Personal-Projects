package biggest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import bigger.typer;

public class Typer extends JPanel{
	private static final long serialVersionUID = 1L;
	public static File Lexicon = new File("C:\\Users\\bkneu\\Desktop\\FinalTexts\\Lexicon.txt");
	public static File RandomizedList = new File("C:\\Users\\bkneu\\Desktop\\FinalTexts\\RandomizedList.txt");
	private static JPanel panel = new JPanel();
	private static JFrame frame = new JFrame("Typing Game");
	private static GridBagLayout gbl = new GridBagLayout();
	private static GridBagConstraints Up = new GridBagConstraints();
	private static GridBagConstraints Down = new GridBagConstraints();
	public static boolean keyPressed = false;
	public static String user;
	public static int backgroudTimer = 100000;
	public static int timer = 20000;
	public static DrawPane figure = new DrawPane();
	public static Font font = new Font("Verdana",Font.BOLD, 60); 
	public static ArrayList<String> unsortedLexicon = new ArrayList<String>();
	private static JTextField text = new JTextField();
	public static NewWord[] wordList = new NewWord[200];

	
	public Typer(){
	}
	public void update(){
		
	}
	/*
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}*/
	/*
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0,0,40,40);
	}
	*/
	@SuppressWarnings("serial")
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Typer game = new Typer();
		//Set Frame
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		
		//Set Layout
		gbl.columnWidths = new int[]{1280};
		gbl.rowHeights = new int[]{600,120};
		panel.setLayout(gbl);
		Up.gridx = 0; Up.gridy = 0;
		Down.gridx = 0; Down.gridy = 1;
		Up.fill = GridBagConstraints.BOTH;
		Down.fill = GridBagConstraints.BOTH;
		
		
		
		//user = JOptionPane.showInputDialog("Enter your Username: ");
		
		//**********************************Setup textbox for where to type word*******************************//
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 0, Color.BLACK);
		text.setBorder(border);
		text.setFocusable(true);
		text.setBackground(Color.GREEN);
		text.setFont(font);
		//***********************************Detect if key is hit in text**************************************//
		int condition = JComponent.WHEN_FOCUSED;
		InputMap iMap = text.getInputMap(condition);
		ActionMap aMap = text.getActionMap();
		
		String enter = "space";
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0),enter);
		aMap.put(enter, new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){ //When space is pressed*****************************//
				checkWord(text.getText());
				text.setText("");
				keyPressed = true;
				
			}
		});
		//********************************ADD DRAWPANE (figure) AND TEXT************************************//
		panel.add(figure, Up);
		panel.add(text, Down);
		
		//********************************INPUT TEXT FROM FILE, RANDOMIZE, AND PUT IN ARRAY LIST***********//
		Scanner sc = new Scanner(Lexicon);
		while(sc.hasNextLine()) unsortedLexicon.add(sc.nextLine());
		sc.close();
		System.out.println(unsortedLexicon.size());
		PrintWriter writer = new PrintWriter(RandomizedList, "UTF-8");
		for(int i = 0; i < 100; i++)
			writer.println(unsortedLexicon.get((int) (Math.random()*unsortedLexicon.size())));
		writer.close();
		
		
		
		
		
		
		
		
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Scanner sc2 = new Scanner(RandomizedList);
		int k = 0;
		while(true){
			if(sc2.hasNextLine() && backgroudTimer%1000 == 0){
				wordList[k] = new NewWord(sc2.nextLine(),100 + (int) (Math.random()*400));
				k++;
			}
			game.update();
			figure.repaint();
			//game.repaint();
			//game.addNotify();
			backgroudTimer -=10;
			if(keyPressed) timer-= 10;
			try {
				Thread.sleep(10);
			}catch(InterruptedException e){e.printStackTrace();}
			if(timer <= 0) break;
		}
		sc2.close();
	}
	private static void checkWord(String text) {
		System.out.println("YOU HIT SPACE!");
	}
	
	

}
class DrawPane extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public void paint(Graphics g){
		g.setColor(Color.CYAN);
		g.fillRect(0,0,1280,600);
		g.setColor(Color.BLACK);
		g.setFont(Typer.font);
		g.drawString(""+Typer.timer, 10, 50);
		NewWord.drawShape(g);
	}
}
