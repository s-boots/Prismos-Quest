package objects;

import java.awt.event.KeyEvent;

import graphics.AnimationCycle;

import gui.HealthBarOverlay;

import input.KeyboardInput;
import input.MouseInput;

import main.Game;

import utilities.KeyboardInputUtilities;

public class PlayerObject extends Object {
	
	public String name;
	public int    health;
	
	public KeyboardInput keyboardInput;
	public MouseInput    mouseInput;
	
	public HealthBarOverlay healthBarOverlay;
	
	public boolean isKeyboardInputMovementEnabled = true;
	public boolean isKeyboardInputThreadRunning   = false;
	
	public PlayerObject(String name, int health, AnimationCycle animationCycle, int[] size, int x, int y, KeyboardInput keyboardInput, MouseInput mouseInput) {
		
		super(animationCycle, size, x, y);
		
		this.name   = name;
		this.health = health;
		
		this.keyboardInput = keyboardInput;
		this.mouseInput    = mouseInput;
		
	}
	
	public void addHealth(int amount) {
		
		this.health += amount;
		
		healthBarOverlay.addAmount(amount);
		
	}
	
	public void subtractHealth(int amount) {
		
		this.health -= amount;
		
		healthBarOverlay.subtractAmount(amount);
		
	}
	
	public void setKeyboardInputThread() {
		
		Thread keybaordInputMovementThread = new Thread(() -> {
			
			while (this.isKeyboardInputThreadRunning) {
				
				if (this.keyboardInput.isKeyDown(KeyEvent.VK_ESCAPE)) {
					
					Game.isRunning = false;
					
				}
				
//				if (this.isKeyboardInputMovementEnabled) {
					
					if (KeyboardInputUtilities.isUpPressed(this.keyboardInput)) {
						
						this.y -= this.movementAmount;
						
					}
					
					if (KeyboardInputUtilities.isDownPressed(this.keyboardInput)) {
						
						this.y += this.movementAmount;
						
					}
					
					if (KeyboardInputUtilities.isLeftPressed(this.keyboardInput)) {
						
						this.x -= this.movementAmount;
						
					}
					
					if (KeyboardInputUtilities.isRightPressed(this.keyboardInput)) {
						
						this.x += this.movementAmount;
						
					}
					
//				}
				
			}
			
		});
		
		keybaordInputMovementThread.start();
		
	}
	
	public void startKeyboardInputThread() {
		
		this.isKeyboardInputThreadRunning = true;
		
	}
	
	public void stopKeyboardInputThread() {
		
		this.isKeyboardInputThreadRunning = false;
		
	}
	
	public void checkForKeyboardInput() {
		
		if (this.keyboardInput.isKeyDown(KeyEvent.VK_ESCAPE)) {
			
			Game.isRunning = false;
			
		}
		
		if (this.isKeyboardInputMovementEnabled) {
			
			boolean isUpPressed    = KeyboardInputUtilities.isUpPressed(this.keyboardInput);
			boolean isDownPressed  = KeyboardInputUtilities.isDownPressed(this.keyboardInput);
			boolean isLeftPressed  = KeyboardInputUtilities.isLeftPressed(this.keyboardInput);
			boolean isRightPressed = KeyboardInputUtilities.isRightPressed(this.keyboardInput);
			
//			this.isCurrentlyMoving = (isUpPressed || isDownPressed || isLeftPressed || isRightPressed);
			
			if (isUpPressed) {
				
				this.y -= this.movementAmount;
				
			}
			
			if (isDownPressed) {
				
				this.y += this.movementAmount;
				
			}
			
			if (isLeftPressed) {
				
				this.x -= this.movementAmount;
				
			}
			
			if (isRightPressed) {
				
				this.x += this.movementAmount;
				
			}
			
		}
		
	}
	
	public void checkForMouseInput() {
		
		if (this.mouseInput.isLeftButtonClicked()) {
			
			//
			
		}
		
	}
	
	@Override
	public void setAnimationThread() {
		
		Thread animationThread = new Thread(() -> {
			
			int cachedDirection = 3;
			
			while (this.isAnimationThreadEnabled) {
				
//				while (this.isCurrentlyMoving) {
					
					if (KeyboardInputUtilities.isLeftPressed(this.keyboardInput)) {
						
						if (!KeyboardInputUtilities.isRightPressed(this.keyboardInput)) {
							
							animate(2);
							
							cachedDirection = 2;
							
						}
						
					} else if (KeyboardInputUtilities.isRightPressed(this.keyboardInput)) {
						
						if (!KeyboardInputUtilities.isLeftPressed(this.keyboardInput)) {
							
							animate(3);
							
							cachedDirection = 3;
							
						}
						
						// This Else-If Preservers Facing Left/Right When Moving Diagonally
						
					} else if (KeyboardInputUtilities.isUpPressed(this.keyboardInput)) {
						
						if (!KeyboardInputUtilities.isDownPressed(this.keyboardInput)) {
							
							animate(0);
							
							cachedDirection = 0;
							
						}
						
					} else if (KeyboardInputUtilities.isDownPressed(this.keyboardInput)) {
						
						if (!KeyboardInputUtilities.isUpPressed(this.keyboardInput)) {
							
							animate(1);
							
							cachedDirection = 1;
							
						}
						
					} else {
						
						// Neutral Sprite Indexes (Up, Down, Left, Right): 0, 8, 16, 24
						
						this.sprite = this.animationCycle.getSprite(cachedDirection == 0 ? cachedDirection : (cachedDirection * 8));
						
					}
					
					try {
						
						Thread.sleep(this.animationCycle.delay);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
						
					}
					
//				}
				
			}
			
		});
		
		animationThread.start();
		
	}
	
}