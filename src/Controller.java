import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

	private UI gui;
	private Eximo game;
	
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
		}
		
	}

}
