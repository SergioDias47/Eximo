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
import javax.swing.JComboBox;
import javax.swing.JMenuBar;

public class SettingsPanel extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private final String maxTimes[] = { "Unlimited", "1000", "2000", "3000", "5000" };
	private final String minTimes[] = { "0", "250", "500", "1000", "2000" };
	private final String depths[] = { "1", "2", "3", "4", "5", "6" };

	private JComboBox<String> timesBoxMax;
	private JComboBox<String> timesBoxMin;
	private JComboBox<String> depthsBox; 
	
	private SettingsButton PBDifficultyLow; 
	private SettingsButton PBDifficultyMedium; 
	private SettingsButton PBDifficultyHigh; 
	
	private SettingsButton BB1DifficultyLow; 
	private SettingsButton BB1DifficultyMedium; 
	private SettingsButton BB1DifficultyHigh; 
	
	private SettingsButton BB2DifficultyLow; 
	private SettingsButton BB2DifficultyMedium; 
	private SettingsButton BB2DifficultyHigh;
	
	private MenuButton exitButton;
	
	private GameLabel playerVSbot, botVSbot, depth, maxTime;
	
	private static final String lowD = "  Low  ";
	private static final String mediumD = "Medium";
	private static final String highD = "  High  ";
	
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
		depth = new GameLabel("Minimax Depth", 16);
		maxTime = new GameLabel("Minimum delay / Maximum time per bot move", 16);
		
		timesBoxMin = new JComboBox<String>(minTimes);
		timesBoxMin.setName("timesBoxMin");
		timesBoxMax = new JComboBox<String>(maxTimes);
		timesBoxMax.setName("timesBoxMax");
		depthsBox = new JComboBox<String>(depths);
		depthsBox.setName("depthsBox");
		
		exitButton = new MenuButton();
		exitButton.setText(Constants.BIG_SPACE + "Go back to menu" + Constants.BIG_SPACE);
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
		c.gridwidth = 8;
		add(depth, c);
		
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 0;
		add(depthsBox, c);
		
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 8;
		add(maxTime, c);
		
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 5;
		add(timesBoxMin, c);
		
		c.gridx = 2;
		c.gridy = 12;
		add(timesBoxMax, c);
		
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 10;
		add(exitButton, c);
		
		initializeSelections();
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
		
		timesBoxMax.addActionListener(ctrl);
		timesBoxMin.addActionListener(ctrl);
		depthsBox.addActionListener(ctrl);
		exitButton.addActionListener(ctrl);
	}
	
	public void initializeSelections() {
		switch(Properties.HEURISTIC_PLAYER_1) {
			case Constants.BASIC_HEURISTIC:
				highlightSelection("bb1-low");
				break;
			case Constants.IMPROVED_HEURISTIC:
				highlightSelection("bb1-medium");
				break;
			case Constants.ADVANCED_HEURISTIC:
				highlightSelection("bb1-high");
				break;
		}
		switch(Properties.HEURISTIC_PLAYER_2) {
			case Constants.BASIC_HEURISTIC:
				highlightSelection("pb-low");
				highlightSelection("bb2-low");
				break;
			case Constants.IMPROVED_HEURISTIC:
				highlightSelection("pb-medium");
				highlightSelection("bb2-medium");
				break;
			case Constants.ADVANCED_HEURISTIC:
				highlightSelection("pb-high");
				highlightSelection("bb2-high");
				break;
		}
		
		
		for(int i = 0; i < depths.length; i++) {
			if(Integer.parseInt(depths[i]) == Properties.MINIMAX_DEPTH)
				depthsBox.setSelectedIndex(i);
		}
		for(int i = 0; i < maxTimes.length; i++) {
			if(maxTimes[i].equals("Unlimited"))
				continue;
			if(Integer.parseInt(maxTimes[i]) == Properties.MAX_SEARCH_TIME)
				timesBoxMax.setSelectedIndex(i);
		}
		for(int i = 0; i < minTimes.length; i++) {
			if(Integer.parseInt(minTimes[i]) == Properties.MIN_DELAY)
				timesBoxMin.setSelectedIndex(i);
		}
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
						PBDifficultyLow.setBackground(Constants.BROWN_COLOR);
						PBDifficultyMedium.setBackground(Constants.BEIGE_COLOR);
						PBDifficultyHigh.setBackground(Constants.BEIGE_COLOR);
						BB2DifficultyLow.setBackground(Constants.BROWN_COLOR);
						BB2DifficultyMedium.setBackground(Constants.BEIGE_COLOR);
						BB2DifficultyHigh.setBackground(Constants.BEIGE_COLOR);
						
						PBDifficultyLow.setForeground(Constants.BEIGE_COLOR);
						PBDifficultyMedium.setForeground(Constants.BROWN_COLOR);
						PBDifficultyHigh.setForeground(Constants.BROWN_COLOR);
						BB2DifficultyLow.setForeground(Constants.BEIGE_COLOR);
						BB2DifficultyMedium.setForeground(Constants.BROWN_COLOR);
						BB2DifficultyHigh.setForeground(Constants.BROWN_COLOR);
						break;
					case "medium":
						PBDifficultyLow.setBackground(Constants.BEIGE_COLOR);
						PBDifficultyMedium.setBackground(Constants.BROWN_COLOR);
						PBDifficultyHigh.setBackground(Constants.BEIGE_COLOR);
						BB2DifficultyLow.setBackground(Constants.BEIGE_COLOR);
						BB2DifficultyMedium.setBackground(Constants.BROWN_COLOR);
						BB2DifficultyHigh.setBackground(Constants.BEIGE_COLOR);
						
						PBDifficultyLow.setForeground(Constants.BROWN_COLOR);
						PBDifficultyMedium.setForeground(Constants.BEIGE_COLOR);
						PBDifficultyHigh.setForeground(Constants.BROWN_COLOR);
						BB2DifficultyLow.setForeground(Constants.BROWN_COLOR);
						BB2DifficultyMedium.setForeground(Constants.BEIGE_COLOR);
						BB2DifficultyHigh.setForeground(Constants.BROWN_COLOR);
						break;
					case "high":
						PBDifficultyLow.setBackground(Constants.BEIGE_COLOR);
						PBDifficultyMedium.setBackground(Constants.BEIGE_COLOR);
						PBDifficultyHigh.setBackground(Constants.BROWN_COLOR);
						BB2DifficultyLow.setBackground(Constants.BEIGE_COLOR);
						BB2DifficultyMedium.setBackground(Constants.BEIGE_COLOR);
						BB2DifficultyHigh.setBackground(Constants.BROWN_COLOR);
						
						PBDifficultyLow.setForeground(Constants.BROWN_COLOR);
						PBDifficultyMedium.setForeground(Constants.BROWN_COLOR);
						PBDifficultyHigh.setForeground(Constants.BEIGE_COLOR);
						BB2DifficultyLow.setForeground(Constants.BROWN_COLOR);
						BB2DifficultyMedium.setForeground(Constants.BROWN_COLOR);
						BB2DifficultyHigh.setForeground(Constants.BEIGE_COLOR);
						break;
				}
				break;
			case "bb1":
				switch(difficulty) {
					case "low":
						BB1DifficultyLow.setBackground(Constants.BROWN_COLOR);
						BB1DifficultyMedium.setBackground(Constants.BEIGE_COLOR);
						BB1DifficultyHigh.setBackground(Constants.BEIGE_COLOR);
						
						BB1DifficultyLow.setForeground(Constants.BEIGE_COLOR);
						BB1DifficultyMedium.setForeground(Constants.BROWN_COLOR);
						BB1DifficultyHigh.setForeground(Constants.BROWN_COLOR);
						break;
					case "medium":
						BB1DifficultyLow.setBackground(Constants.BEIGE_COLOR);
						BB1DifficultyMedium.setBackground(Constants.BROWN_COLOR);
						BB1DifficultyHigh.setBackground(Constants.BEIGE_COLOR);
						
						BB1DifficultyLow.setForeground(Constants.BROWN_COLOR);
						BB1DifficultyMedium.setForeground(Constants.BEIGE_COLOR);
						BB1DifficultyHigh.setForeground(Constants.BROWN_COLOR);
						break;
					case "high":
						BB1DifficultyLow.setBackground(Constants.BEIGE_COLOR);
						BB1DifficultyMedium.setBackground(Constants.BEIGE_COLOR);
						BB1DifficultyHigh.setBackground(Constants.BROWN_COLOR);
						
						BB1DifficultyLow.setForeground(Constants.BROWN_COLOR);
						BB1DifficultyMedium.setForeground(Constants.BROWN_COLOR);
						BB1DifficultyHigh.setForeground(Constants.BEIGE_COLOR);
						break;
				}
				break;
		}
		
	}

	


}