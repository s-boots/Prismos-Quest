package utilities;

import java.awt.event.KeyEvent;

import input.KeyboardInput;

public class KeyboardInputUtilities {
	
	public static boolean isUpPressed(KeyboardInput keyboardInput) {
		
		return (keyboardInput.isKeyDown(KeyEvent.VK_W) || keyboardInput.isKeyDown(KeyEvent.VK_UP));
		
	}
	
	public static boolean isDownPressed(KeyboardInput keyboardInput) {
		
		return (keyboardInput.isKeyDown(KeyEvent.VK_S) || keyboardInput.isKeyDown(KeyEvent.VK_DOWN));
		
	}
	
	public static boolean isLeftPressed(KeyboardInput keyboardInput) {
		
		return (keyboardInput.isKeyDown(KeyEvent.VK_A) || keyboardInput.isKeyDown(KeyEvent.VK_LEFT));
		
	}
	
	public static boolean isRightPressed(KeyboardInput keyboardInput) {
		
		return (keyboardInput.isKeyDown(KeyEvent.VK_D) || keyboardInput.isKeyDown(KeyEvent.VK_RIGHT));
		
	}
	
}