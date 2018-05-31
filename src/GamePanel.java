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
	private ArrayList<ImageButton> buttonBounds;

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
		this.buttonBounds = new ArrayList<ImageButton>();
    	
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
    		ImageButton btn = new ImageButton("Assets/Images/button_img.png", button, 365, y_offset, 300, 75, g2);
    		btn.draw();
    		buttonBounds.add(btn);    		
    		y_offset += 90;
    	}
    }

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		for(int i = 0; i < buttonBounds.size(); i++) {
			if(buttonBounds.get(i).wasClicked(me)) {
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
