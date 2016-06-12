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
		this.color = color;
	}
	
	/**
	 * Returns color choice of this BiColorPiece
	 * @return
	 */
	public boolean getColor() {
		return this.color;
	}
	
	@Override
	public String toString() {
		return "team " + this.color;
	}
}
