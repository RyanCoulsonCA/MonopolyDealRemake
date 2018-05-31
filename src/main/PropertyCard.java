package main;
import java.awt.Color;

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
}
