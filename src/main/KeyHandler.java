package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public final int leftKey = KeyEvent.VK_L;
	public final int rightKey = KeyEvent.VK_QUOTE;
	public final int upKey = KeyEvent.VK_P;
	public final int downKey = KeyEvent.VK_SEMICOLON;
	public final int jumpKey = KeyEvent.VK_SPACE;
	public final int saveKey = KeyEvent.VK_S;
	public final int blockLeftKey = KeyEvent.VK_OPEN_BRACKET;
	public final int blockRightKey = KeyEvent.VK_CLOSE_BRACKET;
	public boolean leftKeyPressed = false;
	public boolean rightKeyPressed = false;
	public boolean upKeyPressed = false;
	public boolean downKeyPressed = false;
	public boolean jumpKeyPressed = false;
	public boolean saveKeyPressed = false;
	public boolean blockLeftKeyPressed = false;
	public boolean blockRightKeyPressed = false;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getExtendedKeyCode()) {
		case leftKey:
			leftKeyPressed = true;
			break;
		case rightKey:
			rightKeyPressed = true;
			break;
		case upKey:
			upKeyPressed = true;
			break;
		case downKey:
			downKeyPressed = true;
			break;
		case jumpKey:
			jumpKeyPressed = true;
			break;
		case saveKey:
			saveKeyPressed = true;
			break;
		case blockLeftKey:
			blockLeftKeyPressed = true;
			break;
		case blockRightKey:
			blockRightKeyPressed = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getExtendedKeyCode()) {
		case leftKey:
			leftKeyPressed = false;
			break;
		case rightKey:
			rightKeyPressed = false;
			break;
		case upKey:
			upKeyPressed = false;
			break;
		case downKey:
			downKeyPressed = false;
			break;
		case jumpKey:
			jumpKeyPressed = false;
			break;
		case saveKey:
			saveKeyPressed = false;
			break;
		case blockLeftKey:
			blockLeftKeyPressed = false;
			break;
		case blockRightKey:
			blockRightKeyPressed = false;
			break;
		}
	}
}
