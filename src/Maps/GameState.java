package Maps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.Card;
import main.Deck;
import main.ImageButton;
import main.Player;

public class GameState extends ScreenState {

	private BufferedImage bg, title, playerOneImg, playerTwoImg;
	private Player playerOne, playerTwo;
	private int turn;
	private ArrayList<ImageButton> buttonBounds;
	private String[] buttons = new String[] {"next", "quit"};
	
	public GameState(StateManager sm) {
		this.sm = sm;
		this.playerOne = sm.getPlayerOne();
		this.playerTwo = sm.getPlayerTwo();
		this.turn = 0; // 0 = player 1, 1 = player 2
		
		this.buttonBounds = new ArrayList<ImageButton>();
		
		Deck deck = new Deck("Assets/base_deck.txt");
		deck.parseDeck();
		deck.shuffle();
		
		// Deal cards
		for(int i = 0; i < 7; i++) {
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
		this.buttonBounds = new ArrayList<ImageButton>();
		
    	// Load images
    	try {
    		bg = ImageIO.read(new File("Assets/Images/temp_background.png"));
    		title = ImageIO.read(new File("Assets/Images/logo_small.png"));
    		
    		playerOneImg = ImageIO.read(new File("Assets/Images/i"+this.playerOne.getImage()));
    		playerTwoImg = ImageIO.read(new File("Assets/Images/i"+this.playerTwo.getImage()));
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	Font reg = g.getFont();
    	Color regc = g.getColor();
    	Stroke regs = g.getStroke();
    	
    	// Background
    	g.drawImage(bg, 0, 0, null);
    	
    	// Logo
    	g.drawImage(title, 387, 30, null);
    	
    	// First Player
    	g.setFont(new Font("Dialog", Font.PLAIN, 10));
    	g.setColor(Color.LIGHT_GRAY);
    	g.drawString("Player One", 22, 20);
    	
    	g.drawImage(playerOneImg, 25, 25, null);
    	   
    	g.setFont(new Font("Dialog", Font.PLAIN, 16));
    	g.setColor(regc);
    	g.drawString(this.playerOne.getCountry(), 80, 45);
    	
    	g.setFont(new Font("DialogInput", Font.PLAIN, 16));
    	g.setColor(Color.GREEN);
    	g.drawString("$"+this.playerOne.getTreasury(), 80, 65);
    	
    	g.setFont(reg);
    	g.setColor(regc);
    	
    	// Glow if player's turn
    	if(this.turn == 0) {
	    	g.setColor(new Color(211, 175, 55));
	    	g.setStroke(new BasicStroke(3));
	    	g.drawOval(25, 25, 47, 47);
	    	g.setColor(regc);
	    	g.setStroke(regs);
    	}
    	
    	// Second Player
    	g.setFont(new Font("Dialog", Font.PLAIN, 10));
    	g.setColor(Color.LIGHT_GRAY);
    	g.drawString("Player Two", 947, 20);
    	
    	g.drawImage(playerTwoImg, 950, 25, null);
    	
    	g.setFont(new Font("Dialog", Font.PLAIN, 16));  
    	g.setColor(regc);
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
    	
    	if(this.turn == 1) {
	    	g.setColor(new Color(211, 175, 55));
	    	g.setStroke(new BasicStroke(3));
	    	g.drawOval(950, 25, 47, 47);
	    	g.setColor(regc);
	    	g.setStroke(regs);
    	}
    	/*
    	new ActionCard("Double Tarrifs", "action", 5, "rent").draw(g, 250, 250);
    	new ActionCard("Territory", "action", 5, "rent").draw(g, 400, 250);
    	new ActionCard("Blitzkrieg", "action", 5, "rent").draw(g, 550, 250);
    	new ActionCard("Quick Ambush", "action", 5, "rent").draw(g, 700, 250);
    	new PropertyCard("Toronto", "wild", 5, new Color(247,126,35), new String[] {"5","10","20"}).draw(g, 850, 250);
    	*/
    	
    	
    	// Action buttons
    	ImageButton endTurn = new ImageButton("Assets/Images/wooden_btn.png", 20, 450, 100, 60, g);
    	endTurn.draw();
    	buttonBounds.add(endTurn);
    	g.drawString("Next Turn", 40, 485);
    	
    	ImageButton quitBtn = new ImageButton("Assets/Images/wooden_btn.png", 20, 530, 100, 60, g);
    	quitBtn.draw();
    	buttonBounds.add(quitBtn);
    	g.drawString("Quit", 55, 565);
    	
    	if(this.turn == 0) {
    		int x_offset = 532 - Math.round(this.playerOne.getHand().size() * 110)/2;
    		for(Card c: this.playerOne.getHand()) {
    			c.draw(g, x_offset, 450);
    			x_offset += 110;
    		}
    	} else {
    		int x_offset = 532 - Math.round(this.playerTwo.getHand().size() * 110)/2;
    		for(Card c: this.playerTwo.getHand()) {
    			c.draw(g, x_offset, 450);
    			x_offset += 110;
    		}
    	}
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		for(int i = 0; i < this.buttonBounds.size(); i++) {
			if(this.buttonBounds.get(i).wasClicked(me)) {
				if(this.buttons[i].equals("next")) {
					if(this.turn == 0) this.turn = 1;
					else this.turn = 0;
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

}
