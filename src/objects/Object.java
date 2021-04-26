package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.ImageObserver;

import graphics.AnimationCycle;

import main.Game;

public class Object {
	
//	final public static int[] SIZE_8_8   = {8,  8};
	final public static int[] SIZE_16_16 = {16, 16};
	final public static int[] SIZE_16_24 = {16, 24};
	final public static int[] SIZE_32_32 = {32, 32};
//	final public static int[] SIZE_64_64 = {64, 64};
	
	final public static int DEFAULT_MOVEMENT_AMOUNT = 4;
	
	public  AnimationCycle animationCycle;
	private int[]          animationCache = new int[4];
	public  boolean        isAnimationAutomatic;
	public  Image          sprite;
	
	public int width;
	public int height;
	
	public int x;
	public int y;
	
	public int movementAmount = DEFAULT_MOVEMENT_AMOUNT;
	
	public boolean isCollidable = true;
	
	public boolean isCurrentlyMoving = false;
	
	public int[] positionCache = new int[2];
	
	public boolean isAnimationThreadEnabled = false;
	public boolean isMovementThreadEnabled  = false;
	
	private void abstractInititalize(int[] size, int x, int y) {
		
		this.width  = size[0];
		this.height = size[1];
		
		this.x = x;
		this.y = y;
		
		this.positionCache[0] = x;
		this.positionCache[1] = y;
		
		// In Future, Only Render Objects that Need To be Rendered...
		
		Game.currentObjectsOnScreen.add(this);
		
	}
	
	public Object(int[] size, int x, int y) {
		
		abstractInititalize(size, x, y);
		
	}
	
	public Object(Image sprite, int[] size, int x, int y) {
		
		this.sprite = sprite;
		
		abstractInititalize(size, x, y);
		
	}
	
	public Object(AnimationCycle animationCycle, int[] size, int x, int y) {
		
		this.animationCycle = animationCycle;
		this.sprite         = this.animationCycle.getSprite(this.animationCycle.neutralSpriteIndexs[1]);
		
		abstractInititalize(size, x, y);
		
		setAnimationThread();
		startAnimationThread();
		
	}
	
	public void move(int x, int y) {
		
		// Lots of This Can Probably be Simplified
		
		if (!this.isMovementThreadEnabled) {
			
			Thread movementThread = new Thread(() -> {
				
				this.isMovementThreadEnabled = true;
				
				int objectMovementSleepAmount = this.movementAmount * 10;
				
				if (this.x < x) {
					
					this.isCurrentlyMoving = true;
					
					for (int i = 0; i < ((x - this.x) / this.movementAmount); i++) {
						
						this.x += this.movementAmount;
						
						try {
							
							Thread.sleep(objectMovementSleepAmount);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
					this.isCurrentlyMoving = false;
					
				} else {
					
					this.isCurrentlyMoving = true;
					
					for (int i = ((this.x - x) / this.movementAmount); i > 0; i--) {
						
						this.x -= this.movementAmount;
						
						try {
							
							Thread.sleep(objectMovementSleepAmount);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
					this.isCurrentlyMoving = false;
					
				}
				
				if (this.y < y) {
					
					this.isCurrentlyMoving = true;
					
					for (int i = 0; i < ((y - this.y) / this.movementAmount); i++) {
						
						this.y += this.movementAmount;
						
						try {
							
							Thread.sleep(objectMovementSleepAmount);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
					this.isCurrentlyMoving = false;
					
				} else {
					
					this.isCurrentlyMoving = true;
					
					for (int i = ((this.y - y) / this.movementAmount); i > 0; i--) {
						
						this.y -= this.movementAmount;
						
						try {
							
							Thread.sleep(objectMovementSleepAmount);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
					this.isCurrentlyMoving = false;
					
				}
				
				this.isMovementThreadEnabled = false;
				
			});
			
			movementThread.start();
			
		}
		
	}
	
	public void setAnimationThread() {
		
		Thread animationThread = new Thread(() -> {
			
			int cacheX = this.x;
			int cacheY = this.y;
			
			while (this.isAnimationThreadEnabled) {
				
				if (!isAnimationAutomatic) {
					
					if (cacheX < this.x) {
						
						animate(3);
						
					} else if (cacheX > this.x) {
						
						animate(2);
						
					}
					
					if (cacheY < this.y) {
						
						animate(1);
						
					} else if (cacheY > this.y) {
						
						animate(0);
						
					}
					
					cacheX = this.x;
					cacheY = this.y;
					
				} else {
					
					this.animate(1);
					
				}
				
				try {
					
					Thread.sleep(this.animationCycle.animationDelay);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
		});
		
		animationThread.start();
		
	}
	
	public void startAnimationThread() {
		
		this.isAnimationThreadEnabled = true;
		
	}
	
	public void stopAnimationThread() {
		
		this.isAnimationThreadEnabled = false;
		
	}
	
	public void updatePositionCache() {
		
		this.positionCache[0] = this.x;
		this.positionCache[1] = this.y;
		
	}
	
	protected void animate(int directionIndex) {
		
		int animationCacheDirection = this.animationCache[directionIndex];
		
		int spriteArrayLengthDividedBy4 = this.animationCycle.spriteArray.length / 4;
		
		this.sprite = this.animationCycle.getSprite((directionIndex == 0 ? animationCacheDirection : ((directionIndex * spriteArrayLengthDividedBy4) + animationCacheDirection)));
		
		this.animationCache[directionIndex] = ((animationCacheDirection == spriteArrayLengthDividedBy4 - 1) ? 0 : animationCacheDirection + 1);
		
	}
	
	public void draw(Graphics rootGraphics, ImageObserver imageObserver, Insets insets, int offsetX, int offsetY) {
		
		if (this.sprite != null) {
			
			rootGraphics.drawImage(this.sprite, insets.left + (this.x - offsetX), insets.top + (this.y - offsetY), imageObserver);
			
		}
		
	}
	
}