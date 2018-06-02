package main;
import java.util.ArrayList;

public class Player {
	private String country;
	private String image;
	private ArrayList<Card> hand;
	private ArrayList<Card> properties;
	private int treasury;
	private int moves;
	private boolean block;
	private boolean double_rent;
	
	public Player() {
		this.country = "Undecided";
		this.treasury = 0;
		this.moves = 3;
		this.hand = new ArrayList<Card>();
		this.properties = new ArrayList<Card>();
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
	
	public int getMovesLeft() {
		return this.moves;
	}
	
	public void setMovesLeft(int left) {
		this.moves = left;
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
	
	public void setImage(String img) {
		this.image = img;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public Card findCheapest() {
		/* Return the cheapest property the player has placed on the board, otherwise
		 * if the player has no properties, return null.
		 */
		
		if(this.properties.size() == 0) {
			return null;
		}
		
		Card lowest = this.properties.get(0);
		int lowest_val = this.properties.get(0).getValue();
		
		for(Card c: this.properties) {
			if(c.getValue() < lowest_val) {
				lowest = c;
				lowest_val = c.getValue();
			}
		}
		
		return lowest;
	}
	
}
