package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GPanel.mouseDown = true;
		GPanel.mouseX = e.getX();
		GPanel.mouseY = e.getY();
		GPanel.mouseButton = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		GPanel.mouseDown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
