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
import javax.swing.SwingUtilities;

import main.Card;
import main.CardButton;
import main.CardStack;
import main.Deck;
import main.ImageButton;
import main.Player;

public class GameState extends ScreenState {

	private BufferedImage bg, title, playerOneImg, playerTwoImg;
	private Player currentPlayer, otherPlayer, playerOne, playerTwo;
	private int turn;
	private ArrayList<ImageButton> buttonBounds;
	private ArrayList<CardButton> cardBounds;
	private ArrayList<CardButton> highlightBounds;
	private String[] buttons = new String[] {"next", "quit"};
	private Deck deck;
	private boolean highlightPlayerProperties, highlightEnemyProperties;
	private Card selectedWild;
	
	public GameState(StateManager sm) {
		this.sm = sm;
		this.playerOne = sm.getPlayerOne();
		this.playerTwo = sm.getPlayerTwo();
		this.turn = 0; // 0 = player 1, 1 = player 2
		this.currentPlayer = this.playerOne;
		this.otherPlayer = this.playerTwo;
		
		this.highlightPlayerProperties = false;
		this.highlightEnemyProperties = false;
		this.selectedWild = null;
		
		this.buttonBounds = new ArrayList<ImageButton>();
		this.cardBounds = new ArrayList<CardButton>();
		this.highlightBounds = new ArrayList<CardButton>();
		
		this.deck = new Deck("Assets/base_deck.txt");
		this.deck.parseDeck();
		this.deck.shuffle();
		
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
		if(this.currentPlayer.getMovesLeft() == 0) {
			this.nextTurn();
		}
		
		this.buttonBounds = new ArrayList<ImageButton>();
		this.cardBounds = new ArrayList<CardButton>();
		this.highlightBounds = new ArrayList<CardButton>();
		
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
    	g.drawImage(title, 387, 20, null);
    	
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
    	g.drawString("$"+this.playerOne.getTreasury()+"M", 81, 65);
    	
    	g.setFont(reg);
    	g.setColor(regc);
    	
    	g.setFont(new Font("DialogInput", Font.PLAIN, 14));
    	
    	if(!this.playerOne.isBlocking()) {
        	g.setColor(Color.RED);
    		g.drawString("X Veto", 170, 43);
    	} else {
        	g.setColor(Color.GREEN);
        	g.drawString("✓ Veto", 170, 43);
    	}
    	
    	if(!this.playerOne.isDoubleRent()) {
    		g.setColor(Color.RED);
    		g.drawString("X Double Tarrifs", 170, 60);
    	} else {
    		g.setColor(Color.GREEN);
    		g.drawString("✓ Double Tarrifs", 170, 60);
    	}
 
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
    	g.drawString("$"+this.playerTwo.getTreasury()+"M", 919 - Integer.toString(this.playerTwo.getTreasury()).length()*9, 65);

    	g.setFont(reg);
    	g.setColor(regc);
    	
    	g.setFont(new Font("DialogInput", Font.PLAIN, 14));
    	
    	if(!this.playerTwo.isBlocking()) {
        	g.setColor(Color.RED);
    		g.drawString("Veto X", 800, 43);
    	} else {
        	g.setColor(Color.GREEN);
        	g.drawString("Veto ✓", 800, 43);
    	}
    	
    	if(!this.playerTwo.isDoubleRent()) {
    		g.setColor(Color.RED);
    		g.drawString("Double Tarrifs X", 720, 60);
    	} else {
    		g.setColor(Color.GREEN);
    		g.drawString("Double Tarrifs ✓", 720, 60);
    	}
 
    	g.setFont(reg);
    	g.setColor(regc);
    	
    	if(this.turn == 1) {
	    	g.setColor(new Color(211, 175, 55));
	    	g.setStroke(new BasicStroke(3));
	    	g.drawOval(950, 25, 47, 47);
	    	g.setColor(regc);
	    	g.setStroke(regs);
    	}
    	
    	/* Blank Cards 
    	new Card("Test","action", 5).draw(g, 50, 80);
    	new Card("Test","action", 5).draw(g, 50, 100);
    	new Card("Test","action", 5).draw(g, 50, 120);
 
    	new Card("Test","action", 5).draw(g, 50, 280);
    	new Card("Test","action", 5).draw(g, 50, 300);
    	new Card("Test","action", 5).draw(g, 50, 320);
    	*/
    	
    	// Action buttons
    	ImageButton endTurn = new ImageButton("Assets/Images/wooden_btn.png", 20, 470, 100, 60, g);
    	endTurn.draw();
    	buttonBounds.add(endTurn);
    	g.drawString("Next Turn", 40, 500);
    	
    	g.setFont(new Font("Dialog", Font.PLAIN, 12));
    	g.setColor(new Color(84, 45, 31));
    	g.drawString(this.currentPlayer.getMovesLeft() + "/3", 60, 515);
    	
    	g.setFont(reg);
    	g.setColor(regc);
    	
    	ImageButton quitBtn = new ImageButton("Assets/Images/wooden_btn.png", 20, 550, 100, 60, g);
    	quitBtn.draw();
    	buttonBounds.add(quitBtn);
    	g.drawString("Quit", 55, 585);
    	
    	// Draw player properties
    	//int x_offset = 532 - Math.round(this.currentPlayer.getProperties().size() * 110)/2;
    	int x_offset = 532 - Math.round(this.currentPlayer.getProperties().size() * 110)/2;
    	for(CardStack cs: this.currentPlayer.getProperties()) {
    		if(this.highlightPlayerProperties) {
    			cs.highlight(g, x_offset, 280);
        		CardButton cardBtn = new CardButton(cs, 100, 170, x_offset, 280, g);
    			this.highlightBounds.add(cardBtn);
    		} else {
    			cs.draw(g, x_offset, 280);
    		}
    		
    		x_offset += 110;
    	}
    	
    	// Draw other player properties
    	x_offset = 532 - Math.round(this.otherPlayer.getProperties().size() * 110)/2;
    	for(CardStack cs: this.otherPlayer.getProperties()) {
    		if(this.highlightEnemyProperties) {
    			cs.highlight(g, x_offset, 80);
        		CardButton cardBtn = new CardButton(cs, 100, 170, x_offset, 80, g);
    			this.highlightBounds.add(cardBtn);
    		} else {
    			cs.draw(g, x_offset, 80);
    		}
    		
    		x_offset += 110;
    	}
    	
    	// Draw player hand
    	x_offset = 532 - Math.round(this.currentPlayer.getHand().size() * 110)/2;
    	for(Card c: this.currentPlayer.getHand()) {
    		c.draw(g, x_offset, 470);
    		  
    		CardButton cardBtn = new CardButton(c, 100, 160, x_offset, 470, g);
    		this.cardBounds.add(cardBtn);
    		
    		x_offset += 110;
    	}
	}
	
	public void nextTurn() {
		this.currentPlayer.setMovesLeft(3);
		
		this.highlightEnemyProperties = false;
		this.highlightPlayerProperties = false;
		this.selectedWild = null;
		
		for(int i = 0; i < 2; i++) {
			if(this.currentPlayer.getHand().size() < 7) {
				this.currentPlayer.addHand(this.deck.pop());
			}
		}
		
		if(this.turn == 0) {
			this.turn = 1;
			this.currentPlayer = this.playerTwo;
			this.otherPlayer = this.playerOne;
		} else {
			this.turn = 0;
			this.currentPlayer = this.playerOne;
			this.otherPlayer = this.playerTwo;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if(SwingUtilities.isLeftMouseButton(me)) {
			// check action buttons first
			for(int i = 0; i < this.buttonBounds.size(); i++) {
				if(this.buttonBounds.get(i).wasClicked(me)) {
					if(this.buttons[i].equals("next")) {
						this.nextTurn();
					} else if(this.buttons[i].equals("quit")) {
						System.exit(0);
					}
				}
			}
		}
		
		// right click to cancel wildcard selection
		if(SwingUtilities.isRightMouseButton(me)) {
			if(this.highlightPlayerProperties) {
				this.highlightPlayerProperties = false;
				this.selectedWild = null;
			}
		}
		
		// check for wild card clicks first
		// TODO: if a player adds to a stack that's already full, or if they add a new card to the stack later,
		// remove the wild card and return it to their hand
		if(this.highlightPlayerProperties) {
			for(int i = 0; i < this.highlightBounds.size(); i++) {
				CardStack cs = this.highlightBounds.get(i).getCardStack();
				
				if(this.highlightBounds.get(i).wasLeftClicked(me)) {
					cs.addCard(this.selectedWild);
					this.currentPlayer.removeHand(this.selectedWild);
					this.highlightPlayerProperties = false;
					this.selectedWild = null;
					this.currentPlayer.setMovesLeft(this.currentPlayer.getMovesLeft() - 1);
				}
			}
		}
		
		// check to see if a card was clicked
		for(int i = 0; i < this.cardBounds.size(); i++) {
			CardButton cardBtn = this.cardBounds.get(i);
			Card card = cardBtn.getCard();
			
			if(cardBtn.wasLeftClicked(me)) {
				if(card.getType() != "wild") {
					card.use(this.currentPlayer, this.otherPlayer, this.deck);
					this.currentPlayer.setMovesLeft(this.currentPlayer.getMovesLeft() - 1);
				} else {
					this.highlightPlayerProperties = true;
					this.selectedWild = card;
				}
			} else if(cardBtn.wasRightClicked(me)) {
				card.bank(this.currentPlayer);
				this.currentPlayer.setMovesLeft(this.currentPlayer.getMovesLeft() - 1);
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
