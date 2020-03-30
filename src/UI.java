import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class UI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GamePanel gamePanel;
	private MenuPanel menuPanel;
	private CardLayout layout;
	
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
		
		/* Adding Swing components to content pane */
		Container c = getContentPane();
		c.add(menuPanel);
		c.add(gamePanel);
	}
	
	/*
	 * Requests the MenuPanel object to set up listeners for their buttons, so that actions can be handled in the specified controller.
	 */
	public void setMenuListener(Controller ctrl) {
		menuPanel.setButtonListeners(ctrl);
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
		if(panel == Constants.GAME_PANEL) {
			if(gamePanel != null)
				getContentPane().remove(gamePanel);
			gamePanel = new GamePanel();
			getContentPane().add(gamePanel);
		}
			
		layout.next(getContentPane());
	}
	
	/*
	 * Requests the GamePanel object to return its BoardPanel. 
	 */
	public BoardPanel getBoard() {
		return gamePanel.getBoard();
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
