import java.util.ArrayList;

public class Player {
	private String country;
	private ArrayList<Card> hand;
	private ArrayList<Card> properties;
	private int treasury;
	private boolean block;
	private boolean double_rent;
	
	public Player() {
		this.country = "Undecided";
		this.hand = new ArrayList<Card>();
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String s) {
		this.country = s;
	}
		
	public int getTreasury() {
		return this.treasury;
	}
	
	public void addTreasury(int amount) {
		this.treasury += amount;
	}
	
	public void subTreasury(int amount) {
		this.treasury -= amount;
	}
	
	public ArrayList<Card> getHand() {
		return this.hand;
	}	
	
	public void addHand(Card card) {
		this.hand.add(card);
	}
	
	public void removeHand(Card card) {
		this.hand.remove(card);
	}
	
	public ArrayList<Card> getProperties() {
		return this.properties;
	}
	
	public void addProperty(Card card) {
		this.properties.add(card);
	}
	
	public void removeProperty(Card card) {
		this.properties.remove(card);
	}
	
	public boolean isBlocking() {
		return this.block;
	}
	
	public void setBlocked(boolean b) {
		this.block = b;
	}
	
	public boolean isDoubleRent() {
		return this.double_rent;
	}
	
	public void setDoubleRent(boolean b) {
		this.double_rent = b;
	}
	
}
