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
	
	private Location checker;
	private JPanel checkerPanel;
	
	public CheckerListener(Location checker, JPanel checkerPanel) {
		this.checker = checker;
		this.checkerPanel = checkerPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(this.checker.getPieceTeamColor());
		LineBorder checkerBorder = (LineBorder) this.checkerPanel.getBorder();
		if (checkerBorder.getLineColor().equals(Color.PINK)) {
				this.checkerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		} else {
			this.checkerPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 4));
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
