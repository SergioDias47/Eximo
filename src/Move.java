public class Move {
    public final Position startPos;
    public final Position endPos;
    public Position captured;
    public final int player;
    public final Board board;

    /* Move that requires further analyzing */
    public Move(Position startPos, Position endPos, Board board, int player) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.player = player;
        this.board = board;
        this.captured = new Position(Constants.ERROR, Constants.ERROR);
        if(checkBoundaries())
	        if (Math.abs(endPos.y - startPos.y) == 2 || Math.abs(endPos.x - startPos.x) == 2) {
	            Position midPos = new Position(Math.abs(endPos.x + startPos.x)/2, Math.abs(endPos.y + startPos.y)/2);
	            if (board.getCell(midPos) == Utils.otherPlayer(player))
	                captured = midPos;
	            else captured = new Position(-1, -1);
	        } else captured = new Position(-1, -1);
    }

    /*
     * Checks if a move is a Capture move.
     */
    public boolean isCapture() {
        return captured.x != -1;
    }

    /*
     * Checks if a move is a Jump Over move.
     */
    public boolean isJumpOver() {
        return captured.x == Constants.ERROR && (Math.abs(endPos.y - startPos.y) == 2 || Math.abs(endPos.x - startPos.x) == 2)
        		&& board.getCell(new Position(Math.abs(endPos.x + startPos.x)/2, Math.abs(endPos.y + startPos.y)/2)) == player;
    }

    /*
     * Checks if boundaries if a move is within the board boundaries.
     */
    public boolean checkBoundaries() {
        return endPos.x >= 0 && 
                endPos.x < Constants.LINE_LENGTH && 
                endPos.y >= 0 && 
                endPos.y < Constants.LINE_LENGTH &&
                  (Math.abs(endPos.y - startPos.y) <= 2 && Math.abs(endPos.x - startPos.x) <= 2) &&
                    board.getCell(endPos) == Constants.EMPTY_CELL; 
    }

    /*
     * Prints the positions associated with the move on the console.
     */
    public void print() {
        System.out.println("Start Pos: (" + startPos.x + "," + startPos.y + ")  End Pos: (" + endPos.x + "," + endPos.y + ")");
    }
}