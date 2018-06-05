package main;
import java.awt.Color;
import java.util.ArrayList;

public class Player {
	private String country;
	private String image;
	private ArrayList<Card> hand;
	private ArrayList<CardStack> properties;
	private int treasury;
	private int moves;
	private boolean block;
	private boolean double_rent;
	
	public Player() {
		this.country = "Undecided";
		this.treasury = 0;
		this.moves = 3;
		this.hand = new ArrayList<Card>();
		this.properties = new ArrayList<CardStack>();
		this.block = false;
		this.double_rent = false;
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
	
	public ArrayList<CardStack> getProperties() {
		return this.properties;
	}
	
	public void addProperty(PropertyCard card) {
		boolean exists = false;
		
		for(CardStack cs: this.properties) {
			PropertyCard wild = null;
		
			if(cs.getColor().equals(card.getColor())) {
				for(PropertyCard c: cs.getCards()) {
					if(c.getType().equals("wild") && cs.isFull()) {
						wild = c;
					}
				}
				exists = true;
				
				if(wild != null) {
					this.hand.add(wild);
					wild.setColor(Color.BLACK);
					wild.setPrices(null);
					cs.removeCard(wild);
					cs.addCard(card);
				} else {
					cs.addCard(card);
				}
			}
		}
		
		if(!exists) {
			CardStack newStack = new CardStack(card.getColor());
			newStack.setPrices(card.getPrices());
			newStack.addCard(card);
			this.properties.add(newStack);
		}
	}
	
	public void removeProperty(PropertyCard card) {
		CardStack toRemove = null;
		
		for(CardStack cs: this.properties) {
			System.out.println(cs + " " + card);
			if(cs.getColor().equals(card.getColor())) {
				cs.removeCard(card);
				
				if(cs.getCards().size() == 0) {
					toRemove = cs;
				}
			}
		}
		
		if(toRemove != null) {
			this.properties.remove(toRemove);
			toRemove = null;
		}
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
	
	public PropertyCard findCheapest() {
		/* Return the cheapest property the player has placed on the board, otherwise
		 * if the player has no properties, return null.
		 */
		
		
		if(this.properties.size() == 0) {
			System.out.println("returning null");
			return null;
		}
		
		
		PropertyCard lowestCard = this.properties.get(0).getLowest();
		int lowest = lowestCard.getValue();
		
		for(CardStack c: this.properties) {
			if(c.getLowest().getValue() < lowest) {
				lowestCard = c.getLowest();
				lowest = c.getLowest().getValue();
			}
		}
		
		return lowestCard;
	}
	
}
