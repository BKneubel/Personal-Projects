package com.testing.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel{
	
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	public static String s;
	
	public Main(){
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		
		
	}
	
	public void paint(Graphics g){
		ball.paint(g);
		racquet.paint(g);
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		JFrame frame = new JFrame();
		frame.setSize(400, 600);
		frame.setResizable(false);
		frame.setBackground(Color.DARK_GRAY);
		
		Object[] possibilities = {"blue","green","red"};
		s = (String)JOptionPane.showInputDialog(frame,"Which color?","Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,possibilities,"");
		
		if(s.equals("blue")||s.equals("green")||s.equals("red")){
			Main game = new Main();
			frame.add(game);
			
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			while (true) {
				game.move();
				game.repaint();
				Thread.sleep(10);
			}
		}
		
		
		
		
	}

	
	
	

}
