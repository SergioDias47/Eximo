import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;

public class MenuButton extends JButton {
	private static final long serialVersionUID = 1L;
	
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
