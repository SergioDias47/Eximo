import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;

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
		
		if(((Component) e.getSource()).getClass().equals(JComboBox.class)) {
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
	        String option = (String)cb.getSelectedItem();
			handleDropDowns(buttonName, option);
		}
		
		if(((Component) e.getSource()).getClass().equals(BoardButton.class)) {
			/* If the bot is thinking, human players should not disturb it */
			if(game.getGameMode() == Constants.PLAYER_VS_BOT && game.getPlayer() == Constants.PLAYER_2)
				return;
			
			/* If the player has extra pieces, the controller must let him choose where to place them */
			if (game.getPiecesToAdd() > 0) {
				new Thread() {
					  public void run() {
						  game.addPieceAt(Utils.getButtonPos(buttonName));
				}}.start();
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
				Properties.HEURISTIC_PLAYER_2 = Constants.BASIC_HEURISTIC;
				break;
			case "pb-medium":
				Properties.HEURISTIC_PLAYER_2 = Constants.IMPROVED_HEURISTIC;
				break;
			case "pb-high":
				Properties.HEURISTIC_PLAYER_2 = Constants.ADVANCED_HEURISTIC;
				break;
			case "bb1-low":
				Properties.HEURISTIC_PLAYER_1 = Constants.BASIC_HEURISTIC;
				break;
			case "bb1-medium":
				Properties.HEURISTIC_PLAYER_1 = Constants.IMPROVED_HEURISTIC;
				break;
			case "bb1-high":
				Properties.HEURISTIC_PLAYER_1 = Constants.ADVANCED_HEURISTIC;
				break;
			case "bb2-low":
				Properties.HEURISTIC_PLAYER_2 = Constants.BASIC_HEURISTIC;
				break;
			case "bb2-medium":
				Properties.HEURISTIC_PLAYER_2 = Constants.IMPROVED_HEURISTIC;
				break;
			case "bb2-high":
				Properties.HEURISTIC_PLAYER_2 = Constants.ADVANCED_HEURISTIC;
				break;
			case "goBackMenu":
				goBackToMenu();
				break;
		}
		gui.getSettingsPanel().highlightSelection(buttonName);
	}
	
	/*
	 * Handles dropdown events.
	 */
	public void handleDropDowns(String name, String option) {
		switch(name) {
			case "timesBoxMin":
				switch(option) {
					case "0":
					case "250":
					case "500":
					case "1000":
					case "2000":
						Properties.MIN_DELAY = Integer.parseInt(option);
				}
				break;
			case "timesBoxMax":
				switch(option) {
					case "1000":
					case "2000":
					case "3000":
					case "5000":
						Properties.MAX_SEARCH_TIME = Integer.parseInt(option);
						break;
					case "Unlimited":
						Properties.MAX_SEARCH_TIME = Integer.MAX_VALUE;
				}
				break;
			case "depthsBox":
				switch(option) {
					case "1":
					case "2":
					case "3":
					case "4":
					case "5":
					case "6":
						Properties.MINIMAX_DEPTH = Integer.parseInt(option);
						break;		
				}
		}
	}
	
	/*
	 * Quits a match and goes back to the main menu.
	 */
	public void goBackToMenu() {
		Properties.print();
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
			if(gui.getCurrentPanel() == Constants.GAME_PANEL || gui.getCurrentPanel() == Constants.SETTINGS_PANEL)
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
