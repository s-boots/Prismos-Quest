package graphics;

import java.awt.Image;

public class AnimationCycle {
	
	public Image[] spriteArray;
	
	public int animationDelay;
	
	public int[] neutralSpriteIndexs;
	
	public AnimationCycle(Image[] spriteArray, int animationDelay, int[] neutralSpriteIndexs) {
		
		this.spriteArray = spriteArray;
		
		this.animationDelay = animationDelay;
		
		this.neutralSpriteIndexs = neutralSpriteIndexs;
		
	}
	
	public Image getSprite(int spriteIndex) {
		
		return spriteArray[spriteIndex];
		
	}
	
}