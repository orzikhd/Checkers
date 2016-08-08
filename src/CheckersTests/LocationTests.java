package CheckersTests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import CheckersPackage.BiColorPiece;
import CheckersPackage.GameTile;
import CheckersPackage.Location;

public class LocationTests {
	@Test
	public void testEqualsReflexive() {
		Location blackPiece = new Location(1, 0, BiColorPiece.COLOR1, false, GameTile.TILE_BLACK);
		assertTrue(blackPiece.equals(blackPiece));
	}
	
	@Test
	public void testEqualsSymmetric() {
		Location blackPiece = new Location(1, 0, BiColorPiece.COLOR1, false, GameTile.TILE_BLACK);
		Location blackPiece2 = new Location(1, 0, BiColorPiece.COLOR1, false, GameTile.TILE_BLACK);
		assertTrue(blackPiece.equals(blackPiece2));		
		assertTrue(blackPiece2.equals(blackPiece));
	}	
	
	@Test
	public void testEqualsTransitive() {
		Location blackPiece = new Location(1, 0, BiColorPiece.COLOR1, false, GameTile.TILE_BLACK);
		Location blackPiece2 = new Location(1, 0, BiColorPiece.COLOR1, false, GameTile.TILE_BLACK);		
		Location blackPiece3 = new Location(1, 0, BiColorPiece.COLOR1, false, GameTile.TILE_BLACK);	
		assertTrue(blackPiece.equals(blackPiece2));		
		assertTrue(blackPiece2.equals(blackPiece3));
		assertTrue(blackPiece3.equals(blackPiece));
	}
	
	@Test
	public void testEqualsNull() {
		Location blackPiece = new Location(1, 0, BiColorPiece.COLOR1, false, GameTile.TILE_BLACK);
		assertTrue(!blackPiece.equals(null));		
	}
}






