package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

public class CardStack {

	private ArrayList<PropertyCard> stack;
	private Color color;
	

	public CardStack(Color color) {
		this.color = color;
		this.stack = new ArrayList<PropertyCard>();
	}
	
	public ArrayList<PropertyCard> getCards() {
		return this.stack;
	}
	
	public void addCard(PropertyCard c) {
		this.stack.add(c);
	}
	
	public void removeCard(Card c) {
		this.stack.remove(c);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Card pop() { 
		if(this.stack.size() > 0) {
			return this.stack.remove(this.stack.size() - 1);
		} else {
			return null;
		}
	}
	
	public void draw(Graphics2D g, int x, int y) {
		int y_offset = 0;
		int y_increase;
		
		if(this.stack.size() == 2) {
			y_increase = 10;
		} else {
			y_increase = 5;
		}
		
		for(Card c: this.stack) {
			c.draw(g, x, y + y_offset);
			y_offset += y_increase;
		}
	}
	
	public void highlight(Graphics2D g, int x, int y) {
		int y_offset = 0;
		int y_increase;
		
		if(this.stack.size() == 2) {
			y_increase = 10;
		} else {
			y_increase = 5;
		}
		
		for(Card c: this.stack) {
			c.draw(g, x, y + y_offset);
			y_offset += y_increase;
		}
		
		Color old = g.getColor();
		Stroke olds = g.getStroke();
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, 100, 160+y_increase*(this.stack.size()-1));
		g.setColor(old);
		g.setStroke(olds);
	}
	
	public boolean isFull() {
		return this.stack.size() == this.stack.get(0).numProperties();
	}
	
	
}
