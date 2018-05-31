package Maps;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.ImageButton;

public class MainMenu extends ScreenState {
	private BufferedImage bg, title, button_img;
	private String[] buttons = {"Start Game", "Documentation", "Exit"};
	private ArrayList<ImageButton> buttonBounds;
	private StateManager sm;
	
	public MainMenu(StateManager sm) {
		this.sm = sm;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
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
    	g.drawImage(bg, 0,0, null);
    	
    	// Draw title
    	g.drawImage(title, 243, 50, null);
    	
    	// Draw buttons
    	int y_offset = 250;
    	for(String button: buttons) {
    		ImageButton btn = new ImageButton("Assets/Images/button_img.png", button, 365, y_offset, 300, 75, g);
    		btn.draw();
    		buttonBounds.add(btn);    		
    		y_offset += 90;
    	}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
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
				} else if(buttons[i].equals("Start Game")) {
					sm.setState(1);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
