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
import javax.swing.JMenuBar;

public class MenuPanel extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private MenuButton startPPGameButton; // start a player vs player match
	private MenuButton startPBGameButton; // start a player vs bot match
	private MenuButton startBBGameButton; // start a bot vs bot match
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
		GridLayout layout = new GridLayout(9,3);
		setLayout(layout);
		this.setBackground(new Color(0,0,0));
		try {
			backgroundImage = ImageIO.read(new File("Pictures\\background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Swing components
		startPPGameButton = new MenuButton();
		startPPGameButton.setText("PLAYER vs PLAYER");
		startPPGameButton.setName("PP");
		
		startPBGameButton = new MenuButton();
		startPBGameButton.setText("PLAYER vs BOT");
		startPBGameButton.setName("PB");
		
		startBBGameButton = new MenuButton();
		startBBGameButton.setText("BOT vs BOT");
		startBBGameButton.setName("BB");
		
		startPPGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		startPBGameButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
	
		exitButton = new MenuButton();
		exitButton.setText("EXIT GAME");
		exitButton.setName("exit");
		
		// Adding components to content pane
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
		add(startBBGameButton, BorderLayout.SOUTH);
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
	
	/*
	 * Sets the listeners for the buttons that belong to this panel, so actions may be handled in the specified controller.
	 */
	public void setButtonListeners(Controller ctrl) {
		startPPGameButton.addActionListener(ctrl);
		startPBGameButton.addActionListener(ctrl);
		startBBGameButton.addActionListener(ctrl);
		exitButton.addActionListener(ctrl);
	}

}