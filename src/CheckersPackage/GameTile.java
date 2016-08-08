package CheckersPackage;

/**
 * Represents a single tile on a gameboard,
 * through its color and occupying piece (if one exists).
 */
public class GameTile {
	
	/*
	 * Rep Invariant:
	 * tileColor != null && tileColor within {available colors}
	 */
	
	private GamePiece occupyingPiece;
	private String color;
	
	/**
	 * Red Color Designation
	 */
	public static final String TILE_RED = new String("R");
	
	/**
	 * Black Color Designation
	 */
	public static final String TILE_BLACK = new String("B");
	
	/**
	 * White Color Designation
	 */
	public static final String TILE_WHITE = new String("W");
	
	/**
	 * Constructs an empty GameTile of the default color. 
	 */
	public GameTile() {
		this(TILE_WHITE);
	}
	
	/**
	 * Constructs an empty GameTile of the specified color.
	 * @param tileColor String color being specified for this tile
	 * @requires tileColor should be one of the tile color constants available
	 */
	public GameTile(String tileColor) {
		if (tileColor == null) {
			throw new IllegalArgumentException("Tile color should not be null");
		}
		
		if (!tileColor.equals(TILE_RED) && !tileColor.equals(TILE_BLACK) && !tileColor.equals(TILE_WHITE)) {
			throw new IllegalArgumentException("Tile color should be one of the available options");
		}
		
		this.occupyingPiece = null;
		this.color = tileColor;
	}
	
	/**
	 * Returns the occupying GamePiece.
	 * @return GamePiece if there is a piece on this tile, null otherwise
	 */
	public GamePiece getOccupyingPiece() {
		return this.occupyingPiece;
	}
	
	/**
	 * Sets the occupyingPiece of this tile to the given piece.
	 * @param newPiece Piece to place in this tile
	 */
	public void putOccupyingPiece(GamePiece newPiece) {
		this.occupyingPiece = newPiece;
	}
	
	/**
	 * Returns this tile's tile color.
	 * @return String representing this tile's color
	 */
	public String getTileColor() {
		return this.color;
	}
}







