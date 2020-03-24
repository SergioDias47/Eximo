import java.util.ArrayList;
import java.util.List;

public class Move {
	public final Position startPos;
	public final Position endPos;
	public int captured;
	public List<Move> nextMoves;
	
	public Move(int startPos, int endPos) {
		this.startPos = new Position(startPos);
		this.endPos = new Position(endPos);
		this.captured = Constants.NO_CAPTURE;
		nextMoves = new ArrayList<Move>();
	}
	
	public boolean checkBoundaries() {
		return endPos.x >= 0 && 
				endPos.x < Constants.LINE_LENGTH && 
				 endPos.y >= 0 && 
				  endPos.y < Constants.LINE_LENGTH;
	}
	
	public void setCaptured(int captured) {
		this.captured = captured;
	}
	
	public void checkCapture() {
		if (Math.abs(startPos.x - endPos.x) == 2 || Math.abs(startPos.y - endPos.y) == 2) {
			this.captured = (startPos.toBoardPos() + endPos.toBoardPos()) / 2;
		}
	}
	
	public boolean isCapture() {
		return this.captured != Constants.NO_CAPTURE;
	}
	
	/* Only used after checking if it is a capture, thus not having a complete check */
	public boolean isJumpOver() {
		return Math.abs(startPos.x - endPos.x) == 2 || Math.abs(startPos.y - endPos.y) == 2;
	}
	
    @Override
    public boolean equals(Object o) {   
        if (o == this) { 
            return true; 
        }    
        if (!(o instanceof Move)) { 
            return false; 
        }     
        Move m = (Move) o; 
        return startPos.equals(m.startPos) && endPos.equals(m.endPos) && captured == m.captured;
    }  

	public void print() {
		System.out.println("startPos: " + startPos + "    endPos: " + endPos + "  Captured: " + captured);
	}
}
