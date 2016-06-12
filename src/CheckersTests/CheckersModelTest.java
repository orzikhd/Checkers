package CheckersTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import CheckersPackage.CheckersModel;

public class CheckersModelTest {
	
	@Test
	public void ConstructorTest() {
		@SuppressWarnings("unused")
		CheckersModel newModel = new CheckersModel();
	}
	
	@Test
	public void countPiecesTest() {
		CheckersModel newModel = new CheckersModel();
		assertEquals(newModel.countPlayer1(), 0);
		assertEquals(newModel.countPlayer2(), 0);
		
		newModel.setUpBoard();
		assertEquals(newModel.countPlayer1(), 12);
		assertEquals(newModel.countPlayer2(), 12);		
	}
	
	@Test
	public void SetUpBoardTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();

		assertEquals(newModel.toString(), "[\n"
				+ "{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}\n"
				+ "{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}\n"
				+ "{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}{Color: RED Piece: null}{Color: BLACK Piece: team true}\n"
				+ "{Color: BLACK Piece: null}{Color: RED Piece: null}{Color: BLACK Piece: null}{Color: RED Piece: null}{Color: BLACK Piece: null}{Color: RED Piece: null}{Color: BLACK Piece: null}{Color: RED Piece: null}\n"
				+ "{Color: RED Piece: null}{Color: BLACK Piece: null}{Color: RED Piece: null}{Color: BLACK Piece: null}{Color: RED Piece: null}{Color: BLACK Piece: null}{Color: RED Piece: null}{Color: BLACK Piece: null}\n"
				+ "{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}\n"
				+ "{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}\n"
				+ "{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}{Color: BLACK Piece: team false}{Color: RED Piece: null}\n"
				+ "]");
	}
	
	@Test
	public void emptyBoardTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		newModel.emptyBoard();
		
		assertTrue(!newModel.toString().contains("Piece: team"));
		
		assertEquals(newModel.countPlayer1(), 0);
		assertEquals(newModel.countPlayer2(), 0);	
	}
	
	@Test
	public void movePieceTest() {
		
	}
	
	@Test
	public void removePieceTest() {
		
	}
	
	@Test
	public void declarePlayer1VictorTest() {
		
	}
	
	@Test
	public void declarePlayer2VictorTest() {
		
	}
}
