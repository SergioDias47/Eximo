import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JMenuBar;

public class MenuPanel extends JMenuBar {
	private MenuButton startPPGameButton; // start a player vs player match
	private MenuButton startPBGameButton; // start a player vs bot match
	private MenuButton exitButton; 
	private Image backgroundImage;
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Drawing the background image
	    g.drawImage(backgroundImage, 0, 0, this);
	  }
	
	public MenuPanel() {
		super();
		
		// Setting layout manager
		
		GridLayout layout = new GridLayout(7,3);
		setLayout(layout);
		this.setBackground(new Color(0,0,0));
		try {
			backgroundImage = ImageIO.read(new File("Pictures\\background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Swing components
		startPPGameButton = new MenuButton();
		startPPGameButton.setText("Start player vs player game");
		startPPGameButton.setName("PP");
		
		startPBGameButton = new MenuButton();
		startPBGameButton.setText("Start player vs bot game");
		startPBGameButton.setName("PB");
		startPPGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		startPBGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	
		exitButton = new MenuButton();
		exitButton.setText("Exit game");
		exitButton.setName("exit");
		
		// Adding components to content pane
	
		add(startPPGameButton);
		add(startPBGameButton);
		add(exitButton);
	}
	
	public void setButtonListeners(Controller ctrl) {
		startPPGameButton.addActionListener(ctrl);
		startPBGameButton.addActionListener(ctrl);
		exitButton.addActionListener(ctrl);
	}

}