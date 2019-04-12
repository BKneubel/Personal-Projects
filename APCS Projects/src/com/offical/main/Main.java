package com.offical.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Main {
	public static boolean Mullet = false,Bowl = false,Dreadlocks = false,Kevin = false,White = true,Black = false,Red = false,Green = false,Blue = false;
	public static JPanel panel = new JPanel();
	public static DrawPane figure = new DrawPane();
	public static void main(String[] args) throws InterruptedException {
		/*
		JFrame input = new JFrame("Input");
		Object[] possibilities = {"Easy", "Medium", "Hard"};
		String difficulty = (String)JOptionPane.showInputDialog(input, "Which Difficulty?", "Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
		*/
		JFrame frame = new JFrame("Character Creation");
		frame.setBounds(0, 0, 640, 480);
		frame.setLocationRelativeTo(null);
		
		
		frame.add(panel);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{320,320};
		gbl.rowHeights = new int[]{80,400};
		panel.setLayout(gbl);
		
		GridBagConstraints UpperLeft = new GridBagConstraints();
		UpperLeft.gridx = 0;	UpperLeft.gridy = 0;
		GridBagConstraints UpperRight = new GridBagConstraints();
		UpperRight.gridx = 1;	UpperRight.gridy = 0;
		GridBagConstraints LowerRight = new GridBagConstraints();
		LowerRight.gridx = 0;	LowerRight.gridy = 1;
		GridBagConstraints LowerLeft = new GridBagConstraints();
		LowerLeft.gridx = 1;	LowerLeft.gridy = 1;
		LowerRight.fill = GridBagConstraints.HORIZONTAL;
		LowerRight.anchor = GridBagConstraints.NORTH;
		
		
		Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 20);
		
		JLabel label1 = new JLabel("Customize");
		label1.setFont(font);
		panel.add(label1,UpperLeft);
		
		JLabel label2 = new JLabel("Character");
		label2.setFont(font);
		panel.add(label2,UpperRight);
		
		Object[] hairStyles = {"Select Hair","Mullet","Bowl Cut","Dreadlocks","Kevin's weird new haircut"};
		JComboBox comboBox = new JComboBox(hairStyles);
		comboBox.setFont(font);
		panel.add(comboBox,LowerRight);
		
		Object[] skinColor = {"Select Skin","White","Black"};
		JComboBox comboBox2 = new JComboBox(skinColor);
		LowerRight.insets = new Insets(50,0,0,0);
		comboBox2.setFont(font);
		panel.add(comboBox2,LowerRight);
		
		Object[] shirtColor = {"Select Shirt","Red","Green","Blue"};
		JComboBox comboBox3 = new JComboBox(shirtColor);
		LowerRight.insets = new Insets(100,0,0,0);
		comboBox3.setFont(font);
		panel.add(comboBox3,LowerRight);
		
		LowerLeft.anchor = GridBagConstraints.FIRST_LINE_START;
		LowerLeft.fill = GridBagConstraints.BOTH;
		JButton button = new JButton( new AbstractAction("Create"){
			@Override
			public void actionPerformed(ActionEvent e){
				if(comboBox.getSelectedIndex()==1)Mullet = true;
				if(comboBox.getSelectedIndex()==2)Bowl = true;
				if(comboBox.getSelectedIndex()==3)Dreadlocks = true;
				if(comboBox.getSelectedIndex()==4)Kevin = true;
				if(comboBox2.getSelectedIndex()==1)White = true;
				if(comboBox2.getSelectedIndex()==2)Black = true;
				if(comboBox3.getSelectedIndex()==1)Red = true;
				if(comboBox3.getSelectedIndex()==2)Green = true;
				if(comboBox3.getSelectedIndex()==3)Blue = true;
				figure.repaint();
				
			}
		});
		button.setFont(font);
		LowerRight.insets = new Insets(150,0,0,0);
		panel.add(button, LowerRight);
		
		JLabel label3 = new JLabel("Constitution");
		LowerRight.insets = new Insets(200,0,0,0);
		label3.setFont(font);
		panel.add(label3, LowerRight);
		
		JLabel label4 = new JLabel("Strength");
		LowerRight.insets = new Insets(250,0,0,0);
		label4.setFont(font);
		panel.add(label4, LowerRight);
		
		JLabel label5 = new JLabel("Dexterity");
		LowerRight.insets = new Insets(300,0,0,0);
		label5.setFont(font);
		panel.add(label5, LowerRight);
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,9,1));
		LowerRight.insets = new Insets(220,0,0,0);
		spinner.setBounds(0, 0, 50, 20);
		panel.add(spinner, LowerRight);
		
		JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(0,0,9,1));
		LowerRight.insets = new Insets(270,0,0,0);
		spinner1.setBounds(0, 0, 50, 20);
		panel.add(spinner1, LowerRight);
		
		JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(0,0,9,1));
		LowerRight.insets = new Insets(320,0,0,0);
		spinner2.setBounds(0, 0, 50, 20);
		panel.add(spinner2, LowerRight);
		
		
		if((Integer)spinner.getValue()+(Integer)spinner1.getValue()+(Integer)spinner2.getValue() >= 9){
			System.out.println("test");
			spinner.setEnabled(false);
			spinner1.setEnabled(false);
			spinner2.setEnabled(false);
		}
		
		
		
		
		
		
		
		
		
		panel.add(figure, LowerLeft);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		while(true){
			System.out.println(spinner.getValue());
		}
		*/
	}

}

class DrawPane extends JPanel{
	boolean hairBlack = false;
	public void paint(Graphics g){
		g.setColor(new Color(238,238,238));//paints background the same gray as the window's default
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawHairBack(g);
		drawShirt(g);
		drawBody(g);
		drawHair(g);
	}
	public void drawShirt(Graphics g){
		if(Main.Red){
			g.setColor(Color.RED);
			Main.Red = false;
		}
		else if(Main.Blue){
			g.setColor(new Color(91,134,204));//shirt blue
			Main.Blue = false;
		}
		else{
			g.setColor(Color.GREEN);
			Main.Green = false;
		}
		g.fillRect(100, 100, 100, 150);
	}
	public void drawBody(Graphics g){
		if(Main.Black){
			g.setColor(new Color(111,79,29));//skin black
			hairBlack = true;
			Main.Black = false;
		}
		else{
			g.setColor(new Color(255,255,102));//skin white
			Main.White = false;
		}
		g.fillOval(100, 28, 100, 75);//head
		g.fillRect(50, 100, 50, 125);//left
		g.fillRect(200, 100, 50, 125);//right
		g.setColor(new Color(0,68,178));//pants blue
		g.fillRect(100, 250, 100, 125);//legs
		g.setColor(Color.black);
		g.fillRect(100, 350, 100, 50);//shoes
		g.fillOval(128, 50, 10, 10);
		g.fillOval(170, 50, 10, 10);
		g.fillOval(135, 70, 40, 20);
	}
	public void drawHair(Graphics g){
		if(Main.Mullet){
			g.setColor(new Color(153,76,0));
			if(hairBlack) g.setColor(Color.black);
			g.fillOval(130, 24, 40, 20);
			hairBlack = false;
			Main.Mullet = false;
		}
		else if(Main.Bowl){
			g.setColor(new Color(153,76,0));
			if(hairBlack) g.setColor(Color.black);
			g.fillArc(110, 28, 80, 30, 0, 180);
			hairBlack = false;
			Main.Bowl = false;
		}
		else if(Main.Dreadlocks){
			g.setColor(new Color(153,76,0));
			if(hairBlack) g.setColor(Color.black);
			g.fillArc(105, 20, 90, 45, 0, 180);
			g.fillRect(110, 42, 10, 70);
			g.fillRect(188, 42, 10, 80);
			g.fillRect(102, 42, 6, 40);
			g.fillRect(180, 42, 6, 52);
			hairBlack = false;
			Main.Dreadlocks = false;
		}
		else if(Main.Kevin){
			g.setColor(new Color(153,76,0));
			if(hairBlack) g.setColor(Color.black);
			Polygon p = new Polygon();
			p.addPoint(130, 32);
			p.addPoint(170, 32);
			p.addPoint(150, 0);
			g.fillPolygon(p);
			hairBlack = false;
			Main.Kevin = false;
		}
	}
	public void drawHairBack(Graphics g){
		if(Main.Mullet){
			g.setColor(new Color(153,76,0));
			if(Main.Black) g.setColor(Color.black);
			g.fillOval(110, 75, 30, 80);
		}
		
		else if(Main.Dreadlocks){
			g.setColor(new Color(120,65,0));
			if(Main.Black) g.setColor(Color.black);
			g.fillRect(105, 40, 90, 60);
		}
		else if(Main.Kevin){
			g.setColor(new Color(153,76,0));
			if(Main.Black) g.setColor(Color.black);
		}
	}
}
