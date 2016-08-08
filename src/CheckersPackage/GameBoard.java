package CheckersPackage;

/**
 * Represents a square game board, with a set
 * amount of length * length tiles.
 */
public interface GameBoard {
	
	/**
	 * Returns the piece at the given coordinates
	 * @param x coordinate to check
	 * @param y coordinate to check
	 * @return GamePiece at the specified location, null if no piece is there
	 * @requires 0 <= x < length && 0 <= y < length
	 */
	public GamePiece getPieceAtLocation(int x, int y);
	
	/**
	 * Returns the tile color at the given coordinates
	 * @param x coordinate to check
	 * @param y coordinate to check
	 * @return String tile color at the specified location
	 * @requires 0 <= x < length && 0 <= y < length
	 */
	public String getColorAtLocation(int x, int y);
	
	/**
	 * Places a piece at the given coordinates
	 * @param x coordinate to place the piece
	 * @param y coordinate to place the piece
	 * @param newPiece piece to place at the given location
	 * @requires 0 <= x < length && 0 <= y < length
	 */
	public void putPieceAtLocation(int x, int y, GamePiece newPiece);
	
	/**
	 * Returns the length of one side of the square board
	 * @return int length of the side of the board
	 */
	public int getLength();
}








