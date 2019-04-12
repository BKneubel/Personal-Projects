package com.classwork2.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JFrame{
	static JFrame frame2 = new JFrame("Character Creation");
	public static void main(String[] args) {
		JFrame frame = new JFrame("Input");
		Object[] possibilities = {"Easy", "Medium", "Hard"};
		String s = (String)JOptionPane.showInputDialog(frame, "Which Difficulty?", "Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
		
		JPanel character = new JPanel();
		frame2.add(character);
		
		frame2.setBounds(0, 0, 640, 480);
		frame2.setLocationRelativeTo(null);
		
		
		GridBagLayout gbl = new GridBagLayout();
		//gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl.columnWidths = new int[]{320,320};
		gbl.rowHeights = new int[]{80,400};
		
		
		character.setLayout(gbl);
		
		Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 20);
		
		
		JLabel lblHair = new JLabel("Creation");
		lblHair.setFont(font);
		GridBagConstraints gbl1 = new GridBagConstraints();
		gbl1.gridx = 0;
		gbl1.gridy = 0;
		//lblHair.setBounds(55, 0, 160, 20);
		character.add(lblHair,gbl1);
		JLabel lblSkin = new JLabel("Character");
		lblSkin.setFont(font);
		GridBagConstraints gbl2 = new GridBagConstraints();
		gbl2.gridx = 1;
		gbl2.gridy = 0;
		//lblSkin.setBounds(25, 55, 160, 20);
		character.add(lblSkin,gbl2);
		
		
		
		String[] hairStyles = {"Select One","Mullet","Bowl Cut","Dreadlocks","Kevin's weird new haircut"};
		JComboBox comboBox = new JComboBox(hairStyles);
		GridBagConstraints gbl3 = new GridBagConstraints();
		gbl3.gridx = 0;
		gbl3.gridy = 1;
		//gbl3.insets = new Insets(0, 0, 0, 0);
		gbl3.fill = GridBagConstraints.HORIZONTAL;
		gbl3.anchor = GridBagConstraints.FIRST_LINE_START;
		//comboBox.setBounds(0, 0, 160, 20); 
		comboBox.setFont(font);
		//comboBox.setSelectedIndex(4);
		character.add(comboBox,gbl3);
		
		
		String[] skinColor = {"Select One","White"};
		JComboBox comboBox2 = new JComboBox(skinColor);
		//comboBox2.setBounds(0, 0, 160, 20); 
		comboBox2.setFont(font);
		//comboBox2.setSelectedIndex(1);
		//gbl3.insets = new Insets(0, 0, 50, 0);
		character.add(comboBox2,gbl3);
		
		GridBagConstraints gbl4 = new GridBagConstraints();
		gbl4.gridx = 1;
		gbl4.gridy = 1;
		gbl4.anchor = GridBagConstraints.FIRST_LINE_START;
		gbl4.fill = GridBagConstraints.BOTH;
		character.add(new DrawPane(),gbl4);
		
		
		//character.add(new DrawPane(), );
		
		frame2.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//if(s.equals("Easy")) JOptionPane.showMessageDialog(frame,"Your Weight on Voltar is " + weight * .091);
		//if(s.equals("Medium")) JOptionPane.showMessageDialog(frame,"Your Weight on Krypton is " + weight * .720);
		//if(s.equals("Hard")) JOptionPane.showMessageDialog(frame,"Your Weight on Fertos is " + weight * .865);
 
	}
	
	
 
}
class DrawPane extends JPanel{
	public void paintComponent(Graphics g){
		g.fillRect(0, 0, 200, 200);
	}
}