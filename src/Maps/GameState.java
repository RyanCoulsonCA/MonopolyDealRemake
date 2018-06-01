package Maps;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import main.Deck;
import main.Player;

public class GameState extends ScreenState {

	private StateManager sm;
	private BufferedImage bg, title, button_img, playerOneImg, playerTwoImg;
	private Player playerOne, playerTwo;
	private Font user;
	
	public GameState(StateManager sm) {
		this.sm = sm;
		this.playerOne = sm.getPlayerOne();
		this.playerTwo = sm.getPlayerTwo();
		
		Deck deck = new Deck("Assets/test.txt");
		deck.parseDeck();
		deck.shuffle();
		
		// Deal cards
		for(int i = 0; i < 3; i++) {
			this.playerOne.addHand(deck.pop());
			this.playerTwo.addHand(deck.pop());
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
    	// Load images
    	try {
    		bg = ImageIO.read(new File("Assets/Images/temp_background.png"));
    		title = ImageIO.read(new File("Assets/Images/logo_small.png"));
    		button_img = ImageIO.read(new File("Assets/Images/button_img.png"));
    		
    		playerOneImg = ImageIO.read(new File("Assets/Images/i"+this.playerOne.getImage()));
    		playerTwoImg = ImageIO.read(new File("Assets/Images/i"+this.playerTwo.getImage()));
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	Font reg = g.getFont();
    	Color regc = g.getColor();
    	
    	// Background
    	g.drawImage(bg, 0, 0, null);
    	
    	// Logo
    	g.drawImage(title, 387, 30, null);
    	
    	// First Player
    	g.drawImage(playerOneImg, 25, 25, null);
    	   
    	g.setFont(new Font("Dialog", Font.PLAIN, 16));
    	g.drawString(this.playerOne.getCountry(), 80, 45);
    	
    	g.setFont(new Font("DialogInput", Font.PLAIN, 16));
    	g.setColor(Color.GREEN);
    	g.drawString("$"+this.playerOne.getTreasury(), 80, 65);
    	
    	g.setFont(reg);
    	g.setColor(regc);
    	
    	// Second Player
    	g.drawImage(playerTwoImg, 950, 25, null);
    	
    	g.setFont(new Font("Dialog", Font.PLAIN, 16));   	   
    	if(this.playerTwo.getCountry().equals("Canada")) {
        	g.drawString(this.playerTwo.getCountry(), 880, 45);	
    	} else {
    		g.drawString(this.playerTwo.getCountry(), 880 + (7 - this.playerTwo.getCountry().length())*7, 45);	
    	}

    	
    	g.setFont(new Font("DialogInput", Font.PLAIN, 16));
    	g.setColor(Color.GREEN);
    	g.drawString("$"+this.playerTwo.getTreasury(), 926 - Integer.toString(this.playerTwo.getTreasury()).length()*9, 65);
    	
    	g.setFont(reg);
    	g.setColor(regc);
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

}
