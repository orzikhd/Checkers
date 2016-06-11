package CheckersPackage;

/**
 * A BiColorPiece is a GamePiece with a color,
 * specified as one of a choice of two colors
 */
public class BiColorPiece extends GamePiece {
	private boolean color;
	
	/**
	 * Constructs a BiColorPiece of the given color
	 * with a default position
	 * @param color boolean value to represent one color
	 * or another as true/false
	 */
	public BiColorPiece(boolean color) {
		this(color, 0, 0);
	}
	
	/**
	 * Constructs a BiColorPiece of the given color
	 * with a default position
	 * @param color boolean value to represent one color
	 * or another as true/false
	 * @param X desired initial int X coordinate
	 * @param Y desired initial int Y coordinate
	 * @requires X and Y should both be >= 0
	 */
	public BiColorPiece(boolean color, int X, int Y) {
		super(X, Y);
		this.color = color;
	}
	
	/**
	 * Returns color choice of this BiColorPiece
	 * @return
	 */
	public boolean getColor() {
		return this.color;
	}
}
