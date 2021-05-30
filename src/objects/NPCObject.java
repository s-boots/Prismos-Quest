package objects;

import java.awt.Image;

import graphics.AnimationCycle;

public class NPCObject extends Object {
	
	public String name;
	
	public boolean isWalkThreadEnabled = false;
	
	// I Haven't Added Much, in the Future I Want to add Random Movement Patterns
	
	public NPCObject(String name, Image sprite, int[] size, int x, int y) {
		
		super(sprite, size, x, y);
		
	}
	
	public NPCObject(String name, AnimationCycle animationCycle, int[] size, int x, int y) {
		
		super(animationCycle, size, x, y);
		
	}
	
	public void walk(int x, int y) {
		
		if (!this.isWalkThreadEnabled) {
			
			Thread walkThread = new Thread(() -> {
				
				int walkingSleepAmount = (this.movementAmount * 10);
				
				this.isWalkThreadEnabled = true;
				
				if (this.x < x) {
					
					for (int i = 0; i < ((x - this.x) / this.movementAmount); i++) {
						
						this.x += this.movementAmount;
						
						try {
							
							Thread.sleep(walkingSleepAmount);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
				} else {
					
					for (int i = ((this.x - x) / this.movementAmount); i > 0; i--) {
						
						this.x -= this.movementAmount;
						
						try {
							
							Thread.sleep(walkingSleepAmount);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
				}
				
				if (this.y < y) {
					
					for (int i = 0; i < ((y - this.y) / this.movementAmount); i++) {
						
						this.y += this.movementAmount;
						
						try {
							
							Thread.sleep(walkingSleepAmount);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
				} else {
					
					this.isCurrentlyMoving = true;
					
					for (int i = ((this.y - y) / this.movementAmount); i > 0; i--) {
						
						this.y -= this.movementAmount;
						
						try {
							
							Thread.sleep(walkingSleepAmount);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
					}
					
				}
				
				this.isWalkThreadEnabled = false;
				
			});
			
			walkThread.start();
			
		}
		
	}
	
}