package CheckersTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CheckersPackage.CheckersBoard;
import CheckersPackage.GameBoard;

public class GameBoardTest {
	@Test
	public void constructSmallBoardTest() {
		GameBoard newBoard = new CheckersBoard(4, "RED", "BLACK");
	}
	
	@Test
	public void constructLargeBoardTest() {
		GameBoard newBoard = new CheckersBoard(16, "RED", "BLACK");
	}
	
	@Test
	public void getLengthBoardTest() {
		GameBoard newBoard = new CheckersBoard(4, "RED", "BLACK");
		assertEquals(newBoard.getLength(), 4);
	}
	
	@Test
	public void printSmallBoardTest() {
		GameBoard newBoard = new CheckersBoard(2, "RED", "BLACK");
		System.out.println(newBoard);
		assertEquals(newBoard.toString(), "[\n"
				+ "{Color: RED Piece: null}{Color: BLACK Piece: null}\n"
				+ "{Color: BLACK Piece: null}{Color: RED Piece: null}\n"
				+ "]");
	}
}
