package sprites.objects.player;

import java.awt.Image;

import graphics.AnimationCycle;

import main.Globals;

import sprites.Sprite;

public class PlayerSprites {
	
	public static AnimationCycle getAnimationCycle() {
		
		String PLAYER_OBJECT_SPRITES = Globals.OBJECT_SPRITES + "player/";
		
		final String UP    = "up_";
		final String DOWN  = "down_";
		final String LEFT  = "left_";
		final String RIGHT = "right_";
		
		Image imageUp1 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, UP + "1", Globals.PNG);
		Image imageUp2 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, UP + "2", Globals.PNG);
		Image imageUp3 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, UP + "3", Globals.PNG);
		Image imageUp4 = imageUp2;
		Image imageUp5 = imageUp1;
		Image imageUp6 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, UP + "6", Globals.PNG);
		Image imageUp7 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, UP + "7", Globals.PNG);
		Image imageUp8 = imageUp6;
		
		Image imageDown1 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, DOWN + "1", Globals.PNG);
		Image imageDown2 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, DOWN + "2", Globals.PNG);
		Image imageDown3 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, DOWN + "3", Globals.PNG);
		Image imageDown4 = imageDown2;
		Image imageDown5 = imageDown1;
		Image imageDown6 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, DOWN + "6", Globals.PNG);
		Image imageDown7 = Sprite.createSprite(PLAYER_OBJECT_SPRITES, DOWN + "7", Globals.PNG);
		Image imageDown8 = imageDown6;
		
		Image[] images = new Image[] {
				
				imageUp1,
				imageUp2,
				imageUp3,
				imageUp4,
				imageUp5,
				imageUp6,
				imageUp7,
				imageUp8,
				
				imageDown1,
				imageDown2,
				imageDown3,
				imageDown4,
				imageDown5,
				imageDown6,
				imageDown7,
				imageDown8,
				
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, LEFT + "1", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, LEFT + "2", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, LEFT + "3", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, LEFT + "4", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, LEFT + "5", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, LEFT + "6", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, LEFT + "7", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, LEFT + "8", Globals.PNG),
				
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, RIGHT + "1", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, RIGHT + "2", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, RIGHT + "3", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, RIGHT + "4", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, RIGHT + "5", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, RIGHT + "6", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, RIGHT + "7", Globals.PNG),
				Sprite.createSprite(PLAYER_OBJECT_SPRITES, RIGHT + "8", Globals.PNG)
				
		};
		
		return new AnimationCycle(images, 85);
		
	}
	
}