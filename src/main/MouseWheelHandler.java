package main;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelHandler implements MouseWheelListener {
	
	public int wheelMovement = 0;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() == 1) {
			GPanel.currentBlock--;
		} else if (e.getWheelRotation() == -1) {
			GPanel.currentBlock++;
		}
	}
}
