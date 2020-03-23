import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Eximo {
	private int cells[];
	private int currentPlayer;
	private UI gui;
	public static boolean jumpOver = false;
	
	public Eximo(UI gui) {
		initializeBoard();
		currentPlayer = Constants.PLAYER_1;
		this.gui = gui;
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
	
	public List<Move> findValidMoves() {
		List<Move> validMoves = new ArrayList<Move>();
		
		for(int i = 0; i < cells.length; i++) {
			if(cells[i] != currentPlayer)
				continue;
			validMoves.addAll(generateMoves(i));
		}
		
		return validMoves;
	}
	
	/* Generates the valid jump over moves from a given position */ 
	public List<Move> generateJumpOverMoves(int startPos) {
		List<Move> jumpOverMoves = new ArrayList<Move>();
		for (int direction : Constants.FrontDirections) {
			Move jumpOver = checkJumpOver(startPos, direction);
			if(jumpOver != null) jumpOverMoves.add(jumpOver);
		}
		return jumpOverMoves;
	}
	
	/* Generates all the possible basic moves from a given position in the board */
	public List<Move> generateMoves(int startPos) {
		List<Move> moves = new ArrayList<Move>();
		if (cells[startPos] != currentPlayer) return moves;
		
		for (int direction : Constants.FrontDirections) {
			Move simpleMove = checkSimpleMove(startPos, direction);
			if(simpleMove != null) moves.add(simpleMove);
			Move jumpOver = checkJumpOver(startPos, direction);
			if(jumpOver != null) moves.add(jumpOver);
			Move captureFront = checkCaptureFront(startPos, direction);
			if(captureFront != null) moves.add(captureFront);
		}
		Move captureWest = checkCaptureSide(startPos, Constants.WEST);
		if(captureWest != null) moves.add(captureWest);
		Move captureEast = checkCaptureSide(startPos, Constants.EAST);
		if(captureEast != null) moves.add(captureEast);
		return moves;
	}
	
	/* Generating and checking a possible basic movement in a given direction */
	public Move checkSimpleMove(int startPos, int direction) {
		int sign = Utils.getSign(currentPlayer);
		int endPos = startPos + sign * (Constants.LINE_LENGTH + direction);
		Move move = new Move(startPos, endPos);
		if(move.checkBoundaries() && cells[endPos] == Constants.EMPTY_CELL) {
			return move;
		}
		return null;
	}
	
	/* Generating and checking a possible jump over movement in a given direction */
	public Move checkJumpOver(int startPos, int direction) {
		int sign = Utils.getSign(currentPlayer);
		int endPos = startPos + sign * 2 * (Constants.LINE_LENGTH + direction);
		int midPos = (startPos + endPos) / 2;
		Move move = new Move(startPos, endPos);
		if(move.checkBoundaries() && cells[endPos] == Constants.EMPTY_CELL && cells[midPos] == currentPlayer) {
			return move;
		}
		return null;
	}
	
	/* Generating and checking a possible capture movement in a given direction (only sides) */
	public Move checkCaptureSide(int startPos, int direction) {
		int sign = Utils.getSign(currentPlayer);
		int endPos = startPos + sign * 2 * direction;
		int midPos = (startPos + endPos) / 2;
		Move move = new Move(startPos, endPos);
		move.setCaptured(midPos);
		if(move.checkBoundaries() && cells[endPos] == Constants.EMPTY_CELL && cells[midPos] == Utils.otherPlayer(currentPlayer)) {
			return move;
		}
		return null;
	}
	
	/* Generating and checking a possible capture movement in a given direction (only in front) */
	public Move checkCaptureFront(int startPos, int direction) {
		int sign = Utils.getSign(currentPlayer);
		int endPos = startPos + sign * 2 * (Constants.LINE_LENGTH + direction);
		int midPos = (startPos + endPos) / 2;
		Move move = new Move(startPos, endPos);
		move.setCaptured(midPos);
		if(move.checkBoundaries() && cells[endPos] == Constants.EMPTY_CELL && cells[midPos] == Utils.otherPlayer(currentPlayer)) {
			return move;
		}
		return null;
	}
	
	public void nextPlayer() {
		currentPlayer = Utils.otherPlayer(currentPlayer);
	}
	
	public void emptyCell(int position) {
		cells[position] = Constants.EMPTY_CELL;
		gui.getBoard().setIconAt(position, Constants.EMPTY_CELL);
	}
	
	public void fillCell(int position) {
		cells[position] = currentPlayer;
		gui.getBoard().setIconAt(position, currentPlayer);
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void playerMove(Move move) {
		int startP = move.startPos.toBoardPos();
		int endP = move.endPos.toBoardPos();
		if (cells[(endP+startP)/2] == Utils.otherPlayer(currentPlayer))
			move.checkCapture();
		if (!generateMoves(startP).contains(move)) {
			return;
		}
		emptyCell(startP);
		fillCell(endP);
		if(move.isCapture()) { // we're handling a capture move
			emptyCell(move.captured);
		}
		else if (move.isJumpOver()) {
			if (generateJumpOverMoves(endP).size() != 0) {
				jumpOver = true;
				return;
			}
		}
		nextPlayer();
		System.out.println("Player turn: " + currentPlayer);
		return;
	}
	
	public void sequentialJumpOverMove(Move move) {
		int startP = move.startPos.toBoardPos();
		int endP = move.endPos.toBoardPos();
		if (!generateJumpOverMoves(startP).contains(move)) {
			return;
		}
		emptyCell(startP);
		fillCell(endP);
		if (generateJumpOverMoves(endP).size() == 0) {
			jumpOver = false;
			nextPlayer();
		}
	}
	
	
	
	
}
