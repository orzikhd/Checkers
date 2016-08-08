package CheckersTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CheckersPackage.BiColorPiece;
import CheckersPackage.CheckerPiece;
import CheckersPackage.CheckersBoard;
import CheckersPackage.GameBoard;
import CheckersPackage.GamePiece;

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
	
	@Test
	public void putPieceTest() {
		GameBoard newBoard = new CheckersBoard(8, "R", "B");
		newBoard.putPieceAtLocation(7, 2, new CheckerPiece(BiColorPiece.COLOR1));
		assertEquals(newBoard.toString(), "[\n"
				+ "{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}\n"
				+ "{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}\n"
				+ "{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: team 1}\n"
				+ "{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}\n"
				+ "{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}\n"
				+ "{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}\n"
				+ "{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}\n"
				+ "{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}\n"
				+ "]");
	}
	
	@Test
	public void getPieceTest() {
		GameBoard newBoard = new CheckersBoard(8, "R", "B");
		GamePiece testPiece = new CheckerPiece(BiColorPiece.COLOR1);
		newBoard.putPieceAtLocation(7, 2, testPiece);
		assertEquals(newBoard.getPieceAtLocation(7, 2), testPiece);
	}
}
