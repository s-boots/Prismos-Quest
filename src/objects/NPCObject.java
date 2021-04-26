package objects;

import java.awt.Image;

import graphics.AnimationCycle;

public class NPCObject extends Object {
	
	public String name;
	
	// I Haven't Added Much, in the Future I Want to add Random Movement Patterns
	
	public NPCObject(String name, Image sprite, int[] size, int x, int y) {
		
		super(sprite, size, x, y);
		
	}
	
	public NPCObject(String name, AnimationCycle animationCycle, int[] size, int x, int y) {
		
		super(animationCycle, size, x, y);
		
	}
	
}