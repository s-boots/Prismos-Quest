package objects;

import java.awt.Image;
import java.awt.event.KeyEvent;

import graphics.AnimationCycle;

import input.KeyboardInputHandler;
import input.MouseInputHandler;

import main.Game;

public class PlayerObject extends Object {
	
	public String name;
	
	public KeyboardInputHandler keyboardInput;
	public MouseInputHandler    mouseInput;
	
	public boolean isKeyboardInputMovementEnabled = true;
	
//	public boolean isKeyboardInputThreadRunning = false;
	
//	private void abstractInititalize() {
//		
//		setKeyboardInputThread();
//		startKeyboardInputThread();
//		
//	}
	
	public PlayerObject(String name, Image sprite, int[] size, int x, int y, KeyboardInputHandler keyboardInput, MouseInputHandler mouseInput) {
		
		super(sprite, size, x, y);
		
		this.name = name;
		
		this.keyboardInput = keyboardInput;
		this.mouseInput    = mouseInput;
		
//		abstractInititalize();
		
	}
	
	public PlayerObject(String name, AnimationCycle animationCycle, int[] size, int x, int y, KeyboardInputHandler keyboardInput, MouseInputHandler mouseInput) {
		
		super(animationCycle, size, x, y);
		
		this.name = name;
		
		this.keyboardInput = keyboardInput;
		this.mouseInput    = mouseInput;
		
//		abstractInititalize();
		
	}
	
//	public void setKeyboardInputThread() {
//		
//		Thread keybaordInputMovementThread = new Thread(() -> {
//			
//			while (true) {
//				
//				if (Game.keyboardInput.isKeyDown(KeyEvent.VK_ESCAPE)) {
//					
//					Game.isRunning = false;
//					
//				}
//				
////				if (this.isKeyboardInputMovementEnabled) {
//					
//					if (Game.keyboardInput.isKeyDown(KeyEvent.VK_UP) || Game.keyboardInput.isKeyDown(KeyEvent.VK_W)) {
//						
//						this.y -= object.movementAmount;
//						
//					}
//					
//					if (Game.keyboardInput.isKeyDown(KeyEvent.VK_DOWN) || Game.keyboardInput.isKeyDown(KeyEvent.VK_S)) {
//						
//						this.y += object.movementAmount;
//						
//					}
//					
//					if (Game.keyboardInput.isKeyDown(KeyEvent.VK_LEFT) || Game.keyboardInput.isKeyDown(KeyEvent.VK_A)) {
//						
//						this.x -= object.movementAmount;
//						
//					}
//					
//					if (Game.keyboardInput.isKeyDown(KeyEvent.VK_RIGHT) || Game.keyboardInput.isKeyDown(KeyEvent.VK_D)) {
//						
//						this.x += object.movementAmount;
//						
//					}
//					
////				}
//				
//			}
//			
//		});
//		
//		keybaordInputMovementThread.start();
//		
//	}
//	
//	public void startKeyboardInputThread() {
//		
//		this.isKeyboardInputThreadRunning = true;
//		
//	}
//	
//	public void stopKeyboardInputThread() {
//		
//		this.isKeyboardInputThreadRunning = false;
//		
//	}
	
	public void checkForKeyboardInput() {
		
		if (this.keyboardInput.isKeyDown(KeyEvent.VK_ESCAPE)) {
			
			Game.isRunning = false;
			
		}
		
		if (this.isKeyboardInputMovementEnabled) {
			
//			this.isCurrentlyMoving = true;
			
			if (this.keyboardInput.isKeyDown(KeyEvent.VK_UP) || this.keyboardInput.isKeyDown(KeyEvent.VK_W)) {
				
				this.isCurrentlyMoving = true;
				
				this.y -= this.movementAmount;
				
			}
			
			if (this.keyboardInput.isKeyDown(KeyEvent.VK_DOWN) || this.keyboardInput.isKeyDown(KeyEvent.VK_S)) {
				
				this.isCurrentlyMoving = true;
				
				this.y += this.movementAmount;
				
			}
			
			if (this.keyboardInput.isKeyDown(KeyEvent.VK_LEFT) || this.keyboardInput.isKeyDown(KeyEvent.VK_A)) {
				
				this.isCurrentlyMoving = true;
				
				this.x -= this.movementAmount;
				
			}
			
			if (this.keyboardInput.isKeyDown(KeyEvent.VK_RIGHT) || this.keyboardInput.isKeyDown(KeyEvent.VK_D)) {
				
				this.isCurrentlyMoving = true;
				
				this.x += this.movementAmount;
				
			}
			
//			this.isCurrentlyMoving = false;
			
		}
		
	}
	
	public void checkForMouseInput() {
		
		if (this.mouseInput.isLeftButtonClicked()) {
			
//			boolean xGreaterThan = false;
//			boolean yGreaterThan = false;
//			
//			xGreaterThan = (this.mouseInput.clickedPoint.x > (this.x - Game.camera.offsetX));
//			yGreaterThan = (this.mouseInput.clickedPoint.y > (this.y - Game.camera.offsetY));
//			
//			// Doesn't Work Properly, Will Need to Fix
//			
//			move((xGreaterThan ? (this.mouseInput.clickedPoint.x + this.x) : (this.x - this.mouseInput.clickedPoint.x)),
//				 (yGreaterThan ? (this.mouseInput.clickedPoint.y + this.y) : (this.y - this.mouseInput.clickedPoint.y)));
			
		}
		
	}
	
	@Override
	public void setAnimationThread() {
		
		Thread animationThread = new Thread(() -> {
			
			int cacheX = this.x;
			int cacheY = this.y;
			
			while (this.isAnimationThreadEnabled) {
				
				if (!isAnimationAutomatic) {
					
					if ((cacheX < this.x) || this.keyboardInput.isKeyDown(KeyEvent.VK_RIGHT)) {
						
						animate(3);
						
					} else if ((cacheX > this.x) || this.keyboardInput.isKeyDown(KeyEvent.VK_LEFT)) {
						
						animate(2);
						
						// There is No Need for an Else If Statement
						// It Justs Makes the Animation Look Better
						
					} else if ((cacheY < this.y) || this.keyboardInput.isKeyDown(KeyEvent.VK_DOWN)) {
						
						animate(1);
						
					} else if ((cacheY > this.y) || this.keyboardInput.isKeyDown(KeyEvent.VK_UP)) {
						
						animate(0);
						
					}
					
				} else {
					
					animate(1);
					
				}
				
				cacheX = this.x;
				cacheY = this.y;
				
				try {
					
					Thread.sleep(this.animationCycle.animationDelay);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		});
		
		animationThread.start();
		
	}
	
}