import java.util.ArrayList;
import java.util.List;

public class MoveSequence implements Comparable<MoveSequence> {
	private List<Board> boards;
	private int player;
	
	public MoveSequence() {}
	
	public MoveSequence(int player) {
		this.player = player;
		boards = new ArrayList<Board>();
	}
	
	public MoveSequence(List<Board> boards, int player) {
		this.boards = boards;
		this.player = player;
	}
	
	public MoveSequence(MoveSequence ms) {
		player = ms.player;
		boards = new ArrayList<Board>(ms.boards); 
	}

	@Override
	public int compareTo(MoveSequence o) {
		Board finalBoard1 = boards.get(boards.size() - 1);
		Board finalBoard2 = o.boards.get(o.boards.size() - 1);
		
		int eval1 = Eximo.heuristic(finalBoard1, player);
		int eval2 = Eximo.heuristic(finalBoard2, player);
		
		if (eval1 > eval2) return -1;
		if (eval1 < eval2) return 1;
		return 0;
	}

	public List<Board> getBoards() {
		return boards;
	}
	
	public void add(Board board) {
		boards.add(board);
	}
	
	public Board getFirstBoard() {
		return boards.get(0);
	}
	
	public Board getLastBoard() {
		return boards.get(boards.size() - 1);
	}
	
	public void remove(int index) {
		boards.remove(index);
	}
	
	public int size() {
		return boards.size();
	}
	
	public void print() {
		System.out.println("Move Sequence: ");
		for (Board b : boards) {
			//System.out.println("Board end: " + b.pieceReachedEnd);
			b.print();
		}
	}
}
