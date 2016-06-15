package CheckersTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import CheckersPackage.BiColorPiece;
import CheckersPackage.CheckersModel;
import CheckersPackage.GameTile;
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
		Location RightEdgePiece = new Location(7, 2, BiColorPiece.TEAM1, false, GameTile.TILE_BLACK);
		Location moveDownLeft = new Location(6, 3, Location.NULL_TEAM_COLOR, false, GameTile.TILE_BLACK);
		Location moveDown = new Location(7, 3, Location.NULL_TEAM_COLOR, false, GameTile.TILE_RED);
		Location moveUp = new Location(7, 1, Location.NULL_TEAM_COLOR, false, GameTile.TILE_RED);
		Location moveUpLeft = new Location(6, 1, BiColorPiece.TEAM1, false, GameTile.TILE_BLACK);
		assertTrue(newModel.checkValidMove(RightEdgePiece, moveDownLeft));
		assertTrue(!newModel.checkValidMove(RightEdgePiece, moveDown));
		assertTrue(!newModel.checkValidMove(RightEdgePiece, moveUp));
		assertTrue(!newModel.checkValidMove(RightEdgePiece, moveUpLeft));	
	}
	
	@Test
	public void validateCornerTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		//upperRight is at (7, 0) so 0*8 + 7 = 7
		Location upperRight = newModel.getBoardState().get(7);
		Location moveRight = new Location(8, 0, Location.NULL_TEAM_COLOR, false, GameTile.TILE_WHITE);
		assertTrue(!newModel.checkValidMove(upperRight, moveRight));
	}
	
	@Test
	public void movePieceTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		assertEquals(newModel.getBoardState().get(30).getPieceTeamColor(), Location.NULL_TEAM_COLOR);
		Location RightEdgePiece = new Location(7, 2, BiColorPiece.TEAM1, false, GameTile.TILE_BLACK);
		Location moveDownLeft = new Location(6, 3, Location.NULL_TEAM_COLOR, false, GameTile.TILE_BLACK);
		newModel.movePiece(RightEdgePiece, moveDownLeft);
		List<Location> postMove = newModel.getBoardState();
		
		//over 6 columns and down 3 rows so 3*8 + 6 = 30
		assertEquals(postMove.get(30).getPieceTeamColor(), BiColorPiece.TEAM1);
	}
	
	@Test
	public void validateJumpOnceTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		newModel.switchPlayer();
		Location startTeam2Spot = new Location(2, 5, BiColorPiece.TEAM2, false, GameTile.TILE_BLACK);
		Location moveUpRight = new Location(3, 4, Location.NULL_TEAM_COLOR, false, GameTile.TILE_BLACK);
		Location moveUpRightPostMove = new Location(3, 4, BiColorPiece.TEAM2, false, GameTile.TILE_BLACK);
		Location moveUpRight2 = new Location(4, 3, Location.NULL_TEAM_COLOR, false, GameTile.TILE_BLACK);
		newModel.movePiece(startTeam2Spot, moveUpRight);
		newModel.movePiece(moveUpRightPostMove, moveUpRight2);
		
		newModel.switchPlayer();
		List<Location> preJump = newModel.getBoardState();
		//team 1 piece at 3, 2 so 2*8 + 3 = 19
		//team 2 piece at 4, 3 so 3*8 + 4 = 28
		//team 1 piece new location would be 5, 4 so 4*8 + 5 = 37
		assertTrue(newModel.checkValidMove(preJump.get(19), preJump.get(37)));
	}

	@Test
	public void validateMoveAdjacent() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		List<Location> startState = newModel.getBoardState();
		//move from (1,2) to (2,3)
		//2*8 + 1 = 17
		//3*8 + 2 = 26
		assertTrue(newModel.checkValidMove(startState.get(17), startState.get(26)));
		newModel.movePiece(startState.get(17), startState.get(26));
		newModel.switchPlayer();
		//move from (2,5) to (1,4)
		//5*8 + 2 = 42
		//4*8 + 1 = 33
		List<Location> secondState = newModel.getBoardState();
		assertTrue(newModel.checkValidMove(secondState.get(42), secondState.get(33)));
		newModel.movePiece(secondState.get(42), secondState.get(33));
		newModel.switchPlayer();
		//move from (2,3) to (3,4)
		//3*8 + 2 = 26
		//4*8 + 3 = 35
		List<Location> thirdState = newModel.getBoardState();
		assertTrue(newModel.checkValidMove(thirdState.get(26), thirdState.get(35)));
		newModel.movePiece(thirdState.get(26), thirdState.get(35));
	}
	
	@Test
	public void JumpOnceTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		newModel.switchPlayer();
		Location startTeam2Spot = new Location(2, 5, BiColorPiece.TEAM2, false, GameTile.TILE_BLACK);
		Location moveUpRight = new Location(3, 4, Location.NULL_TEAM_COLOR, false, GameTile.TILE_BLACK);
		Location moveUpRightPostMove = new Location(3, 4, BiColorPiece.TEAM2, false, GameTile.TILE_BLACK);
		Location moveUpRight2 = new Location(4, 3, Location.NULL_TEAM_COLOR, false, GameTile.TILE_BLACK);
		newModel.movePiece(startTeam2Spot, moveUpRight);
		newModel.movePiece(moveUpRightPostMove, moveUpRight2);
		
		newModel.switchPlayer();
		List<Location> preJump = newModel.getBoardState();
		//team 1 piece at 3, 2 so 2*8 + 3 = 19
		//team 2 piece at 4, 3 so 3*8 + 4 = 28
		//team 1 piece new location would be 5, 4 so 4*8 + 5 = 37
		newModel.movePiece(preJump.get(19), preJump.get(37));
		
		List<Location> postJump = newModel.getBoardState();
		assertEquals(postJump.get(19).getPieceTeamColor(), Location.NULL_TEAM_COLOR);
		assertEquals(postJump.get(28).getPieceTeamColor(), Location.NULL_TEAM_COLOR);
		assertEquals(postJump.get(37).getPieceTeamColor(), BiColorPiece.TEAM1);		
	}
	
	@Test
	public void removePieceTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		assertTrue(newModel.getBoardState().get(1).getPieceTeamColor() != Location.NULL_TEAM_COLOR);
		Location removeTestLoc = new Location(1, 0, 0, false, null);
		newModel.removePiece(removeTestLoc);
		assertEquals(newModel.getBoardState().get(1).getPieceTeamColor(), Location.NULL_TEAM_COLOR);
		
	}
	
	@Test
	public void RespectCurrentPlayerTest() {
		CheckersModel newModel = new CheckersModel();
		newModel.setUpBoard();
		newModel.switchPlayer();
		//this is the test for moving once, but should all fail since now its TEAM2 playing
		Location RightEdgePiece = new Location(7, 2, BiColorPiece.TEAM1, false, GameTile.TILE_BLACK);
		Location moveDownLeft = new Location(6, 3, Location.NULL_TEAM_COLOR, false, GameTile.TILE_BLACK);
		assertTrue(!newModel.checkValidMove(RightEdgePiece, moveDownLeft));
	}
	
	@Test
	public void declarePlayer1VictorTest() {
		
	}
	
	@Test
	public void declarePlayer2VictorTest() {
		
	}
}
