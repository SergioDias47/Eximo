import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class UI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GamePanel gamePanel;
	private MenuPanel menuPanel;
	private SettingsPanel settingsPanel;
	private CardLayout layout;
	private int currentPanel;
	
	public UI() {
		super("gui");
		
		setVisible(true);
		setSize(500,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Eximo");
		
		/* Setting layout manager */
		layout = new CardLayout();
		setLayout(layout);
		
		gamePanel = new GamePanel();
		menuPanel = new MenuPanel();
		settingsPanel = new SettingsPanel();
		
		/* Adding Swing components to content pane */
		Container c = getContentPane();
		c.add(menuPanel);
		c.add(settingsPanel);
		c.add(gamePanel);
		
		/* Sets the current panel as menu panel */
		currentPanel = Constants.MENU_PANEL;
	}
	
	/*
	 * Requests the MenuPanel object to set up listeners for their buttons, so that actions can be handled in the specified controller.
	 */
	public void setMenuListener(Controller ctrl) {
		menuPanel.setButtonListeners(ctrl);
	}
	
	/*
	 * Requests the SettingsPanel object to set up listeners for their buttons, so that actions can be handled in the specified controller.
	 */
	public void setSettingsListener(Controller ctrl) {
		settingsPanel.setButtonListeners(ctrl);
	}
	
	/*
	 * Requests the GamePanel object to set up listeners for their buttons, so that actions can be handled in the specified controller.
	 */
	public void setGamePanelListener(Controller ctrl, boolean activateGrid) {
		gamePanel.setButtonListeners(ctrl, activateGrid);
	}
	
	/*
	 * Switches on to the next card, since UI uses a Card Layout. 
	 */
	public void switchPanel(int panel) {
		switch(panel) {
			case Constants.GAME_PANEL:
				if(gamePanel != null)
					getContentPane().remove(gamePanel);
				gamePanel = new GamePanel();
				getContentPane().add(gamePanel);
				layout.next(getContentPane());
				layout.next(getContentPane());	
				currentPanel = Constants.GAME_PANEL;
				break;
			case Constants.MENU_PANEL:
				layout.next(getContentPane());
				if(currentPanel == Constants.SETTINGS_PANEL)
					layout.next(getContentPane());
				currentPanel = Constants.MENU_PANEL;
				break;
			case Constants.SETTINGS_PANEL:
				layout.next(getContentPane());
				currentPanel = Constants.SETTINGS_PANEL;
		}
	}
	
	/*
	 * Requests the GamePanel object to return its BoardPanel. 
	 */
	public BoardPanel getBoard() {
		return gamePanel.getBoard();
	}
	
	/*
	 * Returns the SettingsPanel object. 
	 */
	public SettingsPanel getSettingsPanel() {
		return settingsPanel;
	}
	
	/*
	 * Returns the name of the current panel.
	 */
	public int getCurrentPanel() {
		return currentPanel;
	}
	
	/*
	 * Requests the GamePanel object to update the information about a match.
	 */
	public void updateMatchInfo(MatchInfo struct) {
		gamePanel.updateMatchInfo(struct);
	}
	
	/*
	 * Closes the window.
	 */
	public void exit() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	/*
	 * Adds a key listener to the UI, so that key events may be handled in the specified controller.
	 */
	public void setKeyListener(Controller ctrl) {
		addKeyListener(ctrl);
	}
	
	/*
	 * Requests the BoardPanel to remove all highlights from its buttons.
	 */
	public void removeAllHighlights() {
		getBoard().removeAllHighlights();
	}
}
