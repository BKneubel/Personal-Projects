package biggest;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class gridBagTesting {

	public static void main(String[] args) {
		JFrame frame = new JFrame("TESTING");
		frame.setSize(1000, 1000);
		JPanel pane = new JPanel();
		frame.add(pane);
		GridBagLayout gbl = new GridBagLayout();
		pane.setLayout(gbl);
		GridBagConstraints Up = new GridBagConstraints();
		GridBagConstraints Down = new GridBagConstraints();
		
		gbl.columnWidths = new int[]{1000};
		gbl.rowHeights = new int[]{500,500};
		Up.gridx = 0; Up.gridy = 0;
		Down.gridx = 0; Down.gridy = 1;
		Up.fill = GridBagConstraints.BOTH;
		Down.fill = GridBagConstraints.BOTH;
		JTextField text1 = new JTextField();
		text1.setBackground(Color.GREEN);
		JTextField text2 = new JTextField();
		text2.setBackground(Color.BLUE);
		
		pane.add(text1,Up);
		pane.add(text2,Down);
		frame.setVisible(true);
		

	}

}
