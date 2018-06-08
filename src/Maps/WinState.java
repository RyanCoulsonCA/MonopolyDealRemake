package Maps;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.ImageButton;
import main.Player;

public class WinState extends ScreenState {
	private BufferedImage winnerImg, bg;
	private String[] buttons = new String[] {"new", "quit"};
	private ArrayList<ImageButton> buttonBounds;
	private Player winner;
	private StateManager sm;
	
	public WinState(StateManager sm) {
		this.sm = sm;
		this.buttonBounds = new ArrayList<ImageButton>();
	}
	
	@Override
	public void draw(Graphics2D g) {
		// Get winner
		this.winner = sm.getWinner();
		
    	// Load images
    	try {
    		bg = ImageIO.read(new File("Assets/Images/temp_background.png"));
    		
    		winnerImg = ImageIO.read(new File("Assets/Images/"+this.winner.getImage()));
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	Font reg = g.getFont();
    	Color regc = g.getColor();
    	Stroke regs = g.getStroke();
    	FontMetrics fm = g.getFontMetrics();
    	
    	// Background
    	g.drawImage(bg, 0, 0, null);
    	g.setColor(Color.WHITE);
    	    	
    	// Draw text
    	g.setFont(new Font("Dialog", Font.BOLD, 48));
    	g.drawString("Game Over", 400, 100);
    	
    	g.drawImage(this.winnerImg, 460, 200, null);
    	
    	if(this.winner.getCountry().length() < 4) {
    		g.drawString(this.winner.getCountry(), 495 - fm.stringWidth(this.winner.getCountry())/2, 400);
    	} else {
    		g.drawString(this.winner.getCountry(), 470 - fm.stringWidth(this.winner.getCountry())/2, 400);
    	}
    	
    	// Draw buttons
    	ImageButton btn = new ImageButton("Assets/Images/button_img.png", "New Game", 200, 500, 300, 75, g);
		btn.draw();
		buttonBounds.add(btn);    	

    	btn = new ImageButton("Assets/Images/button_img.png", "Quit Game", 550, 500, 300, 75, g);
		btn.draw();
		buttonBounds.add(btn);    	
		
    	g.setFont(reg);

	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		for(int i = 0; i < this.buttonBounds.size(); i++) {
			if(this.buttonBounds.get(i).wasClicked(me)) {
				if(this.buttons[i].equals("new")) {
					sm.reset();
				} else if(this.buttons[i].equals("quit")) {
					System.exit(0);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
