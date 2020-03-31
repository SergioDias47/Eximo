import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JMenuBar;

public class SettingsPanel extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private SettingsButton PBDifficultyLow; 
	private SettingsButton PBDifficultyMedium; 
	private SettingsButton PBDifficultyHigh; 
	
	private SettingsButton BB1DifficultyLow; 
	private SettingsButton BB1DifficultyMedium; 
	private SettingsButton BB1DifficultyHigh; 
	
	private SettingsButton BB2DifficultyLow; 
	private SettingsButton BB2DifficultyMedium; 
	private SettingsButton BB2DifficultyHigh;
	
	private SettingsButton exitButton;
	
	private GameLabel playerVSbot, botVSbot;
	
	private static final String lowD = "  Low  ";
	private static final String mediumD = "Medium";
	private static final String highD = "  High  ";
	
	final Color brownColor = new Color(156, 90, 3);
	final Color beigeColor = new Color(255, 255, 227);
	
	private Image backgroundImage;
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Drawing the background image
	    g.drawImage(backgroundImage, 0, 0, this);
	 }
	
	public SettingsPanel() {
		super();
		
		// Setting layout manager
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		
		this.setBackground(new Color(0,0,0));
		try {
			backgroundImage = ImageIO.read(new File("Pictures\\settingsBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Swing components
		PBDifficultyLow = new SettingsButton();
		PBDifficultyLow.setText(lowD);
		PBDifficultyLow.setName("pb-low");
		
		PBDifficultyMedium = new SettingsButton();
		PBDifficultyMedium.setText(mediumD);
		PBDifficultyMedium.setName("pb-medium");
		
		PBDifficultyHigh = new SettingsButton();
		PBDifficultyHigh.setText(highD);
		PBDifficultyHigh.setName("pb-high");
		
		BB1DifficultyLow = new SettingsButton();
		BB1DifficultyLow.setText(lowD);
		BB1DifficultyLow.setName("bb1-low");
		
		BB1DifficultyMedium = new SettingsButton();
		BB1DifficultyMedium.setText(mediumD);
		BB1DifficultyMedium.setName("bb1-medium");
		
		BB1DifficultyHigh = new SettingsButton();
		BB1DifficultyHigh.setText(highD);
		BB1DifficultyHigh.setName("bb1-high");
		
		BB2DifficultyLow = new SettingsButton();
		BB2DifficultyLow.setText(lowD);
		BB2DifficultyLow.setName("bb2-low");
		
		BB2DifficultyMedium = new SettingsButton();
		BB2DifficultyMedium.setText(mediumD);
		BB2DifficultyMedium.setName("bb2-medium");
		
		BB2DifficultyHigh = new SettingsButton();
		BB2DifficultyHigh.setText(highD);
		BB2DifficultyHigh.setName("bb2-high");
		
		playerVSbot = new GameLabel("PLAYER VS BOT", 20);
		botVSbot = new GameLabel("BOT VS BOT", 20);
		
		exitButton = new SettingsButton();
		exitButton.setText("                               Go back to menu                               ");
		exitButton.setName("goBackMenu");    
		
		// Adding components to content pane
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.5;
		c.gridwidth = 8;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 0;
		c.gridy = 1;
		add(playerVSbot, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(70, 1)), c);
		c.gridx = 1;
		c.gridy = 3;
		add(PBDifficultyLow, c);
		c.gridx = 2;
		c.gridy = 3;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 3;
		c.gridy = 3;
		add(PBDifficultyMedium, c);
		c.gridx = 4;
		c.gridy = 3;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 5;
		c.gridy = 3;
		add(PBDifficultyHigh, c);
		c.gridx = 6;
		c.gridy = 3;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 8;
		
		add(botVSbot, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		add(BB1DifficultyLow, c);
		c.gridx = 2;
		c.gridy = 6;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 3;
		c.gridy = 6;
		c.gridwidth = 1;
		add(BB1DifficultyMedium, c);
		c.gridx = 4;
		c.gridy = 6;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 5;
		c.gridy = 6;
		c.gridwidth = 1;
		add(BB1DifficultyHigh, c);
		c.gridx = 7;
		c.gridy = 6;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		add(BB2DifficultyLow, c);
		c.gridx = 2;
		c.gridy = 7;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 3;
		c.gridy = 7;
		c.gridwidth = 1;
		add(BB2DifficultyMedium, c);
		c.gridx = 4;
		c.gridy = 7;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		c.gridx = 5;
		c.gridy = 7;
		c.gridwidth = 1;
		add(BB2DifficultyHigh, c);
		c.gridx = 7;
		c.gridy = 7;
		c.gridwidth = 1;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		
		
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 8;
		add(Box.createRigidArea(new Dimension(35, 1)), c);
		
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 10;
		add(exitButton, c);
		
	}
	
	/*
	 * Sets the listeners for the buttons that belong to this panel, so actions may be handled in the specified controller.
	 */
	public void setButtonListeners(Controller ctrl) {
		PBDifficultyLow.addActionListener(ctrl);
		PBDifficultyMedium.addActionListener(ctrl);
		PBDifficultyHigh.addActionListener(ctrl);
		
		BB1DifficultyLow.addActionListener(ctrl);
		BB1DifficultyMedium.addActionListener(ctrl);
		BB1DifficultyHigh.addActionListener(ctrl);
		
		BB2DifficultyLow.addActionListener(ctrl);
		BB2DifficultyMedium.addActionListener(ctrl);
		BB2DifficultyHigh.addActionListener(ctrl);
		
		exitButton.addActionListener(ctrl);
	}
	
	public void highlightSelection(String selection) {
		if(selection == "goBackMenu") {
			return;
		}
		String[] parts = selection.split("-");
		String section = parts[0];
		String difficulty = parts[1];
		
		switch(section) {
			case "pb":
			case "bb2":
				switch(difficulty) {
					case "low":
						PBDifficultyLow.setBackground(brownColor);
						PBDifficultyMedium.setBackground(beigeColor);
						PBDifficultyHigh.setBackground(beigeColor);
						BB2DifficultyLow.setBackground(brownColor);
						BB2DifficultyMedium.setBackground(beigeColor);
						BB2DifficultyHigh.setBackground(beigeColor);
						
						PBDifficultyLow.setForeground(beigeColor);
						PBDifficultyMedium.setForeground(brownColor);
						PBDifficultyHigh.setForeground(brownColor);
						BB2DifficultyLow.setForeground(beigeColor);
						BB2DifficultyMedium.setForeground(brownColor);
						BB2DifficultyHigh.setForeground(brownColor);
						break;
					case "medium":
						PBDifficultyLow.setBackground(beigeColor);
						PBDifficultyMedium.setBackground(brownColor);
						PBDifficultyHigh.setBackground(beigeColor);
						BB2DifficultyLow.setBackground(beigeColor);
						BB2DifficultyMedium.setBackground(brownColor);
						BB2DifficultyHigh.setBackground(beigeColor);
						
						PBDifficultyLow.setForeground(brownColor);
						PBDifficultyMedium.setForeground(beigeColor);
						PBDifficultyHigh.setForeground(brownColor);
						BB2DifficultyLow.setForeground(brownColor);
						BB2DifficultyMedium.setForeground(beigeColor);
						BB2DifficultyHigh.setForeground(brownColor);
						break;
					case "high":
						PBDifficultyLow.setBackground(beigeColor);
						PBDifficultyMedium.setBackground(beigeColor);
						PBDifficultyHigh.setBackground(brownColor);
						BB2DifficultyLow.setBackground(beigeColor);
						BB2DifficultyMedium.setBackground(beigeColor);
						BB2DifficultyHigh.setBackground(brownColor);
						
						PBDifficultyLow.setForeground(brownColor);
						PBDifficultyMedium.setForeground(brownColor);
						PBDifficultyHigh.setForeground(beigeColor);
						BB2DifficultyLow.setForeground(brownColor);
						BB2DifficultyMedium.setForeground(brownColor);
						BB2DifficultyHigh.setForeground(beigeColor);
						break;
				}
				break;
			case "bb1":
				switch(difficulty) {
					case "low":
						BB1DifficultyLow.setBackground(brownColor);
						BB1DifficultyMedium.setBackground(beigeColor);
						BB1DifficultyHigh.setBackground(beigeColor);
						
						BB1DifficultyLow.setForeground(beigeColor);
						BB1DifficultyMedium.setForeground(brownColor);
						BB1DifficultyHigh.setForeground(brownColor);
						break;
					case "medium":
						BB1DifficultyLow.setBackground(beigeColor);
						BB1DifficultyMedium.setBackground(brownColor);
						BB1DifficultyHigh.setBackground(beigeColor);
						
						BB1DifficultyLow.setForeground(brownColor);
						BB1DifficultyMedium.setForeground(beigeColor);
						BB1DifficultyHigh.setForeground(brownColor);
						break;
					case "high":
						BB1DifficultyLow.setBackground(beigeColor);
						BB1DifficultyMedium.setBackground(beigeColor);
						BB1DifficultyHigh.setBackground(brownColor);
						
						BB1DifficultyLow.setForeground(brownColor);
						BB1DifficultyMedium.setForeground(brownColor);
						BB1DifficultyHigh.setForeground(beigeColor);
						break;
			}
				break;
		}
		
	}

	


}