package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
		g.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		if(this.name.length() <= 10) {
			g.drawString(this.name, x + 62 - this.name.length() * 4, y + 75);
		} else if(this.name.length() <= 12){
			g.drawString(this.name, x + 54 - this.name.length() * 4, y + 75);
		} else {
			g.drawString(this.name, x + 70 - this.name.length() * 5, y + 75);
		}
		// Draw card type
		g.setFont(new Font("Dialog", Font.PLAIN, 11));
		g.drawString("action card", x + 23, y + 150);
			
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
}
