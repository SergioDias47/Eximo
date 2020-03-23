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
		if(((Component) e.getSource()).getClass().equals(MenuButton.class)) {
			String buttonName = ((Component) e.getSource()).getName();
			switch(buttonName) {
				case "PP":
					game = new Eximo(gui);
					gui.displayGamePanel();
					gui.getBoard().setButtonListeners(this);
					break;
				case "exit":
					gui.exit();
					break;
			}
		}
		if(((Component) e.getSource()).getClass().equals(BoardButton.class)) {
			int buttonID = Integer.parseInt(((Component) e.getSource()).getName());
			System.out.println(buttonID);
			if(firstSelection == -1) {
				firstSelection = buttonID;
				return;
			} else {
				Move attemptedMove = new Move(firstSelection, buttonID);
				if (Eximo.jumpOver)
					game.sequentialJumpOverMove(attemptedMove);
				else game.playerMove(attemptedMove);
				firstSelection = -1;
			}	
		}
	}

}
