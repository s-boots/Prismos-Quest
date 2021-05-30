package input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
	
	// This Class was Made by TerranceN
	
	private boolean[] keys = new boolean[256];
	
	public KeyboardInput(Component component) {
		
		component.addKeyListener(this);
		
	}
	
	public boolean isKeyDown(int keyCode) {
		
		if (keyCode >= 0 && keyCode < 256) {
			
			return keys[keyCode];
			
		}
		
		return false;
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode >= 0 && keyCode < 256) {
			
			keys[keyCode] = true;
			
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode >= 0 && keyCode < 256) {
			
			keys[keyCode] = false;
			
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
}