import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private BoardPanel board;
	private JLabel playerTurn;
	private MenuButton exitButton;
	
	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		
		board = new BoardPanel();
		exitButton = new MenuButton();
		exitButton.setText("Go back to menu");
		exitButton.setName("goBackMenu");
		playerTurn = new JLabel(Constants.PLAYER_1_TURN);
		
		add(board, BorderLayout.NORTH);
		add(playerTurn, BorderLayout.CENTER);
		add(exitButton, BorderLayout.SOUTH);
	}	
	
	public BoardPanel getBoard() {
		return board;
	}
	
	public void updatePlayerTurn() {
		if(playerTurn.getText().equals(Constants.PLAYER_1_TURN)) {
			playerTurn.setText(Constants.PLAYER_2_TURN);
		} else {
			playerTurn.setText(Constants.PLAYER_1_TURN);
		}
	}

	public void setButtonListeners(Controller ctrl) {
		board.setButtonListeners(ctrl);
		exitButton.addActionListener(ctrl);
	}
}
