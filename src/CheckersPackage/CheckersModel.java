package CheckersPackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		this.board = new CheckersBoard(LENGTH_CHECKERS_BOARD, "R", "B");
		
		this.p1Pieces = new HashSet<BiColorPiece>();
		this.p2Pieces = new HashSet<BiColorPiece>();
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
	
	
	public List<Location> getBoardState() {
		
		List<Location> locations = new ArrayList<>();
		
		Location curr;
		BiColorPiece currPiece;
		for (int i = 0; i < LENGTH_CHECKERS_BOARD; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				currPiece = (BiColorPiece) this.board.getPieceAtLocation(i, j);
				curr = new Location(i, j, currPiece.getColor(), this.board.getColorAtLocation(i, j));
				locations.add(curr);
			}
		}
		
		return locations;
	}
	
	/**
	 * Empty the board of all game pieces
	 * @modifies this CheckersModel
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
	 * @modifies this CheckersModel
	 */
	public void setUpBoard() {
		
		BiColorPiece curr;
		
		//place player1 pieces on top
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
					curr = new BiColorPiece(true);
					p1Pieces.add(curr);
					this.board.putPieceAtLocation(i, j, curr);
				}
			}
		}	
		
		//place player2 pieces at the bottom
		for (int i = 5; i < LENGTH_CHECKERS_BOARD; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
					curr = new BiColorPiece(false);
					p2Pieces.add(curr);
					this.board.putPieceAtLocation(i, j, curr);
				}
			}
		}
		
		//Acknowledgment that I am violating DRY
	}
	
	/**
	 * Moves a piece from a starting location to a new location
	 * @param moveFrom Location from which to move a piece
	 * @param moveTo Location to which to move the piece
	 * @modifies this CheckersModel
	 * @effects moves Piece from moveFrom to moveTo
	 */
	public void movePiece(Location moveFrom, Location moveTo) {
		
	}
	
	/**
	 * Remove a piece from the given Location
	 * @param removeFrom Location from which to remove a piece on the board
	 * @modifies this CheckersModel
	 * @effects if a piece was present at this location, it has been removed
	 */
	public void removePiece(Location removeFrom) {
		
	}
	
	/**
	 * Returns if player1 or player2 has won, if they have.
	 * @return A string representing player1, player2, or that no one has won yet.
	 */
	public String declareVictor() {
		return null;
	}
	
	@Override
	public String toString() {
		return this.board.toString();
	}
}





