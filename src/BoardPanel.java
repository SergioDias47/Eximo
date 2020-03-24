import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private BoardButton[] buttons;
	
	public BoardPanel() {
		super();
		Dimension size = getPreferredSize();
		size.height = 500;
		setPreferredSize(size);
		getPreferredSize().height = 500;
		buttons = new BoardButton[Constants.BOARD_SIZE];
		setLayout(new GridLayout(8,8));
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new BoardButton();
			buttons[i].setName(i+"");
			add(buttons[i]);
		}
		initialize();
	}
	
	/*
	 * Changes the icon of the button, provided its id and the icon type to use.
	 */
	public void setIconAt(int id, int type) {
		buttons[id].setIcon(type);
	}
	
	/*
	 * Sets the board to match the initial game state.
	 */
	public void initialize() {
		for(int i = 1; i <= 6; i++) {
			setIconAt(i,Constants.WHITE_CELL);
			setIconAt(i+8, Constants.WHITE_CELL);
			setIconAt(i+48, Constants.BLACK_CELL);
			setIconAt(i+56, Constants.BLACK_CELL);
		}
		setIconAt(17, Constants.WHITE_CELL); setIconAt(18, Constants.WHITE_CELL);
		setIconAt(21, Constants.WHITE_CELL); setIconAt(22, Constants.WHITE_CELL); 
		setIconAt(41, Constants.BLACK_CELL); setIconAt(42, Constants.BLACK_CELL);
		setIconAt(45, Constants.BLACK_CELL); setIconAt(46,Constants.BLACK_CELL); 
	}
	
	public void setButtonListeners(Controller ctrl) {
		for(int i = 0; i < buttons.length; i++)
			buttons[i].addActionListener(ctrl);
	}
	
	
}