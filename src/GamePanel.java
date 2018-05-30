import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel  {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private Graphics2D g;
	private boolean running;

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
    	
    	for(int i = 0; i < 5; i++) {
    		g2.drawRect(5 + i*95, 5, 90, 150);
    	}
    }
	
}
