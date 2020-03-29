import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
        print();
    }

    public Board(Board board) {
        pieceReachedEnd = board.pieceReachedEnd;
        nextBoards = new ArrayList<Board>();
        parent = null;
		cells = new int[Constants.LINE_LENGTH][Constants.LINE_LENGTH];
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = board.cells[i][j];
            }
        }
    }

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

    public List<Board> getNextBoards() {
        return nextBoards;
    }

    public void addNextBoard(Board board) {
        nextBoards.add(board);
    }

    public Board getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public int getCell(Position pos) {
		return cells[pos.x][pos.y];
	}
	
	public void setCell(Position pos, int value) {
		cells[pos.x][pos.y] = value;
    }

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
    
    /* Evaluates a board when it comes to the pieces' position in the ambit of a given player.
	 * 	The closer the pieces of the given player are to the opponent's area, the higher the returned evaluation will be. 
	 * 	The closer the pieces of the opponent are to the given player's area, the lower the returned evaluation will be. */
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
}