package CheckersPackage;

/**
 * Represents a square game board, with a set
 * amount of tiles.
 */
public class GameBoard {
	private GameTile[][] tiles;
	
	public GameBoard(int width) {
		this.tiles = new GameTile[width][width];
	}
}
