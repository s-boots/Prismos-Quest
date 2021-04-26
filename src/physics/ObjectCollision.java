package physics;

import main.Game;
import map.Map;
import objects.Object;

public class ObjectCollision {
	
	public static void checkForWorldCollision(Map map) {
		
		for (Object object : Game.currentObjectsOnScreen) {
			
			if (object.isCollidable) {
				
				if (isObjectCollidingLeft(object.x, 0)) {
					
					object.x += object.movementAmount;
					
				}
				
				if (isObjectCollidingRight(object.x + object.width, map.width)) {
					
					object.x -= object.movementAmount;
					
				}
				
				if (isObjectCollidingTop(object.y, 0)) {
					
					object.y += object.movementAmount;
					
				}
				
				if (isObjectCollidingBottom(object.y + object.height, map.height)) {
					
					object.y -= object.movementAmount;
					
				}
				
			}
			
		}
		
	}
	
	public static void checkForObjectCollision() {
		
		// This is Very Poor and Doesn't Work Properly Just Yet
		
		for (Object object : Game.currentObjectsOnScreen) {
			
			if (object.isCollidable) {
				
//			Object object = Game.playerObject;
			
				for (Object otherObject : Game.currentObjectsOnScreen) {
					
					if (otherObject.isCollidable) {
						
						if (object != otherObject) {
							
							// Change to Or Not Else If
							
							if (isObjectCollidingLeft(object, otherObject)) {
								
								object.x = object.positionCache[0];
								
							} else if (isObjectCollidingRight(object, otherObject)) {
								
								object.x = object.positionCache[0];
								
							}
							
							if (isObjectCollidingTop(object, otherObject)) {
								
								object.y = object.positionCache[1];
								
							} else if (isObjectCollidingBottom(object, otherObject)) {
								
								object.y = object.positionCache[1];
								
							}
							
							object.updatePositionCache();
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
	// Should Simplify This
	
	private static boolean isObjectCollidingLeft(int x1, int x2) {
		
		return (x1 < x2);
		
	}
	
	private static boolean isObjectCollidingRight(int x1, int x2) {
		
		return (x1 > x2);
		
	}
	
	private static boolean isObjectCollidingTop(int y1, int y2) {
		
		return (y1 < y2);
		
	}
	
	private static boolean isObjectCollidingBottom(int y1, int y2) {
		
		return (y1 > y2);
		
	}
	
	// Should Simplify...
	
	private static boolean isObjectCollidingLeft(Object object1, Object object2) {
		
		if (((object1.x >= object2.x) && (object1.x <= (object2.x + object2.width))) &&
			(((object1.y + object1.height) >= object2.y) && (object1.y <= (object2.y + object2.height)))) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	private static boolean isObjectCollidingRight(Object object1, Object object2) {
		
		if ((((object1.x <= object2.x) && (object1.x + object1.width) >= object2.x)) &&
			(((object1.y + object1.height) >= object2.y) && (object1.y <= (object2.y + object2.height)))) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	private static boolean isObjectCollidingTop(Object object1, Object object2) {
		
		if ((((object1.y) >= object2.y) && (object1.y <= (object2.y + object2.height))) &&
			(((object1.x + object1.width) >= object2.x) && (object1.x <= (object2.x + object2.width)))) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	private static boolean isObjectCollidingBottom(Object object1, Object object2) {
		
		if (((object1.y <= object2.y) && ((object1.y + object1.height) >= object2.y)) &&
			(((object1.x + object1.width) >= object2.x)  && (object1.x <= (object2.x + object2.width)))) {
			
			return true;
			
		}
		
		return false;
		
	}
	
}