package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import fonts.Fonts;

public class Overlay {
	
	public Image sprite;
	
	public int x;
	public int y;
	
	public Overlay(Image sprite, int x, int y) {
		
		this.sprite = sprite;
		
		this.x = x;
		this.y = y;
		
	}
	
	public void draw(Graphics2D rootGraphics, ImageObserver imageObserver) {
		
		rootGraphics.drawImage(this.sprite, this.x, this.y, imageObserver);
		
	}
	
	public void drawText(int number, int x, int y, boolean isBold) {
		
		Graphics spriteGraphics = this.sprite.getGraphics();
		
		spriteGraphics.setFont(isBold ? Fonts.pixeloidSansBold16Font : Fonts.pixeloidSans16Font);
		spriteGraphics.setColor(Color.BLACK);
		spriteGraphics.drawString(String.valueOf(number), x, y);
		
	}
	
}