import java.util.ArrayList;
import java.util.List;

public class MoveGenerator {
	/*
     * Generates all the possible sequence of boards for a given game state. 
     */
    public static List<MoveSequence> generateAllSequences(Board board, int player) {
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
    
    /*
     * Executes a move on a board that is returned.
     */
    static public Board emulateMove(Move move) {
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

    private static void createBoardsLists(MoveSequence moveSeq, Board board, List<MoveSequence> moveSeqList) {
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

    /* 
     * Generates all the possible basic moves from a given position in the board. 
     */
	private static List<Board> generateBoards(Board board, int player, Position startPos) {
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

    /*
     * Generates all the capture moves that player can make. 
     */
	private static List<Board> generateAllCaptureBoards(Board board, int player) {
		List<Board> captureMoves = new ArrayList<Board>();
		
		for(int i = 0; i < Constants.LINE_LENGTH; i++) {
            for (int j = 0; j < Constants.LINE_LENGTH; j++) {
                Position pos = new Position(j, i);
                captureMoves.addAll(generateCaptureBoards(board, player, pos));
            }
		} 
		
		return captureMoves;
	}
	
	/*
	 * Generates the valid capture moves from a given position.
	 */ 
	private static List<Board> generateCaptureBoards(Board board, int player, Position startPos) {
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
    
    /* 
     * Generates the valid jump over moves from a given position.
     */ 
	private static List<Board> generateJumpOverBoards(Board board, int player, Position startPos) {
		List<Board> jumpOverMoves = new ArrayList<Board>();
		if (board.getCell(startPos) != player) return jumpOverMoves;
		for (int direction : Constants.FrontDirections) {
			Board jumpOver = checkJumpOver(board, player, startPos, direction);
			if(jumpOver != null) jumpOverMoves.add(jumpOver);
		}
		return jumpOverMoves;
	}

    /* 
     * Generating and checking a possible basic movement in a given direction.
     */
	private static Board checkSimpleMove(Board board, int player, Position startPos, int direction) {
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

    /* 
     * Generating and checking a possible jump over movement in a given direction.
     */
	private static Board checkJumpOver(Board board, int player, Position startPos, int direction) {
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

    /* 
     * Generating and checking a possible capture movement in a given direction (only sides). 
     */
	private static Board checkCaptureSide(Board board, int player, Position startPos, int direction) {
        Position endPos = new Position(startPos.x + 2*direction, startPos.y);
		Move move = new Move(startPos, endPos, board, player);
		if(move.checkBoundaries() && move.isCapture()) {
            Board boardRes = emulateMove(move);
            generateNextBoards(boardRes, move);
			return boardRes;
		}
		return null;
	}
	
	/* 
	 * Generating and checking a possible capture movement in a given direction (only in front). 
	 */
	private static Board checkCaptureFront(Board board, int player, Position startPos, int direction) {
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
    
	/*
	 * Generates the subsequent boards that result from a mandatory move.
	 */
    private static void generateNextBoards(Board boardRes, Move move) {
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

    /*
     * Handles the event that occurs when a player has a piece reach the end of the board.
     */
    private static boolean reachedEndOfBoard(Board board, int player) {
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
}
