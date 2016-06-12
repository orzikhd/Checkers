package CheckersPackage;

import java.util.Set;

public class CheckersModel {
	
	private CheckersBoard board;
	private Set<BiColorPiece> p1Pieces;
	private Set<BiColorPiece> p2Pieces;
	
	/**
	 * Models a Checkers Board of given length
	 * @param length of checkers board
	 */
	public CheckersModel(int length) {
		board = new CheckersBoard(length, "RED", "BLACK");
		
		BiColorPiece curr;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				curr = (BiColorPiece) board.getPieceAtLocation(i, j);
				if (curr.getColor()) {
					p1Pieces.add(curr);
				} else {
					p2Pieces.add(curr);
				}
			}
		}
	}
	
	public int countPlayer1() {
		return 0;
	}
	
	public int countPlayer2() {
		return 0;
	}
	
	public Set<Location> getBoardState() {
		return null;
	}
	
	public void emptyBoard() {
		
	}
	
	public void setUpBoard() {
		
	}
	
	public void movePiece() {
		
	}
	
	public String declareVictor() {
		return null;
	}
	
}





