package CheckersPackage;

/**
 * Represents a single tile on a gameboard,
 * including the occupying piece if one exists
 * and the tile's color
 */
public class GameTile {
	private GamePiece occupyingPiece;
	private String tileColor;
	
	public static final String TILE_RED = new String("RED");
	public static final String TILE_BLACK = new String("BLACK");
	public static final String TILE_WHITE = new String("WHITE");
	
	public GameTile() {
		
	}
	
}
