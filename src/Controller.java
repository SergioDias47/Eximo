import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener, KeyListener {

	private UI gui;
	private Eximo game;
	private int firstSelection = -1;
	private List<Move> possibleMoves;
	
	public Controller() {
		//UI
		gui = new UI();
		gui.setMenuListener(this);
		gui.setKeyListener(this);
		possibleMoves = new ArrayList<Move>();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((Component) e.getSource()).getClass().equals(BoardButton.class)) {
			int buttonID = Integer.parseInt(((Component) e.getSource()).getName());
			if(game.getPiecesToPlace() > 0) {
				game.addPieceAt(buttonID);
				return;
			}
			if(firstSelection == -1) {
				firstSelection = buttonID;
				highlightMoves();
			} else {
				Move attemptedMove = new Move(firstSelection, buttonID);
				removeHighlights();
				switch (game.getMoveState()) {
					case Constants.NORMAL: 
						game.playerMove(attemptedMove);
						break;
					case Constants.JUMP_OVER:
						game.sequentialJumpOver(attemptedMove);
						break;
					case Constants.CAPTURE:
						game.sequentialCapture(attemptedMove);
				}
				firstSelection = -1;
			}
		} else {
			String buttonName = ((Component) e.getSource()).getName();
			switch(buttonName) {
				case "PP":
					game = new Eximo(Constants.PLAYER_VS_PLAYER, gui);
					gui.switchPanel(Constants.GAME_PANEL);
					gui.setGamePanelListener(this);
					break;
				case "PB":
					game = new Eximo(Constants.PLAYER_VS_BOT, gui);
					gui.switchPanel(Constants.GAME_PANEL);
					gui.setGamePanelListener(this);
					break;
				case "BB":
					game = new Eximo(Constants.BOT_VS_BOT, gui);
					gui.switchPanel(Constants.GAME_PANEL);
					gui.setGamePanelListener(this);
					break;
				case "exit":
					gui.exit();
					break;
				case "goBackMenu":
					goBackToMenu();
					break;
			}
		} 
		gui.requestFocusInWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_R) {
			if(firstSelection != -1)
				removeHighlights();
			firstSelection = -1;
		}
		if(key == KeyEvent.VK_ESCAPE) {
			if(game != null)
				goBackToMenu();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public void highlightMoves() {
		gui.getBoard().highlightAt(firstSelection);
		possibleMoves = game.generateMoves(firstSelection);
		for(Move move : possibleMoves) gui.getBoard().highlightAt(move.endPos.toBoardPos());
	}
	
	public void removeHighlights() {
		gui.getBoard().highlightAt(firstSelection);
		for(Move move : possibleMoves) gui.getBoard().highlightAt(move.endPos.toBoardPos()); 
	}
	
	public void goBackToMenu() {
		game = null;
		gui.switchPanel(Constants.MENU_PANEL);
		firstSelection = -1;
		possibleMoves.clear();
	}
	
	

}
