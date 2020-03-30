
public class MatchInfo {
	public final int numMoves1;
	public final int numMoves2;
	public final int numPieces1;
	public final int numPieces2;
	public final boolean gameOver;
	
	public MatchInfo(int numMoves1, int numMoves2, int numPieces1, int numPieces2, boolean gameOver) {
		this.numMoves1 = numMoves1;
		this.numMoves2 = numMoves2;
		this.numPieces1 = numPieces1;
		this.numPieces2 = numPieces2;
		this.gameOver = gameOver;
	}
}
