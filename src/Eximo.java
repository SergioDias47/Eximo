import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Eximo {
	
	/* User interface, game mode, execution time */
	private UI gui;
	private int gameMode;
	private boolean stopWorking = false;
	private long startTime;
	
	/* Game state */
    private int player;
    private Board board;
    private int piecesToAdd;
    private List<MoveSequence> forcedStates;

    /* Constructor */
    public Eximo(UI gui, int gameMode) {
        player = Constants.PLAYER_1;
        board = new Board();
        forcedStates = new ArrayList<MoveSequence>();
        this.gameMode = gameMode;
        this.gui = gui;
        if(gameMode == Constants.BOT_VS_BOT) {
			startBotWork();
		}
        piecesToAdd = 0;
	}
	
    /* 
     * Initializes a new thread for bot work. 
     */
	private void startBotWork() {
		stopWorking = false;
		new Thread() {
			  public void run() {
				  botMove();
		}}.start();
	}
	
	/*
	 * Stops the thread that executes the bot work.
	 */
	public void stopBotWork() {
		stopWorking = true;
	}

	/*
	 * Returns the current player.
	 */
    public int getPlayer() {
        return player;
    }
    
    /*
	 * Returns the game mode.
	 */
    public int getGameMode() {
		return gameMode;
	}
    
    /*
     * Returns the number of extra pieces that a player has to place.
     */
    public int getPiecesToAdd() {
    	return piecesToAdd;
    }
    
    /*
     * Ends the turn of a player, switching to the next one and requesting the UI to update the information. 
     */
    public void nextPlayer() {
		player = Utils.otherPlayer(player);
		gui.updateMatchInfo(board.countPieces(Constants.PLAYER_1), board.countPieces(Constants.PLAYER_2), gameOver());
	}
    
    /*
     * Requests the UI to print the current board.
     */
    public void printCurrentBoard() {
        gui.getBoard().printBoard(board);
    }
    
    /* 
     * Handles a move input by a human 
     */
    public void playerMove(Position startPos, Position endPos) {
    	boolean isValid = false;
    	List<MoveSequence> allMoves = (forcedStates.size() > 0)? forcedStates : generateAllSequences(board, player);
    	List<MoveSequence> newForcedStates = new ArrayList<MoveSequence>();
    	Board nextBoard = MoveGenerator.emulateMove(new Move(startPos, endPos, board, player));
    	for(MoveSequence move : allMoves) {
    		if(move.getFirstBoard().equals(nextBoard)) {
    			board = nextBoard;
    			isValid = true;
    			move.remove(0);
    			if(move.size() > 0) {
    				newForcedStates.add(move);
    			}
    		}
    	}
    	
    	System.out.println((isValid)? "Move is valid" : "Move is invalid");
    	
    	if(isValid) {
    		board = nextBoard;
    		printCurrentBoard();
    		forcedStates = newForcedStates;
    		if (board.pieceReachedEnd) {
    			board.pieceReachedEnd = false;
    			int noCells = board.getDropZoneCells(player).size();
    			piecesToAdd = (noCells > 2 ? 2 : noCells);
    		}
    		
    		if(forcedStates.size() == 0) {
        		nextPlayer();
        		if(gameMode == Constants.PLAYER_VS_BOT) botMove();
        	}
    	}
    }
    
    public void addPieceAt(Position pos) {
    	System.out.println("Extra piece added at "); pos.print();
    	if (Utils.isWithinDropZone(pos, player)) {
    		board.setCell(pos, player);
    		printCurrentBoard();
    		if (--piecesToAdd == 0) {
    			nextPlayer();
    			forcedStates.clear();
    		}
    	}
    }

    /*
     * Handles the bot's moves.
     */
	public void botMove() {
		
		long startTime = System.currentTimeMillis();
		MoveSequence chosenMove = findBestSequence();
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Elapsed time: " + elapsedTime);
		int delay = Integer.max((int) (Constants.MIN_DELAY - elapsedTime), 0);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/* For composed moves, each step (frame) will be sequentially displayed */
		for(Board frame : chosenMove.getBoards()) {
			board = frame;
			printCurrentBoard();
			try {
				Thread.sleep(Constants.MIN_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nextPlayer();
		if(gameOver()) return;
		if(gameMode == Constants.BOT_VS_BOT) {
			if(stopWorking) return;
			if(gameMode == Constants.BOT_VS_BOT) botMove();
		}
	}
	
	public boolean checkWinner(Board board, int player) {
		if(generateAllSequences(board, player).isEmpty()) {
			return true;
		}
		return false;
	}
	
	/* 
	 * Handles game over state.
	*/
	public boolean gameOver() {
		if(checkWinner(board, player)) {
			System.out.println("Game over! Player " + Utils.otherPlayer(player) + " wins!");
			return true;
		}
		return false;
	}
	
	/*
	 * Returns the best sequence of moves to play in a given state of the game.
	 */
	private MoveSequence findBestSequence() {
		startTime = System.currentTimeMillis();
		List<MoveSequence> allMoves = generateAllSequences(board, player);
		Collections.sort(allMoves); // sorting nodes for better performance
		int bestScore = Integer.MIN_VALUE;
		MoveSequence bestMove = new MoveSequence(); 
		
		for(MoveSequence move : allMoves) {
			Board finalBoard = move.getLastBoard();
			int newScore = minimax(finalBoard, false, Integer.MIN_VALUE, Integer.MAX_VALUE, Constants.MINIMAX_DEPTH);
			if(newScore > bestScore) {
				bestScore = newScore;
				bestMove = move;
			}
		}
		return bestMove;
	}

	/*
	 * Minimax algorithm with alpha/beta pruning.
	 */
	private int minimax(Board board, boolean isMaximizing, int alpha, int beta, int depth) {
		if(stopWorking) return 0;
		int bestScore;
		int player;
		if (isMaximizing) {
			player = this.player;
			if(checkWinner(board, player)) {
				return Integer.MAX_VALUE;
			}
			bestScore = Integer.MIN_VALUE;
		} else {
			player = Utils.otherPlayer(this.player);
			if(checkWinner(board, player)) {
				return Integer.MAX_VALUE;
			}
			bestScore = Integer.MAX_VALUE;
		}
		
		if(depth == 1) { // we've gone into the desired depth, so it's time to rate the solutions
			int currentScore = Heuristics.evaluateState(board, this.player); 
			return currentScore;
		}
		List<MoveSequence> allMoves = generateAllSequences(board, player);
		Collections.sort(allMoves);
		
		if(!isMaximizing)
			Collections.reverse(allMoves);
		
		for(MoveSequence move : allMoves) {
			if(System.currentTimeMillis() - startTime > Constants.MAX_SEARCH_TIME) { 
				break; // time is up
			}
			Board finalBoard = move.getLastBoard();
			int newScore = minimax(finalBoard, !isMaximizing, alpha, beta, depth - 1);
			if (isMaximizing) {
				bestScore = Integer.max(bestScore, newScore);
				alpha = Integer.max(alpha, newScore);
				if(beta <= alpha)
					break;
			} else {
				bestScore = Integer.min(bestScore, newScore);
				beta = Integer.min(beta, newScore);
				if(beta <= alpha)
					break;
			}
		}
		return bestScore;
	}
	
	/*
	 * Calls the main function of MoveGenerator class.
	 */
	public List<MoveSequence> generateAllSequences(Board board, int player) {
		return MoveGenerator.generateAllSequences(board, player);
	}
    
    /*
     * Requests the UI to highlight the spots that can be selected as the end position of a move.
     */
    public void highlightAvailableMoves(Position startPos) {
    	if(board.getCell(startPos) != player)
    		return;
    	List<MoveSequence> allMoves = (forcedStates.size() > 0)? forcedStates : generateAllSequences(board, player);
    	for(MoveSequence move : allMoves) {
    		Board firstBoard = move.getFirstBoard();
    		if(firstBoard.getCell(startPos) == Constants.EMPTY_CELL) {
    			List<Position> positionsToHighlight = firstBoard.getNewPositions(board, player);
    			for(Position pos : positionsToHighlight)
    				gui.getBoard().highlightAt(pos);
    		}
    	}
    }
}