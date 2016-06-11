package CheckersPackage;

/**
 * A GamePiece is an object that has a position,
 * most likely on some game board, which can also
 * be moved to a different position.
 */
public class GamePiece {
	private int X;
	private int Y;
	
	/**
	 * Constructs a new GamePiece at the default location
	 */
	public GamePiece() {
		this(0, 0);
	}
	
	/**
	 * Constructs a new GamePiece at the given location
	 * @param X component of given location
	 * @param Y component of given location
	 * @requires X and Y >= 0
	 */
	public GamePiece(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}
	
	/**
	 * Returns the X value of this piece's position
	 * @return X component of this piece's position
	 */
	public int getX() {
		return X;
	}
	
	/**
	 * Returns the Y value of this piece's position
	 * @return Y component of this piece's position
	 */
	public int getY() {
		return Y;
	}
	
	/**
	 * Changes the X coordinate 
	 * @param newX new int value for the X coordinate
	 */
	public void setX(int newX) {
		this.X = newX;
	}
	
	/**
	 * Changes the Y coordinate 
	 * @param newY new int value for the Y coordinate
	 */	
	public void setY(int newY) {
		this.Y = newY;
	}
}
