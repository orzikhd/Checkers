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
		
		System.out.println(checker.getPieceTeamColor());
		LineBorder checkerBorder = (LineBorder) checkerPanel.getBorder();
		if (checkerBorder.getLineColor().equals(Color.PINK)) {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		} else {
			checkerPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 4));
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
