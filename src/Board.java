import java.util.List;
import java.util.ArrayList;

public class Board {
    private int[][] cells;
    public boolean pieceReachedEnd;
    private List<Board> nextBoards;
    private Board parent;

    public Board() {
        initialize();
        pieceReachedEnd = false;
        nextBoards = new ArrayList<Board>();
        parent = null;
    }

    /*
     * Copy constructor.
     */
    public Board(Board board) {
        pieceReachedEnd = board.pieceReachedEnd;
        nextBoards = new ArrayList<Board>();
        parent = null;
		cells = new int[Constants.LINE_LENGTH][Constants.LINE_LENGTH];
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[j][i] = board.cells[j][i];
            }
        }
    }

    /*
     * Initializes the board as it should be at the beginning of a match.
     */
    public void initialize() {
        cells = new int[Constants.LINE_LENGTH][Constants.LINE_LENGTH];
        for (int i = 1; i <= 6; i++) {
            cells[i][0] = Constants.WHITE_CELL;
            cells[i][1] = Constants.WHITE_CELL;
            cells[i][6] = Constants.BLACK_CELL;
            cells[i][7] = Constants.BLACK_CELL;
        }
        cells[1][2] = Constants.WHITE_CELL; cells[2][2] = Constants.WHITE_CELL;
        cells[5][2] = Constants.WHITE_CELL; cells[6][2] = Constants.WHITE_CELL;
        cells[1][5] = Constants.BLACK_CELL; cells[2][5] = Constants.BLACK_CELL;
        cells[5][5] = Constants.BLACK_CELL; cells[6][5] = Constants.BLACK_CELL;
    }

    /*
     * Returns the boards that result from mandatory moves starting in this one. 
     */
    public List<Board> getNextBoards() {
        return nextBoards;
    }

    /*
     * Adds a board to the nextBoards list. 
     */
    public void addNextBoard(Board board) {
        nextBoards.add(board);
    }

    /*
     * Gets the parent of this board. 
     */
    public Board getParent() {
        return parent;
    }

    /*
     * Sets the parent of this board. 
     */
    public void setParent(Board parent) {
        this.parent = parent;
    }

    /*
     * Gets the value inside the cell at the specified position.
     */
    public int getCell(Position pos) {
		return cells[pos.x][pos.y];
	}
	
    /*
     * Sets the value inside the cell at the specified position.
     */
	public void setCell(Position pos, int value) {
		cells[pos.x][pos.y] = value;
    }

	/*
     * Prints the board on the console.
     */
    public void print() {
		System.out.println();
		System.out.println("---------------------------------");
		for (int i = 0; i < Constants.LINE_LENGTH; i++) {
			System.out.print("|");
			for (int j = 0; j < Constants.LINE_LENGTH; j++) {
				System.out.print(" " + (cells[j][i] == 0 ? " " : cells[j][i]) + (j == 7 ? " " : " |"));
			}
			System.out.print("|");
			System.out.println();
			System.out.println("---------------------------------");
		}
		System.out.println();
    }
    
    /* 
     * Evaluates a board when it comes to the pieces' position in the ambit of a given player.
	 * The closer the pieces of the given player are to the opponent's area, the higher the returned evaluation will be. 
	 * The closer the pieces of the opponent are to the given player's area, the lower the returned evaluation will be. 
	 * */
	public int evaluatePositioning(int player) {
		int evaluation = 0;
		for (int i = 0; i < Constants.LINE_LENGTH; i++) 
			for (int j = 0; j < Constants.LINE_LENGTH; j++) {
				if(cells[j][i] == player) {
					evaluation += (player == 1)? i : (7 - i);
				}
			}
		return evaluation;
	}
	
	/*
     * Counts the number of pieces that a specified player has on the board.
     */
	public int countPieces(int player) {
		int count = 0;
		for (int i = 0; i < Constants.LINE_LENGTH; i++) 
			for (int j = 0; j < Constants.LINE_LENGTH; j++) {
				if(cells[i][j] == player) {
					count++;
				} 
			}
		return count;
	}
	
	/*
	 * Evaluates the board according to the side rows.
	 * The more pieces a player has there and the closer to the end of the board, the higher will be the returned evaluation.
	 */
	public int evaluateSideColumns(int player) {
		int evaluation = 0;
		for(int i = 0; i < Constants.LINE_LENGTH; i++) {
			if (cells[0][i] == player)
    			evaluation += i * i;
			if (cells[7][i] == player)
    			evaluation += i * i;
		}
		return evaluation;
	}
	
	/**
     * Counts the number of pieces located at the beginning of the board.
     */
    public int countPiecesBeginning(int player) {
    	int count = 0;
    	for(int i = 0; i < Constants.LINE_LENGTH; i++) {
    		if(cells[i][player == Constants.PLAYER_1 ? 0 : 7] == player) { 
    			count++;
    		}
    	}
    	return count;
    }
    
    @Override
    public boolean equals(Object o) {   
        if (o == this) { 
            return true; 
        }    
        if (!(o instanceof Board)) { 
            return false; 
        }     
        Board b = (Board) o;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cells[i][j] != b.cells[i][j])
                    return false;
            }
        }
        return true;
    }
    
    /*
     * Returns a list of the available cells located at the drop zone.
     */
    public List<Position> getDropZoneCells(int player) {
    	List<Position> dropZoneCells = new ArrayList<Position>();
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			Position pos = new Position(j, i);
    			if (cells[j][i] == Constants.EMPTY_CELL && Utils.isWithinDropZone(pos, player))
    				dropZoneCells.add(pos);
    		}
    	}
    	return dropZoneCells;
    }
    
    /* 
     * Returns the positions in which new pieces were placed, compared a previous board.
     */
	public List<Position> getNewPositions(Board previousBoard, int player) {
		List<Position> positions = new ArrayList<Position>();
		for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			if(cells[j][i] == player && previousBoard.cells[j][i] == Constants.EMPTY_CELL)
    				positions.add(new Position(j, i));
    		}
    	}
		return positions;
	}
	
	/**
     * Removes the pieces that are placed at the end of the board and belong to the specified player.
     */
    public void removeEndPieces(int player) {
    	for (int i = 0; i < Constants.LINE_LENGTH; ++i) {
    		if (player == Constants.PLAYER_1 && cells[i][7] == Constants.PLAYER_1)
    			cells[i][7] = Constants.EMPTY_CELL;
    		else if (player == Constants.PLAYER_2 && cells[i][0] == Constants.PLAYER_2)
    			cells[i][0] = Constants.EMPTY_CELL;
    	}
    }
}