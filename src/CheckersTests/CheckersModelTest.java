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
				+ "{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}\n"
				+ "{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}\n"
				+ "{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}{Color: R Piece: null}{Color: B Piece: team 1}\n"
				+ "{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}\n"
				+ "{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}{Color: R Piece: null}{Color: B Piece: null}\n"
				+ "{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}\n"
				+ "{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}\n"
				+ "{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}{Color: B Piece: team 2}{Color: R Piece: null}\n"
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
	public void getBoardStateTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		newModel.getBoardState();
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
