package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class ImageButton {
	private BufferedImage image;
	private int width, height, xpos, ypos;
	private Graphics2D g;
	private String text;
	private boolean enabled;
	
	public ImageButton(String image, int xpos, int ypos, int width, int height, Graphics2D g) {
		try {
			this.image = ImageIO.read(new File(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.width = width;
		this.height = height;
		this.xpos = xpos;
		this.ypos = ypos;
		this.g = g;
		this.enabled = true;
	}
	
	public ImageButton(String image, String text, int xpos, int ypos, int width, int height, Graphics2D g) {
		try {
			this.image = ImageIO.read(new File(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.width = width;
		this.height = height;
		this.xpos = xpos;
		this.ypos = ypos;
		this.text = text;
		this.g = g;
		this.enabled = true;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getXPos() {
		return this.xpos;
	}
	
	public int getYPos() {
		return this.ypos;
	}
	
	public void setEnabled(boolean e) {
		this.enabled = e;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void draw() {
		Font old = g.getFont();
		Color oldColor = g.getColor();
		
		Rectangle rect = new Rectangle(xpos, ypos, width, height);
		g.draw(rect);
		
		g.setFont(new Font("Default", Font.BOLD, 23));
		g.setStroke(new BasicStroke(0.1f));
		g.setColor(Color.LIGHT_GRAY);
		
		g.drawImage(image, xpos, ypos, null);
		
		if(text != null) {
			g.drawString(text, xpos + width/2 - (text.length() * 6), ypos + 44);
		}
		
		g.setFont(old);
		g.setColor(oldColor);
	}
	
	public boolean wasClicked(MouseEvent me) {
		if(!this.enabled || !SwingUtilities.isLeftMouseButton(me)) {
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
