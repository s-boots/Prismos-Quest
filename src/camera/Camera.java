package camera;

import java.awt.Component;

import map.Map;

import objects.Object;

public class Camera {
	
	final public static double ZOOM_MINIMUM = 1;
	final public static double ZOOM_MAXIMUM = 4.0;
	
	public Object    object;
	public Component rootComponent;
	public Map       map;
	
	public int startX;
	public int startY;
	
	public int offsetX;
	public int offsetY;
	
	private boolean isCameraMovementThreadEnabled = false;
	
	public Camera(Object object, Component rootComponent, Map map) {
		
		this.object        = object;
		this.rootComponent = rootComponent;
		this.map           = map;
		
		this.startX = object.x;
		this.startY = object.y;
		
		setCameraMovementThread();
		startCameraMovementThread();
		
	}
	
	private void setCameraMovementThread() {
		
		Thread cameraMovementThread = new Thread(() -> {
			
			int thisObjectWidthHalf  = (this.object.width  / 2);
			int thisObjectHeightHalf = (this.object.height / 2);
			
			int thisRootComponentWidth  = this.rootComponent.getWidth();
			int thisRootComponentHeight = this.rootComponent.getHeight();
			
			int thisRootComponentWidthHalf  = (thisRootComponentWidth  / 2);
			int thisRootComponentHeightHalf = (thisRootComponentHeight / 2);
			
			while (this.isCameraMovementThreadEnabled) {
				
//				while (this.object.isCurrentlyMoving) {
					
					if ((this.object.x + thisObjectWidthHalf) <= thisRootComponentWidthHalf) {
						
						this.offsetX = 0;
						
					} else if ((this.object.x + thisObjectWidthHalf) >= (this.map.width - thisRootComponentWidthHalf)) {
						
						this.offsetX = (this.map.width - thisRootComponentWidth);
						
					} else {
						
						this.offsetX = (((this.object.x - this.startX) - (thisRootComponentWidthHalf - this.startX)) + thisObjectWidthHalf);
						
					}
					
					if ((this.object.y + thisObjectHeightHalf) <= thisRootComponentHeightHalf) {
						
						this.offsetY = 0;
						
					} else if ((this.object.y + thisObjectHeightHalf) >= (this.map.height - thisRootComponentHeightHalf)) {
						
						this.offsetY = (this.map.height - thisRootComponentHeight);
						
					} else {
						
						this.offsetY = (((this.object.y - this.startY) - (thisRootComponentHeightHalf - this.startY)) + thisObjectHeightHalf);
						
					}
					
					try {
						
						// Generally Really Bad Because it Causes lots of Computational Work
						// Need to Fix the While Loops Boolean to Prevent there Being Huge Amounts of Work
						
//						Thread.sleep(1);
						
						Thread.sleep((long) 0.999);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
						
					}
					
				}
				
//			}
			
		});
		
		cameraMovementThread.start();
		
	}
	
	private void startCameraMovementThread() {
		
		this.isCameraMovementThreadEnabled = true;
		
	}
	
//	private void stopCameraMovementThread() {
//		
//		this.isCameraMovementEnabled = false;
//		
//	}
	
}