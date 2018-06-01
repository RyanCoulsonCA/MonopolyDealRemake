package main;

import java.awt.Graphics2D;

public class Card {

	protected String name;
	protected String type;
	protected int value;
	
	public Card(String name, String type, int value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() { return this.name; }
	public String getType() { return this.type; }
	public int getValue() { return this.value; }
	public void draw(Graphics2D g, int x, int y) { };
}
