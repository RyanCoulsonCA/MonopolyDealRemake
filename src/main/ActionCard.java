package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class ActionCard extends Card {
	private String action;
	
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
			
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
	
	public void use(Player user, Player target, Deck deck) {
		if(this.action.equals("draw")) {
			for(int i = 0; i < 2; i++) {
				if(user.getHand().size() < 7) {
					user.addHand(deck.pop());
				}
			}
		} else if(this.action.equals("doublerent")) {
			user.setDoubleRent(true);
		} else if(this.action.equals("no")) {
			user.setBlocked(true);
		} else if(this.action.equals("takemoney")) {
			int sentValue = 0;
			if(target.getTreasury() >= 5) {
				target.subTreasury(5);
				user.addTreasury(5);
			} else {
				user.addTreasury(target.getTreasury());
				sentValue += target.getTreasury();
				target.subTreasury(target.getTreasury());
				
				while(sentValue < 5) {
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
		user.removeHand(this);
	}
	
	public void bank(Player user) {
		user.addTreasury(this.value);
		user.removeHand(this);
	}
}
