
public class Position {
	public int x;
	public int y;
	
	public Position(int position) {
		this.x = position % Constants.LINE_LENGTH;
		this.y = position / Constants.LINE_LENGTH;
	}
}
