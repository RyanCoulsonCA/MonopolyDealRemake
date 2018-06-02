package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class MoneyCard extends Card {

	public MoneyCard(String name, String type, int value) {
		super(name, type, value);
	}

	@Override
	public void draw(Graphics2D g, int x, int y) {
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();
		
		
		// Draw physical card
		g.setColor(new Color(210, 235, 255));
		g.fillRect(x, y, 100, 160);
		
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawRect(x, y, 100, 160);
		
		// Draw price
		g.drawString(Integer.toString(this.value) + "M", x+5, y+15);

		// Draw card name
		g.setFont(new Font("Dialog", Font.PLAIN, 13));
		FontMetrics fm = g.getFontMetrics();

		g.drawString(this.name, x + 50 - (fm.stringWidth(this.name))/2, y + 75);
		
		// Draw card type
		g.setFont(new Font("Dialog", Font.PLAIN, 11));
		g.drawString("money card", x + 23, y + 150);
			
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
	
	@Override
	public void use(Player user, Player target) {
		user.addTreasury(this.value);
		user.removeHand(this);	
	}
	
	@Override
	public void bank(Player user) {
		user.addTreasury(this.value);
		user.removeHand(this);
	}
}
