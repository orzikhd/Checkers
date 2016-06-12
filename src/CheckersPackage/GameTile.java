package CheckersPackage;

/**
 * Represents a single tile on a gameboard,
 * including the occupying piece if one exists
 * and the tile's color
 */
public class GameTile {
	
	/*
	 * Rep Invariant:
	 * tileColor != null && tileColor within {available colors}
	 */
	
	private GamePiece occupyingPiece;
	private String tileColor;
	
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
	 * Constructs a new GameTile of the default color 
	 */
	public GameTile() {
		this(TILE_WHITE);
	}
	
	/**
	 * Constructs a GameTile of the specified color
	 * @param tileColor String color specified for this tile
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
		this.tileColor = tileColor;
	}
	
	/**
	 * Returns the occupying GamePiece
	 * @return GamePiece if there is a piece on this tile, null otherwise
	 */
	public GamePiece getOccupyingPiece() {
		return this.occupyingPiece;
	}
	
	/**
	 * Sets the occupyingPiece of this tile to the given piece
	 * @param newPiece
	 */
	public void putOccupyingPiece(GamePiece newPiece) {
		this.occupyingPiece = newPiece;
	}
	
	/**
	 * Returns this tile's color
	 * @return String representing this tile's color
	 */
	public String getTileColor() {
		return this.tileColor;
	}
}







