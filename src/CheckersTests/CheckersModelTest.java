package CheckersTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import CheckersPackage.BiColorPiece;
import CheckersPackage.CheckersModel;
import CheckersPackage.Location;

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
		assertEquals(newModel.getBoardState().get(1).getPieceTeamColor(), BiColorPiece.TEAM1);
		newModel.emptyBoard();
		assertEquals(newModel.getBoardState().get(1).getPieceTeamColor(), Location.NULL_TEAM_COLOR);
	}
	
	@Test
	public void validateMoveOnceTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		Location LeftEdgePiece = new Location(7, 2, BiColorPiece.TEAM1, false, "B");
		Location moveDownLeft = new Location(6, 3, Location.NULL_TEAM_COLOR, false, "B");
		Location moveDown = new Location(7, 3, Location.NULL_TEAM_COLOR, false, "R");
		Location moveUp = new Location(7, 1, Location.NULL_TEAM_COLOR, false, "R");
		Location moveUpLeft = new Location(6, 1, Location.NULL_TEAM_COLOR, false, "B");
		assertTrue(newModel.checkValidMove(LeftEdgePiece, moveDownLeft));
		assertTrue(!newModel.checkValidMove(LeftEdgePiece, moveDown));
		assertTrue(!newModel.checkValidMove(LeftEdgePiece, moveUp));
		assertTrue(!newModel.checkValidMove(LeftEdgePiece, moveUpLeft));	
	}
	
	@Test
	public void movePieceTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		assertEquals(newModel.getBoardState().get(51).getPieceTeamColor(), Location.NULL_TEAM_COLOR);
		Location LeftEdgePiece = new Location(7, 2, BiColorPiece.TEAM1, false, "B");
		Location moveDownLeft = new Location(6, 3, Location.NULL_TEAM_COLOR, false, "B");
		newModel.movePiece(LeftEdgePiece, moveDownLeft);
		List<Location> postMove = newModel.getBoardState();
		postMove.get(30).getPieceTeamColor();
		
		//over 6 columns and down 3 rows so 6*8 + 3 = 51
		assertEquals(postMove.get(51).getPieceTeamColor(), BiColorPiece.TEAM1);
	}
	
	@Test
	public void validateJumpOnceTest() {
		
	}
	
	@Test
	public void removePieceTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		assertTrue(newModel.getBoardState().get(1).getPieceTeamColor() != Location.NULL_TEAM_COLOR);
		Location removeTestLoc = new Location(1, 0, 0, false, null);
		newModel.removePiece(removeTestLoc);
		//list goes down columns and then over one in the row, so get(8) is location (1, 0)
		assertEquals(newModel.getBoardState().get(8).getPieceTeamColor(), Location.NULL_TEAM_COLOR);
		
	}
	
	@Test
	public void declarePlayer1VictorTest() {
		
	}
	
	@Test
	public void declarePlayer2VictorTest() {
		
	}
}
