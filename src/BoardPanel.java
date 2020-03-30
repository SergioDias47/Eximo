import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
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
	}
	
	/*
	 * Sets up a listener for each button of the grid, so actions can be handled in the controller.
	 */
	public void setButtonListeners(Controller ctrl) {
		for(int i = 0; i < Constants.LINE_LENGTH; i++)
			for(int j = 0; j < Constants.LINE_LENGTH; j++) {
				buttons[i][j].addActionListener(ctrl);
			}
	}
	
	/*
	 * Changes the icon of the button, provided its id and the icon type to use.
	 */
	public void setIconAt(Position pos, int type) {
		buttons[pos.x][pos.y].setIcon(type);
	}
	
	/*
	 * Highlights the button at the specified position.
	 */
	public void highlightAt(Position pos) {
		buttons[pos.x][pos.y].highlight();
	}
	
	/*
	 * Removes highlights from every button.
	 */
	public void removeAllHighlights() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	buttons[j][i].removeHighlight();
            }
        }
	}
	
	/*
	 * Paints all the cells to match the specified board.
	 */
	public void printBoard(Board board) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	Position pos = new Position(i,j);
            	setIconAt(pos, board.getCell(pos));
            }
        }
	}
	
	
}