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
	private String firstSelected;
	
	public Controller() {
		firstSelected = Constants.NONE_SELECTED;
		//UI
		gui = new UI();
		gui.setMenuListener(this);
		gui.setKeyListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((Component) e.getSource()).getClass().equals(BoardButton.class)) {
			String buttonName = ((Component) e.getSource()).getName();
			
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
		} else {
			String buttonName = ((Component) e.getSource()).getName();
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
					game.printCurrentBoard();
					gui.setGamePanelListener(this, !Constants.ACTIVATE_GRID);
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
	
	/*
	 * Quits a match and goes back to the main menu.
	 */
	public void goBackToMenu() {
		game.stopBotWork();
		game = null;
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
