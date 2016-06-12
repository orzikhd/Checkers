package CheckersPackage;

/**
 * A BiColorPiece is a GamePiece with a color,
 * specified as one of a choice of two colors
 */
public class BiColorPiece extends GamePiece {
	
	public static final int TEAM1 = 1;
	public static final int TEAM2 = 2;
	private int teamColor;
	
	/**
	 * Constructs a BiColorPiece of the given color
	 * @param teamColor one of the available team colors
	 * @requires teamColor must be one of the available team colors
	 */
	public BiColorPiece(int teamColor) {
		if (teamColor != TEAM1 && teamColor != TEAM2) {
			throw new IllegalArgumentException("Invalid team");
		}
		
		this.teamColor = teamColor;
	}
	
	/**
	 * Returns color choice of this BiColorPiece
	 * @return
	 */
	public int getTeamColor() {
		return this.teamColor;
	}
	
	@Override
	public String toString() {
		return "team " + this.teamColor;
	}
}
