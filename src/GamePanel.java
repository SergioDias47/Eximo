import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private BoardPanel board;
	private GameLabel playerTurn;
	private GameLabel numPieces1; // remaining pieces of player 1
	private GameLabel numPieces2; // remaining pieces of player 2
	private MenuButton exitButton;
	
	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		
		board = new BoardPanel();
		exitButton = new MenuButton();
		exitButton.setText("Go back to menu");
		exitButton.setName("goBackMenu");
		playerTurn = new GameLabel(Constants.PLAYER_1_TURN, 22);
		numPieces1 = new GameLabel(Constants.REMAINING_PIECES_MSG_1 + 16, 17);
		numPieces2 = new GameLabel(Constants.REMAINING_PIECES_MSG_2 + 16, 17);
		
		JPanel gameInfoPanel = new JPanel();
		gameInfoPanel.setLayout(new GridLayout(6,1));
		gameInfoPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		gameInfoPanel.add(playerTurn);
		gameInfoPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		gameInfoPanel.add(numPieces1);
		gameInfoPanel.add(numPieces2);
		gameInfoPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(board, BorderLayout.NORTH);
		add(gameInfoPanel, BorderLayout.CENTER);
		add(exitButton, BorderLayout.SOUTH);
	}	
	
	public BoardPanel getBoard() {
		return board;
	}
	
	public void updateMatchInfo(int numPieces1, int numPieces2) {
		if(playerTurn.getText().equals(Constants.PLAYER_1_TURN)) {
			playerTurn.setText(Constants.PLAYER_2_TURN);
		} else {
			playerTurn.setText(Constants.PLAYER_1_TURN);
		}
		this.numPieces1.setText(Constants.REMAINING_PIECES_MSG_1 + numPieces1);
		this.numPieces2.setText(Constants.REMAINING_PIECES_MSG_2 + numPieces2);
	}

	public void setButtonListeners(Controller ctrl) {
		board.setButtonListeners(ctrl);
		exitButton.addActionListener(ctrl);
	}
}
