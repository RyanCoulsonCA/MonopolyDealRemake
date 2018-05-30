import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Deck {

	private String file;
	private ArrayList<Card> cards;
	//private Settings settings;
	
	public Deck(String file) {
		this.file = file;
		this.cards = new ArrayList<Card>();
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
							
							System.out.println(color + " " + color_sec);
							
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
	
	public ArrayList<Card> getDeck() {
		return this.cards;
	}
	
	public int getDeckSize() {
		return this.cards.size();
	}
	
	public static void main(String[] args) {
		Deck deck = new Deck("Assets/test.txt");
		deck.parseDeck();
		
		System.out.println("Deck size: " + deck.getDeckSize());
	}
}
