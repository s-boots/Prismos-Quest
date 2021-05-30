package objects;

import java.awt.Image;

public class WeatherObject extends Object {
	
	public Image sprite;
	
	public int x2;
	public int y2;
	
	public WeatherObject(Image sprite, int x1, int y1, int x2, int y2) {
		
		super(sprite, new int[] {(x2 - x1), (y2 - y1)}, x1, y1);
		
		this.sprite = sprite;
		
		this.x2 = x2;
		this.y2 = y2;
		
		this.isCollidable = false;
		
	}
	
}