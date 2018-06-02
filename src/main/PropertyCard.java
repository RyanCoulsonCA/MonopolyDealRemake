package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class PropertyCard extends Card {
	private Color color;
	private String[] prices; // the rent amount when owning one, two or three properties
	
	public PropertyCard(String name, String type, int value, Color color, String[] prices) {
		super(name, type, value);
		this.color = color;
		this.prices = prices;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String[] getPrices() {
		return this.prices;
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
		
		// Draw property
		g.setColor(this.color);
		g.fillRect(x+4, y + 5, 92, 30);
		
		g.setColor(Color.BLACK);
		g.drawRect(x+4, y+5, 92, 30);
		
		// Draw card name
		g.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		if(this.name.length() <= 10) {
			g.drawString(this.name, x + 58 - this.name.length() * 4, y + 25);
		} else {
			g.drawString(this.name, x + 54 - this.name.length() * 4, y + 75);
		}
		
		// Draw prices
		g.setStroke(oldStroke);
		
		int temp = y+50;
		for(int i = 0; i < this.prices.length; i++) {
			g.drawRect(x+5, temp, 20, 20);
			g.drawString(Integer.toString(i+1), x+12, temp+15);
			
			if(i == this.prices.length-1) {
				g.setFont(new Font("Dialog", Font.PLAIN, 10));
				g.drawString("full set", x+32,temp+6);
			}
			
			g.setFont(new Font("Dialog", Font.PLAIN, 13));
			g.drawString("........", x+32, temp+10);
			g.drawString(this.prices[i] + "M", x+67, temp+13);
			temp+= 25;
		}
		

		// Draw card type
		g.setFont(new Font("Dialog", Font.PLAIN, 11));
		g.drawString("worth " + this.value + "M", x + 30, y + 150);
			
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
}
