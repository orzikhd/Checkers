package CheckersPackage;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Listens for actions on a particular checker
 */
public class CheckerListener implements MouseListener{
	
	public static final int BORDER_THICKNESS = 4;
	private static final int DUMMY_COORDINATE = -1;
	private static final Color ERROR_COLOR = Color.WHITE;
	private static final Color VALID_SELECTION = Color.GREEN;
	private static final Color VALID_FOLLOWUP = Color.YELLOW;
	private static final Color INVALID_SELECTION = Color.PINK;
	private static final Color INVALID_FOLLOWUP = Color.GRAY;
	
	private CheckersPanel masterPanel;
	//currentSquare is the square clicked to begin an action
	private int currentSquareX;
	private int currentSquareY;
	//followUpSquare is the square clicked to follow up/finish an action
	private int followUpSquareX;
	private int followUpSquareY;
	
	public CheckerListener(CheckersPanel masterPanel) {
		this.masterPanel = masterPanel;
		this.currentSquareX = CheckerListener.DUMMY_COORDINATE;
		this.currentSquareY = CheckerListener.DUMMY_COORDINATE;
		this.followUpSquareX = CheckerListener.DUMMY_COORDINATE;
		this.followUpSquareY = CheckerListener.DUMMY_COORDINATE;
	}
	
	/**
	 * Notify CheckerListener to forget whatever its thinking about
	 */
	public void resetAllTracking() {
		this.currentSquareX = CheckerListener.DUMMY_COORDINATE;
		this.currentSquareY = CheckerListener.DUMMY_COORDINATE;
		this.followUpSquareX = CheckerListener.DUMMY_COORDINATE;
		this.followUpSquareY = CheckerListener.DUMMY_COORDINATE;		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int squareWidth = this.masterPanel.getBoardWidth() / 8;
				
		System.out.println(e.getX() / squareWidth + ", " +  e.getY() / squareWidth);
		
		int justPressedX = e.getX() / squareWidth;
		int justPressedY = e.getY() / squareWidth;
				
		if (justPressedX == this.currentSquareX && justPressedY == this.currentSquareY) {
			//situation: they pressed on their original selection again, clearing it
			this.clearSelectionBorders();
			this.currentSquareX = CheckerListener.DUMMY_COORDINATE;
			this.currentSquareY = CheckerListener.DUMMY_COORDINATE;
			
		} else if (this.currentSquareX == CheckerListener.DUMMY_COORDINATE && this.currentSquareY == CheckerListener.DUMMY_COORDINATE) {
			//situation: they pressed on a square while nothing was selected, selecting a new current
			this.currentSquareX = justPressedX;
			this.currentSquareY = justPressedY;
			this.clearAllOtherBorders();
			this.setCurrentBorder();	
			
		} else if (justPressedX == this.followUpSquareX && justPressedY == this.followUpSquareY) {
			//situation: they pressed on the followUpSquare again, confirming the move
			int currIndex = 8 * this.currentSquareY + this.currentSquareX;
			int followIndex = 8 * this.followUpSquareY + this.followUpSquareX;
			
			//the move is actually valid-- execute it
			if (this.masterPanel.validMove(currIndex, followIndex)) {
				this.clearAllOtherBorders();
				this.clearSelectionBorders();
				
				this.masterPanel.requestMove(currIndex, followIndex);
				this.currentSquareX = CheckerListener.DUMMY_COORDINATE;
				this.currentSquareY = CheckerListener.DUMMY_COORDINATE;
				this.followUpSquareX = CheckerListener.DUMMY_COORDINATE;
				this.followUpSquareY = CheckerListener.DUMMY_COORDINATE;
				
				/*
				this.currentSquareX = this.followUpSquareX;
				this.currentSquareY = this.followUpSquareY;
				this.followUpSquareX = CheckerListener.DUMMY_COORDINATE;
				this.followUpSquareY = CheckerListener.DUMMY_COORDINATE;
				System.out.println("setting current border on: " + this.currentSquareX + ", " + this.currentSquareY);
				this.setCurrentBorder();
				this.masterPanel.repaint();
				*/
			}

		} else {
			//situation: they pressed somewhere new from both squares, signifying a new followup square
			this.clearSelectionBorders();
			this.followUpSquareX = justPressedX;
			this.followUpSquareY = justPressedY;
			this.setCurrentBorder();
			this.setFollowUpBorder();
		}
		
		
		/*
		int boardIndex = 8 * this.currentSquareY + currentSquareX;
		
		
		Location checker = masterPanel.getLocationAtIndex(boardIndex);
		JPanel checkerPanel = masterPanel.getTileAtIndex(boardIndex);
		
		clearAllOtherBorders();
		boolean valid = masterPanel.validSelection(boardIndex);
		
		if (valid) {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, CheckerListener.BORDER_THICKNESS));
		} else {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, CheckerListener.BORDER_THICKNESS));
		}
		*/
		//System.out.println(checker.getPieceTeamColor());
	}
	
	//set the border on the current square based on validitity etc
	private void setCurrentBorder() {
		int boardIndex = 8 * this.currentSquareY + this.currentSquareX;
		
		JPanel checkerPanel = masterPanel.getTileAtIndex(boardIndex);
		
		boolean valid = masterPanel.validSelection(boardIndex);
		
		if (valid) {
			checkerPanel.setBorder(BorderFactory.createLineBorder(CheckerListener.VALID_SELECTION, CheckerListener.BORDER_THICKNESS));
		} else {
			checkerPanel.setBorder(BorderFactory.createLineBorder(CheckerListener.INVALID_SELECTION, CheckerListener.BORDER_THICKNESS));
		}
	}
	
	//set the border on the follow up square based on validitity etc
	private void setFollowUpBorder() {
		int currIndex = 8 * this.currentSquareY + this.currentSquareX;
		int followIndex = 8 * this.followUpSquareY + this.followUpSquareX;
		
		JPanel checkerPanel = masterPanel.getTileAtIndex(followIndex);
		
		boolean valid = masterPanel.validMove(currIndex, followIndex);
		
		if (valid) {
			checkerPanel.setBorder(BorderFactory.createLineBorder(CheckerListener.VALID_FOLLOWUP, CheckerListener.BORDER_THICKNESS));
		} else {
			checkerPanel.setBorder(BorderFactory.createLineBorder(CheckerListener.INVALID_FOLLOWUP, CheckerListener.BORDER_THICKNESS));
		}		
	}
	
	//clear all borders not associated with current clicked piece; i.e. only one piece highlighted at a time
	private void clearAllOtherBorders() {
		
		int boardIndex = 8 * this.currentSquareY + this.currentSquareX;
		
		for (int i = 0; i < 64; i++) {
			if (i != boardIndex) {
				resetBorderAt(i);
			}
		}
	}
	
	//clear borders on current and followup selection
	private void clearSelectionBorders() {
		int currentIndex = 8 * this.currentSquareY + this.currentSquareX;
		int followUpIndex = 8 * this.followUpSquareY + this.followUpSquareX;
		
		resetBorderAt(currentIndex);
		
		//check that it is a valid tile to clear -- this can be called when followup is empty
		if (followUpIndex != 8 * CheckerListener.DUMMY_COORDINATE + CheckerListener.DUMMY_COORDINATE) {
			resetBorderAt(followUpIndex);
		}
	}
	
	//resets border at a given index
	private void resetBorderAt(int index) {
		Location checker = masterPanel.getLocationAtIndex(index);
		JPanel checkerPanel = masterPanel.getTileAtIndex(index);
		
		if (checker.getTileColor().equals("R")) {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.RED, CheckerListener.BORDER_THICKNESS));
		} else if (checker.getTileColor().equals("B")) {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, CheckerListener.BORDER_THICKNESS));
		} else {
			checkerPanel.setBorder(BorderFactory.createLineBorder(CheckerListener.ERROR_COLOR, CheckerListener.BORDER_THICKNESS));
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
