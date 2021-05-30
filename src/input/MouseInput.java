package input;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
	
	public boolean leftButtonClicked = false;
	
	public Point clickedPoint;
	
	public MouseInput(Component component) {
		
		component.addMouseListener(this);
		
	}
	
	public boolean isLeftButtonClicked() {
		
		if (this.leftButtonClicked) {
			
			this.leftButtonClicked = false;
			
			return true;
			
		}
		
		return false;
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		this.leftButtonClicked = true;
		
		this.clickedPoint = e.getPoint();
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
}