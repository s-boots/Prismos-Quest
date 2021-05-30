package graphics;

import java.awt.Image;
import java.awt.MediaTracker;

//import main.Game;

public class AnimationCycle {
	
	public Image[] spriteArray;
	
	public int delay;
	
	public MediaTracker mediaTracker;
	
	public AnimationCycle(Image[] spriteArray, int delay) {
		
		this.spriteArray = spriteArray;
		
		this.delay = delay;
		
	}
	
	public Image getSprite(int spriteIndex) {
		
		return spriteArray[spriteIndex];
		
	}
	
}