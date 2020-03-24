import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class UI extends JFrame {
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
		
		// Set layout manager
		layout = new CardLayout();
		setLayout(layout);
		
		gamePanel = new GamePanel();
		menuPanel = new MenuPanel();
		
		// Add Swing components to content pane
		Container c = getContentPane();
		c.add(menuPanel);
		c.add(gamePanel);
	}
	
	public void setMenuListener(Controller ctrl) {
		menuPanel.setButtonListeners(ctrl);
	}
	
	public void setGamePanelListener(Controller ctrl) {
		gamePanel.setButtonListeners(ctrl);
	}
	
	public void switchPanel(int panel) {
		if(panel == Constants.GAME_PANEL) {
			if(gamePanel != null)
				getContentPane().remove(gamePanel);
			gamePanel = new GamePanel();
			getContentPane().add(gamePanel);
		}
			
		layout.next(getContentPane());
	}
	
	public BoardPanel getBoard() {
		return gamePanel.getBoard();
	}
	
	public void updatePlayerTurn() {
		gamePanel.updatePlayerTurn();
	}
	
	public void exit() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	public void setKeyListener(Controller ctrl) {
		addKeyListener(ctrl);
	}
}
