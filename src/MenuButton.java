import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;

public class MenuButton extends JButton {
	public MenuButton() {
		super();
		this.setOpaque(false);
		this.setBackground(new Color(255,255,255));
		this.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        ((JComponent) evt.getSource()).setBackground(new Color(255,255,255));
		    }

		    
		});
		 
		
		
	}
	
	
}
