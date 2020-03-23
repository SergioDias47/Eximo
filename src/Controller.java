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
		if(((Component) e.getSource()).getName().equals("PP")) {
			game = new Eximo(gui);
			gui.displayGamePanel();
			gui.getBoard().setButtonListeners(this);
		}
		if(((Component) e.getSource()).getClass().equals(BoardButton.class)) {
			int buttonID = Integer.parseInt(((Component) e.getSource()).getName());
			System.out.println(buttonID);
			if(firstSelection == -1) {
				firstSelection = buttonID;
				return;
			} else {
				Move attemptedMove = new Move(firstSelection, buttonID);
				game.executeMove(attemptedMove);
				firstSelection = -1;
			}	
			List<Move> list = game.findValidMoves();
			for(Move m: list) {
				//m.print();
			}
		}
	}

}
