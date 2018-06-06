package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Notification {

	private Graphics2D g;
	private int width, height;
	private int xpos, ypos;
	private BufferedImage arrow;
	
	public Notification(Graphics2D g, String text, int xpos, int ypos, int width, int height) {
		this.g= g;
		this.width = width;
		this.height = height;
		this.xpos = xpos;
		this.ypos = ypos;
		
		try {
			arrow = ImageIO.read(new File("Assets/Images/notificationArrow.png"));		
		} catch(Exception e) {
			e.printStackTrace();
		}

		Color old = g.getColor();
		FontMetrics fm = g.getFontMetrics();
		
		g.setColor(new Color(254, 254, 221));
		g.fillRect(xpos, ypos, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(xpos, ypos, width, height);
		g.drawImage(arrow, xpos+10, ypos+height,null);
		g.drawString(text, xpos+5, ypos+5+(height/2));
		g.setColor(old);
		
	}
	
}
