import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 650;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage bg, title, button_img;
	private String[] buttons = {"Start Game", "Documentation", "Exit"};
	private ArrayList<Shape> buttonBounds;

	public GamePanel() {
		super();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		addMouseListener(this);
		requestFocus();
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
		this.buttonBounds = new ArrayList<Shape>();
    	
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
    		g2.setFont(new Font("Default", Font.BOLD, 23));
    		g2.setStroke(new BasicStroke(0.1f));
    		g2.setColor(Color.LIGHT_GRAY);
    		
    		Rectangle btn = new Rectangle(365, y_offset+4, 293, 67);
    		buttonBounds.add(btn);
    		g2.draw(btn);
    		
    		g2.drawImage(button_img, 362, y_offset, null);
    		g2.drawString(button, 512 - (button.length() * 6), y_offset + 44);
    		y_offset += 90;
    	}
    }

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		for(int i = 0; i < buttonBounds.size(); i++) {
			if(buttonBounds.get(i).contains(me.getPoint())) {
				if(buttons[i].equals("Exit")) {
					System.exit(0);
				} else if(buttons[i].equals("Documentation")) {
					try {
						Desktop.getDesktop().browse(new URI("http://ryan-coulson.com/miniprojects/daskapital/docs.html"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println(buttons[i]);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
