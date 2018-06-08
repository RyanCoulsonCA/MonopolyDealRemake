package Maps;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class ScreenState implements MouseListener, MouseMotionListener {
	protected StateManager sm;
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
}
