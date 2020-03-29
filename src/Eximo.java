import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Eximo {
	
	/* User interface & game mode */
	private UI gui;
	private int gameMode;
	private boolean stopWorking = false;
	
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
	
	private void startBotWork() {
		stopWorking = false;
		new Thread() {
			  public void run() {
				  botMove();
		}}.start();
	}
	
	public void stopBotWork() {
		stopWorking = true;
	}

    public int getPlayer() {
        return player;
    }
    
    public void nextPlayer() {
		player = Utils.otherPlayer(player);
		gui.updateMatchInfo(board.countPieces(Constants.PLAYER_1), board.countPieces(Constants.PLAYER_2));
	}
    
    public void printCurrentBoard() {
        gui.getBoard().printBoard(board);
    }
    
    public void playerMove(Position startPos, Position endPos) {
    	
    	
    	boolean isValid = false;
    	List<MoveSequence> allMoves = (forcedStates.size() > 0)? forcedStates : generateAllBoards(board, player);
    	List<MoveSequence> newForcedStates = new ArrayList<MoveSequence>();
    	Board nextBoard = emulateMove(new Move(startPos, endPos, board, player));
    	for(MoveSequence move : allMoves) {
    		if(move.getFirstBoard().equals(nextBoard)) {
    			//move.print();
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
        	}
    	}
    }
    
    public void addPieceAt(Position pos) {
    	System.out.println("Chegou aqui com pos: "); pos.print();
    	if (Utils.isWithinDropZone(pos, player)) {
    		board.setCell(pos, player);
    		printCurrentBoard();
    		if (--piecesToAdd == 0) {
    			nextPlayer();
    			forcedStates.clear();
    		}
    	}
    }
    
    public int getPiecesToAdd() {
    	return piecesToAdd;
    }


    /* Handles the bot's moves */
	public void botMove() {
		
		long startTime = System.currentTimeMillis();
		MoveSequence chosenMove = findBestBoard();
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Elapsed time: " + elapsedTime);
		int delay = Integer.max((int) (Constants.MIN_DELAY - elapsedTime), 0);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		board = chosenMove.getLastBoard();
		printCurrentBoard();
		nextPlayer();
		if(gameOver()) return;
		if(gameMode == Constants.BOT_VS_BOT) {
			if(stopWorking) return;
			botMove();
		}
	}
	
	public static int heuristic(Board resultingBoard, int player) {
		return resultingBoard.evaluatePositioning(player) + resultingBoard.countPieces(player)*15; 
	}
	
	public boolean checkWinner(Board board, int player) {
		if(generateAllBoards(board, player).isEmpty()) {
			return true;
		}
		return false;
	}
	
	/* Handles game over */
	public boolean gameOver() {
		if(checkWinner(board, player)) {
			// handles game over
			System.out.println("Game over! Player " + Utils.otherPlayer(player) + " wins!");
			return true;
		}
		return false;
	}
	
	private MoveSequence findBestBoard() {
		List<MoveSequence> allMoves = generateAllBoards(board, player);
		
		Collections.sort(allMoves);
		
		int bestScore = Integer.MIN_VALUE;
		MoveSequence bestMove = new MoveSequence(); 
		
		for(MoveSequence move : allMoves) {
			Board finalBoard = move.getLastBoard();
			int newScore = minimax(finalBoard, false, Integer.MIN_VALUE, Integer.MAX_VALUE, 3);
			if(newScore > bestScore) {
				bestScore = newScore;
				bestMove = move;
			}
		}
		return bestMove;
	}

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
		
		
		if(depth == 1) {
			int currentScore = heuristic(board, this.player); 
			return currentScore;
		}
		
		List<MoveSequence> allMoves = generateAllBoards(board, player);
		
		Collections.sort(allMoves);
		
		if(!isMaximizing)
			Collections.reverse(allMoves);
		
		for(MoveSequence move : allMoves) {
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
    
    
    
    
    
    public List<MoveSequence> generateAllBoards(Board board, int player) {
        List<Board> boards = new ArrayList<Board>();
        
        /* For each cell of the board, we generate the board sequences from there */
        for(int i = 0; i < Constants.LINE_LENGTH; i++) {
            for (int j = 0; j < Constants.LINE_LENGTH; j++) {
                Position pos = new Position(j, i);
                boards.addAll(generateBoards(board, player, pos));
            }
        }

        List<MoveSequence> moveSeqList = new ArrayList<MoveSequence>();
        for (Board b : boards) {
            createBoardsLists(new MoveSequence(player), b, moveSeqList);
        }
        return moveSeqList;
    }

    public void createBoardsLists(MoveSequence moveSeq, Board board, List<MoveSequence> moveSeqList) {
    	moveSeq.add(board);
        List<Board> nextBoards = board.getNextBoards();
        if (nextBoards.size() == 0) {
            moveSeqList.add(moveSeq);
            return;
        }
        for (Board b : nextBoards) {
        	MoveSequence moveSeqTemp = new MoveSequence(moveSeq);
            createBoardsLists(moveSeqTemp, b, moveSeqList);
        }
    }

    /* Generates all the possible basic moves from a given position in the board */
	public List<Board> generateBoards(Board board, int player, Position startPos) {
		List<Board> boards = new ArrayList<Board>();
        if (board.getCell(startPos) != player) return boards;
        
		boards = generateAllCaptureBoards(board, player); // checking if there are any capture moves possible
		if (boards.size() != 0) {
			return generateCaptureBoards(board, player, startPos);
		}
		
		for (int direction : Constants.FrontDirections) {
			Board simpleMove = checkSimpleMove(board, player, startPos, direction);
			if(simpleMove != null) boards.add(simpleMove);
			Board jumpOver = checkJumpOver(board, player, startPos, direction);
			if(jumpOver != null) boards.add(jumpOver);
		}
		return boards;
	}

    /* Generates all the capture moves that player can make */
	public List<Board> generateAllCaptureBoards(Board board, int player) {
		List<Board> captureMoves = new ArrayList<Board>();
		
		for(int i = 0; i < Constants.LINE_LENGTH; i++) {
            for (int j = 0; j < Constants.LINE_LENGTH; j++) {
                Position pos = new Position(j, i);
                captureMoves.addAll(generateCaptureBoards(board, player, pos));
            }
		} 
		
		return captureMoves;
	}
	
	/* Generates the valid capture moves from a given position */ 
	public List<Board> generateCaptureBoards(Board board, int player, Position startPos) {
		List<Board> captureBoards = new ArrayList<Board>();
		if (board.getCell(startPos) != player) return captureBoards;
		for (int direction : Constants.FrontDirections) {
			Board capture = checkCaptureFront(board, player, startPos, direction);
			if(capture != null) captureBoards.add(capture);
		}
		Board captureWest = checkCaptureSide(board, player, startPos, Constants.WEST);
		if(captureWest != null) captureBoards.add(captureWest);
		Board captureEast = checkCaptureSide(board, player, startPos, Constants.EAST);
		if(captureEast != null) captureBoards.add(captureEast);
		
		return captureBoards;
    }
    
    /* Generates the valid jump over moves from a given position */ 
	public List<Board> generateJumpOverBoards(Board board, int player, Position startPos) {
		List<Board> jumpOverMoves = new ArrayList<Board>();
		if (board.getCell(startPos) != player) return jumpOverMoves;
		for (int direction : Constants.FrontDirections) {
			Board jumpOver = checkJumpOver(board, player, startPos, direction);
			if(jumpOver != null) jumpOverMoves.add(jumpOver);
		}
		return jumpOverMoves;
	}

    /* Generating and checking a possible basic movement in a given direction */
	public Board checkSimpleMove(Board board, int player, Position startPos, int direction) {
        int sign = Utils.getSign(player);
        Position endPos = new Position(startPos.x + direction, startPos.y + sign);
		Move move = new Move(startPos, endPos, board, player);
		if(move.checkBoundaries() && board.getCell(endPos) == Constants.EMPTY_CELL) {
            Board boardRes = emulateMove(move);
            generateNextBoards(boardRes, move);
            return boardRes;
		}
		return null;
    }

    /* Generating and checking a possible jump over movement in a given direction */
	public Board checkJumpOver(Board board, int player, Position startPos, int direction) {
        int sign = Utils.getSign(player);
        Position endPos = new Position(startPos.x + 2*direction, startPos.y + 2*sign);
        Move move = new Move(startPos, endPos, board, player);
		if(move.checkBoundaries() && move.isJumpOver()) {
            Board boardRes = emulateMove(move);
            generateNextBoards(boardRes, move);
			return boardRes;
		}
		return null;
    }

    /* Generating and checking a possible capture movement in a given direction (only sides) */
	public Board checkCaptureSide(Board board, int player, Position startPos, int direction) {
        Position endPos = new Position(startPos.x + 2*direction, startPos.y);
		Move move = new Move(startPos, endPos, board, player);
		if(move.checkBoundaries() && move.isCapture()) {
            Board boardRes = emulateMove(move);
            generateNextBoards(boardRes, move);
			return boardRes;
		}
		return null;
	}
	
	/* Generating and checking a possible capture movement in a given direction (only in front) */
	public Board checkCaptureFront(Board board, int player, Position startPos, int direction) {
		int sign = Utils.getSign(player);
        Position endPos = new Position(startPos.x + 2*direction, startPos.y + 2*sign);
		Move move = new Move(startPos, endPos, board, player);
		if(move.checkBoundaries() && move.isCapture()) {
            Board boardRes = emulateMove(move);
            generateNextBoards(boardRes, move);
			return boardRes;
		}
		return null;
    }
    
    public void generateNextBoards(Board boardRes, Move move) {
    	if (reachedEndOfBoard(boardRes, move.player)) return;
        List<Board> nextBoards = new ArrayList<Board>();
        if (move.isCapture()) 
            nextBoards = generateCaptureBoards(boardRes, move.player, move.endPos);
        else if (move.isJumpOver()) 
        	nextBoards = generateJumpOverBoards(boardRes, move.player, move.endPos);
        for (Board b : nextBoards) {
            boardRes.addNextBoard(b);
            b.setParent(boardRes);
        }
    }

    public boolean reachedEndOfBoard(Board board, int player) {
        if (board.pieceReachedEnd) {
        	List<Position> dropZoneCells = board.getDropZoneCells(player);
        	switch(dropZoneCells.size()) {
	        	case 0:
	        		break;
	        	case 1:
	        		for (Position pos : dropZoneCells) {
	        			Board temp = new Board(board);
	        			temp.setCell(pos, player);
	        			board.addNextBoard(temp);
	        		}
	        		break;
	        	default:
	        		for (Position pos1 : dropZoneCells) {
	        			for (Position pos2 : dropZoneCells) {
	        				if (!pos1.equals(pos2)) {
	        					Board temp = new Board(board);
	        					temp.setCell(pos1, player);
	        					temp.setCell(pos2, player);
	        					board.addNextBoard(temp);
	        				}
	        			}
	        		}
	        		break;
        	}
        	return true;
        }
        return false;
    }
    
    public Board emulateMove(Move move) {
        Board boardRes = new Board(move.board);
        boardRes.setCell(move.startPos, Constants.EMPTY_CELL);
        boardRes.setCell(move.endPos, move.player);
        if (move.isCapture()) {
            boardRes.setCell(move.captured, Constants.EMPTY_CELL);
        }
        if ((move.endPos.y == 7 && move.player == Constants.PLAYER_1) || (move.endPos.y == 0 && move.player == Constants.PLAYER_2)) {
        	boardRes.pieceReachedEnd = true;
        	boardRes.setCell(move.endPos, Constants.EMPTY_CELL);
        }
        return boardRes;
    }
}