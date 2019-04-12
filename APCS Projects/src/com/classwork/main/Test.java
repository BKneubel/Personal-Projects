package com.classwork.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;

public class Test extends JFrame {

	private JPanel contentPane;
	private JTextField txtStats;
	private JTextField txtCharacter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.columnWidths = new int[]{320,320};
		gbl_contentPane.rowHeights = new int[]{80,400};
		//gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		//gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtStats = new JTextField();
		txtStats.setText("Stats");
		GridBagConstraints gbc_txtStats = new GridBagConstraints();
		gbc_txtStats.insets = new Insets(0, 0, 5, 5);
		gbc_txtStats.fill = GridBagConstraints.HORIZONTAL;
		//gbc_txtStats.gridx = 0;
		//gbc_txtStats.gridy = 0;
		contentPane.add(txtStats, gbc_txtStats);
		txtStats.setColumns(10);
		
		txtCharacter = new JTextField();
		txtCharacter.setText("Character");
		GridBagConstraints gbc_txtCharacter = new GridBagConstraints();
		gbc_txtCharacter.insets = new Insets(0, 0, 5, 0);
		gbc_txtCharacter.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCharacter.gridx = 1;
		gbc_txtCharacter.gridy = 0;
		contentPane.add(txtCharacter, gbc_txtCharacter);
		txtCharacter.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);
	}

}
