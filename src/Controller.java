import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controller implements ActionListener {

	private UI gui;
	private Eximo game;
	private int firstSelection = -1;
	
	public Controller() {
		//UI
		gui = new UI();
		gui.setMenuListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((Component) e.getSource()).getClass().equals(BoardButton.class)) {
			int buttonID = Integer.parseInt(((Component) e.getSource()).getName());
			if(game.getPiecesToPlace() > 0) {
				game.addPieceAt(buttonID);
			}
			if(firstSelection == -1) {
				firstSelection = buttonID;
				return;
			} else {
				Move attemptedMove = new Move(firstSelection, buttonID);
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
				case "exit":
					gui.exit();
					break;
				case "goBackMenu":
					gui.switchPanel(Constants.MENU_PANEL);
					break;
			}
		}
	}

}
