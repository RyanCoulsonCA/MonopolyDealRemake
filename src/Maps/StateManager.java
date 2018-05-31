package Maps;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.Player;

public class StateManager {
	private ArrayList<ScreenState> states;
	private int currentState;
	
	public static final int MENU = 0;
	public static final int SELECT = 1;
	public static final int GAME = 2;
	
	public Player playerOne;
	public Player playerTwo;
	
	public StateManager() {
		this.states = new ArrayList<ScreenState>();
		this.currentState = MENU;
		
		this.playerOne = new Player();
		this.playerTwo = new Player();
		
		this.states.add(new MainMenu(this));
		this.states.add(new PlayerSelect(this));
		this.states.add(new GameState(this));
	}
	
	public Player getPlayerOne() {
		return this.playerOne;
	}
	
	public Player getPlayerTwo() {
		return this.playerTwo;
	}
	
	public void reset() {
		this.states = new ArrayList<ScreenState>();
		this.currentState = MENU;
		
		this.playerOne = new Player();
		this.playerTwo = new Player();
		
		this.states.add(new MainMenu(this));
		this.states.add(new PlayerSelect(this));
		this.states.add(new GameState(this));
	}
	
	public void setState(int state) {
		this.currentState = state;
		System.out.println("Set state to " + state);
	}
	
	public int getState() {
		return this.currentState;
	}
	
	public void draw(Graphics2D g) {
		states.get(this.currentState).draw(g);
	}
	
	public void mouseClicked(MouseEvent me) {
		states.get(this.currentState).mouseClicked(me);
	}

	public void mouseEntered(MouseEvent me) {
		states.get(this.currentState).mouseEntered(me);
	}

	public void mouseExited(MouseEvent me) {
		states.get(this.currentState).mouseExited(me);
	}

	public void mousePressed(MouseEvent me) {
		states.get(this.currentState).mousePressed(me);
	}

	public void mouseReleased(MouseEvent me) {
		states.get(this.currentState).mouseReleased(me);
	}
}
