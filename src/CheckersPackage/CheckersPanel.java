package CheckersPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private CheckerListener listener;
	private boolean needsRedraw;
	
	public CheckersPanel() {
		super();

		this.boardWrapper = new JPanel();
		this.boardWrapper.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4, true));
		this.masterModel = new CheckersModel();
		this.menu = new MenuPanel(masterModel, this);
		this.tiles = new ArrayList<JPanel>();
		this.listener = new CheckerListener(this);
		this.boardWrapper.addMouseListener(listener);
		
		masterModel.setUpBoard();
		
		boardWrapper.setLayout(new GridLayout(8, 8));
		for (int i = 0; i < 64; i++) {
			JPanel currLocPanel = new JPanel();
			tiles.add(currLocPanel);
			boardWrapper.add(currLocPanel);
		}
		boardWrapper.setPreferredSize(new Dimension(1024, 1024));
		boardWrapper.setBackground(Color.WHITE);
		boardWrapper.validate();
		
		menu.setBackground(Color.ORANGE);
		menu.setPreferredSize(new Dimension(500, 1024));
		menu.validate();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));	
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		this.add(boardWrapper);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
		this.add(menu);
		this.add(Box.createRigidArea(new Dimension(10, 0)));
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
	
	public String getStatusMessage() {
		return this.listener.getStatusMessage();
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
				currLocPanel.setBorder(BorderFactory.createLineBorder(Color.RED, CheckerListener.BORDER_THICKNESS));
			} else if (currLoc.getTileColor().equals("B")) {
				currLocPanel.setBackground(Color.BLACK);
				currLocPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, CheckerListener.BORDER_THICKNESS));
			} else {
				currLocPanel.setBackground(Color.YELLOW);
				currLocPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, CheckerListener.BORDER_THICKNESS));
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
		this.listener.resetAllTracking();
		this.repaint();
	}
	
	//switches the current player
	//package-private
	void switchPlayer() {
		this.masterModel.switchPlayer();
	}
	
	//Requests the model to execute a move
	//package-private
	void requestMove(int startIndex, int endIndex) {
		Location start = this.masterModel.getBoardState().get(startIndex);
		Location end = this.masterModel.getBoardState().get(endIndex);
		
		this.masterModel.movePiece(start, end);		
		this.requestUpdate();
	}
	
	
	//Determines if a move with these indices is valid
	//package-private
	boolean validMove(int startIndex, int endIndex) {
		Location start = this.masterModel.getBoardState().get(startIndex);
		Location end = this.masterModel.getBoardState().get(endIndex);
		
		return this.masterModel.checkValidMove(start, end);
	}			
			
	//return the tile of the index
	//package-private
	JPanel getTileAtIndex(int index) {
		return this.tiles.get(index);
	}
	
	//return the location data of the index
	//package-private
	Location getLocationAtIndex(int index) {
		return this.masterModel.getBoardState().get(index);
	}
	
	//return if this piece can be moved right now
	//package-private
	boolean validSelection(int index) {
		
		Location selection = this.masterModel.getBoardState().get(index);
		System.out.println(masterModel);
		return this.masterModel.checkValidSelection(selection);
	}
	
	/**
	 * Returns the width of just the checkerboard
	 * @return int width of the checkerboard
	 */
	public int getBoardWidth() {
		return this.boardWrapper.getWidth();
	}
}






