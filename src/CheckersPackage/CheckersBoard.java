package CheckersPackage;

/**
 * Represents a game board, with a checkered pattern within the tiles
 */
public class CheckersBoard implements GameBoard {

	/*
	 * Rep Invariant:
	 * for all i, j tiles[i][j] != null
	 * and tiles[i][j] rep invariant holds true
	 * and all tiles touching a particular tile have a different color
	 * from that particular tile
	 * and there are exactly two distinct tile colors in use
	 */
	
	private GameTile[][] tiles;
	
	/**
	 * Constructs a GameBoard of the given dimension
	 * @param length the side length of the square GameBoard
	 * @param color1 one of two colors to use for the checkered pattern
	 * @param color2 one of two colors to use for the checkered pattern
	 * @requires color1 and color2 should both be available, distinct tile colors
	 * 	and length > 0
	 */
	public CheckersBoard(int length, String color1, String color2) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length of board less than 1");
		}
		if (color1.equals(color2)) {
			throw new IllegalArgumentException("color1 and color2 should be distinct");
		}
		this.tiles = new GameTile[length][length];
		
		this.fillBoard(color1, color2);
	}
	
	//creates the checkered pattern by flipping on even rows and on each tile
	private void fillBoard(String color1, String color2) {
		
		boolean colorFlip = false;
		for (int i = 0; i < this.getLength(); i++) {
			for (int j = 0; j < this.getLength(); j++) {
				if (i % 2 == 0) {
					if (colorFlip) {
						this.tiles[i][j] = new GameTile(color2);
					} else {
						this.tiles[i][j] = new GameTile(color1);
					}
				} else {
					if (colorFlip) {
						this.tiles[i][j] = new GameTile(color1);
					} else {
						this.tiles[i][j] = new GameTile(color2);
					}					
				}
				colorFlip = !colorFlip;
			}
		}
	}
	
	/**
	 * Returns the piece at the given coordinates
	 * @param x coordinate to check
	 * @param y coordinate to check
	 * @return GamePiece at the specified location, null if no piece is there
	 * @requires 0 <= x < length && 0 <= y < length
	 */
	public GamePiece getPieceAtLocation(int x, int y) {
		if (x < 0 || x >= this.getLength() || y < 0 || y >= this.getLength()) {
			throw new IllegalArgumentException("Coordinate out of bounds");
		}
				
		//since arrays are accessed by row and then col, it should be y first and then x
		return this.tiles[y][x].getOccupyingPiece();
	}
	
	/**
	 * Returns the tile color at the given coordinates
	 * @param x coordinate to check
	 * @param y coordinate to check
	 * @return String tile color at the specified location
	 * @requires 0 <= x < length && 0 <= y < length
	 */
	public String getColorAtLocation(int x, int y) {
		if (x < 0 || x >= this.getLength() || y < 0 || y >= this.getLength()) {
			throw new IllegalArgumentException("Coordinate out of bounds");
		}
		
		//since arrays are accessed by row and then col, it should be y first and then x
		return this.tiles[y][x].getTileColor();
	}
	
	/**
	 * Places a piece at the given coordinates
	 * @param x coordinate to place the piece
	 * @param y coordinate to place the piece
	 * @param newPiece piece to place at the given location
	 * @requires 0 <= x < length && 0 <= y < length
	 */
	public void putPieceAtLocation(int x, int y, GamePiece newPiece) {
		if (x < 0 || x >= this.getLength() || y < 0 || y >= this.getLength()) {
			throw new IllegalArgumentException("Coordinate out of bounds");
		}
		
		//since arrays are accessed by row and then col, it should be y first and then x
		this.tiles[y][x].putOccupyingPiece(newPiece);
	}
	
	/**
	 * Returns the length of one side of the square board
	 * @return int length of the side of the board
	 */
	public int getLength() {
		return this.tiles.length;
	}
	
	/**
	 * String that represents the CheckersBoard and its contents
	 */
	@Override
	public String toString() {
		String result = "[\n";
		
		for (int i = 0; i < this.getLength(); i++) {
			for (int j = 0; j < this.getLength(); j++) {
				result += "{Color: " + this.tiles[i][j].getTileColor() + " Piece: " + this.tiles[i][j].getOccupyingPiece() + "}";
			}
			result += "\n";
		}
		
		return result + "]";
	}
}
