package main;
import java.awt.Color;

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

}
