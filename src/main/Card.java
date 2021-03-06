package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Card {

	protected String name;
	protected String type;
	protected int value;
	protected boolean hovering;
	
	public Card(String name, String type, int value) {
		this.name = name;
		this.type = type;
		this.value = value;
		this.hovering = false;
	}

	public String getName() { return this.name; }
	public String getType() { return this.type; }
	public int getValue() { return this.value; }
	public void draw(Graphics2D g, int x, int y) { 
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		Stroke oldStroke = g.getStroke();
		
		// Draw physical card
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 80, 128);
		
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawRect(x, y, 80, 128);
		
		g.setColor(oldColor);
		g.setFont(oldFont);
		g.setStroke(oldStroke);
	}
	public void use(Player user, Player target, Deck deck) { }
	public void bank(Player user) { }
	public void setHover(boolean b) {
		this.hovering = b;
	}
	public void hover() { }
}
