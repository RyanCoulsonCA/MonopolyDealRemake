package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Maps.StateManager;

public class GamePanel extends JPanel implements MouseListener {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 650;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StateManager sm;

	public GamePanel() {
		super();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		addMouseListener(this);
		requestFocus();
		
		this.sm = new StateManager();
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	sm.draw(g2);
    }

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		sm.mouseClicked(me);
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub
		sm.mouseEntered(me);
	}

	@Override
	public void mouseExited(MouseEvent me) {
		// TODO Auto-generated method stub
		sm.mouseExited(me);
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		sm.mousePressed(me);
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		sm.mouseReleased(me);
	}
	
}
