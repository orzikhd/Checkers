package CheckersTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CheckersPackage.GameBoard;

public class GameBoardTest {
	@Test
	public void constructSmallBoardTest() {
		GameBoard newBoard = new GameBoard(4);
	}
	
	@Test
	public void constructLargeBoardTest() {
		GameBoard newBoard = new GameBoard(16);
	}
	
	@Test
	public void getLengthBoardTest() {
		GameBoard newBoard = new GameBoard(4);
		assertEquals(newBoard.getLength(), 4);
	}
	
	@Test
	public void printSmallBoardTest() {
		GameBoard newBoard = new GameBoard(2);
		assertEquals(newBoard.toString(), "[\n"
				+ "{Color: WHITE Piece: null}{Color: WHITE Piece: null}\n"
				+ "{Color: WHITE Piece: null}{Color: WHITE Piece: null}\n"
				+ "]");
	}
}
