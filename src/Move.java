
public class Move {
	public final Position startPos;
	public final Position endPos;
	
	public Move(int startPos, int endPos) {
		this.startPos = new Position(startPos);
		this.endPos = new Position(endPos);
	}
	
	public boolean checkBoundaries() {
		return endPos.x >= 0 && 
				endPos.x < Constants.LINE_LENGTH && 
				 endPos.y >= 0 && 
				  endPos.y < Constants.LINE_LENGTH;
	}
}
