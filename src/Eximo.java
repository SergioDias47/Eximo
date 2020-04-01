import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Eximo {
	
	/* User interface, game mode, control vars */
	private UI gui;
	private int gameMode;
	private boolean stopWorking = false;
	private long startTime;
	
	/* Statistics */
	private int numMoves1;
	private int numMoves2;
	
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
        initializeStatistics();
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
	 * Initializes the statistics variables of the game.
	 */
	private void initializeStatistics() {
		numMoves1 = 0;
		numMoves2 = 0;
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
		int numPieces1 = board.countPieces(Constants.PLAYER_1);
		int numPieces2 = board.countPieces(Constants.PLAYER_2);
		MatchInfo struct = new MatchInfo(numMoves1, numMoves2, numPieces1, numPieces2, gameOver());
		gui.updateMatchInfo(struct);
	}
    
    /*
     * Requests the UI to print the current board.
     */
    public void printCurrentBoard() {
        gui.getBoard().printBoard(board);
    }
    
    /* 
     * Handles a move input by a human player.
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
    		if(player == Constants.PLAYER_1)
				numMoves1++;
			else numMoves2++;
    		printCurrentBoard();
    		forcedStates = newForcedStates;
    		if (board.pieceReachedEnd) {
    			board.pieceReachedEnd = false;
    			highlightDropZone();
    			int noCells = board.getDropZoneCells(player).size();
    			piecesToAdd = noCells > 2 ? 2 : noCells;
    			if (piecesToAdd == 0) {
    				forcedStates.clear();
    				board.removeEndPieces(player);
    			}
    		}
    	
    		
    		if(forcedStates.size() == 0) {
        		nextPlayer();
        		if(gameMode == Constants.PLAYER_VS_BOT) botMove();
        	}
    	}
    }
    
    /**
     * Handles the case when a player needs to choose a cell in the drop zone to put a new piece after another reached the end of the board.
     */
    public void addPieceAt(Position pos) {
    	if (Utils.isWithinDropZone(pos, player) && board.getCell(pos) == Constants.EMPTY_CELL) {
    		board.setCell(pos, player);
    		printCurrentBoard();
    		if (--piecesToAdd == 0) {
    			forcedStates.clear();
    			board.removeEndPieces(player);
    			nextPlayer();
    			gui.removeAllHighlights();
    			printCurrentBoard();
    			if(gameMode == Constants.PLAYER_VS_BOT) botMove();
    		}
    	}
    }

    /*
     * Handles the bot's moves.
     */
	public void botMove() {
		
		long startTime = System.currentTimeMillis();
		MoveSequence chosenMove = findBestSequenceAlphaBeta();
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Elapsed time: " + elapsedTime);
		int delay = Integer.max((int) (Properties.MIN_DELAY - elapsedTime), 0);
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
				Thread.sleep(Properties.MIN_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(player == Constants.PLAYER_1)
			numMoves1 += chosenMove.size();
		else numMoves2 += chosenMove.size();;
		
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
	 * Returns the best sequence of moves to play in a given state of the game. (calls minimax with alpha/beta pruning)
	 */
	private MoveSequence findBestSequenceAlphaBeta() {
		startTime = System.currentTimeMillis();
		List<MoveSequence> allMoves = generateAllSequences(board, player);
		Collections.sort(allMoves); // sorting nodes for better performance
		int bestScore = Integer.MIN_VALUE;
		MoveSequence bestMove = allMoves.get(0); // if time is up before finding any solution, this one is returned
		
		int depth = player == Constants.PLAYER_1 ? Properties.DEPTH_PLAYER_1 : Properties.DEPTH_PLAYER_2;
		
		for(MoveSequence move : allMoves) {
			Board finalBoard = move.getLastBoard();
			int newScore = minimaxAlphaBeta(finalBoard, false, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
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
	private int minimaxAlphaBeta(Board board, boolean isMaximizing, int alpha, int beta, int depth) {
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
			if(System.currentTimeMillis() - startTime > Integer.max(Properties.MAX_SEARCH_TIME, Properties.MINIMAX_DEPTH*20)) { 
				break; // time is up
			}
			Board finalBoard = move.getLastBoard();
			int newScore = minimaxAlphaBeta(finalBoard, !isMaximizing, alpha, beta, depth - 1);
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
	 * Returns the best sequence of moves to play in a given state of the game. (calls minimax without alpha/beta pruning)
	 */
	private MoveSequence findBestSequence() {
		startTime = System.currentTimeMillis();
		List<MoveSequence> allMoves = generateAllSequences(board, player);
		int bestScore = Integer.MIN_VALUE;
		MoveSequence bestMove = allMoves.get(0); // if time is up before finding any solution, this one is returned
		
		int depth = player == Constants.PLAYER_1 ? Properties.DEPTH_PLAYER_1 : Properties.DEPTH_PLAYER_2;
		
		for(MoveSequence move : allMoves) {
			Board finalBoard = move.getLastBoard();
			int newScore = minimax(finalBoard, false, depth);
			if(newScore > bestScore) {
				bestScore = newScore;
				bestMove = move;
			}
		}
		return bestMove;
	}

	/*
	 * Minimax algorithm without alpha/beta pruning.
	 */
	private int minimax(Board board, boolean isMaximizing, int depth) {
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
		
		for(MoveSequence move : allMoves) {
			if(System.currentTimeMillis() - startTime > Integer.max(Properties.MAX_SEARCH_TIME, Properties.MINIMAX_DEPTH*20)) { 
				break; // time is up
			}
			Board finalBoard = move.getLastBoard();
			int newScore = minimax(finalBoard, !isMaximizing, depth - 1);
			if (isMaximizing) {
				bestScore = Integer.max(bestScore, newScore);
			} else {
				bestScore = Integer.min(bestScore, newScore);
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
    
    /*
     * Requests the UI to highlight the free spots that located at the drop zone.
     */
    public void highlightDropZone() {
    	List<Position> positions = board.getDropZoneCells(player);
    	for(Position pos : positions) {
    		gui.getBoard().highlightAt(pos);
    	}
    }
    
    
}