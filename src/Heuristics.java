
public class Heuristics {
	
	public static int heuristicID = 0;
	
	public static int evaluateState(Board board, int player) {
		if(player == Constants.PLAYER_1)
			heuristicID = Constants.HEURISTIC_PLAYER_1;
		else heuristicID = Constants.HEURISTIC_PLAYER_2;
		
		switch(heuristicID) {
			case Constants.RANDOM_HEURISTIC:
				return randomHeuristic();
			case Constants.BASIC_HEURISTIC:
				return basicHeuristic(board, player);
			case Constants.FIRST_HEURISTIC:
				return firstHeuristic(board, player);
			case Constants.IMPROVED_HEURISTIC:
				return improvedHeuristic(board, player);
			case Constants.ADVANCED_HEURISTIC:
				return advancedHeuristic(board, player);
			default:
				return Constants.ERROR;
		}
	}
	
	/*
	 * Heuristic functions used to evaluate each generated board.
	 */
	
	/*
	 * Plays randomly.
	 */
	private static int randomHeuristic() {
		return (int) Math.random()*1000;
	}
	
	/*
	 * Only takes into consideration the number of pieces, thus being basic.
	 */
	private static int basicHeuristic(Board board, int player) {
		return board.countPieces(player);
	}
	
	/*
	 * First implemented serious heuristic - takes into consideration the pieces' positioning and number of pieces.
	 */
	private static int firstHeuristic(Board board, int player) {
		return board.evaluatePositioning(player)
				+ board.countPieces(player) * 15;
	}
	
	/*
	 * Improvement for the first implemented heuristic. Also takes into account the pieces of the opponent.
	 */
	private static int improvedHeuristic(Board board, int player) {
		return board.evaluatePositioning(player)
				+ (board.countPieces(player)-board.countPieces(Utils.otherPlayer(player))) * 15;
	}
	
	/*
	 * Most advanced heuristic. Takes into account all above and also uses a defensive strategy, as well as attack.
	 */
	private static int advancedHeuristic(Board board, int player) {
		return board.evaluatePositioning(player)
				+ board.countPieces(player)-board.countPieces(Utils.otherPlayer(player)) * 60
				  + Integer.min(6, board.countPiecesBeginning(player)) * 30
				     + board.evaluateSideColumns(player);
	}
}
