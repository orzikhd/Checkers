package CheckersPackage;

/**
 * Represents a Checker Piece
 */
public class CheckerPiece extends BiColorPiece {
	private boolean isKing;
	
	/**
	 * Constructs a default Checker Piece
	 * @param teamColor one of the available team colors
	 * @requires teamColor must be one of the available team colors
	 */
	public CheckerPiece(int teamColor) {
		this(teamColor, false);
	}
	
	/**
	 * Constructs a default Checker Piece
	 * @param teamColor one of the available team colors
	 * @param isKing boolean to set if the piece is a king piece or not
	 * @requires teamColor must be one of the available team colors
	 */
	public CheckerPiece(int teamColor, boolean isKing) {
		super(teamColor);
		this.isKing = isKing;
	}
	
	/**
	 * Flips the piece from being a standard piece to a king piece or viceversa
	 */
	public void flipPiece() {
		this.isKing = !this.isKing;
	}
	
	/**
	 * Returns if this piece is a king piece
	 * @return true if this is a king piece, false otherwise
	 */
	public boolean isKing() {
		return this.isKing;
	}
}
