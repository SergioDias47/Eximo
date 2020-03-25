import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class MenuButton extends JButton {
	public MenuButton() {
		super();
		this.setOpaque(true);
		this.setBackground(new Color(255, 255, 227));
		this.setForeground(new Color(156, 90, 3));
		setFont(new Font("Arial Narrow", Font.BOLD, 16));
		this.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        ((JComponent) evt.getSource()).setBackground(new Color(156, 90, 3));
		        ((JComponent) evt.getSource()).setForeground(new Color(255, 255, 227));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        ((JComponent) evt.getSource()).setBackground(new Color(255, 255, 227));
		        ((JComponent) evt.getSource()).setForeground(new Color(156, 90, 3));
		    }
		});
	}
}
