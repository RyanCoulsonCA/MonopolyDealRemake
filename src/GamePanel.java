import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable  {
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

	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
	}
	
	public void run() {
		init();
		
		while(running) {
			System.out.println("test");
		}
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(200, 62, 30, 10);
    }
	
}
