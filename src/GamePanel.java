import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/* Swing components */
	private BoardPanel board;
	private GameLabel timerLabel;
	private GameLabel playerTurn;
	private GameLabel player1Stats; // Label for a message containing the stats of player 1
	private GameLabel player2Stats; // Label for a message containing the stats of player 1
	private MenuButton exitButton;
	
	/* Timer */
	private int seconds;
	private int minutes;
	private boolean stopTimer;
	
	public GamePanel() {
		super();
		setLayout(new BorderLayout());
		
		board = new BoardPanel();
		exitButton = new MenuButton();
		exitButton.setText("Go back to menu");
		exitButton.setName("goBackMenu");
		playerTurn = new GameLabel(Constants.PLAYER_1_TURN, 20);
		timerLabel = new GameLabel(Constants.ELAPSED_TIME_MSG + "00:00", 17);
		player1Stats = new GameLabel(Constants.REMAINING_PIECES_MSG_1 + 16 + Constants.NUM_MOVES_MSG + 0, 13);
		player2Stats = new GameLabel(Constants.REMAINING_PIECES_MSG_2 + 16 + Constants.NUM_MOVES_MSG + 0, 13);
		
		JPanel gameInfoPanel = new JPanel();
		gameInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.5;
		gameInfoPanel.add(Box.createRigidArea(new Dimension(10, 10)), c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		gameInfoPanel.add(playerTurn, c);
		c.gridx = 0;
		c.gridy = 2;
		gameInfoPanel.add(Box.createRigidArea(new Dimension(10, 10)), c);
		c.gridx = 1;
		c.gridy = 3;
		gameInfoPanel.add(player1Stats, c);
		c.gridx = 1;
		c.gridy = 4;
		gameInfoPanel.add(player2Stats, c);
		c.gridx = 0;
		c.gridy = 5;
		gameInfoPanel.add(Box.createRigidArea(new Dimension(10, 10)), c);
		c.gridx = 0;
		c.gridy = 6;
		gameInfoPanel.add(timerLabel, c);
		c.gridx = 0;
		c.gridy = 7;
		gameInfoPanel.add(Box.createRigidArea(new Dimension(10, 10)), c);
		
		add(board, BorderLayout.NORTH);
		add(gameInfoPanel, BorderLayout.CENTER);
		add(exitButton, BorderLayout.SOUTH);
		
		initializeTimer();
	}	
	
	/*
	 * Returns the BoardPanel object.
	 */
	public BoardPanel getBoard() {
		return board;
	}
	
	/*
	 * Updates the match information on the screen: player turn, number of pieces or eventually displays a game over message.
	 */
	public void updateMatchInfo(MatchInfo struct) {
		if(struct.gameOver) {
			stopTimer = true;
			playerTurn.setText((struct.numPieces1 > struct.numPieces2)? Constants.GAME_OVER_MSG_1 : Constants.GAME_OVER_MSG_2);
		}
		else if(playerTurn.getText().equals(Constants.PLAYER_1_TURN)) {
			playerTurn.setText(Constants.PLAYER_2_TURN);
		} else {
			playerTurn.setText(Constants.PLAYER_1_TURN);
		}
		this.player1Stats.setText(Constants.REMAINING_PIECES_MSG_1 + struct.numPieces1 + Constants.NUM_MOVES_MSG + struct.numMoves1);
		this.player2Stats.setText(Constants.REMAINING_PIECES_MSG_2 + struct.numPieces2 + Constants.NUM_MOVES_MSG + struct.numMoves2);
	}
	
	/*
	 * Requests the BoardPanel object to set up listeners for their buttons.
	 * Adds a listener for the exit button, so that upon clicking it the controller can handle the event.
	 */
	public void setButtonListeners(Controller ctrl, boolean activateGrid) {
		if(activateGrid) board.setButtonListeners(ctrl);
		exitButton.addActionListener(ctrl);
	}
	
	/*
	 * 
	 */
	public void initializeTimer() {
		seconds = 0;
		minutes = 0;
		stopTimer = false;
		Timer timer = new Timer();
		int begin = 1000;
		int timeInterval = 1000;
		timer.schedule(new TimerTask() {
	       @Override
		   public void run() {
	    	   if(stopTimer)
	    		   return;
	    	   if(seconds < 59) {
	    		   seconds++;
	    	   } else {
	    		   seconds = 0;
	    		   minutes++;
	    	   }
	    	   timerLabel.setText(Constants.ELAPSED_TIME_MSG + (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds);
	       }
		}, begin, timeInterval);
	}
}
