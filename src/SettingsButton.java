import java.awt.Font;

import javax.swing.JButton;

public class SettingsButton extends JButton {
	private static final long serialVersionUID = 1L;

	public SettingsButton() {
		this.setOpaque(true);
		this.setFocusable(false);
		this.setBackground(Constants.BEIGE_COLOR);
		this.setForeground(Constants.BROWN_COLOR);
		setFont(new Font("Arial Narrow", Font.BOLD, 16));
	}
}
