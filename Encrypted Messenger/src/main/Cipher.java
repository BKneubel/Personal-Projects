/* Matt Zenko
 * APCS
 * 12/8/2016
 * Encryption and Decryption program using a modified Vigenère cipher
 */
package main;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Cipher extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//Eclipse made me do it!

	//Code Vars
	public static String cipher, toAnalyze, endResult, mixedAlpha;//Declare variables
	
	/*The characters the program can use. ¶, µ, and § represent new line, tab, and " "(space) respectively.
	 * Automatically adding the characters used in the message if they do not already exist and inserting that information in the resulting coded message is still a WIP
	 */
	public static String masterAlpha = "¶µ§abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-+;:ABCDE'FGHIJKLMNOPQRSTUVWXYZ{}[]\\/?<>,.`~\"";
	
	//Frame Vars
	private JPanel contentPane;
	private JTextField textField;
	public static Cipher frame = new Cipher();
	public static ShowResult frame2 = new ShowResult();
	//

	public static void tool(Boolean b){//Makes the second window pop up with correct text
		mixedAlpha = mixer(cipher);//mixes up the alphabet that will be used based on the keyword
		if(b) endResult = decode();//decodes or encodes text based on the parameters given
		else endResult = encode();
		frame2.changeLabel(readability(cipher, false));//changes the text on the second window to be correct
		frame2.changeTextPane(endResult);
		frame2.setVisible(true);//makes the second window appear
	}
	public static String readability(String s, Boolean in){//this method makes the output more readable, and changes inputs to friendlier characters if need be
		String toReturn;
		if(in) toReturn = (((s.replace(' ', '§')).replaceAll("\n", "¶")).replaceAll("\t", "µ"));
		else toReturn = (((s.replace('§', ' ')).replaceAll("¶", "\n")).replaceAll("µ", "\t"));
		return toReturn;
	}
	public static String mixer(String keyword){//This mixes up the alphabet string used based on the cipher keyword given
		int hash = keyword.hashCode();//Generates a hashcode based on the keyword
		HashMap<Integer, Character> masterMap = new HashMap<Integer, Character>();//Hashmap that associates characters with their ascii value
		HashMap<Double, Character> moddedMap = new HashMap<Double, Character>();//Hashmap that will associate characters with a generated double
		for(int i = 0; i < masterAlpha.length(); i++){
			masterMap.put((int)masterAlpha.charAt(i), masterAlpha.charAt(i));//populates masterMap
		}
		Iterator entries = masterMap.entrySet().iterator();//Iterates through the masterMap, populating the second hashmap using a sine function.
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    Double key = (double)((Integer) entry.getKey()).intValue();
		    Character value = (Character)entry.getValue();
		    moddedMap.put(Math.sin((hash * Math.pow(key, 0.75))), value); //This will pseudo-randomize the values
		}
		Double[] keys = moddedMap.keySet().toArray(new Double[masterAlpha.length()]);//Converts set of the pseudo-random keys into an array
		Arrays.sort(keys);//Sorts the array from low to high
		Character[] scrambled = new Character[keys.length];
		for(int i = 0; i < keys.length; i++){//Populates array by taking the pseudo-random value and looking up what character it is associated with in moddedMap
			scrambled[i] = moddedMap.get(keys[i]);
		}
		StringBuilder sb = new StringBuilder(scrambled.length);//String builder that will contain the Characters within the Character[]
		for(Character c : scrambled)//Builds string
			sb.append(c);
		return sb.toString();//Converts stringbuilder to string, returns result
	}
	public static String decode(){//The method decoding the message
		char[][] alphas = genAlphas(cipher);//generates the 'alphabets' that correspond to the cipher
		char[] decoded = new char[toAnalyze.length()];//an array that will hold the decoded characters
		String[] alphaStrings = new String[cipher.length()];
		for(int i = 0; i < cipher.length(); i++){//converts the 2d character array to a 1d string array
			String temp = new String(alphas[i]);//Converts the decoded array to a string
			alphaStrings[i] = temp;
		}
		for(int i = 0; i < toAnalyze.length(); i++){//for loop that decodes the message a character at a time
			decoded[i] = mixedAlpha.charAt(alphaStrings[i%cipher.length()].indexOf(toAnalyze.charAt(i)));
		}
		String output = readability((new String(decoded)), false);//makes the output look better
		return output;
	}
	public static String encode(){//The method encoding the message
		char[][] alphas = genAlphas(cipher);//generates the 'alphabets' that correspond to the cipher
		char[] encoded = new char[toAnalyze.length()];//an array that will hold the encoded characters
		for(int i = 0; i < toAnalyze.length(); i++)//for loop that encodes the message a character at a time
			encoded[i] = alphas[i%cipher.length()][mixedAlpha.indexOf(toAnalyze.charAt(i))];
		String output = new String(encoded);//Converts the encoded array to a string
		return output;	
	}
	public static char[][] genAlphas(String cipher){//This method will generate a 2d char array with as many 'alphabets' as the cipher has characters in it
		char[][] alphas = new char[cipher.length()][mixedAlpha.length()];//Declares the char[][]
		for(int i = 0; i < cipher.length(); i++){//for loop that cycles through the char[][]
			for (int k = 0; k < mixedAlpha.length(); k++){//for loop that cycles through the selected char[] within the char[][]
				alphas[i][k] = mixedAlpha.charAt((k + mixedAlpha.indexOf(cipher.charAt(i)))%mixedAlpha.length());//Shifts characters in the 'alphabet' to form the Vigenère square
			}
		}
		return alphas;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {//launches the two windows, keeping the second hidden
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					frame.setVisible(true);
					frame2.setVisible(false);//this keeps the second hidden
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cipher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//framework within the first window
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourCipher = new JLabel("Your cipher:");
		lblYourCipher.setBounds(10, 11, 74, 14);
		contentPane.add(lblYourCipher);
		
		textField = new JTextField();
		textField.setBounds(76, 8, 199, 20); //The box you type your cipher into
		contentPane.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnEncode = new JRadioButton("Encode");
		rdbtnEncode.setBounds(315, 7, 109, 23);
		contentPane.add(rdbtnEncode);
		
		JRadioButton rdbtnDecode = new JRadioButton("Decode");
		rdbtnDecode.setBounds(315, 27, 109, 23);
		contentPane.add(rdbtnDecode);
		
		ButtonGroup group = new ButtonGroup();//makes it so either encode or decode can be selected, but not both
		group.add(rdbtnEncode);
		group.add(rdbtnDecode);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 414, 163);//Lets user scroll through their text
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);//the box you type your message text into
		textArea.setLineWrap(true);//Makes it look nicer with wrappable text
		textArea.setWrapStyleWord(true);
		
		JLabel lblYourText = new JLabel("Your Text");
		scrollPane.setColumnHeaderView(lblYourText);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((rdbtnDecode.isSelected()||rdbtnEncode.isSelected()) && (!(textArea.getText()).equals("")) && (!(textField.getText()).equals(""))){//If there is text in all fields, and encode or decode has been selected
					cipher = readability(textField.getText(), true);
					toAnalyze = readability(textArea.getText(), true);
					if (rdbtnDecode.isSelected()) tool(true);//Opens the second window in decode mode
					else tool(false);//Opens the seconds window in encode mode
					Window w = SwingUtilities.getWindowAncestor(contentPane); w.dispose();//closes the first (current) window
				}
			}
		});
		btnContinue.setBounds(335, 227, 89, 23);
		contentPane.add(btnContinue);
	}
}

class ShowResult extends JFrame {//The class of the second window

	public static JLabel lblYourT = new JLabel("your text here");//The text field that reminds the user of their cipher (declaration)
	public static JTextArea textArea = new JTextArea();//The area showing the user their result (declaration)
	private static final long serialVersionUID = 1L;//Eclipse made me do it!
	private JPanel contentPane;
	public ShowResult() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Framework for the window
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCipher = new JLabel("The cipher used:");
		lblCipher.setBounds(10, 11, 110, 14);
		contentPane.add(lblCipher);
		
		lblYourT.setBounds(120, 11, 285, 14);//adds the text field containing the cipher to the window
		contentPane.add(lblYourT);
		
		JScrollPane scrollPane = new JScrollPane();//Makes result message scrollable
		scrollPane.setBounds(20, 36, 392, 214);
		contentPane.add(scrollPane);
		
		JLabel lblYourResult = new JLabel("Your Result:");//Contains the resultant message
		scrollPane.setColumnHeaderView(lblYourResult);
		
		textArea.setEditable(false);//Can't accidentally change the output
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);//Makes it look nicer with wrappable text
		textArea.setWrapStyleWord(true);
		
	}
	//Methods that are called within the first class to change stuff on the second window
	public void changeLabel(String s) {//Sets the cipher label
		lblYourT.setText(s);
	}
	public void changeTextPane(String s){
		textArea.setText(s);	//Sets the output text box
	}

}