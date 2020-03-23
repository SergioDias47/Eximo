import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private Board board;
	private MenuButton exitButton;
	
	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		
		board = new Board();
		exitButton = new MenuButton();
		exitButton.setText("Go back to menu");
		exitButton.setName("goBackMenu");
		
		add(board, BorderLayout.NORTH);
		add(exitButton, BorderLayout.SOUTH);
	}	
	
	public Board getBoard() {
		return board;
	}

	public void setButtonListeners(Controller ctrl) {
		board.setButtonListeners(ctrl);
		exitButton.addActionListener(ctrl);
	}
}
