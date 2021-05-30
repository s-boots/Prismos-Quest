package objects.presets;

import main.Globals;

import objects.TileObject;

import sprites.Sprite;

public class ErrorTileObject extends TileObject {
	
	public ErrorTileObject(int x, int y) {
		
		super(Sprite.createSprite(Globals.OTHER_TILE_SPRITES, "error", Globals.PNG), x, y, false);
		
	}
	
}