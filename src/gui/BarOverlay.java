package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class BarOverlay extends Overlay {
	
	final int MAIN_REGION_ENDING_X = 216;
	
	public Color primaryColor;
	public Color secondaryColor;
	
	public int totalAmount;
	public int currentAmount;
	
	public BarOverlay(Image sprite, int x, int y, Color primaryColor, Color secondaryColor, int totalAmount) {
		
		super(sprite, x, y);
		
		this.primaryColor   = primaryColor;
		this.secondaryColor = secondaryColor;
		
		this.totalAmount   = totalAmount;
		
	}
	
	public void addAmount(int amount) {
		
		updateAmount(amount);
		
	}
	
	public void subtractAmount(int amount) {
		
		updateAmount(-amount);
		
	}
	
	public void updateAmount(int amount) {
		
		final int MAIN_REGION_STARTING_X = 16;
		final int MAIN_REGION_HEIGHT 	 = 16;
		final int MAIN_REGION_WIDTH      = 200;
		
		BufferedImage bufferedImage = new BufferedImage(this.sprite.getWidth(null), this.sprite.getHeight(null), BufferedImage.TYPE_INT_RGB);
		
		Graphics2D bufferedImageGraphics = bufferedImage.createGraphics();
		
		if (!((this.currentAmount > this.totalAmount) || (this.currentAmount < 0))) {
			
			if (amount > 0) {
				
				//
				
			} else if (amount < 0) {
				
				bufferedImageGraphics.drawImage(this.sprite, 0, 0, null);
				
				int graphicsScale = (MAIN_REGION_WIDTH / this.totalAmount);
				
				int currentAmountScaled = (this.currentAmount * graphicsScale);
				
				int startingX = ((MAIN_REGION_STARTING_X + currentAmountScaled) + (amount * graphicsScale));
				
				if (startingX < MAIN_REGION_STARTING_X) {
					
					startingX = MAIN_REGION_STARTING_X;
					
				}
				
				int endingX = (MAIN_REGION_STARTING_X + currentAmountScaled);
				
				if (endingX > MAIN_REGION_ENDING_X) {
					
					endingX = MAIN_REGION_ENDING_X;
					
				}
				
				bufferedImageGraphics.setColor(secondaryColor);
				bufferedImageGraphics.fillRect(startingX, 0, endingX, MAIN_REGION_HEIGHT);
				
				this.sprite = bufferedImage;
				
			}
			
		}
		
		this.currentAmount += amount;
		
		drawAmount();
		
	}
	
	public void drawAmount() {
		
		super.drawText(this.currentAmount, ((MAIN_REGION_ENDING_X - (Integer.toString(this.currentAmount).length() * 10)) - 2), 13, true);
		
	}
	
}