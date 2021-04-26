package camera;

import java.awt.Component;

import map.Map;

import objects.Object;

public class Camera {
	
	private Object    object;
	private Component rootComponent;
	private Map       map;
	
	private int startX;
	private int startY;
	
	public int offsetX;
	public int offsetY;
	
	boolean isCameraMovementThreadEnabled = false;
	
	public Camera(Object object, Component rootComponent, Map map) {
		
		this.object        = object;
		this.rootComponent = rootComponent;
		this.map           = map;
		
		this.startX = this.object.x;
		this.startY = this.object.y;
		
		setCameraMovementThread();
		startCameraMovementThread();
		
	}
	
	private void setCameraMovementThread() {
		
		Thread cameraMovementThread = new Thread(() -> {
			
			while (this.isCameraMovementThreadEnabled) {
				
				// For Some Reason this Boolean had Issues
				// Should Really Fix it in the Future
				// To Prevent Excessive CPU Usage
				
//				if (this.object.isCurrentlyMoving) {
					
					// Lots of this Should be Reduced With Variables
					
					if ((this.object.x + (this.object.width / 2)) <= (this.rootComponent.getWidth() / 2)) {
						
						this.offsetX = 0;
						
					} else if ((this.object.x + (this.object.width / 2)) >= (this.map.width - (this.rootComponent.getWidth() / 2))) {
						
						this.offsetX = (this.map.width - this.rootComponent.getWidth());
						
					} else {
						
						this.offsetX = (((this.object.x - startX) - ((this.rootComponent.getWidth() / 2) - this.startX)) + (this.object.width / 2));
						
					}
					
					if ((this.object.y + (this.object.height / 2)) <= (this.rootComponent.getHeight() / 2)) {
						
						this.offsetY = 0;
						
					} else if ((this.object.y + (this.object.height / 2)) >= (this.map.height - (this.rootComponent.getHeight() / 2))) {
						
						this.offsetY = (this.map.height - this.rootComponent.getHeight());
						
					} else {
						
						this.offsetY = (((this.object.y - startY) - ((this.rootComponent.getHeight() / 2) - this.startY)) + (this.object.height / 2));
						
					}
					
					try {
						
						// Generally Really Bad Because it Causes lots of Computational Work
						// Need to Fix the While Loops Boolean to Prevent there Being Huge Amounts of Work
						
						Thread.sleep(1);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
						
					}
					
//				}
				
			}
			
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