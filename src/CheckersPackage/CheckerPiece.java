package CheckersPackage;

/**
 * Represents a Checker Piece.
 */
public class CheckerPiece extends BiColorPiece {
	
	//rep invariant: color is only ever COLOR1 or COLOR2
	
	private boolean isKing;
	
	/**
	 * Constructs a default Checker Piece of the given color.
	 * @param teamColor one of the available colors
	 * @requires teamColor must be one of the available colors
	 */
	public CheckerPiece(int teamColor) {
		this(teamColor, false);
	}
	
	/**
	 * Constructs a Checker Piece of the given color and king status.
	 * @param teamColor one of the available colors
	 * @param isKing boolean true if the piece is a king piece, false otherwise
	 * @requires teamColor must be one of the available colors
	 */
	public CheckerPiece(int teamColor, boolean isKing) {
		super(teamColor);
		this.isKing = isKing;
	}
	
	/**
	 * Flips the piece from being a standard piece to a king piece or vice-versa.
	 */
	public void flipPiece() {
		this.isKing = !this.isKing;
	}
	
	/**
	 * Returns this CheckerPiece's king status.
	 * @return true if this is a king piece, false otherwise
	 */
	public boolean isKing() {
		return this.isKing;
	}
}
