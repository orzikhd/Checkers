package CheckersPackage;

/**
 * Helper class for transferring information between Checkers model
 * and view.
 * Holds (x,y) coordinate location and color flag of a BiColorPiece
 */
public class Location {
	
	/*
	 * Rep Invariant:
	 * X >= 0 and Y >= 0
	 */
	
	private int X;
	private int Y;
	private boolean colorFlag;
	
	/**
	 * Creates a Location given a coordinate pair and color boolean
	 * @param X component of location
	 * @param Y component of location
	 * @param colorFlag boolean value to represent one color or another
	 * 	as a true/false value
	 * @requires X >= 0 and Y >= 0
	 */
	public Location(int X, int Y, boolean colorFlag) {
		if (X < 0 || Y < 0) {
			throw new IllegalArgumentException("Location component should be 0 or positive");
		}
		
		this.X = X;
		this.Y = Y;
		this.colorFlag = colorFlag;
	}
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public boolean getColor() {
		return this.colorFlag;
	}
}
