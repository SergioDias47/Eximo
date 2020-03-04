import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardButton extends JButton{
	ImageIcon white, black, empty; 
	final String whiteImgPath = "Pictures\\whitePiece2.jpg";
	final String blackImgPath = "Pictures\\blackPiece2.jpg";
	final String emptyImgPath = "Pictures\\emptyPiece.jpg";
	
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

}
