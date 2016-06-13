package CheckersPackage;

import javax.swing.JFrame;

import javax.swing.WindowConstants;

import java.awt.*; 

public class CheckersMain {
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("Checkers!");
		
		final Dimension screenSize = new Dimension(1224, 1024);
		
		//terminate app when closed
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(screenSize);
		frame.setResizable(false);
		frame.setBackground(Color.CYAN);
		
		CheckersPanel checkersWrapper = new CheckersPanel();
		checkersWrapper.setBackground(Color.MAGENTA);
		frame.add(checkersWrapper);
		
		//menu.setBackground(Color.BLUE);
		//menu.setPreferredSize(new Dimension(200, 1024));

		//boardWrapper.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//boardWrapper.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight() - 32);
		
		frame.pack();
		frame.setVisible(true);
	}
}

