package CheckersPackage;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.*; 

public class CheckersMain {
	public static void main(String args[]) {
		CheckersModel masterModel = new CheckersModel();
		masterModel.setUpBoard();
		List<Location> currState = masterModel.getBoardState();
		
		
		Dimension screenSize = new Dimension(1024, 1024);
		
		JFrame frame = new JFrame("Checkers!");
		//terminate app when closed
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(screenSize);
		
		JPanel boardWrapper = new JPanel();
		boardWrapper.setLayout(new GridLayout(8, 8));
		frame.add(boardWrapper);
		
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
				TextField pieceName = new TextField();
				pieceName.setText("Team: " + currLoc.getPieceTeamColor());
				currLocPanel.add(pieceName);
			}
			
			boardWrapper.add(currLocPanel);
		}
		
		
		frame.pack();
		frame.setVisible(true);
	}
}
