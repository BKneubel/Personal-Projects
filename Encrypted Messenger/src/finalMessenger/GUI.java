package finalMessenger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class GUI{

	public static File file = new File("res/GuiLoaderTest.messages");
	private static JPanel panel = new JPanel();
	private static JFrame frame = new JFrame("LAN Messenger - Brennen and Matt");
	private static GridBagLayout gbl = new GridBagLayout();
	private static GridBagConstraints UpperLeft = new GridBagConstraints();
	private static GridBagConstraints UpperRight = new GridBagConstraints();
	private static GridBagConstraints LowerRight = new GridBagConstraints();
	private static GridBagConstraints LowerLeft = new GridBagConstraints();
	private static GridBagConstraints UpperMid = new GridBagConstraints();
	private static GridBagConstraints LowerMid = new GridBagConstraints();
	private static String username;
	private static Color color;
	private static Contact contact;
	private static JTextPane messageBox = new JTextPane();
	private static JTextField text = new JTextField();
	private static Font font;
	public static ArrayList<Message> MessageList;//will be in client
	private static JTextPane side = new JTextPane();
	private static Color background = new Color(192, 192, 192);
	private static int prevDay = 0;
	private static JTextField text1 = new JTextField();
	private static JButton send;

	public static void launch(){

		//Set Frame
		frame.setBounds(0, 0, 1000, 750);
		frame.setPreferredSize(new Dimension(640, 480));
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.add(panel);

		//Temporary status bar on right side
		//appendToPane(side, "Frame Status", Color.GREEN);

		//Set Layout (Noting if frame is resized
		frame.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e){
				gbl.columnWidths = new int[]{ (frame.getWidth() / 32) * 4, (frame.getWidth() / 32) * 24,
						(frame.getWidth() / 32) * 4 };//column ratios: 1/8 : 3/4 : 1/8
				gbl.rowHeights = new int[]{ (frame.getHeight() / 8) * 7, frame.getHeight() / 8 };//row ratios: 7/8 : 1/8
				/**
				 * if(color != Color.BLACK) appendToPane(side, "\nResizing",
				 * Color.BLACK); else appendToPane(side, "\nResizing...",
				 * Color.WHITE);
				 */
				panel.setLayout(gbl);
				UpperLeft.gridx = 0;
				UpperLeft.gridy = 0;
				UpperRight.gridx = 2;
				UpperRight.gridy = 0;
				LowerRight.gridx = 2;
				LowerRight.gridy = 1;
				LowerLeft.gridx = 0;
				LowerLeft.gridy = 1;
				UpperMid.gridx = 1;
				UpperMid.gridy = 0;
				LowerMid.gridx = 1;
				LowerMid.gridy = 1;
				font = new Font("arial", Font.PLAIN, frame.getWidth() / 80);//serif? choose font
				text.setFont(font);
				text1.setFont(font);
				send.setFont(font);
			}
		});

		frame.addWindowStateListener(new WindowStateListener(){
			@Override
			public void windowStateChanged(WindowEvent e){
				try{
					gbl.columnWidths = new int[]{ (frame.getWidth() / 32) * 4, (frame.getWidth() / 32) * 24,
							(frame.getWidth() / 32) * 4 };//column ratios: 1/8 : 3/4 : 1/8
					gbl.rowHeights = new int[]{ (frame.getHeight() / 8) * 7, frame.getHeight() / 8 };//row ratios: 7/8 : 1/8
					if(color != Color.BLACK)
						appendToPane(side, "\nResizing", Color.BLACK);
					else
						appendToPane(side, "\nResizing...", Color.WHITE);
					panel.setLayout(gbl);
					UpperLeft.gridx = 0;
					UpperLeft.gridy = 0;
					UpperRight.gridx = 2;
					UpperRight.gridy = 0;
					LowerRight.gridx = 2;
					LowerRight.gridy = 1;
					LowerLeft.gridx = 0;
					LowerLeft.gridy = 1;
					UpperMid.gridx = 1;
					UpperMid.gridy = 0;
					LowerMid.gridx = 1;
					LowerMid.gridy = 1;
					font = new Font("arial", Font.PLAIN, frame.getWidth() / 80);
					text.setFont(font);
					text1.setFont(font);
					send.setFont(font);
				} catch(Exception ex){

				}
			}
		});

		//Get Username, Max 10 Chars, and Color
		username = getUsername();
		color = getColor();
		if(username.length() > 10)
			JOptionPane.showMessageDialog(frame, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
		//Create local contact with information and try to obtain local ip
		try{
			contact = new Contact(username, InetAddress.getLocalHost(), color);

		} catch(UnknownHostException e1){
			appendToPane(side, "Please Connect to a local network", Color.RED);
			e1.printStackTrace();
		}

		//Add Components
		showUsername();
		writeMessage();
		makeMessageBox();
		contacts();
		sideBar();
		sendButton();

		//Display Frame
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static String getUsername(){//Receive input for username
		return JOptionPane.showInputDialog("Enter your Username: ");
	}

	public static Color getColor(){//Receive input for color
		String[] colors = { "Black", "Green", "Red", "Blue", "Cyan", "Magenta", "Yellow", "Pink" };
		String s = (String) JOptionPane.showInputDialog(frame, "Which color?", "Color Select",
				JOptionPane.PLAIN_MESSAGE, null, colors, "");
		Color cyan = new Color(51, 204, 255);
		Color green = new Color(74, 193, 56);
		Color red = new Color(208, 13, 13);
		Color blue = new Color(51, 51, 255);
		Color pink = new Color(255, 0, 213);
		if(s.equals("Green"))
			return green;
		if(s.equals("Red"))
			return red;
		if(s.equals("Blue"))
			return blue;
		if(s.equals("Cyan"))
			return cyan;
		if(s.equals("Magenta"))
			return Color.MAGENTA;
		if(s.equals("Yellow"))
			return Color.YELLOW;
		if(s.equals("Pink"))
			return pink;
		return Color.BLACK;
	}

	public static void showUsername(){//Display username on the left

		text1.setFont(font);
		text1.setText(username + ":  ");
		text1.setBackground(background);
		text1.setEditable(false);
		LowerLeft.fill = GridBagConstraints.BOTH;
		panel.add(text1, LowerLeft);
	}

	@SuppressWarnings("serial")
	public static void writeMessage(){//Create box (text) where you type message
		LowerMid.fill = GridBagConstraints.BOTH;
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 0, Color.BLACK);
		text.setBorder(border);
		//Detect if enter key is hit in text
		text.setFocusable(true);
		int condition = JComponent.WHEN_FOCUSED;
		InputMap iMap = text.getInputMap(condition);
		ActionMap aMap = text.getActionMap();

		String enter = "enter";
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		aMap.put(enter, new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(color != Color.BLACK)
					appendToPane(side, "\nSent", Color.BLACK);
				else
					appendToPane(side, "\nSent", Color.WHITE);
				displayMessage(new Message(text.getText(), contact));
				MultiThreadClient.pullMessage(new Message(text.getText(), contact));
				text.setText("");
			}
		});
		text.setFont(font);
		panel.add(text, LowerMid);
	}

	public static void sendButton(){//Create Send Button
		send = new JButton(new AbstractAction("Send"){
			@Override
			public void actionPerformed(ActionEvent e){
				/**
				 * if(color != Color.BLACK) appendToPane(side, "\nSent",
				 * Color.BLACK); else appendToPane(side, "\nSent", Color.WHITE);
				 */
				displayMessage(new Message(text.getText(), contact));
				MultiThreadClient.pullMessage(new Message(text.getText(), contact));
				text.setText("");
			}
		});
		Border border = BorderFactory.createMatteBorder(2, 0, 2, 2, Color.BLACK);
		send.setBorder(border);
		send.setFont(font);
		send.setBackground(background);
		LowerRight.fill = GridBagConstraints.BOTH;
		panel.add(send, LowerRight);
	}

	public static void makeMessageBox(){//Create Message Window
		UpperMid.fill = GridBagConstraints.BOTH;
		messageBox.setFont(font);
		/**
		 * if(color != Color.BLACK) appendToPane(side, "\nLoaded", Color.BLACK);
		 * else appendToPane(side, "\nLoaded", Color.WHITE);
		 */
		JScrollPane sp = new JScrollPane(messageBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Border border = BorderFactory.createMatteBorder(2, 2, 0, 0, Color.BLACK);
		sp.setBorder(border);
		panel.add(sp, UpperMid);

	}

	public static void displayMessage(Message m){//Method to be used to display other players messages
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss a");
		String date = df.format(new Date());
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMMMM dd, YYYY");
		String month = monthFormat.format(new Date());

		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

		if(prevDay == Integer.parseInt(dayFormat.format(new Date()))){
			appendToPane(messageBox, "\n[", Color.BLACK);
			appendToPane(messageBox, m.getSender().getName(), m.getSender().getColor());
			appendToPane(messageBox, "] (" + date + ") : " + m.getText(), Color.BLACK);
		} else{
			appendToPane(messageBox, "\n" + month, Color.BLACK);
			appendToPane(messageBox, "\n[", Color.BLACK);
			appendToPane(messageBox, m.getSender().getName(), m.getSender().getColor());
			appendToPane(messageBox, "] (" + date + ") : " + m.getText(), Color.BLACK);
			prevDay = Integer.parseInt(dayFormat.format(new Date()));
		}
		playSound();

	}

	public static void contacts(){//Create left Field, currently unused
		UpperLeft.fill = GridBagConstraints.BOTH;
		JTextPane text = new JTextPane();
		appendToPane(text, "Currently Online", Color.GREEN);
		/**
		 * for(Contact c : contacts){ if(color != Color.BLACK)
		 * appendToPane(text, "\n" + c.getName() + ": " + c.getAddress(),
		 * Color.BLACK); else appendToPane(text, "\n" + c.getName() + ": " +
		 * c.getAddress(), Color.WHITE); }
		 */
		text.setEditable(false);
		text.setBackground(color);
		JScrollPane sp = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(sp, UpperLeft);
	}

	public static void sideBar(){//Create side bar, used as a status reminder
		UpperRight.fill = GridBagConstraints.BOTH;
		side.setFont(font);
		side.setBackground(color);
		JScrollPane sp = new JScrollPane(side, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(sp, UpperRight);
	}

	public static void appendToPane(JTextPane tp, String msg, Color c){//Appends a string to text pane with color of choice
		tp.setEditable(true);
		tp.setFont(font);
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "arial");//previously Lucida Console
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		tp.replaceSelection(msg);
		tp.setEditable(false);
	}

	public static void playSound(){
		try{
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("audio/alert.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex){
			System.err.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

}