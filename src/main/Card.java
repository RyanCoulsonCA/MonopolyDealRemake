package main;
public class Card {

	private String name;
	private String type;
	private int value;
	
	public Card(String name, String type, int value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public String getName() { return this.name; }
	public String getType() { return this.type; }
	public int getValue() { return this.value; }
}
