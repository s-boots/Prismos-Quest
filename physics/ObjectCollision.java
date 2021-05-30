package physics;

import main.Game;

import map.Room;

import objects.Object;
import objects.PlayerObject;

public class ObjectCollision {
	
	public Room room;
	
	public ObjectCollision(Room room) {
		
		this.room = room;
		
	}
	
	public void checkForWorldCollision() {
		
		for (Object object : this.room.currentObjectList) {
			
			if (object.isCollidable) {
				
				if (isObjectCollidingLeft(object.x, 0)) {
					
					object.x += object.movementAmount;
					
				} else if (isObjectCollidingRight((object.x + object.width), this.room.map.width)) {
					
					object.x -= object.movementAmount;
					
				}
				
				if (isObjectCollidingTop(object.y, 0)) {
					
					object.y += object.movementAmount;
					
				} else if (isObjectCollidingBottom((object.y + object.height), this.room.map.height)) {
					
					object.y -= object.movementAmount;
					
				}
				
			}
			
		}
		
	}
	
	public void checkForObjectCollision() {
		
		PlayerObject object = Game.playerObject;
		
//		for (Object object : this.room.currentObjectList) {
			
			if (object.isCollidable) {
				
				for (Object otherObject : this.room.currentObjectList) {
					
					if (otherObject.isCollidable) {
						
						if (object != otherObject) {
							
							if (isObjectCollidingLeft(object, otherObject)) {
								
								object.x += object.movementAmount;
								
							} else if (isObjectCollidingRight(object, otherObject)) {
								
								object.x -= object.movementAmount;
								
							}
							
							if (isObjectCollidingTop(object, otherObject)) {
								
								object.y += object.movementAmount;
								
							} else if (isObjectCollidingBottom(object, otherObject)) {
								
								object.y -= object.movementAmount;
								
							}
							
						}
						
					}
					
				}
				
			}
			
//		}
		
	}
	
	private boolean isObjectCollidingLeft(int x1, int x2) {
		
		return (x1 < x2);
		
	}
	
	private boolean isObjectCollidingRight(int x1, int x2) {
		
		return (x1 > x2);
		
	}
	
	private boolean isObjectCollidingTop(int y1, int y2) {
		
		// Same Code
		
		return isObjectCollidingLeft(y1, y2);
		
	}
	
	private boolean isObjectCollidingBottom(int y1, int y2) {
		
		// Same Code
		
		return isObjectCollidingRight(y1, y2);
		
	}
	
	private boolean isObjectCollidingLeft(Object object1, Object object2) {
		
		return ((((object1.x >= object2.x) && (object1.x <= (object2.x + object2.width))) &&
				(((object1.y + object1.height) >= object2.y) && (object1.y <= (object2.y + object2.height)))) &&
				
				((((object2.x + object2.width) - object1.x) < ((object2.y + object2.height) - object1.y)) &&
				(((object2.x + object2.width) - object1.x) < ((object1.y + object1.height) - object2.y))));
		
	}
	
	private boolean isObjectCollidingRight(Object object1, Object object2) {
		
		return (((((object1.x <= object2.x) && (object1.x + object1.width) >= object2.x)) &&
				(((object1.y + object1.height) >= object2.y) && (object1.y <= (object2.y + object2.height)))) &&
				
				((((object1.x + object1.width) - object2.x) < ((object1.y + object1.height) - object2.y)) &&
				(((object1.x + object1.width) - object2.x) < ((object2.y + object2.height) - object1.y))));
		
	}
	
	private boolean isObjectCollidingTop(Object object1, Object object2) {
		
		return ((((object1.y >= object2.y)) && ((object2.y + object2.height) > object1.y)) &&
				(((object1.x + object1.width) >= object2.x) && (object1.x <= (object2.x + object2.width))));
		
	}
	
	private boolean isObjectCollidingBottom(Object object1, Object object2) {
		
		return (((object1.y <= object2.y) && ((object1.y + object1.height) >= object2.y)) &&
				(((object1.x + object1.width) >= object2.x) && (object1.x <= (object2.x + object2.width))));
		
	}
	
}
