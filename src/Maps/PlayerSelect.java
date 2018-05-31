package Maps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PlayerSelect extends ScreenState {

	private StateManager sm;
	private BufferedImage bg, title, button_img;
	
	public PlayerSelect(StateManager sm) {
		this.sm = sm;
	}
	
	@Override
	public void draw(Graphics2D g) {
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
    	
    	// Draw text   	
    	g.drawString("Please select your country.", 360, 200);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
