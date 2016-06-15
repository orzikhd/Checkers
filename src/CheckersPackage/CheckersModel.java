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
	private int currentPlayer;
	public static final int LENGTH_CHECKERS_BOARD = 8;
	public static final int PLAYER1 = BiColorPiece.TEAM1;
	public static final int PLAYER2 = BiColorPiece.TEAM2;
	
	/**
	 * Models a Checkers Board of given length
	 * @param length of checkers board
	 */
	public CheckersModel() {
		this.board = new CheckersBoard(LENGTH_CHECKERS_BOARD, "R", "B");
		
		this.p1Pieces = new HashSet<CheckerPiece>();
		this.p2Pieces = new HashSet<CheckerPiece>();
		this.currentPlayer = Location.NULL_TEAM_COLOR;
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
				currPiece = (CheckerPiece) this.board.getPieceAtLocation(j, i);
				if (currPiece == null) {
					curr = new Location(j, i, Location.NULL_TEAM_COLOR, false, this.board.getColorAtLocation(j, i));
				} else {
					curr = new Location(j, i, currPiece.getTeamColor(), currPiece.isKing(), this.board.getColorAtLocation(j, i));
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
				this.board.putPieceAtLocation(j, i, null);
			}
		}
		
		this.p1Pieces = new HashSet<CheckerPiece>();
		this.p2Pieces = new HashSet<CheckerPiece>();
	}
	
	/**
	 * Switches the current player in the game
	 */
	public void switchPlayer() {
		if (this.currentPlayer == CheckersModel.PLAYER1) {
			this.currentPlayer = CheckersModel.PLAYER2;
		} else {
			this.currentPlayer = CheckersModel.PLAYER1;
		}
	}
	
	/**
	 * Returns the current player in the game
	 * @return int representing current player in the game
	 */
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	/**
	 * Remove a piece from the given Location
	 * @param removeFrom Location from which to remove a piece on the board
	 * @modifies this CheckersModel
	 * @effects if a piece was present at this location, it has been removed
	 */
	public void removePiece(Location removeFrom) {
		GamePiece removed = this.board.getPieceAtLocation(removeFrom.getX(), removeFrom.getY());
		if (removeFrom.getPieceTeamColor() == BiColorPiece.TEAM1) {
			this.p1Pieces.remove(removed);
		} else if (removeFrom.getPieceTeamColor() == BiColorPiece.TEAM2){
			this.p2Pieces.remove(removed);
		} else {
			System.out.println("sad trombone");
		}

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
	
	/**
	 * Set up game pieces for a game of Checkers.
	 * Board needs to be set up before a player can make a move.
	 * If the board isn't empty, empty it first for expected results.
	 * @modifies this CheckersModel
	 */
	public void setUpBoard() {
		
		CheckerPiece curr;
		
		this.currentPlayer = CheckersModel.PLAYER1;
		
		//place player1 pieces on top
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
					curr = new CheckerPiece(BiColorPiece.TEAM1);
					p1Pieces.add(curr);
					this.board.putPieceAtLocation(j, i, curr);
				}
			}
		}	
		
		//place player2 pieces at the bottom
		for (int i = 5; i < LENGTH_CHECKERS_BOARD; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
					curr = new CheckerPiece(BiColorPiece.TEAM2);
					p2Pieces.add(curr);
					this.board.putPieceAtLocation(j, i, curr);
				}
			}
		}
		//Acknowledgment that I am violating DRY
	}
	
	/**
	 * Moves a piece from a starting location to a new location with one jump
	 * @param moveFrom Location from which to move a piece
	 * @param moveTo Location to which to move the piece
	 * @modifies this CheckersModel
	 * @effects moves Piece from moveFrom to moveTo if move is valid
	 */
	public void movePiece(Location moveFrom, Location moveTo) {
		if (checkValidMove(moveFrom, moveTo)) {
			CheckerPiece replacementPiece = new CheckerPiece(moveFrom.getPieceTeamColor());
			this.removePiece(moveFrom);
			this.board.putPieceAtLocation(moveTo.getX(), moveTo.getY(), replacementPiece);
			if (replacementPiece.getTeamColor() == BiColorPiece.TEAM1) {
				this.p1Pieces.add(replacementPiece);
			} else {
				this.p2Pieces.add(replacementPiece);
			}
			
			int frX = moveFrom.getX();
			int frY = moveFrom.getY();
			int toX = moveTo.getX();
			int toY = moveTo.getY();
			
			//remove piece if a jump occurred
			if (toX == frX - 2 && toY == frY + 2) {
				CheckerPiece piece = (CheckerPiece)this.board.getPieceAtLocation(frX - 1, frY + 1);
				this.removePiece(new Location(frX - 1, frY + 1, piece.getTeamColor(), false, "W"));
			} else if (toX == frX + 2 && toY == frY + 2) {
				CheckerPiece piece = (CheckerPiece)this.board.getPieceAtLocation(frX + 1, frY + 1);
				this.removePiece(new Location(frX + 1, frY + 1, piece.getTeamColor(), false, "W"));
			} else if (toX == frX - 2 && toY == frY - 2) {
				CheckerPiece piece = (CheckerPiece)this.board.getPieceAtLocation(frX - 1, frY - 1);
				this.removePiece(new Location(frX - 1, frY - 1, piece.getTeamColor(), false, "W"));
			} else if (toX == frX + 2 && toY == frY - 2) {
				CheckerPiece piece = (CheckerPiece)this.board.getPieceAtLocation(frX + 1, frY - 1);
				this.removePiece(new Location(frX + 1, frY - 1, piece.getTeamColor(), false, "W"));
			}
		}
	}
	
	/**
	 * Returns if a move from one location to another is valid for the piece at the from location.
	 * @param moveFrom Location of piece to move from
	 * @param moveTo Location to move piece to
	 * @return true if valid move for current player, false otherwise
	 */
	public boolean checkValidMove(Location moveFrom, Location moveTo) {
		
		//if there is no current player, no move is valid
		if (this.currentPlayer == Location.NULL_TEAM_COLOR) {
			return false;
		}
		
		//if a piece is at the spot you want to move to, there is no way you can go there
		if (moveTo.getPieceTeamColor() != Location.NULL_TEAM_COLOR) {
			return false;
		}
		
		//if there is no piece at the moveFrom location, no valid move can be made
		if (moveFrom.getPieceTeamColor() == Location.NULL_TEAM_COLOR) {
			return false;
		}
		
		int frX = moveFrom.getX();
		int frY = moveFrom.getY();
		int toX = moveTo.getX();
		int toY = moveTo.getY();
		int left = frX - 1;
		int right = frX + 1;
		int up = frY - 1;
		int down = frY + 1;
		
		List<CheckerPiece> surroundingPieces = this.getSurroundingPieces(moveFrom);
		List<CheckerPiece> surroundingJumpPieces = this.getSurroundingJumpPieces(moveFrom);
		
		CheckerPiece downLeft = surroundingPieces.get(0);
		CheckerPiece downRight = surroundingPieces.get(1);
		CheckerPiece upLeft = surroundingPieces.get(2);
		CheckerPiece upRight = surroundingPieces.get(3);
		CheckerPiece farDownLeft = surroundingJumpPieces.get(0);
		CheckerPiece farDownRight = surroundingJumpPieces.get(1);
		CheckerPiece farUpLeft = surroundingJumpPieces.get(2);
		CheckerPiece farUpRight = surroundingJumpPieces.get(3);
		
		//at this point we know the jumpto spot is empty, the from spot is a piece, and someone is playing
		
		//now finally check if the move/jump is valid
		if (this.currentPlayer == CheckersModel.PLAYER1 && moveFrom.getPieceTeamColor() == BiColorPiece.TEAM1) {
			
			//jumping an enemy is available and must be done, but the moveTo is not a jump
			//jump available if the jumpto spot is empty and the jump over spot has an enemy piece in it
			if (((downLeft != null && downLeft.getTeamColor() == BiColorPiece.TEAM2 && farDownLeft == null) || (downRight != null && downRight.getTeamColor() == BiColorPiece.TEAM2 && farDownRight == null))
					&& (toX == left && toY == down || toX == right && toY == down)) {
				return false;
			}
			
			//if a piece exists then jump it
			if (downLeft != null && downLeft.getTeamColor() == BiColorPiece.TEAM2 && toX == frX - 2 && toY == frY + 2) {
				return true;
			} else if (downRight != null && downRight.getTeamColor() == BiColorPiece.TEAM2 && toX == frX + 2 && toY == frY + 2) {
				return true;
			} else if (toX == left && toY == down
					|| toX == right && toY == down){
				//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
				//we also now know that no jump is available
				return true;
			} else if (moveFrom.getIsKing()) {
				//kings can move backwards
				if (upLeft != null && upLeft.getTeamColor() == BiColorPiece.TEAM2 && toX == frX - 2 && toY == frY - 2) {
					return true;
				} else if (upRight != null && upRight.getTeamColor() == BiColorPiece.TEAM2 && toX == frX + 2 && toY == frY - 2) {
					return true;
				} else if (toX == left && toY == up
						|| toX == right && toY == up){
					//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
					return true;
				} 
			}		
		}
		
		if (this.currentPlayer == CheckersModel.PLAYER2 && moveFrom.getPieceTeamColor() == BiColorPiece.TEAM2) {
			
			//jumping an enemy is available and must be done, but the moveTo is not a jump
			//jump available if the jumpto spot is empty and the jump over spot has an enemy piece in it
			if (((upLeft != null && upLeft.getTeamColor() == BiColorPiece.TEAM1 && farUpLeft == null) || (upRight != null && upRight.getTeamColor() == BiColorPiece.TEAM1 && farUpRight == null))
					&& (toX == left && toY == up || toX == right && toY == up)) {
				return false;
			}
			
			//if a piece exists then jump it, otherwise just walk
			if (upLeft != null && upLeft.getTeamColor() == BiColorPiece.TEAM1 && toX == frX - 2 && toY == frY - 2) {
				return true;
			} else if (upRight != null && upRight.getTeamColor() == BiColorPiece.TEAM1 && toX == frX + 2 && toY == frY - 2) {
				return true;
			} else if (toX == left && toY == up
					|| toX == right && toY == up){
				//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
				return true;
			} else if (moveFrom.getIsKing()) {
				//kings can move backwards
				if (downLeft != null && downLeft.getTeamColor() == BiColorPiece.TEAM1 && toX == frX - 2 && toY == frY + 2) {
					return true;
				} else if (downRight != null && downRight.getTeamColor() == BiColorPiece.TEAM1 && toX == frX + 2 && toY == frY + 2) {
					return true;
				} else if (toX == left && toY == down
						|| toX == right && toY == down){
					//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
					return true;
				} 
			}
		}
		
		return false;
	}
	
	/**
	 * Returns if this is a valid piece to select for movement.
	 * For movement to be valid, a piece must be in this location.
	 * A piece is valid for movement if the current player owns the
	 * selected piece and there is a valid move available.
	 * @param selection
	 * @return
	 */
	public boolean checkValidSelection(Location selection) {
		
		List<Location> surroundingLocations = new ArrayList<>();
		List<CheckerPiece> surroundingPieces = this.getSurroundingPieces(selection);
		List<CheckerPiece> surroundingJumpPieces = this.getSurroundingJumpPieces(selection);
		
		int frX = selection.getX();
		int frY = selection.getY();
		int left = frX - 1;
		int farLeft = frX - 2;
		int right = frX + 1;
		int farRight = frX + 2;
		int up = frY - 1;
		int farUp = frY - 2;
		int down = frY + 1;
		int farDown = frY + 2;
		
		//valid to move left
		if (left > 0) {
			//valid to move up
			if (up > 0) {
				if (surroundingPieces.get(2) != null) {
					surroundingLocations.add(new Location(left, up, surroundingPieces.get(2).getTeamColor(), surroundingPieces.get(2).isKing(), GameTile.TILE_WHITE));
				} else {
					surroundingLocations.add(new Location(left, up, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE));
				}
				
			}
			//valid to move down
			if (down < CheckersModel.LENGTH_CHECKERS_BOARD) {
				if (surroundingPieces.get(0) != null) {
					surroundingLocations.add(new Location(left, down, surroundingPieces.get(0).getTeamColor(), surroundingPieces.get(0).isKing(), GameTile.TILE_WHITE));
				} else {
					surroundingLocations.add(new Location(left, down, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE));
				}
			}
		}
		
		//valid to move right
		if (right < CheckersModel.LENGTH_CHECKERS_BOARD) {
			//valid to move up
			if (up > 0) {
				if (surroundingPieces.get(3) != null) {
					surroundingLocations.add(new Location(right, up, surroundingPieces.get(3).getTeamColor(), surroundingPieces.get(3).isKing(), GameTile.TILE_WHITE));
				} else {
					surroundingLocations.add(new Location(right, up, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE));
				}
			}
			//valid to move down
			if (down < CheckersModel.LENGTH_CHECKERS_BOARD) {
				if (surroundingPieces.get(1) != null) {
					surroundingLocations.add(new Location(right, down, surroundingPieces.get(1).getTeamColor(), surroundingPieces.get(1).isKing(), GameTile.TILE_WHITE));
				} else {
					surroundingLocations.add(new Location(right, down, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE));
				}
			}			
		}
		
		//valid to move far left
		if (farLeft > 0) {
			//valid to move far up
			if (farUp > 0) {
				if (surroundingJumpPieces.get(2) != null) {
					surroundingLocations.add(new Location(farLeft, farUp, surroundingJumpPieces.get(2).getTeamColor(), surroundingJumpPieces.get(2).isKing(), GameTile.TILE_WHITE));
				} else {
					surroundingLocations.add(new Location(farLeft, farUp, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE));
				}
				
			}
			//valid to move far down
			if (farDown < CheckersModel.LENGTH_CHECKERS_BOARD) {
				if (surroundingJumpPieces.get(0) != null) {
					surroundingLocations.add(new Location(farLeft, farDown, surroundingJumpPieces.get(0).getTeamColor(), surroundingJumpPieces.get(0).isKing(), GameTile.TILE_WHITE));
				} else {
					surroundingLocations.add(new Location(farLeft, farDown, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE));
				}
			}
		}
		
		//valid to move right
		if (farRight < CheckersModel.LENGTH_CHECKERS_BOARD) {
			//valid to move up
			if (farUp > 0) {
				if (surroundingJumpPieces.get(3) != null) {
					surroundingLocations.add(new Location(farRight, farUp, surroundingJumpPieces.get(3).getTeamColor(), surroundingJumpPieces.get(3).isKing(), GameTile.TILE_WHITE));
				} else {
					surroundingLocations.add(new Location(farRight, farUp, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE));
				}
			}
			//valid to move down
			if (farDown < CheckersModel.LENGTH_CHECKERS_BOARD) {
				if (surroundingJumpPieces.get(1) != null) {
					surroundingLocations.add(new Location(farRight, farDown, surroundingJumpPieces.get(1).getTeamColor(), surroundingJumpPieces.get(1).isKing(), GameTile.TILE_WHITE));
				} else {
					surroundingLocations.add(new Location(farRight, farDown, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE));
				}
			}			
		}
		
		for (Location poss : surroundingLocations) {
			if (this.checkValidMove(selection, poss)) {
				return true;
			}
		}
		
		return false;
	}
	
	//in order of downleft, downright, upleft, upright
	//null if no piece there
	private List<CheckerPiece> getSurroundingPieces(Location center) {
		
		int frX = center.getX();
		int frY = center.getY();
		
		CheckerPiece downLeft;
		CheckerPiece downRight;
		CheckerPiece upLeft;
		CheckerPiece upRight;
		
		//check what pieces (if any) exist around center
		if (frX - 1 < 0 || frY + 1 > CheckersModel.LENGTH_CHECKERS_BOARD - 1) {
			downLeft = null;
		} else {
			downLeft = (CheckerPiece) this.board.getPieceAtLocation(frX - 1, frY + 1);
		}
		
		if (frX + 1 > CheckersModel.LENGTH_CHECKERS_BOARD - 1 || frY + 1 > CheckersModel.LENGTH_CHECKERS_BOARD - 1) {
			downRight = null;
		} else {
			downRight = (CheckerPiece) this.board.getPieceAtLocation(frX + 1, frY + 1);
		}
		
		if (frX - 1 < 0 || frY - 1 < 0) {
			upLeft = null;
		} else {
			upLeft = (CheckerPiece) this.board.getPieceAtLocation(frX - 1, frY - 1);
		}
		
		if (frX + 1 > CheckersModel.LENGTH_CHECKERS_BOARD - 1 || frY - 1 < 0) {
			upRight = null;
		} else {
			upRight = (CheckerPiece) this.board.getPieceAtLocation(frX + 1, frY - 1);
		}
		
		List<CheckerPiece> surroundingPieces = new ArrayList<>();
		surroundingPieces.add(downLeft);
		surroundingPieces.add(downRight);
		surroundingPieces.add(upLeft);
		surroundingPieces.add(upRight);
		
		return surroundingPieces;
	}
	
	//in order of far downleft, far downright, far upleft, far upright
	//null if no piece there
	private List<CheckerPiece> getSurroundingJumpPieces(Location center) {
		
		int frX = center.getX();
		int frY = center.getY();
		
		CheckerPiece farDownLeft;
		CheckerPiece farDownRight;
		CheckerPiece farUpLeft;
		CheckerPiece farUpRight;
		
		//check what pieces (if any) exist around center
		if (frX - 2 < 0 || frY + 2 > CheckersModel.LENGTH_CHECKERS_BOARD - 1) {
			farDownLeft = null;
		} else {
			farDownLeft = (CheckerPiece) this.board.getPieceAtLocation(frX - 2, frY + 2);
		}
		
		if (frX + 2 > CheckersModel.LENGTH_CHECKERS_BOARD - 1 || frY + 2 > CheckersModel.LENGTH_CHECKERS_BOARD - 1) {
			farDownRight = null;
		} else {
			farDownRight = (CheckerPiece) this.board.getPieceAtLocation(frX + 2, frY + 2);
		}
		
		if (frX - 2 < 0 || frY - 2 < 0) {
			farUpLeft = null;
		} else {
			farUpLeft = (CheckerPiece) this.board.getPieceAtLocation(frX - 2, frY - 2);
		}
		
		if (frX + 2 > CheckersModel.LENGTH_CHECKERS_BOARD - 1 || frY - 2 < 0) {
			farUpRight = null;
		} else {
			farUpRight = (CheckerPiece) this.board.getPieceAtLocation(frX + 2, frY - 2);
		}
		
		List<CheckerPiece> surroundingPieces = new ArrayList<>();
		surroundingPieces.add(farDownLeft);
		surroundingPieces.add(farDownRight);
		surroundingPieces.add(farUpLeft);
		surroundingPieces.add(farUpRight);
		
		return surroundingPieces;
	}	
}





