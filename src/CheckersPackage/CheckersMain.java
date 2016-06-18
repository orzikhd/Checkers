package CheckersPackage;

import javax.swing.JFrame;

import javax.swing.WindowConstants;

import java.awt.*; 

public class CheckersMain {
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("Checkers!");
		
		final Dimension screenSize = new Dimension(1524, 1024);
		
		//terminate app when closed
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(screenSize);
		frame.setResizable(false);
		frame.setBackground(Color.CYAN);
		
		CheckersPanel checkersWrapper = new CheckersPanel();
		checkersWrapper.setBackground(Color.BLACK);
		frame.add(checkersWrapper);
		
		frame.pack();
		frame.setVisible(true);
	}
}

