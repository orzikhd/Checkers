package CheckersPackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles data related to a game of Checkers
 */
public class CheckersModel {
	
	//player1 is the player starting at the top, player2 is the player starting at the bottom
	
	private CheckersBoard board;
	private Set<CheckerPiece> p1Pieces;
	private Set<CheckerPiece> p2Pieces;
	private static final int LENGTH_CHECKERS_BOARD = 8;
	
	/**
	 * Models a Checkers Board of given length
	 * @param length of checkers board
	 */
	public CheckersModel() {
		this.board = new CheckersBoard(LENGTH_CHECKERS_BOARD, "R", "B");
		
		this.p1Pieces = new HashSet<CheckerPiece>();
		this.p2Pieces = new HashSet<CheckerPiece>();
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
		CheckerPiece currPiece;
		for (int i = 0; i < LENGTH_CHECKERS_BOARD; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				currPiece = (CheckerPiece) this.board.getPieceAtLocation(i, j);
				if (currPiece == null) {
					curr = new Location(i, j, Location.NULL_TEAM_COLOR, false, this.board.getColorAtLocation(i, j));
				} else {
					curr = new Location(i, j, currPiece.getTeamColor(), currPiece.isKing(), this.board.getColorAtLocation(i, j));
				}
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
		
		this.p1Pieces = new HashSet<CheckerPiece>();
		this.p2Pieces = new HashSet<CheckerPiece>();
	}
	
	/**
	 * Set up game pieces for a game of Checkers
	 * @modifies this CheckersModel
	 */
	public void setUpBoard() {
		
		CheckerPiece curr;
		
		//place player1 pieces on top
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
					curr = new CheckerPiece(BiColorPiece.TEAM1);
					p1Pieces.add(curr);
					this.board.putPieceAtLocation(i, j, curr);
				}
			}
		}	
		
		//place player2 pieces at the bottom
		for (int i = 5; i < LENGTH_CHECKERS_BOARD; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
					curr = new CheckerPiece(BiColorPiece.TEAM2);
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
		if (checkValidMove(moveFrom, moveTo)) {
			this.removePiece(moveFrom);
			this.board.putPieceAtLocation(moveTo.getX(), moveTo.getY(), new CheckerPiece(moveFrom.getPieceTeamColor()));
		}
	}
	
	/**
	 * Returns if a move from one location to another is valid for the piece at the from location.
	 * @param moveFrom Location of piece to move from
	 * @param moveTo Location to move piece to
	 * @return true if valid move by any means, false otherwise
	 */
	public boolean checkValidMove(Location moveFrom, Location moveTo) {
		return checkValidMove(moveFrom, moveTo, false);
	}
	
	//checks if the rules of checkers allow this move
	private boolean checkValidMove(Location moveFrom, Location moveTo, boolean isKing) {
		
		//if a piece is at the spot you want to move to, there is no way you can go there
		//also, if there is no piece at the moveFrom location, no valid move can be made; so also false
		if (moveTo.getPieceTeamColor() != Location.NULL_TEAM_COLOR || moveFrom.getPieceTeamColor() != Location.NULL_TEAM_COLOR) {
			return false;
		}
		
		int frX = moveFrom.getX();
		int frY = moveFrom.getY();
		int toX = moveTo.getX();
		int toY = moveTo.getY();
		
		//check what pieces (if any) exist around this piece
		CheckerPiece downLeft = (CheckerPiece) this.board.getPieceAtLocation(frX - 1, frY + 1);
		CheckerPiece downRight = (CheckerPiece) this.board.getPieceAtLocation(frX + 1, frY + 1);
		CheckerPiece upLeft = (CheckerPiece) this.board.getPieceAtLocation(frX - 1, frY - 1);
		CheckerPiece upRight = (CheckerPiece) this.board.getPieceAtLocation(frX + 1, frY - 1);
		
		if (moveFrom.getPieceTeamColor() == BiColorPiece.TEAM1) {
			//jumping an enemy is available and must be done
			if (downLeft.getTeamColor() == BiColorPiece.TEAM2 && toX == frX - 2 && toY == frY + 2) {
				return true;
			} else if (downRight.getTeamColor() == BiColorPiece.TEAM2 && toX == frX + 2 && toY == frY + 2) {
				return true;
			} else if (toX == frX - 1 && toY == frY + 1
					|| toX == frX + 1 && toY == frY + 1){
				//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
				return true;
			} else {
				return false;
			}
			/*
			if (toX == frX - 1 && toY == frY + 1
					|| toX == frX + 1 && toY == frY + 1
					|| (toX == frX - 2 && toY == frY + 2 && downLeft.getTeamColor() == BiColorPiece.TEAM2)
					|| (toX == frX + 2 && toY == frY + 2 && downRight.getTeamColor() == BiColorPiece.TEAM2)) {
				//this is a standard player1 move
				return true;
			} else { //check player1 combos
				
				Location moveFromComboDL = new Location(frX - 2, frY + 2, moveFrom.getPieceTeamColor(), "W");
				Location moveFromComboDR = new Location(frX + 2, frY + 2, moveFrom.getPieceTeamColor(), "W");
				return ((downLeft.getTeamColor() == BiColorPiece.TEAM2 && checkValidMove(moveFromComboDL, moveTo, true))
						|| (downRight.getTeamColor() == BiColorPiece.TEAM2 && checkValidMove(moveFromComboDR, moveTo, true)));
			}
			*/
		} else { //team2
			/*if (toX == frX - 1 && toY == frY - 1
					|| toX == frX + 1 && toY == frY - 1
					|| (toX == frX - 2 && toY == frY - 2 && upLeft.getTeamColor() == BiColorPiece.TEAM2)
					|| (toX == frX + 2 && toY == frY - 2 && upRight.getTeamColor() == BiColorPiece.TEAM2)) {
				//this is a standard player2 move
				return true;
			} else { //check player2 combos
				
				Location moveFromComboUL = new Location(frX - 2, frY - 2, moveFrom.getPieceTeamColor(), "W");
				Location moveFromComboUR = new Location(frX + 2, frY - 2, moveFrom.getPieceTeamColor(), "W");
				return ((upLeft.getTeamColor() == BiColorPiece.TEAM1 && checkValidMove(moveFromComboUL, moveTo, true))
						|| (upRight.getTeamColor() == BiColorPiece.TEAM1 && checkValidMove(moveFromComboUR, moveTo, true)));
			}
			*/
		}

		return false;
	}
	
	/**
	 * Remove a piece from the given Location
	 * @param removeFrom Location from which to remove a piece on the board
	 * @modifies this CheckersModel
	 * @effects if a piece was present at this location, it has been removed
	 */
	public void removePiece(Location removeFrom) {
		this.board.putPieceAtLocation(removeFrom.getX(), removeFrom.getY(), null);
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





