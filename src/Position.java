
public class Position {
	public int x;
	public int y;
	
	public Position(int position) {
		this.x = position % Constants.LINE_LENGTH;
		this.y = position / Constants.LINE_LENGTH;
	}
 
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
    public boolean equals(Object o) {   
        if (o == this) { 
            return true; 
        } 
        if (!(o instanceof Position)) { 
            return false; 
        } 
        Position p = (Position) o; 
        return p.x == x && p.y == y;
    } 
	
	public int toBoardPos() {
		return x + y * Constants.LINE_LENGTH;
	}
	
}
