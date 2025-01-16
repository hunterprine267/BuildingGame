package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		GPanel.mouseX = e.getX();
		GPanel.mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GPanel.mouseX = e.getX();
		GPanel.mouseY = e.getY();
	}
}
