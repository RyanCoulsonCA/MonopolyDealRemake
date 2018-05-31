package main;
public class ActionCard extends Card {
	private String action;
	
	public ActionCard(String name, String type, int value, String action) {
		super(name, type, value);
		this.action = action;
	}
	
	public String getAction() { return this.action; }
}
