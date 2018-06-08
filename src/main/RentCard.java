package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class RentCard extends Card {

	private Color color_primary;
	private Color color_secondary;
	
	public RentCard(String name, String type, int value, Color pri, Color sec) {
		super(name, type, value);
		this.color_primary = pri;
		this.color_secondary = sec;
	}
	
	public Color getPrimaryColor() {
		return this.color_primary;
	}
	
	public Color getSecondaryColor() {
		return this.color_secondary;
	}

	public void draw(Graphics2D g, int x, int y) {
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();
		
		
		// Draw physical card
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 100, 160);
		
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawRect(x, y, 100, 160);

		
		// Draw card name
		g.setColor(this.color_primary);
		g.fillRect(x+20, y+20, 60, 27);
		
		g.setColor(this.color_secondary);
		g.fillRect(x+20, y+45, 60, 27);
		
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(12));
		g.drawOval(x+16,y+11, 68, 69);
		
		g.fillOval(x+30, y+26, 40,40);
		
		g.setColor(Color.BLACK);		
		g.setFont(new Font("Dialog", Font.BOLD, 12));
		g.drawString("Rent", x+37, y+50);
		
		// Card text
		g.setFont(new Font("Dialog", Font.PLAIN, 9));
		g.drawString("Your opponent must", x+7, y+90);
		g.drawString("pay you rent for", x+17, y+100);
		g.drawString("properties you own", x+12, y+110);
		g.drawString("in these colours.", x+18, y+120);

		g.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		// Draw price
		g.drawString(Integer.toString(this.value) + "M", x+5, y+15);
		
		
		// Draw card type
		g.setFont(new Font("Dialog", Font.PLAIN, 11));
		g.drawString("rent card", x + 30, y + 150);
		
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
	
	public void use(Player user, Player target, Deck d) {	
		int total = 0;
		
		for(CardStack cs: user.getProperties()) {
			String[] prices = cs.getPrices();
			if(cs.getColor().equals(this.getPrimaryColor())) {
				if(prices[cs.getCards().size()-1].equals("n/a")) {
					total += 0;
				} else {
					total += Integer.parseInt(prices[cs.getCards().size() - 1]);
				}
			}
		}
		
		for(CardStack cs: user.getProperties()) {
			String[] prices = cs.getPrices();
			if(cs.getColor().equals(this.getSecondaryColor())) {
				if(prices[cs.getCards().size()-1].equals("n/a")) {
					total += 0;
				} else {
					total += Integer.parseInt(prices[cs.getCards().size() - 1]);
				}
			}
		}
		
		if(user.isDoubleRent()) {
			total += total;
			user.setDoubleRent(false);
		}
		
		//System.out.println("Charging player " + total);
		
		
		if(!target.isBlocking()) {
			if(target.getTreasury() >= total) {
				target.subTreasury(total);
				user.addTreasury(total);
			} else {
				user.addTreasury(target.getTreasury());
				total -= target.getTreasury();
				target.subTreasury(target.getTreasury());
				
				while(total > 0) {
					PropertyCard c = target.findCheapest();
					
					if(c != null) {
						user.addProperty(c);
						target.removeProperty(c);
						total -= c.getValue();
					} else {
						break;
					}
				}
			}
		} else {
			target.setBlocked(false);
		}
		
		user.removeHand(this);
	}
	
	public void bank(Player user) {
		user.addTreasury(this.value);
		user.removeHand(this);
	}
}
