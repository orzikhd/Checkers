package CheckersPackage;

/**
 * Represents a square game board, with a set
 * amount of length * length tiles.
 */
public interface GameBoard {
	
	/**
	 * Returns the piece at the given location
	 * @param x component of location to check
	 * @param y component of location to check
	 * @return GamePiece at the specified location, null if no piece is there
	 * @requires X and Y should both be <= length
	 */
	public GamePiece getPieceAtLocation(int x, int y);
	
	/**
	 * Returns the color at the given location
	 * @param x component of location to check
	 * @param y component of location to check
	 * @return String color at the specified location
	 * @requires X and Y should both be <= length
	 */
	public String getColorAtLocation(int x, int y);
	
	/**
	 * Places a piece at the given location
	 * @param x component of the location to place the piece
	 * @param y component of the location to place the piece
	 * @param newPiece piece to place at the given location
	 * @requires X and Y should both be <= length
	 */
	public void putPieceAtLocation(int x, int y, GamePiece newPiece);
	
	/**
	 * Returns the length of one side of the square board
	 * @return int length of the side of the board
	 */
	public int getLength();
}








