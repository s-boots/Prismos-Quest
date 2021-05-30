package objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.ImageObserver;

import graphics.AnimationCycle;

public abstract class Object {
	
//	final public static int[] SIZE_8_8   = {8,  8};
//	final public static int[] SIZE_16_16 = {16, 16};
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
	
	public boolean isAnimationThreadEnabled = false;
	
	private void abstractInititalize(int[] size, int x, int y) {
		
		this.width  = size[0];
		this.height = size[1];
		
		this.x = x;
		this.y = y;
		
	}
	
	public Object(Image sprite, int[] size, int x, int y) {
		
		this.sprite = sprite;
		
		abstractInititalize(size, x, y);
		
	}
	
	public Object(AnimationCycle animationCycle, int[] size, int x, int y) {
		
		this.animationCycle = animationCycle;
		this.sprite         = animationCycle.getSprite(0);
		
		abstractInititalize(size, x, y);
		
		setAnimationThread();
		startAnimationThread();
		
	}
	
	public void setAnimationThread() {
		
		Thread animationThread = new Thread(() -> {
			
			int cacheX = this.x;
			int cacheY = this.y;
			
			while (this.isAnimationThreadEnabled) {
				
				if (!this.isAnimationAutomatic) {
					
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
					
					Thread.sleep(this.animationCycle.delay);
					
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
	
	protected void animate(int directionIndex) {
		
		int animationCacheDirection = this.animationCache[directionIndex];
		
		int spriteArrayLengthDividedBy4 = this.animationCycle.spriteArray.length / 4;
		
		this.sprite = this.animationCycle.getSprite((directionIndex == 0 ? animationCacheDirection : ((directionIndex * spriteArrayLengthDividedBy4) + animationCacheDirection)));
		
		this.animationCache[directionIndex] = ((animationCacheDirection == (spriteArrayLengthDividedBy4 - 1)) ? 0 : (animationCacheDirection + 1));
		
	}
	
	public void draw(Graphics2D rootGraphics, ImageObserver imageObserver, Insets insets, int offsetX, int offsetY) {
		
		if (this.sprite != null) {
			
			rootGraphics.drawImage(this.sprite, (int) ((insets.left + (this.x - offsetX) / rootGraphics.getTransform().getScaleX())), (int) ((insets.top + (this.y - offsetY)) / rootGraphics.getTransform().getScaleY()), imageObserver);
			
		}
		
	}
	
}