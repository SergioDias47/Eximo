import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardButton extends JButton{
	ImageIcon white, black;
	final String whiteImgPath = "Pictures\\whitePiece.jpg";
	final String blackImgPath = "Pictures\\blackPiece.jpg";
	
	public BoardButton() {
		super();
		white = new ImageIcon(whiteImgPath);
		black = new ImageIcon(blackImgPath);
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
		else setIcon(black);
	}

}
