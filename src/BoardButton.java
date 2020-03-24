import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class BoardButton extends JButton{
	private ImageIcon white, black, empty; 
	private final Border highlighted = BorderFactory.createLineBorder(Color.yellow, 2);
	private final String whiteImgPath = "Pictures\\whitePiece2.jpg";
	private final String blackImgPath = "Pictures\\blackPiece2.jpg";
	private final String emptyImgPath = "Pictures\\emptyPiece.jpg";
	
	public BoardButton() {
		super();
		white = new ImageIcon(whiteImgPath);
		black = new ImageIcon(blackImgPath);
		empty = new ImageIcon(emptyImgPath);
		setIcon(Constants.EMPTY_CELL);
		
	}
	
	public BoardButton(int type) {
		super();
		white = new ImageIcon(whiteImgPath);
		black = new ImageIcon(blackImgPath);
		setIcon(type);
	}

	public void setIcon(int type) {
		if(type == Constants.WHITE_CELL)
			setIcon(white);
		else if (type == Constants.BLACK_CELL)
			setIcon(black);
		else setIcon(empty);
	}
	
	public void highlight() {
		if(highlighted != getBorder()) {
			setBorder(highlighted);
		} else {
			setBorder(new JButton().getBorder()); // sets the border to default
		}
	}

}
