package CheckersPackage;

/**
 * Represents a square game board, with a set
 * amount of length * length tiles.
 */
public class GameBoard {
	private GameTile[][] tiles;
	
	/**
	 * Constructs a GameBoard of the given dimension
	 * @param length the side length of the square GameBoard
	 */
	public GameBoard(int length) {
		this.tiles = new GameTile[length + 1][length + 1];
		
		for (int i = 0; i <= length; i++) {
			for (int j = 0; j <= length; j++) {
				this.tiles[i][j] = new GameTile();
			}
		}
	}
	
	/**
	 * Returns the piece at the given location
	 * @param x component of location to check
	 * @param y component of location to check
	 * @return GamePiece at the specified location, null if no piece is there
	 * @requires X and Y should both be <= length
	 */
	public GamePiece getPieceAtLocation(int x, int y) {
		return this.tiles[x][y].getOccupyingPiece();
	}
	
	/**
	 * Returns the color at the given location
	 * @param x component of location to check
	 * @param y component of location to check
	 * @return String color at the specified location
	 * @requires X and Y should both be <= length
	 */
	public String getColorAtLocation(int x, int y) {
		return this.tiles[x][y].getTileColor();
	}
	
	/**
	 * Places a piece at the given location
	 * @param x component of the location to place the piece
	 * @param y component of the location to place the piece
	 * @param newPiece piece to place at the given location
	 * @requires X and Y should both be <= length
	 */
	public void putPieceAtLocation(int x, int y, GamePiece newPiece) {
		this.tiles[x][y].putOccupyingPiece(newPiece);
	}
	
	/**
	 * Returns the length of one side of the square board
	 * @return int length of the side of the board
	 */
	public int getLength() {
		return this.tiles.length;
	}
}








