package CheckersPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * CheckersPanel handles all logic relevant to maintaining the Checkers Game, including its board and menu
 */
public class CheckersPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8107067215852039233L;
	private JPanel boardWrapper;
	private MenuPanel menu;
	private CheckersModel masterModel;
	private boolean needsRedraw;
	
	public CheckersPanel() {
		super();

		this.boardWrapper = new JPanel();
		this.masterModel = new CheckersModel();
		this.menu = new MenuPanel(masterModel, this);

		masterModel.setUpBoard();
		
		//this.remove(this.boardWrapper);
		//this.boardWrapper = new JPanel();
		boardWrapper.setLayout(new GridLayout(8, 8));
		boardWrapper.setBackground(Color.GREEN);
		menu.setBackground(Color.ORANGE);
				
		this.add(boardWrapper, BorderLayout.WEST);
		this.add(menu, BorderLayout.CENTER);
		this.needsRedraw = false;
		
		drawState();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.needsRedraw) {
			drawState();
			this.needsRedraw = false;
		}
	}
	
	//draws all locations from the masterModel
	private void drawState() {
		
		List<Location> currState = this.masterModel.getBoardState();
		
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
		boardWrapper.validate();
	}
	
	/**
	 * Requests the CheckersPanel to update the board
	 */
	public void requestUpdate() {
		this.needsRedraw = true;
		this.repaint();
	}
}