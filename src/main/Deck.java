package main;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private String file;
	private ArrayList<Card> cards, used;
	//private Settings settings;
	
	public Deck(String file) {
		this.file = file;
		this.cards = new ArrayList<Card>();
		this.used = new ArrayList<Card>();
	}
	
	public void parseDeck() {
		try {
			BufferedReader file = new BufferedReader(new FileReader(this.file));
			String line;
			
			while((line = file.readLine()) != null) {
				if(!line.startsWith("#")) {
					String[] properties = line.split("\\t");
					
					String name;
					String type;
					String action;
					int value;
					Color color;
					Color color_sec;
					String[] prices;

					if(properties[1].equals("property")) {
						name = properties[0];
						type = properties[1];
						value = Integer.parseInt(properties[2]);
						
						if(!properties[3].equals("wild")) {
							String[] colors = properties[3].split(",");
							int color_r = Integer.parseInt(colors[0]);
							int color_g = Integer.parseInt(colors[1]);
							int color_b = Integer.parseInt(colors[2]);
							color = new Color(color_r, color_g, color_b);
						} else {
							color = Color.BLACK;
							type = "wild";
						}
						prices = new String[]{properties[4], properties[5], properties[6]};
						
						this.addCard(new PropertyCard(name, type, value, color, prices));
					} else if(properties[1].equals("action")) {
						name = properties[0];
						type = properties[1];
						value = Integer.parseInt(properties[2]);
						action = properties[3];
						
						if(action.equals("rent")) {
							String[] colors = properties[4].split(",");
							int color_r = Integer.parseInt(colors[0]);
							int color_g = Integer.parseInt(colors[1]);
							int color_b = Integer.parseInt(colors[2]);
							color = new Color(color_r, color_g, color_b);
							
							colors = properties[5].split(",");
							color_r = Integer.parseInt(colors[0]);
							color_g = Integer.parseInt(colors[1]);
							color_b = Integer.parseInt(colors[2]);
							color_sec = new Color(color_r, color_g, color_b);
							
							this.addCard(new RentCard(name, type, value, color, color_sec));
						} else {
							this.addCard(new ActionCard(name, type, value, action));	
						}
					} else if(properties[1].equals("money")) {
						name = properties[0];
						type = properties[1];
						value = Integer.parseInt(properties[2]);
						
						this.addCard(new MoneyCard(name, type, value));
					}
				}
			}
			
			file.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public void addUsed(Card card) {
		this.used.add(card);
	}
	
	public Card pop() {
		try {
			return this.cards.remove(0);
		} catch(Exception e) {
			for(Card c: this.used) {
				this.cards.add(c);
			}
			System.out.println("reset");
			this.used = new ArrayList<Card>();
			this.shuffle();
			return this.cards.remove(0);
		}
	}
	
	public ArrayList<Card> getDeck() {
		return this.cards;
	}
	
	public int getDeckSize() {
		return this.cards.size();
	}
	
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
}
