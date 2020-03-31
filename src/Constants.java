import java.awt.Color;

public class Constants {
	
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
	public final static int SETTINGS_PANEL = 2;
	public final static int PLAYER_VS_PLAYER = 0;
	public final static int PLAYER_VS_BOT = 1;
	public final static int BOT_VS_BOT = 2;
	public final static int NORMAL = 0;
	public final static int CAPTURE = 1;
	public final static int JUMP_OVER = 2;
	public final static int ERROR = -1;
	public final static int RANDOM_HEURISTIC = 0;
	public final static int BASIC_HEURISTIC = 1;
	public final static int FIRST_HEURISTIC = 2;
	public final static int IMPROVED_HEURISTIC = 3;
	public final static int ADVANCED_HEURISTIC = 4;
	public final static boolean ACTIVATE_GRID = true;
	
	public final static Color BROWN_COLOR = new Color(156, 90, 3);
	public final static Color BEIGE_COLOR = new Color(255, 255, 227);
	
	public final static String NONE_SELECTED = "";
	public final static String PLAYER_1_TURN = "   Player 1's Turn";
	public final static String PLAYER_2_TURN = "   Player 2's Turn";
	public final static String REMAINING_PIECES_MSG_1 = "      Player 1 ->  \t\t Remaining pieces: ";
	public final static String REMAINING_PIECES_MSG_2 = "      Player 2 ->  \t\t Remaining pieces: ";
	public final static String GAME_OVER_MSG_1 = "   Game over! Player 1 wins!";
	public final static String GAME_OVER_MSG_2 = "   Game over! Player 2 wins!";
	public static final String NUM_MOVES_MSG = "     Nº Movements: ";
	public static final String ELAPSED_TIME_MSG = "     Elapsed time: ";
}
