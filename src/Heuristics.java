
public class Heuristics {
	
	public static int heuristicID = 0;
	
	public static int evaluateState(Board board, int player) {
		if(player == Constants.PLAYER_1)
			heuristicID = Constants.HEURISTIC_PLAYER_1;
		else heuristicID = Constants.HEURISTIC_PLAYER_2;
		
		switch(heuristicID) {
			case 0:
				return mainHeuristic(board, player);
			case 1:
				return basicHeuristic(board, player);
			case 2:
				return randomHeuristic();
			default:
				return Constants.ERROR;
		}
	}
	
	/*
	 * Heuristic functions used to evaluate each generated board.
	 */
	
	private static int mainHeuristic(Board board, int player) {
		return board.evaluatePositioning(player)
				+ board.countPieces(player) * 15;
	}
	
	private static int basicHeuristic(Board board, int player) {
		return board.countPieces(player) * 15;
	}
	
	private static int randomHeuristic() {
		return (int) Math.random()*100;
	}
}
