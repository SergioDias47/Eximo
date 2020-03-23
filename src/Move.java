
public class Move {
	public final Position startPos;
	public final Position endPos;
	public int captured;
	
	public Move(int startPos, int endPos) {
		this.startPos = new Position(startPos);
		this.endPos = new Position(endPos);
		this.captured = Constants.NO_CAPTURE;
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
		System.out.println("startPos: " + startPos + "    endPos: " + endPos);
	}
}
