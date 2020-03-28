import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private BoardButton[][] buttons;
	
	public BoardPanel() {
		super();
		Dimension size = getPreferredSize();
		size.height = 500;
		setPreferredSize(size);
		getPreferredSize().height = 500;
		buttons = new BoardButton[Constants.LINE_LENGTH][Constants.LINE_LENGTH];
		setLayout(new GridLayout(8,8));
		for(int i = 0; i < Constants.LINE_LENGTH; i++)
			for(int j = 0; j < Constants.LINE_LENGTH; j++) {
				buttons[j][i] = new BoardButton();
				buttons[j][i].setName(Utils.getButtonName(new Position(j,i)));
				add(buttons[j][i]);
			}
		//initialize();
	}
	
	/*
	 * Changes the icon of the button, provided its id and the icon type to use.
	 */
	public void setIconAt(Position pos, int type) {
		buttons[pos.x][pos.y].setIcon(type);
	}
	
	/*
	 * Sets the board to match the initial game state.
	 */
	public void initialize() {
		/*for(int i = 1; i <= 6; i++) {
			setIconAt(i,Constants.WHITE_CELL);
			setIconAt(i+8, Constants.WHITE_CELL);
			setIconAt(i+48, Constants.BLACK_CELL);
			setIconAt(i+56, Constants.BLACK_CELL);
		}
		setIconAt(17, Constants.WHITE_CELL); setIconAt(18, Constants.WHITE_CELL);
		setIconAt(21, Constants.WHITE_CELL); setIconAt(22, Constants.WHITE_CELL); 
		setIconAt(41, Constants.BLACK_CELL); setIconAt(42, Constants.BLACK_CELL);
		setIconAt(45, Constants.BLACK_CELL); setIconAt(46,Constants.BLACK_CELL); */
	}
	
	public void initializeDebugBoard() {
		/*setIconAt(33,Constants.WHITE_CELL);
		setIconAt(40,Constants.BLACK_CELL);*/
	}
	
	public void setButtonListeners(Controller ctrl) {
		for(int i = 0; i < Constants.LINE_LENGTH; i++)
			for(int j = 0; j < Constants.LINE_LENGTH; j++) {
				buttons[i][j].addActionListener(ctrl);
			}
	}
	
	public void highlightAt(Position pos) {
		buttons[pos.x][pos.y].highlight();
	}
	
	public void printBoard(Board board) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	Position pos = new Position(i,j);
            	setIconAt(pos, board.getCell(pos));
            }
        }
	}
	
	
}