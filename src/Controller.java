import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

	private UI gui;
	private int cells[];
	
	public Controller() {
		//UI
		gui = new UI();
		gui.setMenuListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
