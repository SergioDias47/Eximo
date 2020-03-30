import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class GameLabel extends JLabel{
	private static final long serialVersionUID = 1L;

	public GameLabel(String content, int size) {
		super(content);
		setForeground(new Color(101, 67, 33));
		setFont(new Font("Arial Rounded MT Bold", Font.BOLD, size));
	}
}
