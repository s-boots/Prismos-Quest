package gui;

import java.awt.Color;

import main.Globals;

import objects.PlayerObject;

import sprites.Sprite;

public class HealthBarOverlay extends BarOverlay {
	
	public PlayerObject playerObject;
	
	public HealthBarOverlay(PlayerObject playerObject) {
		
		super(Sprite.createSprite(Globals.OVERLAY_SPRITES, "health_bar_2", Globals.PNG), 20, 20, new Color(90, 195, 60), new Color(255, 60, 60), 100);
		
		this.playerObject = playerObject;
		
		playerObject.healthBarOverlay = this;
		
		this.currentAmount = this.totalAmount;
		
		updateAmount(this.currentAmount - playerObject.health);
		
	}
	
}