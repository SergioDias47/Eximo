
public class Properties {
	/* 
	 * 
	 * Game Properties 
	 * 
	 * */
	
	public static int MINIMAX_DEPTH = 3;
	
	/* Maximum amount of time (milliseconds) to search for the best move. After then, the best move found so far is used */
	public static int MAX_SEARCH_TIME = 5000; 
	
	/* Minimum delay between moves (milliseconds) */
	public static int MIN_DELAY = 0; 
	
	/* Heuristic used for each player */
	public static int HEURISTIC_PLAYER_1 = 4;
	public static int HEURISTIC_PLAYER_2 = 3;
	
	/* Depth used for each player */
	public static int DEPTH_PLAYER_1 = 3;
	public static int DEPTH_PLAYER_2 = 3;
	
	public static void print() {
		System.out.println("-----------------------");
		System.out.println("MINIMAX_DEPTH = " + MINIMAX_DEPTH);
		System.out.println("MIN_DELAY = " + MIN_DELAY);
		System.out.println("MAX_SEARCH_TIME = " + MAX_SEARCH_TIME);
		System.out.println("HEURISTIC_PLAYER_1 = " + HEURISTIC_PLAYER_1);
		System.out.println("HEURISTIC_PLAYER_2 = " + HEURISTIC_PLAYER_2);
		System.out.println("-----------------------");
	}
}
