
public class Heuristics {
	
	public static int heuristicID = 0;
	
	public static int evaluateState(Board board, int player) {
		switch(heuristicID) {
			case 0:
				return heuristic(board, player);
			default:
				return Constants.ERROR;
		}
	}
	
	/*
	 * Heuristic functions used to evaluate each generated board.
	 */
	
	public static int heuristic(Board resultingBoard, int player) {
		return resultingBoard.evaluatePositioning(player) + resultingBoard.countPieces(player)*15; 
	}
}
