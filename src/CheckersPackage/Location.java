package CheckersPackage;

/**
 * Helper class for transferring information between Checkers model
 * and view.
 * Holds (x,y) coordinate location and colors associated with a tile/piece
 */
public class Location {
	
	/*
	 * Rep Invariant:
	 * X >= 0 and Y >= 0
	 */
	
	/**
	 * Team for locations where a piece does not exist
	 */
	public static final int NULL_TEAM_COLOR = 0;
	
	/** 
	 * Team 1 Designation
	 */
	public static final int PIECE_COLOR1 = BiColorPiece.COLOR1;
	
	/**
	 * Team 2 Designation
	 */
	public static final int PIECE_COLOR2 = BiColorPiece.COLOR2;
	
	private int X;
	private int Y;
	private int pieceColor;
	private boolean isKing;
	private String tileColor;
	
	/**
	 * Creates a Location given a coordinate pair and color boolean
	 * @param X component of location
	 * @param Y component of location
	 * @param pieceTeamColor int corresponding to a team color
	 * @param isKing true if the piece here is a king, false otherwise
	 * @param tileColor String representing the present tile color
	 * @requires X >= 0 and Y >= 0
	 */
	public Location(int X, int Y, int pieceTeamColor, boolean isKing, String tileColor) {
		if (X < 0 || Y < 0) {
			throw new IllegalArgumentException("Location component should be 0 or positive");
		}
		
		this.X = X;
		this.Y = Y;
		this.pieceColor = pieceTeamColor;
		this.isKing = isKing;
		this.tileColor = tileColor;
	}
	
	/**
	 * Returns X Coordinate of this Location.
	 * @return int value for X coordinate
	 */
	public int getX() {
		return this.X;
	}
	
	/**
	 * Returns Y Coordinate of this Location.
	 * @return int value for Y coordinate
	 */
	public int getY() {
		return this.Y;
	}
	
	/**
	 * Returns this location's piece's color.
	 * @return one of the piece colors available or the 
	 * null team color designation if there is no piece
	 * at this location
	 */
	public int getPieceTeamColor() {
		return this.pieceColor;
	}
	
	/**
	 * Returns this location's piece's king status
	 * @return true if the piece is a king piece, false otherwise
	 */
	public boolean getIsKing() {
		return this.isKing;
	}
	
	/**
	 * Return this location's tile color
	 * @return String representing this location's tile color
	 */
	public String getTileColor() {
		return this.tileColor;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Location)) {
			return false;
		}
		
		Location o = (Location) other;
		if (this.X == o.X && this.Y == o.Y && this.pieceColor == o.pieceColor 
				&& this.isKing == o.isKing && this.tileColor.equals(o.tileColor)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.X * 13 + this.Y + this.pieceColor * 21 + this.tileColor.hashCode();
	}
	
	@Override
	public String toString() {
		return "[(" + this.X + "," + this.Y + ") colored " + this.tileColor + " holds " + this.pieceColor + ", isKing: " + this.isKing + "]";
	}
}
