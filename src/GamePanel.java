import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel  {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 650;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage bg, title, button_img;
	private Graphics2D g;
	private boolean running;
	private String[] buttons = {"Start Game", "Documentation", "Exit"};

	public GamePanel() {
		super();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	int x;
    	
    	// Load images
    	try {
    		bg = ImageIO.read(new File("Assets/Images/temp_background.png"));
    		title = ImageIO.read(new File("Assets/Images/temp_logo.png"));
    		button_img = ImageIO.read(new File("Assets/Images/button_img.png"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	// Draw background
    	g2.drawImage(bg, 0,0, null);
    	
    	// Draw title
    	g2.drawImage(title, 243, 50, null);
    	
    	// Draw buttons
    	int y_offset = 250;
    	for(String button: buttons) {
    		g2.drawImage(button_img, 362, y_offset, null);
    		g2.setFont(new Font("Default", Font.BOLD, 23));
    		g2.setColor(Color.LIGHT_GRAY);
    		g2.drawString(button, 512 - (button.length() * 6), y_offset + 44);
    		y_offset += 90;
    	}
    }
	
}
