package CheckersPackage;

/**
 * A BiColorPiece is a GamePiece with a "color",
 * specified as one of a choice of two colors.
 */
public class BiColorPiece extends GamePiece {
	
	//rep invariant: color is only ever COLOR1 or COLOR2
	
	/** 
	 * Color 1 Designation
	 */
	public static final int COLOR1 = 1;
	/**
	 * Color 2 Designation
	 */
	public static final int COLOR2 = 2;
	
	private int color;
	
	/**
	 * Constructs a BiColorPiece of the given color.
	 * @param colorChoice one of the available colors
	 * @requires colorChoice must be one of the available colors
	 */
	public BiColorPiece(int colorChoice) {
		if (colorChoice != COLOR1 && colorChoice != COLOR2) {
			throw new IllegalArgumentException("Invalid team");
		}
		
		this.color = colorChoice;
	}
	
	/**
	 * Returns the color associated with this BiColorPiece.
	 * @return int matching one of the available color options
	 */
	public int getTeamColor() {
		return this.color;
	}
	
	/**
	 * String that represents this BiColorPiece
	 */
	@Override
	public String toString() {
		return "team " + this.color;
	}
}
