package CheckersPackage;

import java.util.HashSet;
import java.util.Set;

/**
 * Handles data related to a game of Checkers
 */
public class CheckersModel {
	
	private CheckersBoard board;
	private Set<BiColorPiece> p1Pieces;
	private Set<BiColorPiece> p2Pieces;
	private static final int LENGTH_CHECKERS_BOARD = 8;
	
	/**
	 * Models a Checkers Board of given length
	 * @param length of checkers board
	 */
	public CheckersModel() {
		this.board = new CheckersBoard(LENGTH_CHECKERS_BOARD, "RED", "BLACK");
		
		this.p1Pieces = new HashSet<BiColorPiece>();
		this.p2Pieces = new HashSet<BiColorPiece>();
		
		BiColorPiece curr;
		for (int i = 0; i < LENGTH_CHECKERS_BOARD; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				curr = (BiColorPiece) board.getPieceAtLocation(i, j);
				if (curr.getColor()) {
					p1Pieces.add(curr);
				} else {
					p2Pieces.add(curr);
				}
			}
		}
	}
	
	/**
	 * Returns an int count of the number of pieces player 1 has left on the board.
	 * @return int count number of pieces left for player 1
	 */
	public int countPlayer1() {
		return p1Pieces.size();
	}
	
	/**
	 * Returns an int count of the number of pieces player 2 has left on the board.
	 * @return int count number of pieces left for player 2
	 */
	public int countPlayer2() {
		return p2Pieces.size();
	}
	
	
	public Set<Location> getBoardState() {
		return null;
	}
	
	/**
	 * Empty the board of all game pieces
	 */
	public void emptyBoard() {
		for (int i = 0; i < LENGTH_CHECKERS_BOARD; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				this.board.putPieceAtLocation(i, j, null);
			}
		}
		
		this.p1Pieces = new HashSet<BiColorPiece>();
		this.p2Pieces = new HashSet<BiColorPiece>();
	}
	
	/**
	 * Set up game pieces for a game of Checkers
	 */
	public void setUpBoard() {
		
	}
	
	/**
	 * Moves a piece from a starting location to a new location
	 * @param moveFrom Location from which to move a piece
	 * @param moveTo Location to which to move the piece
	 */
	public void movePiece(Location moveFrom, Location moveTo) {
		
	}
	
	/**
	 * Returns if player1 or player2 has won, if they have.
	 * @return A string representing player1, player2, or that no one has won yet.
	 */
	public String declareVictor() {
		return null;
	}
	
}





