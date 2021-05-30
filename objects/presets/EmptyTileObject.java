package objects.presets;

import main.Globals;

import objects.TileObject;

import sprites.Sprite;

public class EmptyTileObject extends TileObject {
	
	public EmptyTileObject(int x, int y) {
		
		super(Sprite.createSprite(Globals.OTHER_TILE_SPRITES, "empty", Globals.PNG), x, y, false);
		
	}
	
}