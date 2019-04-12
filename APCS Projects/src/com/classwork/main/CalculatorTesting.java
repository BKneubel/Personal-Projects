package com.classwork.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculatorTesting extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton button_15;
	private JButton button_16;
	private JButton button_17;
	private JButton button_18;
	private JButton button_19;
	private JButton button_20;
	private JButton button_21;
	private JButton button_22;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorTesting frame = new CalculatorTesting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculatorTesting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 10, 260, 100);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 120, 260, 30);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 160, 65, 30);
		contentPane.add(btnNewButton);
		
		button = new JButton("New button");
		button.setBounds(75, 160, 65, 30);
		contentPane.add(button);
		
		button_1 = new JButton("New button");
		button_1.setBounds(140, 160, 65, 30);
		contentPane.add(button_1);
		
		button_2 = new JButton("New button");
		button_2.setBounds(205, 160, 65, 30);
		contentPane.add(button_2);
		
		button_3 = new JButton("New button");
		button_3.setBounds(10, 190, 65, 30);
		contentPane.add(button_3);
		
		button_4 = new JButton("New button");
		button_4.setBounds(75, 190, 65, 30);
		contentPane.add(button_4);
		
		button_5 = new JButton("New button");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_5.setBounds(140, 190, 65, 30);
		contentPane.add(button_5);
		
		button_6 = new JButton("New button");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_6.setBounds(205, 190, 65, 30);
		contentPane.add(button_6);
		
		button_7 = new JButton("New button");
		button_7.setBounds(10, 230, 65, 30);
		contentPane.add(button_7);
		
		button_8 = new JButton("New button");
		button_8.setBounds(75, 230, 65, 30);
		contentPane.add(button_8);
		
		button_9 = new JButton("New button");
		button_9.setBounds(140, 230, 65, 30);
		contentPane.add(button_9);
		
		button_10 = new JButton("New button");
		button_10.setBounds(205, 230, 65, 30);
		contentPane.add(button_10);
		
		button_11 = new JButton("New button");
		button_11.setBounds(75, 270, 65, 30);
		contentPane.add(button_11);
		
		button_12 = new JButton("New button");
		button_12.setBounds(140, 270, 65, 30);
		contentPane.add(button_12);
		
		button_13 = new JButton("New button");
		button_13.setBounds(205, 270, 65, 30);
		contentPane.add(button_13);
		
		button_14 = new JButton("New button");
		button_14.setBounds(75, 300, 65, 30);
		contentPane.add(button_14);
		
		button_15 = new JButton("New button");
		button_15.setBounds(140, 300, 65, 30);
		contentPane.add(button_15);
		
		button_16 = new JButton("New button");
		button_16.setBounds(205, 300, 65, 30);
		contentPane.add(button_16);
		
		button_17 = new JButton("New button");
		button_17.setBounds(75, 330, 65, 30);
		contentPane.add(button_17);
		
		button_18 = new JButton("New button");
		button_18.setBounds(140, 330, 65, 30);
		contentPane.add(button_18);
		
		button_19 = new JButton("New button");
		button_19.setBounds(205, 330, 65, 30);
		contentPane.add(button_19);
		
		button_20 = new JButton("New button");
		button_20.setBounds(140, 360, 65, 30);
		contentPane.add(button_20);
		
		button_21 = new JButton("New button");
		button_21.setBounds(140, 400, 130, 40);
		contentPane.add(button_21);
		
		button_22 = new JButton("New button");
		button_22.setBounds(10, 400, 130, 40);
		contentPane.add(button_22);
	}
}
