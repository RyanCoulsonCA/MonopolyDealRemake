import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageButton {
	private BufferedImage image;
	private int width, height, xpos, ypos;
	private Graphics2D g;
	private String text;
	
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
	
	public void draw() {
		g.setFont(new Font("Default", Font.BOLD, 23));
		g.setStroke(new BasicStroke(0.1f));
		g.setColor(Color.LIGHT_GRAY);
		
		g.drawImage(image, xpos, ypos, null);
		
		if(text != null) {
			g.drawString(text, xpos + width/2 - (text.length() * 6), ypos + 44);
		}
	}
	
	public boolean wasClicked(MouseEvent me) {	
		if(me.getX() >= xpos && me.getX() <= (xpos + width)) {
			if(me.getY() >= ypos && me.getY() <= (ypos+height)) {
				return true;
			}
		}
		return false;
	}
}
