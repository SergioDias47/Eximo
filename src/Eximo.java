
public class Eximo {
	private int cells[];
	
	public Eximo() {
		initializeBoard();
		
	}
	
	public void initializeBoard() {
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
	
	
}
