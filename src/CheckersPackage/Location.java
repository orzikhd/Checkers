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
	 * Color for locations where a piece does not exist
	 */
	public static final int NULL_TEAM_COLOR = 0;
	
	private int X;
	private int Y;
	private int pieceTeamColor;
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
		this.pieceTeamColor = pieceTeamColor;
		this.isKing = isKing;
		this.tileColor = tileColor;
	}
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public int getPieceTeamColor() {
		return this.pieceTeamColor;
	}
	
	public boolean getIsKing() {
		return this.isKing;
	}
	
	public String getTileColor() {
		return this.tileColor;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Location)) {
			return false;
		}
		
		Location o = (Location) other;
		if (this.X == o.X && this.Y == o.Y && this.pieceTeamColor == o.pieceTeamColor 
				&& this.isKing == o.isKing && this.tileColor.equals(o.tileColor)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.X * 13 + this.Y + this.pieceTeamColor * 21 + this.tileColor.hashCode();
	}
	
	@Override
	public String toString() {
		return "[(" + this.X + "," + this.Y + ") colored " + this.tileColor + " holds " + this.pieceTeamColor + ", isKing: " + this.isKing + "]";
	}
}
