package main;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private JPanel contentPanel;
	private final JScrollPane contactsScroller = new JScrollPane();
	private JScrollBar contactsScrollbar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		contactsScroller.setBounds(10, 30, 100, 420);
		contentPanel.add(contactsScroller);
		
		contactsScrollbar = new JScrollBar();
		contactsScroller.setRowHeaderView(contactsScrollbar);
		DefaultListModel listModel = new DefaultListModel();
		JList contactsList = new JList(listModel);
		contactsScroller.setViewportView(contactsList);
	}
}
