import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Eximo {
	private int cells[];
	private int currentPlayer;
	
	public Eximo() {
		initializeBoard();
		currentPlayer = Constants.PLAYER_1;
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
	
	public boolean validMove(int startPos, int endPos) {
		
		if(cells[startPos] != currentPlayer || cells[endPos] != Constants.EMPTY_CELL) 
			return false;
		
		switch(currentPlayer) {
			case Constants.PLAYER_1:
				
				break;
			case Constants.PLAYER_2:
				break;
		}
			
		return false;
	}
	
	public HashSet<Move> findValidMoves() {
		HashSet<Move> validMoves = new HashSet<Move>();
		
		for(int cell : cells) {
			if(cell != currentPlayer)
				continue;
			validMoves.addAll(validFrontMove(cell));
		}
		
		return validMoves;
	}
	
	public List<Move> generateMoves(int startPos) {
		List<Move> moves = new ArrayList<Move>();
		/* Generating front move */
		
		
		return moves;
	}
	
	/* Generating and checking a possible north movement */
	public Move checkMoveN(int startPos) {
		int sign = 1;
		if(currentPlayer == Constants.PLAYER_2) {
			sign = -1;
		}
		int endPos = startPos + sign * Constants.LINE_LENGTH;
		Move move = new Move(startPos, endPos);
		if(move.checkBoundaries() && cells[endPos] != Constants.EMPTY_CELL) {
			return move;
		}
		return null;
	}
	
	public Move checkMoveNE(int startPos) {
		int sign = 1;
		if(currentPlayer == Constants.PLAYER_2) {
			sign = -1;
		}
		int endPos = startPos + sign * (Constants.LINE_LENGTH + 1);
		Move move = new Move(startPos, endPos);
		if(move.checkBoundaries() && cells[endPos] != Constants.EMPTY_CELL) {
			return move;
		}
		return null;
	}
	
	public Move checkMoveNW(int startPos) {
		int sign = 1;
		if(currentPlayer == Constants.PLAYER_2) {
			sign = -1;
		}
		int endPos = startPos + sign * (Constants.LINE_LENGTH + 1);
		Move move = new Move(startPos, endPos);
		if(move.checkBoundaries() && cells[endPos] != Constants.EMPTY_CELL) {
			return move;
		}
		return null;
	}
	
	
	
	
	public void nextPlayer() {
		currentPlayer = (currentPlayer + 1) % 2 + 1;
	}
	
	
}
