package CheckersTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CheckersPackage.CheckersBoard;
import CheckersPackage.GameBoard;

public class GameBoardTest {
	@Test
	public void constructSmallBoardTest() {
		@SuppressWarnings("unused")
		GameBoard newBoard = new CheckersBoard(4, "R", "B");
	}
	
	@Test
	public void constructLargeBoardTest() {
		@SuppressWarnings("unused")
		GameBoard newBoard = new CheckersBoard(16, "R", "B");
	}
	
	@Test
	public void getLengthBoardTest() {
		GameBoard newBoard = new CheckersBoard(4, "R", "B");
		assertEquals(newBoard.getLength(), 4);
	}
	
	@Test
	public void printSmallBoardTest() {
		GameBoard newBoard = new CheckersBoard(2, "R", "B");
		assertEquals(newBoard.toString(), "[\n"
				+ "{Color: R Piece: null}{Color: B Piece: null}\n"
				+ "{Color: B Piece: null}{Color: R Piece: null}\n"
				+ "]");
	}
}
