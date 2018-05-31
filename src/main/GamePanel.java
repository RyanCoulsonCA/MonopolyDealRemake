package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Maps.StateManager;

public class GamePanel extends JPanel implements MouseListener {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 650;
	public static final float VERSION = 0.1f;
	
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
    	System.out.println("paint");
    	Graphics2D g2 = (Graphics2D) g;
    	Font f = new Font("Default", Font.PLAIN, 14);
    	g.setFont(f);
    	g.setColor(Color.WHITE);
    	
    	sm.draw(g2);
    	g2.drawString("Current Version " + VERSION, 10,640);
    }

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		sm.mouseClicked(me);
		this.repaint();
		
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
