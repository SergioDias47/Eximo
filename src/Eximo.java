import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Eximo {
	private int gamemode;
	private Board board;
	private int currentPlayer;
	private UI gui;
	private int moveState = Constants.NORMAL;
	private int piecesToPlace = 0;
	
	public Eximo(int gamemode, UI gui) {
		this.gamemode = gamemode;
		this.board = new Board();
		currentPlayer = Constants.PLAYER_1;
		this.gui = gui;
	}
	
	/* Finds all the valid moves that the current player can make*/
	public List<Move> findValidMoves() {
		List<Move> validMoves = new ArrayList<Move>();
		
		for(int i = 0; i < board.length(); i++) {
			validMoves.addAll(generateMoves(i));
		} 
		
		return validMoves;
	}
	
	/* Generates the valid jump over moves from a given position */ 
	public List<Move> generateJumpOverMoves(int startPos) {
		List<Move> jumpOverMoves = new ArrayList<Move>();
		if (board.getCell(startPos) != currentPlayer) return jumpOverMoves;
		for (int direction : Constants.FrontDirections) {
			Move jumpOver = checkJumpOver(startPos, direction);
			if(jumpOver != null) jumpOverMoves.add(jumpOver);
		}
		return jumpOverMoves;
	}
	
	/* Generates all the capture moves that player can make */
	public List<Move> generateAllCaptureMoves() {
		List<Move> captureMoves = new ArrayList<Move>();
		
		for(int i = 0; i < board.length(); i++) {
			captureMoves.addAll(generateCaptureMoves(i));
		} 
		
		return captureMoves;
	}
	
	/* Generates the valid capture moves from a given position */ 
	public List<Move> generateCaptureMoves(int startPos) {
		List<Move> captureMoves = new ArrayList<Move>();
		if (board.getCell(startPos) != currentPlayer) return captureMoves;
		for (int direction : Constants.FrontDirections) {
			Move capture = checkCaptureFront(startPos, direction);
			if(capture != null) captureMoves.add(capture);
		}
		Move captureWest = checkCaptureSide(startPos, Constants.WEST);
		if(captureWest != null) captureMoves.add(captureWest);
		Move captureEast = checkCaptureSide(startPos, Constants.EAST);
		if(captureEast != null) captureMoves.add(captureEast);
		
		for (Move capture : captureMoves) {
			int endPos = capture.endPos.toBoardPos();
			board.setCell(endPos, currentPlayer);
			capture.nextMoves.addAll(generateCaptureMoves(endPos));
			board.setCell(endPos, Constants.EMPTY_CELL);
		}
		return captureMoves;
	}
	
	/* Generates all the possible basic moves from a given position in the board */
	public List<Move> generateMoves(int startPos) {
		List<Move> moves = generateAllCaptureMoves(); // checking if there are any capture moves possible
		if (board.getCell(startPos) != currentPlayer) return moves;
		
		if (moves.size() != 0) {
			return moves;
		}
		
		for (int direction : Constants.FrontDirections) {
			Move simpleMove = checkSimpleMove(startPos, direction);
			if(simpleMove != null) moves.add(simpleMove);
			Move jumpOver = checkJumpOver(startPos, direction);
			if(jumpOver != null) moves.add(jumpOver);
		}
		return moves;
	}
	
	/* Generating and checking a possible basic movement in a given direction */
	public Move checkSimpleMove(int startPos, int direction) {
		int sign = Utils.getSign(currentPlayer);
		int endPos = startPos + sign * (Constants.LINE_LENGTH + direction);
		Move move = new Move(startPos, endPos);
		if(move.checkBoundaries() && board.getCell(endPos) == Constants.EMPTY_CELL) {
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
		if(move.checkBoundaries() && board.getCell(endPos) == Constants.EMPTY_CELL && board.getCell(midPos) == currentPlayer) {
			board.setCell(endPos, currentPlayer);
			move.nextMoves.addAll(generateJumpOverMoves(endPos));
			board.setCell(endPos, Constants.EMPTY_CELL);
			return move;
		}
		return null;
	}
	
	/* Generating and checking a possible capture movement in a given direction (only sides) */
	public Move checkCaptureSide(int startPos, int direction) {
		int sign = Utils.getSign(currentPlayer);
		int endPos = startPos + sign * 2 * direction;
		Move move = new Move(startPos, endPos);
		if(move.setCaptured() && move.checkBoundaries() && board.getCell(endPos) == Constants.EMPTY_CELL && board.getCell(move.captured) == Utils.otherPlayer(currentPlayer)) {
			return move;
		}
		return null;
	}
	
	/* Generating and checking a possible capture movement in a given direction (only in front) */
	public Move checkCaptureFront(int startPos, int direction) {
		int sign = Utils.getSign(currentPlayer);
		int endPos = startPos + sign * 2 * (Constants.LINE_LENGTH + direction);
		Move move = new Move(startPos, endPos);
		if(move.setCaptured() && move.checkBoundaries() && board.getCell(endPos) == Constants.EMPTY_CELL && board.getCell(move.captured) == Utils.otherPlayer(currentPlayer)) {
			return move;
		}
		return null;
	}
	
	/* Passes the turn to the next player */
	public void nextPlayer() {
		currentPlayer = Utils.otherPlayer(currentPlayer);
		gui.updateMatchInfo(board.countPieces(Constants.PLAYER_1), board.countPieces(Constants.PLAYER_2));
	}
	
	/* Empties the cell in the given position */
	public void emptyCell(int position) {
		board.setCell(position, Constants.EMPTY_CELL);
		gui.getBoard().setIconAt(position, Constants.EMPTY_CELL);
	}
	
	/* Fills the cell in the given position with the current player's piece */
	public void fillCell(int position) {
		board.setCell(position, currentPlayer);
		gui.getBoard().setIconAt(position, currentPlayer);
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	/* Handles the player's moves */ 
	public void playerMove(Move move) {
		this.board.print();
		int startP = move.startPos.toBoardPos();
		int endP = move.endPos.toBoardPos();
		if (board.getCell((endP+startP)/2) == Utils.otherPlayer(currentPlayer))
			move.setCaptured();
		if (!generateMoves(startP).contains(move)) {
			return;
		}
		System.out.println("Player turn: " + currentPlayer);
		emptyCell(startP);
		fillCell(endP);
		if(move.isCapture()) { // we're handling a capture move
			emptyCell(move.captured);
			if (!reachedEndOfBoard(endP))
				if (generateCaptureMoves(endP).size() != 0) {
					moveState = Constants.CAPTURE;
					return;
				}
		}
		else if (move.isJumpOver()) {
			if (generateJumpOverMoves(endP).size() != 0) {
				moveState = Constants.JUMP_OVER;
				return;
			}
		}
		if (!reachedEndOfBoard(endP)) 
			nextPlayer();
		if(gameOver()) return;
		
		if(gamemode == Constants.PLAYER_VS_BOT) {
			botMove();
		}
	}
	
	/* Verifies whether a piece has reached the end of the board */
	public boolean reachedEndOfBoard(int endPos) {
		if ((currentPlayer == Constants.PLAYER_1 && endPos/Constants.LINE_LENGTH == 7) 
				|| (currentPlayer == Constants.PLAYER_2 && endPos/Constants.LINE_LENGTH == 0)) {
			moveState = Constants.NORMAL;
			switch(board.countSafeZoneFreeCells(currentPlayer)) {
				case 0:
					System.out.println("Your piece is gone and no extra pieces will be given!");
					nextPlayer();
					break;
				case 1:
					System.out.println("You earned yourself 1 new piece!");
					piecesToPlace = 1;
					break;
				default:
					System.out.println("You earned yourself 2 new pieces!");
					piecesToPlace = 2;
					break;
			}
			emptyCell(endPos);
		} else return false;
		return true;
	}
	
	/* Handles the case when a player makes multiple jumpOver moves in one turn */
	public void sequentialJumpOver(Move move) {
		int startP = move.startPos.toBoardPos();
		int endP = move.endPos.toBoardPos();
		if (!generateJumpOverMoves(startP).contains(move)) {
			return;
		}
		emptyCell(startP);
		fillCell(endP);
		if (!reachedEndOfBoard(endP))
			if (generateJumpOverMoves(endP).size() == 0) {
				moveState = Constants.NORMAL;
				nextPlayer();
			}
	}
	
	/* Handles the case when a player makes multiple capture moves in one turn */
	public void sequentialCapture(Move move) {
		int startP = move.startPos.toBoardPos();
		int endP = move.endPos.toBoardPos();
		move.setCaptured();
		if (!generateCaptureMoves(startP).contains(move)) {
			return;
		}
		emptyCell(startP);
		fillCell(endP);
		emptyCell(move.captured);
		if (!reachedEndOfBoard(endP))
			if (generateCaptureMoves(endP).size() == 0) {
				moveState = Constants.NORMAL;
				nextPlayer();
			}
	}
	
	/* Handles the bot's moves */
	public void botMove() {
		List<Move> possibleMoves = findValidMoves();
		Random ran = new Random();
		int x = ran.nextInt(possibleMoves.size());
		
		Move randomMove = possibleMoves.get(x); // chooses move
		
		int startP = randomMove.startPos.toBoardPos();
		int endP = randomMove.endPos.toBoardPos();
		
		//if (cells[(endP+startP)/2] == Utils.otherPlayer(currentPlayer))
			//move.checkCapture();
		emptyCell(startP);
		fillCell(endP);
		if(randomMove.isCapture()) { // we're handling a capture move
			emptyCell(randomMove.captured);
		}
		else if (randomMove.isJumpOver()) {
			if (generateJumpOverMoves(endP).size() != 0) {
				moveState = Constants.NORMAL;
				return;
			}
		}
		nextPlayer();
		System.out.println("Player turn: " + currentPlayer);
	}

	public int getMoveState() {
		return moveState;
	}

	public int getPiecesToPlace() {
		return piecesToPlace;
	}

	/* Handles game over */
	public boolean gameOver() {
		if(generateAllCaptureMoves().isEmpty() && findValidMoves().isEmpty()) {
			// handles game over
			System.out.println("Game over! Player " + Utils.otherPlayer(currentPlayer) + " wins!");
			return true;
		}
		return false;
	}

	/* Adds a piece to a certain position only if it's in the drop zone. Used when a piece reaches the end of the board */
	public void addPieceAt(int position) {
		System.out.println("Pieces: "+ piecesToPlace);
		if(Utils.isWithinSafeZone(currentPlayer, position) && board.getCell(position) == Constants.EMPTY_CELL) {
			fillCell(position);
			piecesToPlace--;
		}
		if (piecesToPlace == 0) nextPlayer();
	}
	
	/* Returns the modified board after executing a certain move */
	Board emulateMove(Board currentBoard, Move move, int player) {
		Board boardRes = new Board(currentBoard);
		int startPos = move.startPos.toBoardPos();
		int endPos = move.endPos.toBoardPos();
		boardRes.setCell(startPos, Constants.EMPTY_CELL);
		boardRes.setCell(endPos, player);
		if (move.isCapture()) {
			int midPos = (startPos + endPos) / 2;
			boardRes.setCell(midPos, Constants.EMPTY_CELL);
		}
		return boardRes;
	}
	
	/* Generates all the boards from a given set of valid moves */
	List<Board> generateBoards(List<Move> validMoves, int player) {
		List<Board> boards = new ArrayList<Board>();
		for (Move move : validMoves) {
			if (move.isCapture() || move.isJumpOver()) {
				boards.addAll(getSubsequentialBoards(this.board, move, player));
			} else {
				boards.add(emulateMove(this.board, move, player));
			}	
		}
		return boards;
	}
	
	/* Generates the boards that are the result of multiple jumpOver and capture moves in one turn */
	List<Board> getSubsequentialBoards(Board board, Move move, int player) {
		List<Board> boards = new ArrayList<Board>();
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(board, move));
		while(!queue.isEmpty()) {
			Pair pair = queue.remove();
			Board newBoard = emulateMove(pair.first(), pair.second(), player);
			List<Move> nextMoves = pair.second().nextMoves;
			if (nextMoves.isEmpty()) {
				boards.add(newBoard);
			} else {
				for (Move m : nextMoves) {
					queue.add(new Pair(newBoard, m));
				}
			}
		}
		return boards;
	}

}
