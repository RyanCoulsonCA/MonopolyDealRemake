package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class ActionCard extends Card {
	private String action;
	private static final int TAKEMONEY = 5;
	private static final int BIRTHDAY = 2;
	
	public ActionCard(String name, String type, int value, String action) {
		super(name, type, value);
		this.action = action;
	}
	
	public String getAction() { return this.action; }
	
	@Override
	public void draw(Graphics2D g, int x, int y) {
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();
		
		if(this.hovering) {
			// Draw physical card
			g.setColor(new Color(15, 15, 15));
			g.fillRect(x, y, 100, 160);
			
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2));
			g.drawRect(x, y, 100, 160);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", Font.PLAIN, 12));
			
			// Card info
			if(this.action.equals("draw")) {
				g.drawString("Draw two cards", x+8, y+40);
				g.drawString("from deck and", x+10, y+55);
				g.drawString("add to your hand", x+5, y+70);
			} else if(this.action.equals("steal1")) {
				g.drawString("Steal one card", x+11, y+40);
				g.drawString("from your", x+25, y+55);
				g.drawString("opponents hand", x+5, y+70);
			} else if(this.action.equals("trade")) {
				g.drawString("Select one card", x+8, y+40);
				g.drawString("from your hand", x+10, y+55);
				g.drawString("to swap with your", x+4, y+70);
				g.drawString("opponents hand", x+5, y+85);
			} else if(this.action.equals("steal3")) {
				g.drawString("Steal one card", x+10, y+40);
				g.drawString("set from your", x+13, y+55);
				g.drawString("opponents hand.", x+5, y+70);
			} else if(this.action.equals("takemoney")) {
				g.drawString("Steal $5M from", x+10, y+25);
				g.drawString("your opponent. If", x+5, y+40);
				g.drawString("they do not have", x+6, y+55);
				g.drawString("enough money,", x+8, y+70);
				g.drawString("take equivalent", x+8, y+85);
				g.drawString("in property value", x+5, y+100);
			} else if(this.action.equals("doublerent")) {
				g.drawString("Double the", x+20, y+40);
				g.drawString("payout of tariff", x+10, y+55);
				g.drawString("cards", x+33, y+70);
			} else if(this.action.equals("birthday")) {
				g.drawString("Gain $2M and", x+12, y+40);
				g.drawString("add one card", x+13, y+55);
				g.drawString("to your hand", x+15, y+70);
			} else if(this.action.equals("no")) {
				g.drawString("Block any", x+23, y+40);
				g.drawString("action card your", x+7, y+55);
				g.drawString("opponent uses", x+8, y+70);
				g.drawString("on you", x+30, y+85);
			} else {
				g.drawString("Click this card", x+10, y+40);
				g.drawString("to add it to", x+20, y+55);
				g.drawString("any property set", x+5, y+70);
			}
			
			g.drawString("Right-click to", x+13, y+140);
			g.drawString("bank", x+37, y+155);
		} else {
			// Draw physical card
			g.setColor(new Color(255, 220, 220));
			g.fillRect(x, y, 100, 160);
			
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2));
			g.drawRect(x, y, 100, 160);
			
			// Draw price
			g.drawString(Integer.toString(this.value) + "M", x+5, y+15);
	
			// Draw card name
			FontMetrics fm = g.getFontMetrics();
			
			g.setFont(new Font("Dialog", Font.PLAIN, 13));
			g.drawString(this.name, x + 52 - fm.stringWidth(this.name)/2, y + 75);
			
			// Draw card type
			g.setFont(new Font("Dialog", Font.PLAIN, 11));
			g.drawString("action card", x + 23, y + 150);
		}
		
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
	
	public void drawDisabled(Graphics2D g, int x, int y) {
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();
		
		// Draw physical card
		g.setColor(new Color(170, 170, 170));
		g.fillRect(x, y, 100, 160);
			
		g.setColor(new Color(200, 200, 200));
		g.setStroke(new BasicStroke(2));
		g.drawRect(x, y, 100, 160);
					
		// Draw price
		g.drawString(Integer.toString(this.value) + "M", x+5, y+15);
		
		// Draw card name
		FontMetrics fm = g.getFontMetrics();
				
		g.setFont(new Font("Dialog", Font.PLAIN, 13));
		g.drawString(this.name, x + 52 - fm.stringWidth(this.name)/2, y + 75);
					
		// Draw card type
		g.setFont(new Font("Dialog", Font.PLAIN, 11));
		g.drawString("action card", x + 23, y + 150);
		
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
	
	public void use(Player user, Player target, Deck deck) {
		deck.addUsed(this);
		
		if(this.action.equals("draw")) {
			for(int i = 0; i < 2; i++) {
				if(user.getHand().size() < 7) {
					user.addHand(deck.pop());
				}
			}
		} else if(this.action.equals("doublerent")) {
			user.setDoubleRent(true);
		} else if(this.action.equals("no")) {
			user.setBlocked();
		} else if(!target.isBlocking()) {
			if(this.action.equals("takemoney")) {
				int sentValue = 0;
				if(target.getTreasury() >= TAKEMONEY) {
					target.subTreasury(TAKEMONEY);
					user.addTreasury(TAKEMONEY);
				} else {
					user.addTreasury(target.getTreasury());
					sentValue += target.getTreasury();
					target.subTreasury(target.getTreasury());
					
					while(sentValue < TAKEMONEY) {
						PropertyCard c = target.findCheapest();
						
						if(c != null) {
							user.addProperty(c);
							target.removeProperty(c);
							sentValue += c.getValue();
						} else {
							break;
						}
					}
				}
			} else if(this.action.equals("birthday")) {
				int sentValue = 0;
				
				if(user.getHand().size() < 7) {
					user.addHand(deck.pop());
				}
				
				if(target.getTreasury() >= BIRTHDAY) {
					target.subTreasury(BIRTHDAY);
					user.addTreasury(BIRTHDAY);
				} else {
					user.addTreasury(target.getTreasury());
					sentValue += target.getTreasury();
					target.subTreasury(target.getTreasury());
					
					while(sentValue < BIRTHDAY) {
						PropertyCard c = target.findCheapest();
						
						if(c != null) {
							user.addProperty(c);
							target.removeProperty(c);
							sentValue += c.getValue();
						} else {
							break;
						}
					}
				}
			}
		}
		user.removeHand(this);
	}
	
	public void bank(Player user) {
		user.addTreasury(this.value);
		user.removeHand(this);
	}
}
