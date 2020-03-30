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

public class SettingsPanel extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private MenuButton PBDifficultyLow; 
	private MenuButton PBDifficultyMedium; 
	private MenuButton PBDifficultyHigh; 
	
	private MenuButton BB1DifficultyLow; 
	private MenuButton BB1DifficultyMedium; 
	private MenuButton BB1DifficultyHigh; 
	
	private MenuButton BB2DifficultyLow; 
	private MenuButton BB2DifficultyMedium; 
	private MenuButton BB2DifficultyHigh;
	
	private MenuButton exitButton;
	
	private GameLabel playerVSbot, botVSbot;
	
	private Image backgroundImage;
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Drawing the background image
	    g.drawImage(backgroundImage, 0, 0, this);
	 }
	
	public SettingsPanel() {
		super();
		
		// Setting layout manager
		GridLayout layout = new GridLayout(13,7);
		setLayout(layout);
		this.setBackground(new Color(0,0,0));
		try {
			backgroundImage = ImageIO.read(new File("Pictures\\settingsBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Swing components
		PBDifficultyLow = new MenuButton();
		PBDifficultyLow.setText("LOW");
		PBDifficultyLow.setName("pb-low");
		
		PBDifficultyMedium = new MenuButton();
		PBDifficultyMedium.setText("MID");
		PBDifficultyMedium.setName("pb-medium");
		
		PBDifficultyHigh = new MenuButton();
		PBDifficultyHigh.setText("HIGH");
		PBDifficultyHigh.setName("pb-high");
		
		BB1DifficultyLow = new MenuButton();
		BB1DifficultyLow.setText("LOW");
		BB1DifficultyLow.setName("pb-low");
		
		BB1DifficultyMedium = new MenuButton();
		BB1DifficultyMedium.setText("MID");
		BB1DifficultyMedium.setName("pb-medium");
		
		BB1DifficultyHigh = new MenuButton();
		BB1DifficultyHigh.setText("HIGH");
		BB1DifficultyHigh.setName("pb-high");
		
		BB2DifficultyLow = new MenuButton();
		BB2DifficultyLow.setText("LOW");
		BB2DifficultyLow.setName("pb-low");
		
		BB2DifficultyMedium = new MenuButton();
		BB2DifficultyMedium.setText("MID");
		BB2DifficultyMedium.setName("pb-medium");
		
		BB2DifficultyHigh = new MenuButton();
		BB2DifficultyHigh.setText("HIGH");
		BB2DifficultyHigh.setName("pb-high");
		
		playerVSbot = new GameLabel("PLAYER vs BOT", 20);
		botVSbot = new GameLabel("BOT vs BOT", 20);
		
		exitButton = new MenuButton();
		exitButton.setText("Go back to menu");
		exitButton.setName("goBackMenu");
		
		// Adding components to content pane
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(playerVSbot);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(PBDifficultyLow);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(PBDifficultyMedium);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(PBDifficultyHigh);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(botVSbot);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BB1DifficultyLow);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BB1DifficultyMedium);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BB1DifficultyHigh);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BB2DifficultyLow);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BB2DifficultyMedium);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(BB2DifficultyHigh);
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(exitButton);
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		add(Box.createRigidArea(new Dimension(10, 10)));
		
	}
	
	/*
	 * Sets the listeners for the buttons that belong to this panel, so actions may be handled in the specified controller.
	 */
	public void setButtonListeners(Controller ctrl) {
		
	}

}