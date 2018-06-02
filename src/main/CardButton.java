package main;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class CardButton {
	private int width, height, xpos, ypos;
	private Card card;
	
	public CardButton(Card c, int w, int h, int xpos, int ypos, Graphics2D g) {
		this.card = c;
		this.width = w;
		this.height = h;
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	public Card getCard() {
		return this.card;
	}
	
	public boolean wasLeftClicked(MouseEvent me) {
		if(!SwingUtilities.isLeftMouseButton(me)) {
			return false;
		}
		
		if(me.getX() >= xpos && me.getX() <= (xpos + width)) {
			if(me.getY() >= ypos && me.getY() <= (ypos+height)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean wasRightClicked(MouseEvent me) {
		if(!SwingUtilities.isRightMouseButton(me)) {
			return false;
		}
		
		if(me.getX() >= xpos && me.getX() <= (xpos + width)) {
			if(me.getY() >= ypos && me.getY() <= (ypos+height)) {
				return true;
			}
		}
		return false;
	}
}
