package Maps;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;

public abstract class ScreenState implements MouseListener {
	protected StateManager sm;
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
}
