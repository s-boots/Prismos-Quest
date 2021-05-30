package objects;

import java.awt.Image;

public class TileObject extends Object {
	
	public static int SIZE = 32;
	
	public TileObject(Image sprite, int x, int y, boolean isCollidable) {
		
		super(sprite, Object.SIZE_32_32, x, y);
		
		this.isCollidable = isCollidable;
		
	}
	
	@Override
	public void setAnimationThread() {}
	
	@Override
	public void startAnimationThread() {}
	
	@Override
	protected void animate(int directionIndex) {}
	
}