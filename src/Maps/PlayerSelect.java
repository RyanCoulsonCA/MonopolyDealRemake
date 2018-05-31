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
import main.Player;

public class PlayerSelect extends ScreenState {

	private StateManager sm;
	private BufferedImage bg, title, button_img;
	private ArrayList<ImageButton> buttonBounds;
	private String[] buttons = {"Canada", "America", "China", "EU", "Russia", "UK", "Menu"};
	private String[] images = {"canada_circle.png", "america_circle.png", "china_circle.png", "eu_circle.png", "russia_circle.png", "uk_circle.png"};
	private Player playerOne, playerTwo;
	private int counter;
	private String prompt;
	
	public PlayerSelect(StateManager sm) {
		this.sm = sm;

		this.playerOne = sm.getPlayerOne();
		this.playerTwo = sm.getPlayerTwo();
		this.counter = 0;
		this.prompt = "Player One: Please select your country.";
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
    	g.drawString(this.prompt, 290, 200);
    	
    	// Draw country buttons
    	int btn_x = 290;
    	int btn_y = 220;
    	
    	for(int i = 0; i < 6; i++) {
    		if(i == 3) {
    			btn_y += 150;
    			btn_x = 290;
    		}
    		
    		ImageButton btn = new ImageButton("Assets/Images/"+images[i], btn_x, btn_y, 125, 125, g);
    		buttonBounds.add(btn);
    		btn.draw();
    		btn_x += 150;
    	}
    	
    	// Back button
    	ImageButton ret = new ImageButton("Assets/Images/button_img.png", "Return to Menu", 365, 550, 300, 75, g);
    	buttonBounds.add(ret);
    	ret.draw();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		for(int i = 0; i < buttonBounds.size(); i++) {
			if(buttonBounds.get(i).wasClicked(me)) {
				if(buttons[i].equals("Menu")) {
					sm.reset();
				} else {
					if(images[i] != "hide_circle.png") {
						if(counter == 0) {
							this.playerOne.setCountry(buttons[i]);
							this.prompt = "Player Two: Please select your country.";
						} else if(counter == 1) {
							this.playerTwo.setCountry(buttons[i]);
							sm.setState(2);
						}
						images[i] = "hide_circle.png";
						counter++;
					}
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
