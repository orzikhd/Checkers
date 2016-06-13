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
	
	private static JFrame frame = new JFrame("Checkers!");
	private static JPanel boardWrapper = new JPanel();
	private static JPanel menu = new JPanel();
	private static final Dimension screenSize = new Dimension(1424, 1024);
	
	public static void main(String args[]) {
		CheckersModel masterModel = new CheckersModel();
		masterModel.setUpBoard();
		List<Location> currState = masterModel.getBoardState();
		
		//terminate app when closed
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(screenSize);
		frame.setResizable(false);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		frame.add(boardWrapper, c);
		c.gridx = 1;
		frame.add(menu, c);
		menu.setBackground(Color.BLUE);

		boardWrapper.setLayout(new GridLayout(8, 8));
		boardWrapper.setBackground(Color.GREEN);
		//boardWrapper.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//boardWrapper.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight() - 32);
		drawState(currState);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void drawState(List<Location> currState) {
		
		boardWrapper.removeAll();
		
		for (Location currLoc : currState) {
			JPanel currLocPanel = new JPanel();
			if (currLoc.getTileColor().equals("R")) {
				currLocPanel.setBackground(Color.RED);
			} else if (currLoc.getTileColor().equals("B")) {
				currLocPanel.setBackground(Color.BLACK);
			} else {
				currLocPanel.setBackground(Color.YELLOW);
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
				pieceName.setMaximumSize(pieceName.getParent().getSize());
				System.out.println(pieceName.getParent().getSize());
			}
			
			boardWrapper.add(currLocPanel);
		}
	}
}
