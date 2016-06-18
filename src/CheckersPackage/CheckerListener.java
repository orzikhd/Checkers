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
	private static final String RESET_MESSAGE = "Select piece";
	private static final String INVALID_SELECTION_MESSAGE = "You cannot move this piece";
	private static final String SELECT_FOLLOWUP_MESSAGE = "Choose where to move it";
	private static final String INVALID_FOLLOWUP_MESSAGE = "You cannot move here";
	private static final String CONFIRM_MOVE_MESSAGE = "Click again to confirm move";
	public static final String PLAYER1_WINS_MESSAGE = "Player 1 wins!";
	public static final String PLAYER2_WINS_MESSAGE = "Player 2 wins!";
	
	private CheckersPanel masterPanel;
	private String statusMessage;
	//currentSquare is the square clicked to begin an action
	private int currentSquareX;
	private int currentSquareY;
	//followUpSquare is the square clicked to follow up/finish an action
	private int followUpSquareX;
	private int followUpSquareY;
	
	public CheckerListener(CheckersPanel masterPanel) {
		this.masterPanel = masterPanel;
		this.statusMessage = CheckerListener.RESET_MESSAGE;
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
		this.statusMessage = CheckerListener.RESET_MESSAGE;
	}
	
	//package-private
	void declareVictor(int victor) {
		if (victor == CheckersModel.PLAYER1) {
			this.statusMessage = CheckerListener.PLAYER1_WINS_MESSAGE;
		} else {
			this.statusMessage = CheckerListener.PLAYER2_WINS_MESSAGE;
		}
	}
	
	//package-private
	String getStatusMessage() {
		return this.statusMessage;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int squareWidth = this.masterPanel.getBoardWidth() / 8;
				
		int justPressedX = e.getX() / squareWidth;
		int justPressedY = e.getY() / squareWidth;

		int justPressedIndex = 8 * justPressedY + justPressedX;
		int currentIndex = 8 * this.currentSquareY + this.currentSquareX;
		int followUpIndex = 8 * this.followUpSquareY + this.followUpSquareX;
		int dummyIndex = 8 * CheckerListener.DUMMY_COORDINATE + CheckerListener.DUMMY_COORDINATE;
		
		boolean justPressedValid = masterPanel.validSelection(justPressedIndex);
		boolean currentValid = false;
		
		//check that it exists on the board before checking if its valid
		if (currentIndex != dummyIndex) {
			currentValid = masterPanel.validSelection(currentIndex);
		}
		
		if (!currentValid) {
			//situation: a previous primary selection was invalid, so replace it
			this.clearSelectionBorders();
			this.currentSquareX = justPressedX;
			this.currentSquareY = justPressedY;
			this.setCurrentBorder();
			if (justPressedValid) {
				//and this is a valid primary selection
				this.statusMessage = CheckerListener.SELECT_FOLLOWUP_MESSAGE;
			} else {
				//and this is an invalid primary selection
				this.statusMessage = CheckerListener.INVALID_SELECTION_MESSAGE;
			}
			
		} else if (justPressedX == this.currentSquareX && justPressedY == this.currentSquareY) {
			//situation: they pressed on their original selection again, clearing it
			this.clearBorderAt(currentIndex);
			this.clearBorderAt(followUpIndex);
			this.currentSquareX = CheckerListener.DUMMY_COORDINATE;
			this.currentSquareY = CheckerListener.DUMMY_COORDINATE;
			this.statusMessage = CheckerListener.RESET_MESSAGE;
			
		} else if (justPressedX == this.followUpSquareX && justPressedY == this.followUpSquareY) {
			//situation: they pressed on the followUpSquare again
			//if the move is actually valid-- execute it
			if (this.masterPanel.validMove(currentIndex, followUpIndex)) {
							
				this.masterPanel.requestMove(currentIndex, followUpIndex);
				
				//check if the move was a jump to set justJumped
				boolean justJumped = Math.abs(justPressedX - this.currentSquareX) == 2;
				
				//if can jump again (i.e. combo) then keep going
				if (this.masterPanel.canJump(followUpIndex) && justJumped) {
					this.currentSquareX = justPressedX;
					this.currentSquareY = justPressedY;
					this.colorBorderAt(justPressedIndex, VALID_SELECTION);
					this.masterPanel.requestPreserve(justPressedIndex);
					System.out.println(currentSquareX + ", " + currentSquareY);
					this.statusMessage = CheckerListener.SELECT_FOLLOWUP_MESSAGE;
				} else {
					this.masterPanel.switchPlayer();
					this.currentSquareX = CheckerListener.DUMMY_COORDINATE;
					this.currentSquareY = CheckerListener.DUMMY_COORDINATE;
					this.statusMessage = CheckerListener.RESET_MESSAGE;
				}
				
				this.followUpSquareX = CheckerListener.DUMMY_COORDINATE;
				this.followUpSquareY = CheckerListener.DUMMY_COORDINATE;
				
			} else {
				//followUp was invalid so clear it
				this.clearBorderAt(followUpIndex);
				this.followUpSquareX = CheckerListener.DUMMY_COORDINATE;
				this.followUpSquareY = CheckerListener.DUMMY_COORDINATE;
				this.statusMessage = CheckerListener.INVALID_FOLLOWUP_MESSAGE;
			}
			
		} else {
			//situation: they pressed somewhere new from both squares, signifying a new followup square
			//check if its a valid move and notify appropriately
		
			this.clearBorderAt(currentIndex);
			this.clearBorderAt(followUpIndex);
			
			this.followUpSquareX = justPressedX;
			this.followUpSquareY = justPressedY;

			int currIndex = 8 * this.currentSquareY + this.currentSquareX;
			int followIndex = 8 * this.followUpSquareY + this.followUpSquareX;
			
			this.setCurrentBorder();
			this.setFollowUpBorder();
			if (this.masterPanel.validMove(currIndex, followIndex)) {
				this.statusMessage = CheckerListener.CONFIRM_MOVE_MESSAGE;
			} else {
				this.statusMessage = CheckerListener.INVALID_FOLLOWUP_MESSAGE;
			}
		}
		
		this.masterPanel.repaint();
	}
	
	//set the border on the current square based on validitity etc
	private void setCurrentBorder() {
		int currIndex = 8 * this.currentSquareY + this.currentSquareX;
		
		boolean valid = masterPanel.validSelection(currIndex);
		
		if (valid) {
			this.colorBorderAt(currIndex, CheckerListener.VALID_SELECTION);
		} else {
			this.colorBorderAt(currIndex, CheckerListener.INVALID_SELECTION);
		}
	}
	
	//set the border on the follow up square based on validitity etc
	private void setFollowUpBorder() {
		int currIndex = 8 * this.currentSquareY + this.currentSquareX;
		int followIndex = 8 * this.followUpSquareY + this.followUpSquareX;
		
		boolean valid = masterPanel.validMove(currIndex, followIndex);
		
		if (valid) {
			this.colorBorderAt(followIndex, CheckerListener.VALID_FOLLOWUP);
		} else {
			this.colorBorderAt(followIndex, CheckerListener.INVALID_FOLLOWUP);
		}		
	}
	
	//clear borders on current and followup selection
	private void clearSelectionBorders() {
		int currentIndex = 8 * this.currentSquareY + this.currentSquareX;
		int followUpIndex = 8 * this.followUpSquareY + this.followUpSquareX;
		this.clearBorderAt(currentIndex);
		this.clearBorderAt(followUpIndex);
	}
	
	//clears border at a given index if it is valid
	private void clearBorderAt(int index) {
		
		if (index != 8 * CheckerListener.DUMMY_COORDINATE + CheckerListener.DUMMY_COORDINATE) {
			Location checker = masterPanel.getLocationAtIndex(index);
			
			if (checker.getTileColor().equals("R")) {
				this.colorBorderAt(index, Color.RED);
			} else if (checker.getTileColor().equals("B")) {
				this.colorBorderAt(index, Color.BLACK);
			} else {
				this.colorBorderAt(index, CheckerListener.ERROR_COLOR);
			}
		}
	}
	
	//color border of panel at given index the given color
	private void colorBorderAt(int index, Color borderColor) {
		JPanel checkerPanel = masterPanel.getTileAtIndex(index);
		checkerPanel.setBorder(BorderFactory.createLineBorder(borderColor, CheckerListener.BORDER_THICKNESS));
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
