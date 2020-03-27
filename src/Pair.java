
public class Pair {
	private Board board;
	private Move move;
	public Pair(Board board, Move move) {
		this.board = board;
		this.move = move;
	}
	
	public Board first() {
		return this.board;
	}
	
	public Move second() {
		return this.move;
	}
}
