import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class MenuButton extends JButton {
	final Color brownColor = new Color(156, 90, 3);
	final Color beigeColor = new Color(255, 255, 227);
	
	public MenuButton() {
		super();
		this.setOpaque(true);
		this.setFocusable(false);
		this.setBackground(beigeColor);
		this.setForeground(brownColor);
		setFont(new Font("Arial Narrow", Font.BOLD, 16));
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        ((JComponent) evt.getSource()).setBackground(brownColor);
		        ((JComponent) evt.getSource()).setForeground(beigeColor);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        ((JComponent) evt.getSource()).setBackground(beigeColor);
		        ((JComponent) evt.getSource()).setForeground(brownColor);
		    }
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	((JComponent) evt.getSource()).setBackground(brownColor);
		        ((JComponent) evt.getSource()).setForeground(beigeColor);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	((JComponent) evt.getSource()).setBackground(beigeColor);
		        ((JComponent) evt.getSource()).setForeground(brownColor);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	((JComponent) evt.getSource()).setBackground(brownColor);
		        ((JComponent) evt.getSource()).setForeground(beigeColor);
		    }
		});
	}
}
