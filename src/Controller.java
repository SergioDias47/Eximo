import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements ActionListener, KeyListener {

	private UI gui;
	private Eximo game;
	private String firstSelected;
	
	public Controller() {
		firstSelected = Constants.NONE_SELECTED;
		//UI
		gui = new UI();
		gui.setMenuListener(this);
		gui.setSettingsListener(this);
		gui.setKeyListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonName = ((Component) e.getSource()).getName();
		
		if(((Component) e.getSource()).getClass().equals(BoardButton.class)) {
			/* If the bot is thinking, human players should not disturb it */
			if(game.getGameMode() == Constants.PLAYER_VS_BOT && game.getPlayer() == Constants.PLAYER_2)
				return;
			
			/* If the player has extra pieces, the controller must let him choose where to place them */
			if (game.getPiecesToAdd() > 0) {
				game.addPieceAt(Utils.getButtonPos(buttonName));
			}
			else {
				if(firstSelected.equals(Constants.NONE_SELECTED)) {
					firstSelected = buttonName;
					game.highlightAvailableMoves(Utils.getButtonPos(firstSelected));
				} else {
					gui.removeAllHighlights();
					Position startP = Utils.getButtonPos(firstSelected);
					Position endP = Utils.getButtonPos(buttonName);
					new Thread() {
						  public void run() {
							  game.playerMove(startP, endP);
					}}.start();
					firstSelected = Constants.NONE_SELECTED;
				}
			}
		} else if(((Component) e.getSource()).getClass().equals(SettingsButton.class)) {
			handleSettingsActions(buttonName);
		} else {
			switch(buttonName) {
				case "PP":
					game = new Eximo(gui, Constants.PLAYER_VS_PLAYER);
					gui.switchPanel(Constants.GAME_PANEL);
					gui.setGamePanelListener(this, Constants.ACTIVATE_GRID);
					game.printCurrentBoard();
					break;
				case "PB":
					game = new Eximo(gui, Constants.PLAYER_VS_BOT);
					gui.switchPanel(Constants.GAME_PANEL);
					gui.setGamePanelListener(this, Constants.ACTIVATE_GRID);
					game.printCurrentBoard();
					break;
				case "BB":
					game = new Eximo(gui, Constants.BOT_VS_BOT);
					gui.switchPanel(Constants.GAME_PANEL);
					gui.setGamePanelListener(this, !Constants.ACTIVATE_GRID);
					game.printCurrentBoard();
					break;
				case "exit":
					gui.exit();
					break;
				case "goBackMenu":
					goBackToMenu();
					break;
				case "settings":
					gui.switchPanel(Constants.SETTINGS_PANEL);
					break;
			}
		} 
		gui.requestFocusInWindow();
	}
	
	/*
	 * Handles settings events.
	 */
	public void handleSettingsActions(String buttonName) {
		switch(buttonName) {
			case "pb-low":
				Properties.HEURISTIC_PLAYER_2 = 0;
				break;
			case "pb-medium":
				Properties.HEURISTIC_PLAYER_2 = 1;
				break;
			case "pb-high":
				Properties.HEURISTIC_PLAYER_2 = 4;
				break;
			case "bb1-low":
				Properties.HEURISTIC_PLAYER_1 = 0;
				break;
			case "bb1-medium":
				Properties.HEURISTIC_PLAYER_1 = 1;
				break;
			case "bb1-high":
				Properties.HEURISTIC_PLAYER_1 = 4;
				break;
			case "bb2-low":
				Properties.HEURISTIC_PLAYER_2 = 0;
				break;
			case "bb2-medium":
				Properties.HEURISTIC_PLAYER_2 = 1;
				break;
			case "bb2-high":
				Properties.HEURISTIC_PLAYER_2 = 4;
				break;
			case "goBackMenu":
				goBackToMenu();
				break;
		}
		gui.getSettingsPanel().highlightSelection(buttonName);
	}
	
	/*
	 * Quits a match and goes back to the main menu.
	 */
	public void goBackToMenu() {
		if(game != null) {
			game.stopBotWork();
			game = null;
		}
		gui.switchPanel(Constants.MENU_PANEL);
		firstSelected = Constants.NONE_SELECTED;
	}

	/*
	 * 
	 * Handling keyboard events.
	 * 
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_R) {
			if(firstSelected != Constants.NONE_SELECTED)
				gui.removeAllHighlights();
			firstSelected = Constants.NONE_SELECTED;
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

}
