package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PropertyCard extends Card {
	private Color color;
	private String[] prices; // the rent amount when owning one, two or three properties
	private BufferedImage wild;
	
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
	
	public int numProperties() {
		int count = 0;
		for(int i = 0; i < prices.length; i++) {
			if(!this.prices[i].equals("n/a")) {
				count++;
			}
		}
		return count;
	}
	
	public void draw(Graphics2D g, int x, int y) {
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();

		try {
			wild = ImageIO.read(new File("Assets/Images/wildcard_img.png"));		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		// Draw physical card
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 100, 160);
		
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawRect(x, y, 100, 160);
		
		// Draw property
		
		if(this.type != "wild") {
			g.setColor(this.color);
			g.fillRect(x+4, y + 5, 92, 30);	

			g.setColor(Color.BLACK);
			g.drawRect(x+4, y+5, 92, 30);
		} else {
			g.drawImage(wild, x+3, y+5, null);
		}
		// Draw card name
		g.setStroke(oldStroke);
		
		g.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString(this.name, x + 52 - fm.stringWidth(this.name)/2, y + 25);

		
		// Draw prices

		
		if(this.type != "wild") {
			int temp = y+50;
			for(int i = 0; i < this.prices.length; i++) {
				if(!this.prices[i].equals("n/a")) {
					g.drawRect(x+5, temp, 20, 20);
					g.drawString(Integer.toString(i+1), x+12, temp+15);
				
					if(i == this.prices.length-1 || this.prices[i+1].equals("n/a")) {
						g.setFont(new Font("Dialog", Font.PLAIN, 10));
						g.drawString("full set", x+32,temp+6);
					}
					
					g.setFont(new Font("Dialog", Font.PLAIN, 13));
					g.drawString("........", x+32, temp+10);
					g.setFont(new Font("Dialog", Font.PLAIN, 12));
					g.drawString(this.prices[i] + "M", x+67, temp+13);
					temp+= 25;
				}
			}
		} else {
			g.setFont(new Font("Dialog", Font.PLAIN, 11));
			g.drawString("This card may", x+17, y+70);
			g.drawString("be used on any", x+14, y+82);
			g.drawString("set of properties.", x+11, y+94);
		}
		

		// Draw card type
		g.setFont(new Font("Dialog", Font.PLAIN, 11));
		g.drawString("worth " + this.value + "M", x + 30, y + 150);
			
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
	
	public void use(Player user, Player target, Deck deck) {
		if(this.type != "wild") {
			user.addProperty(this);
			user.removeHand(this);
		}
	}
	
	public void bank(Player user) {
		user.addTreasury(this.value);
		user.removeHand(this);
	}
	
	public boolean equals(PropertyCard other) {
		if(this.color.getBlue() == other.color.getBlue() &&
				this.color.getRed() == other.color.getRed() &&
				this.color.getGreen() == other.color.getGreen()) {
			return true;
		}
		return false;
	}
}
