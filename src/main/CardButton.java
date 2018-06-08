package main;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class CardButton {
	private int width, height, xpos, ypos;
	private Card card;
	private CardStack cs;
	
	public CardButton(Card c, int w, int h, int xpos, int ypos, Graphics2D g) {
		this.card = c;
		this.width = w;
		this.height = h;
		this.xpos = xpos;
		this.ypos = ypos;
		
		//g.drawRect(xpos, ypos, w, h);
	}

	public CardButton(CardStack cs, int w, int h, int xpos, int ypos, Graphics2D g) {
		this.cs = cs;
		this.width = w;
		this.height = h;
		this.xpos = xpos;
		this.ypos = ypos;
		
		g.drawRect(xpos, ypos, w, h);
	}
	
	public Card getCard() {
		return this.card;
	}
	
	public CardStack getCardStack() {
		return this.cs;
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
	
	public boolean isHovering(MouseEvent me) {
		if(me.getX() >= xpos && me.getX() <= (xpos + width)) {
			if(me.getY() >= ypos && me.getY() <= (ypos+height)) {
				return true;
			}
		}
		return false;
	}
}
