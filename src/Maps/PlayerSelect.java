package Maps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.ImageButton;

public class PlayerSelect extends ScreenState {

	private StateManager sm;
	private BufferedImage bg, title, button_img;
	private ArrayList<ImageButton> buttonBounds;
	
	public PlayerSelect(StateManager sm) {
		this.sm = sm;
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
    	
    	// Draw text   	
    	g.drawString("Please select your country.", 360, 200);
    	
    	// Back button
    	ImageButton ret = new ImageButton("Assets/Images/button_img.png", "Return to Menu", 365, 500, 300, 75, g);
    	buttonBounds.add(ret);
    	ret.draw();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if(buttonBounds.get(0).wasClicked(me)) {
			sm.setState(0);
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
