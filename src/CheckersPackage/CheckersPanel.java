package CheckersPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * CheckersPanel handles all logic relevant to maintaining the Checkers Game, including its board and menu
 */
public class CheckersPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8107067215852039233L;
	private JPanel boardWrapper;
	private List<JPanel> tiles;
	private MenuPanel menu;
	private CheckersModel masterModel;
	private boolean needsRedraw;
	
	public CheckersPanel() {
		super();

		this.boardWrapper = new JPanel();
		this.masterModel = new CheckersModel();
		this.menu = new MenuPanel(masterModel, this);
		this.tiles = new ArrayList<JPanel>();
		
		masterModel.setUpBoard();
		
		boardWrapper.setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 64; i++) {
			JPanel currLocPanel = new JPanel();
			CheckerListener currListener = new CheckerListener(this, i);
			currLocPanel.addMouseListener(currListener);
			tiles.add(currLocPanel);
			boardWrapper.add(currLocPanel);
		}
		
		boardWrapper.validate();
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
		
		//boardWrapper.removeAll();
		int i = 0;
		for (Location currLoc : currState) {
			JPanel currLocPanel = this.tiles.get(i);
			currLocPanel.removeAll();
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
			}
			
			i++;
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
	
	//package-private
	JPanel getTileAtIndex(int index) {
		return this.tiles.get(index);
	}
	
	//package-private
	Location getLocationAtIndex(int index) {
		return this.masterModel.getBoardState().get(index);
	}
}
