import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;

public class MenuButton extends JButton {
	private static final long serialVersionUID = 1L;
	
	public MenuButton() {
		super();
		this.setOpaque(true);
		this.setFocusable(false);
		this.setBackground(Constants.BEIGE_COLOR);
		this.setForeground(Constants.BROWN_COLOR);
		setFont(new Font("Arial Narrow", Font.BOLD, 16));
		this.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        ((JComponent) evt.getSource()).setBackground(Constants.BROWN_COLOR);
		        ((JComponent) evt.getSource()).setForeground(Constants.BEIGE_COLOR);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        ((JComponent) evt.getSource()).setBackground(Constants.BEIGE_COLOR);
		        ((JComponent) evt.getSource()).setForeground(Constants.BROWN_COLOR);
		    }
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	((JComponent) evt.getSource()).setBackground(Constants.BROWN_COLOR);
		        ((JComponent) evt.getSource()).setForeground(Constants.BEIGE_COLOR);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	((JComponent) evt.getSource()).setBackground(Constants.BEIGE_COLOR);
		        ((JComponent) evt.getSource()).setForeground(Constants.BROWN_COLOR);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	((JComponent) evt.getSource()).setBackground(Constants.BROWN_COLOR);
		        ((JComponent) evt.getSource()).setForeground(Constants.BEIGE_COLOR);
		    }
		});
	}
}
