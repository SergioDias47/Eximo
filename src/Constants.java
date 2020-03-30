
public class Constants {
	/* 
	 * 
	 * Properties 
	 * 
	 * */
	
	public final static int MINIMAX_DEPTH = 3;
	
	/* Maximum amount of time (milliseconds) to search for the best move. After then, the best move found so far is used */
	public final static int MAX_SEARCH_TIME = 1500; 
	
	/* Minimum delay between moves (milliseconds) */
	public final static int MIN_DELAY = 0; 
	
	/* Heuristic used for each player */
	public final static int HEURISTIC_PLAYER_1 = 2;
	public final static int HEURISTIC_PLAYER_2 = 0;
	
	/* 
	 * 
	 * Other constants 
	 * 
	 * */
	
	public final static int EMPTY_CELL = 0;
	public final static int WHITE_CELL = 1;
	public final static int BLACK_CELL = 2;
	public final static int DRAW = 0;
	public final static int PLAYER_1 = 1;
	public final static int PLAYER_2 = 2;
	public final static int BOARD_SIZE = 64;
	public final static int LINE_LENGTH = 8;
	public final static int NORTH = 0;
	public final static int NORTHWEST = 1;
	public final static int NORTHEAST = -1;
	public final static int[] FrontDirections = {NORTH, NORTHWEST, NORTHEAST};
	public final static int NO_CAPTURE = -1;
	public final static int WEST = 1;
	public final static int EAST = -1;
	public final static int MENU_PANEL = 0;
	public final static int GAME_PANEL = 1;
	public final static int PLAYER_VS_PLAYER = 0;
	public final static int PLAYER_VS_BOT = 1;
	public final static int BOT_VS_BOT = 2;
	public final static int NORMAL = 0;
	public final static int CAPTURE = 1;
	public final static int JUMP_OVER = 2;
	public final static int ERROR = -1;
	public final static boolean ACTIVATE_GRID = true;
	public final static String NONE_SELECTED = "";
	public final static String PLAYER_1_TURN = "   Player 1's Turn";
	public final static String PLAYER_2_TURN = "   Player 2's Turn";
	public final static String REMAINING_PIECES_MSG_1 = "      Player 1 \t\t Remaining pieces: ";
	public final static String REMAINING_PIECES_MSG_2 = "      Player 2 \t\t Remaining pieces: ";
	public final static String GAME_OVER_MSG_1 = "   Game over! Player 1 wins!";
	public final static String GAME_OVER_MSG_2 = "   Game over! Player 2 wins!";
}
