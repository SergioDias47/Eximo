
public class Board {
	private int cells[];
	
	public Board() {
		initialize();
	}
	
	public Board(Board board) {
		cells = new int[Constants.BOARD_SIZE];
		System.arraycopy(board.cells, 0, cells, 0, board.length());
	}
	
	/* Initializes the board as it should be in the beginning of a match */
	public void initialize() {
		cells = new int[Constants.BOARD_SIZE];
		for(int i = 1; i <= 6; i++) {
			cells[i] = Constants.WHITE_CELL;
			cells[i+8] = Constants.WHITE_CELL;
			cells[i+48] = Constants.BLACK_CELL;
			cells[i+56] = Constants.BLACK_CELL;
		}
		cells[17] = Constants.WHITE_CELL; cells[18] = Constants.WHITE_CELL;
		cells[21] = Constants.WHITE_CELL; cells[22] = Constants.WHITE_CELL; 
		cells[41] = Constants.BLACK_CELL; cells[42] = Constants.BLACK_CELL;
		cells[45] = Constants.BLACK_CELL; cells[46] = Constants.BLACK_CELL; 
	}
	
	public void initializeDebugBoard() {
		cells = new int[Constants.BOARD_SIZE];
		cells[33] = Constants.WHITE_CELL;
		cells[40] = Constants.BLACK_CELL;
	}
	
	public int getCell(int id) {
		return cells[id];
	}
	
	public void setCell(int id, int value) {
		cells[id] = value;
	}
	 
	public int length() {
		return cells.length;
	}
	
	public int countPieces(int player) {
		int count = 0;
		for(int cell : cells) {
			if(cell == player)
				count++;
		}
		return count;
	}
	
	public int countSafeZoneFreeCells(int player) {
		int count = 0;
		for(int position = 0; position < cells.length; position++) {
			if(Utils.isWithinSafeZone(player, position) && cells[position] == Constants.EMPTY_CELL)
				count++;
		}
		return count;
	}
	
	/* Evaluates a board when it comes to the pieces' position in the ambit of a given player.
	 * 	The closer the pieces of the given player are to the opponent's area, the higher the returned evaluation will be. 
	 * 	The closer the pieces of the opponent are to the given player's area, the lower the returned evaluation will be. */
	public int evalutatePositioning(int player) {
		int evaluation = 0;
		for(int cell : cells) {
			if(cell == player) {
				Position pos = new Position(cell);
				evaluation += (player == 1)? pos.y : 7 - pos.y;
			} else if(cell == Utils.otherPlayer(player)){
				Position pos = new Position(cell);
				evaluation -= (player == 1)? 7 - pos.y : pos.y;
			}
		}
		return evaluation;
	}
}
