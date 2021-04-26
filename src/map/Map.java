package map;

import java.awt.Toolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import objects.TileObject;

public class Map {
	
	// When Multiple Tile Sets are Added, an ID is Used to Differentiate
	
	private static HashMap<Integer, String> tileSetMap = new HashMap<Integer, String>();
	
	static {
		
		tileSetMap.put(0, "nature_1");
		
	}
	
	public String type;
	
	public int width;
	public int height;
	
	public String text;
	
	public Map(File file) {
		
		Scanner fileScanner = null;
		
		try {
			
			fileScanner = new Scanner(new FileReader(file));
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		String typeLine = fileScanner.nextLine();
		
		this.type = Map.tileSetMap.get(Integer.valueOf(typeLine));
		
		this.text = "";
		
		while (fileScanner.hasNextLine()) {
			
			this.text += (fileScanner.nextLine() + "\n");
			
		}
		
		fileScanner.close();
		
		setSize();
		
	}
	
	public void setSize() {
		
		// Since Multiple Lines can Have Different Widths, it Sets the Width to the Longest Lines Width
		
		int trueWidth = 0;
		
		int widthCache = 0;
		
		this.height = 0;
		
		for (String line : this.text.split("\n")) {
			
			for (int i = 0; i < line.split(" ").length; i++) {
				
				widthCache += 32;
				
			}
			
			if (widthCache > trueWidth) {
				
				trueWidth = widthCache;
				
			}
			
			widthCache = 0;
			
			this.height += 32;
			
		}
		
		this.width = trueWidth;
		
	}
	
	public List<TileObject> getTileObjects() {
		
		List<TileObject> tileObjects = new ArrayList<TileObject>();
		
		int positionXCache = 0;
		int positionYCache = 0;
		
		Integer tileIDCache = null;
		
		for (String tileLine : this.text.split("\n")) {
			
			for (String tileID : tileLine.split(" ")) {
				
				Integer tileIDInteger = Integer.valueOf(tileID);
				
				// This Conditional Check Reduces Memory by "Copying" the Last TileObject if The Next is the Same ID
				// I'll Need to Add More When I add Customizability to Tiles Like Collision
				
				if (tileIDCache == null || (tileIDCache != tileIDInteger)) {
					
					tileIDCache = tileIDInteger;
					
					tileObjects.add(new TileObject(Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/sprites/tiles/" + this.type + "/" + tileID + ".png")), positionXCache, positionYCache, false));
					
				} else {
					
					TileObject currentTileObject = tileObjects.get(tileObjects.size() - 1);
					
					tileObjects.add(new TileObject(currentTileObject.sprite, positionXCache, positionYCache, currentTileObject.isCollidable));
					
				}
				
				positionXCache += 32;
				
			}
			
			positionXCache =  0;
			positionYCache += 32;
			
		}
		
		return tileObjects;
		
	}
	
}