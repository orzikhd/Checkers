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
	private Set<Location> p1Locations;
	private Set<Location> p2Locations;
	private int currentPlayer;
	public static final int LENGTH_CHECKERS_BOARD = 8;
	public static final int PLAYER1 = BiColorPiece.TEAM1;
	public static final int PLAYER2 = BiColorPiece.TEAM2;
	
	/**
	 * Models a Checkers Board of given length
	 * @param length of checkers board
	 */
	public CheckersModel() {
		this.board = new CheckersBoard(LENGTH_CHECKERS_BOARD, GameTile.TILE_BLACK, GameTile.TILE_RED);
		
		this.p1Locations = new HashSet<Location>();
		this.p2Locations = new HashSet<Location>();
		this.currentPlayer = Location.NULL_TEAM_COLOR;
	}
	
	/**
	 * Returns an int count of the number of pieces player 1 has left on the board.
	 * @return int count number of pieces left for player 1
	 */
	public int countPlayer1() {
		return p1Locations.size();
	}
	
	/**
	 * Returns an int count of the number of pieces player 2 has left on the board.
	 * @return int count number of pieces left for player 2
	 */
	public int countPlayer2() {
		return p2Locations.size();
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
		
		this.p1Locations = new HashSet<Location>();
		this.p2Locations = new HashSet<Location>();
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
		this.board.putPieceAtLocation(removeFrom.getX(), removeFrom.getY(), null);
		
		if (removeFrom.getPieceTeamColor() == BiColorPiece.TEAM1) {
			boolean changed = this.p1Locations.remove(removeFrom);
			if (!changed) {
				System.out.println(this.p1Locations);
				System.out.println(removeFrom);
			}
		} else if (removeFrom.getPieceTeamColor() == BiColorPiece.TEAM2){
			this.p2Locations.remove(removeFrom);
		} else {
			System.out.println("sad trombone");
		}
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
					this.board.putPieceAtLocation(j, i, curr);
					p1Locations.add(this.getLocationAtCoordinates(j, i));
				}
			}
		}	
		
		//place player2 pieces at the bottom
		for (int i = 5; i < LENGTH_CHECKERS_BOARD; i++) {
			for (int j = 0; j < LENGTH_CHECKERS_BOARD; j++) {
				if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
					curr = new CheckerPiece(BiColorPiece.TEAM2);
					this.board.putPieceAtLocation(j, i, curr);
					p2Locations.add(this.getLocationAtCoordinates(j, i));
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
			//put the new piece's location in its correct set
			if (replacementPiece.getTeamColor() == BiColorPiece.TEAM1) {
				this.p1Locations.add(this.getLocationAtCoordinates(moveTo.getX(), moveTo.getY()));
			} else {
				this.p2Locations.add(this.getLocationAtCoordinates(moveTo.getX(), moveTo.getY()));
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
				
		//at this point we know the jumpto spot is empty, the from spot is a piece, and someone is playing
		//if a jump is available, this should be a jump, otherwise its an invalid move
		List<Location> jumps = this.jumpsAvailable(frX, frY);
		if (!jumps.isEmpty()) {
			if (jumps.contains(this.getLocationAtCoordinates(toX, toY))) {
				return true;
			} else {	
				return false;
			}
		}

		if (this.currentPlayer == CheckersModel.PLAYER1 && moveFrom.getPieceTeamColor() == BiColorPiece.TEAM1) {

			if (toX == left && toY == down	|| toX == right && toY == down){
				//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
				//we also now know that no jump is available
				return true;
			} else if (moveFrom.getIsKing()) {
				//kings can move backwards
				if (toX == left && toY == up || toX == right && toY == up){
					//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
					return true;
				} 
			}		
		}
		
		if (this.currentPlayer == CheckersModel.PLAYER2 && moveFrom.getPieceTeamColor() == BiColorPiece.TEAM2) {
			
			if (toX == left && toY == up || toX == right && toY == up){
				//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
				//we also now know that no jump is available
				return true;
			} else if (moveFrom.getIsKing()) {
				//kings can move backwards
				if (toX == left && toY == down || toX == right && toY == down){
					//initial if already checks that this spot is empty; don't have to worry about overriding existing piece
					return true;
				} 
			}
		}
		
		return false;
	}
	
	/**
	 * Return a list of all jumps available for the piece at the given coordinates
	 * @param frX X component of start location
	 * @param frY Y component of start location
	 * @return List of Locations that can be jumped to from the given coordinates.
	 * 	Empty if no jumps available.
	 */
	public List<Location> jumpsAvailable(int frX, int frY) {
		CheckerPiece startPiece = (CheckerPiece) this.board.getPieceAtLocation(frX, frY);
		List<Location> result = new ArrayList<>();
		if (startPiece == null) {
			return result;
		}
		
		List<CheckerPiece> surroundingPieces = this.getSurroundingPieces(frX, frY);
		List<CheckerPiece> surroundingJumpPieces = this.getSurroundingJumpPieces(frX, frY);
		
		CheckerPiece downLeft = surroundingPieces.get(0);
		CheckerPiece downRight = surroundingPieces.get(1);
		CheckerPiece upLeft = surroundingPieces.get(2);
		CheckerPiece upRight = surroundingPieces.get(3);
		CheckerPiece farDownLeft = surroundingJumpPieces.get(0);
		CheckerPiece farDownRight = surroundingJumpPieces.get(1);
		CheckerPiece farUpLeft = surroundingJumpPieces.get(2);
		CheckerPiece farUpRight = surroundingJumpPieces.get(3);
		
		Location toAdd;
		if (startPiece.getTeamColor() == CheckerPiece.TEAM1 && this.getCurrentPlayer() == CheckersModel.PLAYER1) {			
			if (downLeft != null && downLeft.getTeamColor() == CheckerPiece.TEAM2
					&& farDownLeft == null) {
				//add farDownLeft to result
				toAdd = this.getLocationAtCoordinates(frX - 2, frY + 2);
				result.add(toAdd);
			} else if (downRight != null && downRight.getTeamColor() == CheckerPiece.TEAM2
					&& farDownRight == null) {
				//add farDownRight to result
				toAdd = this.getLocationAtCoordinates(frX + 2, frY + 2);
				result.add(toAdd);
			} else {
				if (startPiece.isKing()) {
					if (upLeft != null && upLeft.getTeamColor() == CheckerPiece.TEAM2
							&& farUpLeft == null) {
						//add farUpLeft to result
						toAdd = this.getLocationAtCoordinates(frX - 2, frY - 2);
						result.add(toAdd);
					} else if (upRight != null && upRight.getTeamColor() == CheckerPiece.TEAM2
							&& farUpRight == null) {
						//add farUpRight to result
						toAdd = this.getLocationAtCoordinates(frX + 2, frY - 2);
						result.add(toAdd);
					}					
				}
			}
		} else if (startPiece.getTeamColor() == CheckerPiece.TEAM2 && this.getCurrentPlayer() == CheckersModel.PLAYER2) {
			if (upLeft != null && upLeft.getTeamColor() == CheckerPiece.TEAM1
					&& farUpLeft == null) {
				//add farUpLeft to result
				toAdd = this.getLocationAtCoordinates(frX - 2, frY - 2);
				result.add(toAdd);
			} else if (upRight != null && upRight.getTeamColor() == CheckerPiece.TEAM1
					&& farUpRight == null) {
				//add farUpRight to result
				toAdd = this.getLocationAtCoordinates(frX + 2, frY - 2);
				result.add(toAdd);
			} else {
				if (startPiece.isKing()) {
					if (downLeft != null && downLeft.getTeamColor() == CheckerPiece.TEAM1
							&& farDownLeft == null) {
						//add farDownLeft to result
						toAdd = this.getLocationAtCoordinates(frX - 2, frY + 2);
						result.add(toAdd);
					} else if (downRight != null && downRight.getTeamColor() == CheckerPiece.TEAM1
							&& farDownRight == null) {
						//add farDownRight to result
						toAdd = this.getLocationAtCoordinates(frX + 2, frY + 2);
						result.add(toAdd);
					}					
				}
			}			
		}
		
		return result;
	}
	
	private Location getLocationAtCoordinates(int frX, int frY) {
		List<Location> currentState = this.getBoardState();
		
		return currentState.get(frY*8 + frX);
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
		List<CheckerPiece> surroundingPieces = this.getSurroundingPieces(selection.getX(), selection.getY());
		List<CheckerPiece> surroundingJumpPieces = this.getSurroundingJumpPieces(selection.getX(), selection.getY());
		
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
	private List<CheckerPiece> getSurroundingPieces(int frX, int frY) {
		
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
	private List<CheckerPiece> getSurroundingJumpPieces(int frX, int frY) {

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





