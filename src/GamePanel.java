import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private BoardPanel board;
	private MenuButton exitButton;
	
	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		
		board = new BoardPanel();
		exitButton = new MenuButton();
		exitButton.setText("Go back to menu");
		exitButton.setName("goBackMenu");
		
		add(board, BorderLayout.NORTH);
		add(exitButton, BorderLayout.SOUTH);
	}	
	
	public BoardPanel getBoard() {
		return board;
	}

	public void setButtonListeners(Controller ctrl) {
		board.setButtonListeners(ctrl);
		exitButton.addActionListener(ctrl);
	}
}
