package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class CardStack {

	private ArrayList<Card> stack;
	private Color color;
	

	public CardStack(Color color) {
		this.color = color;
		this.stack = new ArrayList<Card>();
	}
	
	public void addCard(Card c) {
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
	
}
