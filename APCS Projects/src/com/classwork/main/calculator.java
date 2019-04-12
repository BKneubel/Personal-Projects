package com.classwork.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class calculator{
	
	
	public static String temp = "";
	public static String temp2 = "";
	
	public static void main(String[]args){
		JFrame frame = new JFrame("Calculator");
		frame.setSize(300, 500);
		frame.setResizable(false);
		Color background = new Color(20,100,50);
		frame.getContentPane().setBackground(background);
		
		Font font = new Font("Helvetica", Font.BOLD, 20);
		Font font2 = new Font("Verdana",0, 20);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JTextField text = new JTextField();
		text.setFont(font2);text.setBounds(10, 10, 260, 100);text.setColumns(10);

		JTextField text2 = new JTextField();
		text2.setBounds(10, 120, 260, 30);text2.setColumns(10);text2.setBackground(Color.white);text2.setEditable(false);
		
		JButton button1 = new JButton( new AbstractAction("1"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "1");
			}
		});
		JButton button2 = new JButton( new AbstractAction("2"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "2");
			}
		});
		JButton button3 = new JButton( new AbstractAction("3"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "3");
			}
		});
		JButton button4 = new JButton( new AbstractAction("4"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "4");
			}
		});
		JButton button5 = new JButton( new AbstractAction("5"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "5");
			}
		});
		JButton button6 = new JButton( new AbstractAction("6"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "6");
			}
		});
		JButton button7 = new JButton( new AbstractAction("7"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "7");
			}
		});
		JButton button8 = new JButton( new AbstractAction("8"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "8");
			}
		});
		JButton button9 = new JButton( new AbstractAction("9"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "9");
			}
		});
		JButton button0 = new JButton( new AbstractAction("0"){
			@Override
			public void actionPerformed(ActionEvent e){
				String temp = text.getText();
				text.setText(temp + "0");
			}
		});
		
		
		
		JButton addButton = new JButton( new AbstractAction("+"){
			@Override
			public void actionPerformed(ActionEvent e){
				temp = text.getText();
				text.setText(temp + " + ");
			}
		});
		
		JButton subtractButton = new JButton( new AbstractAction("-"){
			@Override
			public void actionPerformed(ActionEvent e){
				temp = text.getText();
				text.setText(temp + " - ");
			}
		});
		
		JButton multiplyButton = new JButton( new AbstractAction("x"){
			@Override
			public void actionPerformed(ActionEvent e){
				temp = text.getText();
				text.setText(temp + " x ");
			}
		});
		
		JButton divideButton = new JButton( new AbstractAction("/"){
			@Override
			public void actionPerformed(ActionEvent e){
				temp = text.getText();
				text.setText(temp + " / ");
			}
		});
		
		JButton equalsButton = new JButton( new AbstractAction("="){
			@Override
			public void actionPerformed(ActionEvent e){
				
				if(text.getText().contains("+")){
					temp2 = text.getText().substring(text.getText().indexOf('+')+ 2, text.getText().length());
					double a = Double.parseDouble(temp);
					double b = Double.parseDouble(temp2);
					double c = a + b;
					text2.setText("" + c);
				}
				else if (text.getText().contains("-")){
					temp2 = text.getText().substring(text.getText().indexOf('-')+ 2, text.getText().length());
					double a = Double.parseDouble(temp);
					double b = Double.parseDouble(temp2);
					double c = a - b;
					text2.setText("" + c);
				}
				else if (text.getText().contains("x")){
					temp2 = text.getText().substring(text.getText().indexOf('x')+ 2, text.getText().length());
					double a = Double.parseDouble(temp);
					double b = Double.parseDouble(temp2);
					double c = a * b;
					text2.setText("" + c);
				}
				else if (text.getText().contains("/")){
					temp2 = text.getText().substring(text.getText().indexOf('/')+ 2, text.getText().length());
					double a = Double.parseDouble(temp);
					double b = Double.parseDouble(temp2);
					double c = a / b;
					text2.setText("" + c);
				}
				
				
				
			}
		});
		
		JButton clearButton = new JButton( new AbstractAction("CE"){
			@Override
			public void actionPerformed(ActionEvent e){
				text.setText("");
				text2.setText("");
			}
		});
		
		JButton function1 = new JButton( new AbstractAction("#"){
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton function2 = new JButton( new AbstractAction("#"){
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton function3 = new JButton( new AbstractAction("#"){
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton function4 = new JButton( new AbstractAction("#"){
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton function5 = new JButton( new AbstractAction("#"){
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton function6 = new JButton( new AbstractAction("#"){
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton function7 = new JButton( new AbstractAction("#"){
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
		JButton function8 = new JButton( new AbstractAction("#"){
			@Override
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		
		frame.setLayout(null);
		
		
		button1.setBounds(75, 270, 65, 30);button2.setBounds(140, 270, 65, 30);button3.setBounds(205, 270, 65, 30);
		button4.setBounds(75, 300, 65, 30);button5.setBounds(140, 300, 65, 30);button6.setBounds(205, 300, 65, 30);
		button7.setBounds(75, 330, 65, 30);button8.setBounds(140, 330, 65, 30);button9.setBounds(205, 330, 65, 30);
		button0.setBounds(140, 360, 65, 30);
		
		function1.setBounds(10, 160, 65, 30);function2.setBounds(75, 160, 65, 30);function3.setBounds(140, 160, 65, 30);
		function4.setBounds(205, 160, 65, 30);function5.setBounds(10, 190, 65, 30);function6.setBounds(75, 190, 65, 30);
		function7.setBounds(140, 190, 65, 30);function8.setBounds(205, 190, 65, 30);
		
		addButton.setBounds(10, 230, 65, 30);subtractButton.setBounds(75, 230, 65, 30);multiplyButton.setBounds(140, 230, 65, 30);
		divideButton.setBounds(205, 230, 65, 30);
		
		equalsButton.setBounds(140, 400, 130, 40);clearButton.setBounds(10, 400, 130, 40);
		
		Color foreground = new Color(80,220,220);
		
		addButton.setBackground(foreground);subtractButton.setBackground(foreground);
		multiplyButton.setBackground(foreground);divideButton.setBackground(foreground);
		
		button1.setBackground(foreground);button2.setBackground(foreground);button3.setBackground(foreground);button4.setBackground(foreground);
		button5.setBackground(foreground);button6.setBackground(foreground);button7.setBackground(foreground);button8.setBackground(foreground);
		button9.setBackground(foreground);button0.setBackground(foreground);
		
		equalsButton.setBackground(foreground);clearButton.setBackground(foreground);
		
		function1.setBackground(foreground);function2.setBackground(foreground);function3.setBackground(foreground);
		function4.setBackground(foreground);function5.setBackground(foreground);function6.setBackground(foreground);
		function7.setBackground(foreground);function8.setBackground(foreground);
		
		
		addButton.setFont(font);subtractButton.setFont(font);multiplyButton.setFont(font);divideButton.setFont(font);
		
		button1.setFont(font);button2.setFont(font);button3.setFont(font);button4.setFont(font);button5.setFont(font);
		button6.setFont(font);button7.setFont(font);button8.setFont(font);button9.setFont(font);button0.setFont(font);
		
		equalsButton.setFont(font);clearButton.setFont(font);
		
		function1.setFont(font);function2.setFont(font);function3.setFont(font);function4.setFont(font);
		function5.setFont(font);function6.setFont(font);function7.setFont(font);function8.setFont(font);
		
		frame.add(text);frame.add(text2);
		
		
		frame.add(addButton);frame.add(subtractButton);frame.add(multiplyButton);frame.add(divideButton);
		
		frame.add(button1);frame.add(button2);frame.add(button3);frame.add(button4);frame.add(button5);
		frame.add(button6);frame.add(button7);frame.add(button8);frame.add(button9);frame.add(button0);
		
		frame.add(equalsButton);frame.add(clearButton);
		
		frame.add(function1);frame.add(function2);frame.add(function3);frame.add(function4);
		frame.add(function5);frame.add(function6);frame.add(function7);frame.add(function8);
		
		frame.setVisible(true);
	}
}