package map;

import java.awt.Image;
import java.awt.MediaTracker;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import main.Globals;

import objects.TileObject;
import objects.presets.EmptyTileObject;
import objects.presets.ErrorTileObject;

import sprites.Sprite;

public class Map {
	
	final private static HashMap<Integer, String> TILE_SPRITE_DIRECTORY_MAP = new HashMap<Integer, String>();
	
	static {
		
		TILE_SPRITE_DIRECTORY_MAP.put(139, "nature_1");
		TILE_SPRITE_DIRECTORY_MAP.put(395, "pokemon_grass");
		TILE_SPRITE_DIRECTORY_MAP.put(411, "shaun_spalding");
		
	}
	
	final public static String TILE_TYPE_EMPTY   = "0";
	final public static String TILE_TYPE_SPRITE  = "1";
	final public static String TILE_TYPE_WEATHER = "2";
	
	final public static String TILE_EMPTY = "{}";
	
	final public static String TILE_FLAG_COLLIDE = "~";
	final public static String TILE_FLAG_LAYER   = "x";
	
	public int width;
	public int height;
	
	public String text;
	
	public MediaTracker mediaTracker;
	
	public Map(InputStream inputStream) {
		
		Scanner inputStreamScanner = null;
		
		inputStreamScanner = new Scanner(new InputStreamReader(inputStream));
		
		this.text = "";
		
		while (inputStreamScanner.hasNextLine()) {
			
			this.text += (inputStreamScanner.nextLine() + "\n");
			
		}
		
		inputStreamScanner.close();
		
		setSize();
		
	}
	
	public void setSize() {
		
		// Since Multiple Lines can Have Different Widths, it Sets the Width to the Longest Lines Width
		
		int trueWidth = 0;
		
		int widthCache = 0;
		
		for (String line : this.text.split("\n")) {
			
			for (int i = 0; i < line.split(" ").length; i++) {
				
				widthCache += TileObject.SIZE;
				
			}
			
			if (widthCache > trueWidth) {
				
				trueWidth = widthCache;
				
			}
			
			widthCache = 0;
			
			this.height += TileObject.SIZE;
			
		}
		
		this.width = trueWidth;
		
	}
	
	public List<TileObject> getTileObjects() {
		
		List<TileObject> tileObjects = new ArrayList<TileObject>();
		
		int positionXCache = 0;
		int positionYCache = 0;
		
		HashMap<String, Image> spriteTileIDCacheMap = new HashMap<String, Image>();
		
		for (String tileLine : this.text.split("\n")) {
			
			for (String abstractTile : tileLine.split(" ")) {
				
				boolean isCollidable = false;
				
				try {
					
					if (abstractTile.startsWith(TILE_FLAG_COLLIDE)) {
						
						isCollidable = true;
						
						abstractTile = abstractTile.replaceFirst(TILE_FLAG_COLLIDE, "");
						
					}
					
					if (abstractTile.contains(TILE_FLAG_LAYER)) {
						
						List<Image> spriteLayersList = new ArrayList<Image>();
						
						for (String spriteTileID : abstractTile.split(TILE_FLAG_LAYER)) {
							
							if (spriteTileIDCacheMap.keySet().contains(spriteTileID)) {
								
								spriteLayersList.add(spriteTileIDCacheMap.get(spriteTileID));
								
							} else {
								
								Image sprite = Sprite.createSprite(getTileSpriteFolderFromSpriteID(spriteTileID), spriteTileID, Globals.PNG);
								
								spriteLayersList.add(sprite);
								
								spriteTileIDCacheMap.put(spriteTileID, sprite);
								
							}
							
						}
						
						Image layeredSprite = Sprite.createLayeredSprite(spriteLayersList);
						
						tileObjects.add(new TileObject(layeredSprite, positionXCache, positionYCache, isCollidable));
						
					} else {
						
						switch (getTileType(abstractTile)) {
							
							case TILE_TYPE_EMPTY:
								
								tileObjects.add(new EmptyTileObject(positionXCache, positionYCache));
								
								break;
							
//							case TILE_TYPE_WEATHER:
//								
//								String endCoordinates = abstractTile.substring(abstractTile.indexOf("-"));
//								
//								int indexOfX = endCoordinates.indexOf("x");
//								
//								int x2 = (positionXCache + (TileObject.LENGTH * Integer.valueOf(endCoordinates.substring(0, indexOfX))));
//								int y2 = (positionYCache + (TileObject.LENGTH * Integer.valueOf(endCoordinates.substring(indexOfX))));
//								
//								WeatherObject weatherObject = new WeatherObject(Sprites.createSprite(Globals.WEATHER_SPRITES, getTileID(abstractTile), Globals.GIF), positionXCache, positionYCache, x2, y2);
//								
//								weatherObjectList.add(weatherObject);
//								
//								break;
							
							case TILE_TYPE_SPRITE:
								
								String spriteTileID = getTileID(abstractTile);
								
								if (spriteTileIDCacheMap.keySet().contains(spriteTileID)) {
									
									tileObjects.add(new TileObject(spriteTileIDCacheMap.get(spriteTileID), positionXCache, positionYCache, isCollidable));
									
								} else {
									
									Image tileObjectSprite = Sprite.createSprite(getTileSpriteFolderFromSpriteID(abstractTile), abstractTile, Globals.PNG);
									
									tileObjects.add(new TileObject(tileObjectSprite, positionXCache, positionYCache, isCollidable));
									
									spriteTileIDCacheMap.put(abstractTile, tileObjectSprite);
									
								}
								
								break;
							
						}
						
					}
					
				} catch (Exception e) {
					
					// Only use for Debugging
					
//					e.printStackTrace();
					
					tileObjects.add(new ErrorTileObject(positionXCache, positionYCache));
					
				}
				
				positionXCache += TileObject.SIZE;
				
			}
			
			while (positionXCache < this.width) {
				
				tileObjects.add(new EmptyTileObject(positionXCache, positionYCache));
				
				positionXCache += TileObject.SIZE;
				
			}
			
			positionXCache =  0;
			positionYCache += TileObject.SIZE;
			
		}
		
		return tileObjects;
		
	}
	
	private String getTileType(String tile) {
		
		if (tile.equals(TILE_EMPTY)) {
			
			return TILE_TYPE_EMPTY;
			
		} else if (tile.startsWith("[") && tile.endsWith("]")) {
			
			return TILE_TYPE_WEATHER;
			
		} else {
			
			return TILE_TYPE_SPRITE;
			
		}
		
	}
	
	private String getTileID(String tile) {
		
		switch (getTileType(tile)) {
			
			case TILE_TYPE_EMPTY:
				
				return TILE_EMPTY;
			
			case TILE_TYPE_WEATHER:
				
				tile = tile.replaceFirst("[", "");
				tile = tile.replaceFirst("]", "");
				
				return (tile.substring(0, tile.indexOf("-")));
			
			case TILE_TYPE_SPRITE:
				
				return tile;
			
			default:
				
				return null;
			
		}
		
	}
	
	public String getTileSpriteFolderFromSpriteID(String tileSpriteID) {
		
		int tileSpriteIDInteger = Integer.valueOf(tileSpriteID);
		
		for (int tileSpriteDirectoryMaxID : TILE_SPRITE_DIRECTORY_MAP.keySet()) {
			
			if (tileSpriteIDInteger <= tileSpriteDirectoryMaxID) {
				
				return (Globals.TILE_SPRITES + TILE_SPRITE_DIRECTORY_MAP.get(tileSpriteDirectoryMaxID) + "/");
				
			}
			
		}
		
		return null;
		
	}
	
}