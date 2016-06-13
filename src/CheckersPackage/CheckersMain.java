package CheckersPackage;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import java.awt.*; 

public class CheckersMain {
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("Checkers!");
		JPanel boardWrapper = new JPanel();
		MenuPanel menu = new MenuPanel();
		final Dimension screenSize = new Dimension(1224, 1024);
		CheckersModel masterModel = new CheckersModel();
		masterModel.setUpBoard();
		List<Location> currState = masterModel.getBoardState();
		
		//terminate app when closed
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(screenSize);
		frame.setResizable(false);
		frame.setBackground(Color.CYAN);
		/*
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20, 20, 20, 0);
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 8;

		frame.add(boardWrapper, c);
		c.gridx = 9;
		c.gridwidth = 4;
		c.insets = new Insets(0, 0, 0, 0);
		frame.add(menu, c);
		*/
		
		frame.add(boardWrapper, BorderLayout.WEST);
		frame.add(menu, BorderLayout.CENTER);
		
		//menu.setBackground(Color.BLUE);
		//menu.setPreferredSize(new Dimension(200, 1024));
		boardWrapper.setLayout(new GridLayout(8, 8));
		boardWrapper.setBackground(Color.GREEN);
		//boardWrapper.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//boardWrapper.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight() - 32);
		drawState(currState, boardWrapper);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void drawState(List<Location> currState, JPanel boardWrapper) {
		
		boardWrapper.removeAll();
		
		for (Location currLoc : currState) {
			JPanel currLocPanel = new JPanel();
			if (currLoc.getTileColor().equals("R")) {
				currLocPanel.setBackground(Color.RED);
				currLocPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
			} else if (currLoc.getTileColor().equals("B")) {
				currLocPanel.setBackground(Color.BLACK);
				currLocPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
			} else {
				currLocPanel.setBackground(Color.YELLOW);
				currLocPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
			}
			
			if (currLoc.getPieceTeamColor() != Location.NULL_TEAM_COLOR) {
				ImageIcon icon = null;
				if (currLoc.getPieceTeamColor() == 1) {
					icon = new ImageIcon("src/Images/BlackChecker.png");
				}
				if (currLoc.getPieceTeamColor() == 2) {
					icon = new ImageIcon("src/Images/RedChecker.png");
				}
				
				JLabel pieceName = new JLabel(icon);
				currLocPanel.add(pieceName);
				currLocPanel.addMouseListener(new CheckerListener(currLoc, currLocPanel));
			}

			boardWrapper.add(currLocPanel);
		}
	}
}
