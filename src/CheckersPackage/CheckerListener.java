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
	
	private CheckersPanel masterPanel;
	private int currentSquareX;
	private int currentSquareY;
	private int previousSquareX;
	private int previousSqaureY;
	
	public CheckerListener(CheckersPanel masterPanel) {
		this.masterPanel = masterPanel;
		this.currentSquareX = 100; //some dummy value
		this.currentSquareY = 100; //some dummy value
		this.previousSquareX = 100;
		this.previousSqaureY = 100;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		int squareWidth = this.masterPanel.getBoardWidth() / 8;
		
		System.out.println(e.getX() / squareWidth + ", " +  e.getY() / squareWidth);
		
		this.currentSquareX = e.getX() / squareWidth;
		this.currentSquareY = e.getY() / squareWidth;
		
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
		
		//System.out.println(checker.getPieceTeamColor());
	}
	
	//clear all borders not associated with current clicked piece; i.e. only one piece highlighted at a time
	private void clearAllOtherBorders() {
		
		int boardIndex = 8 * this.currentSquareY + currentSquareX;
		
		for (int i = 0; i < 64; i++) {
			if (i != boardIndex) {
				Location checker = masterPanel.getLocationAtIndex(i);
				JPanel checkerPanel = masterPanel.getTileAtIndex(i);
				//System.out.println(checker.getTileColor());
				if (checker.getTileColor().equals("R")) {
					checkerPanel.setBorder(BorderFactory.createLineBorder(Color.RED, CheckerListener.BORDER_THICKNESS));
				} else if (checker.getTileColor().equals("B")) {
					checkerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, CheckerListener.BORDER_THICKNESS));
				} else {
					checkerPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, CheckerListener.BORDER_THICKNESS));
				}
			}
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
