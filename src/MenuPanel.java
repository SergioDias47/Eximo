import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JLabel;
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
			e.printStackTrace();
		}
		
		// Swing components
		startPPGameButton = new MenuButton();
		startPPGameButton.setText("Player vs Player");
		startPPGameButton.setName("PP");
		
		startPBGameButton = new MenuButton();
		startPBGameButton.setText("Players vs Bot");
		startPBGameButton.setName("PB");
		
		startPPGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		startPBGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	
		exitButton = new MenuButton();
		exitButton.setText("Exit game");
		exitButton.setName("exit");
		
		// Add components to content pane
	
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(startPPGameButton, BorderLayout.NORTH);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(startPBGameButton, BorderLayout.SOUTH);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(exitButton, BorderLayout.SOUTH);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
	}
	
	public void setButtonListeners(Controller ctrl) {
		startPPGameButton.addActionListener(ctrl);
		startPBGameButton.addActionListener(ctrl);
		exitButton.addActionListener(ctrl);
	}

}