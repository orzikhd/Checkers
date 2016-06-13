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
	
	private int boardIndex;
	private CheckersPanel masterPanel;
	
	public CheckerListener(CheckersPanel masterPanel, int boardIndex) {
		this.boardIndex = boardIndex;
		this.masterPanel = masterPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Location checker = masterPanel.getLocationAtIndex(this.boardIndex);
		JPanel checkerPanel = masterPanel.getTileAtIndex(this.boardIndex);
		
		clearAllOtherBorders();
		boolean valid = masterPanel.validSelection(this.boardIndex);
		
		if (valid) {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, CheckerListener.BORDER_THICKNESS));
		} else {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, CheckerListener.BORDER_THICKNESS));
		}
		
		System.out.println(checker.getPieceTeamColor());
		/*
		LineBorder checkerBorder = (LineBorder) checkerPanel.getBorder();
		if (checkerBorder.getLineColor().equals(Color.PINK)) {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, CheckerListener.BORDER_THICKNESS));
		} else {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, CheckerListener.BORDER_THICKNESS));
		}
		*/
	}
	
	//clear all borders not associated with current clicked piece; i.e. only one piece highlighted at a time
	private void clearAllOtherBorders() {
		for (int i = 0; i < 64; i++) {
			if (i != this.boardIndex) {
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
