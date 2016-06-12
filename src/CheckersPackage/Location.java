package CheckersPackage;

/**
 * Helper class for transferring information between Checkers model
 * and view.
 * Holds (x,y) coordinate location and color flag of a BiColorPiece
 */
public class Location {
	private int X;
	private int Y;
	private boolean colorFlag;
	
	public Location(int X, int Y, boolean colorFlag) {
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
