import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private Board board;
	
	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		
		board = new Board();
		add(board, BorderLayout.NORTH);
		add(new JLabel(""), BorderLayout.CENTER);
		add(new JLabel(""), BorderLayout.SOUTH);
	}	
	
	public Board getBoard() {
		return board;
	}
}
