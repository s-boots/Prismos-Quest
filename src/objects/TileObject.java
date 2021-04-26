package objects;

import java.awt.Image;

public class TileObject extends Object {
	
	public TileObject(Image sprite, int x, int y, boolean isCollidable) {
		
		super(sprite, Object.SIZE_32_32, x, y);
		
		this.isCollidable = isCollidable;
		
	}
	
	// I'm Not Sure if This Reduces Memory...
	
	@Override
	public void move(int x, int y) {}
	
	@Override
	public void setAnimationThread() {}
	
	@Override
	public void startAnimationThread() {}

	@Override
	public void stopAnimationThread() {}
	
	@Override
	public void updatePositionCache() {}
	
	@Override
	protected void animate(int directionIndex) {}
	
}