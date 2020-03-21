import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			game = new Eximo();
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
				gui.getBoard().setIconAt(firstSelection, Constants.EMPTY_CELL);
				gui.getBoard().setIconAt(buttonID, Constants.WHITE_CELL);
				firstSelection = -1;
			}	
		}
	}

}
