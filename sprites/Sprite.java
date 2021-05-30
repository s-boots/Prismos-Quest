package sprites;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.util.List;

import javax.imageio.ImageIO;

import objects.TileObject;

public class Sprite {
	
	public static Image createSprite(String location, String name, String type) {
		
		Image currentImage;
		
		try {
			
//			currentImage = (Image) ImageIO.read(Sprite.class.getResource(location + name + type));
			currentImage = (Image) ImageIO.read(Sprite.class.getResourceAsStream(location + name + type));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return null;
			
		}
		
		return currentImage;
		
//		if (Cache.imageCacheList.contains(currentImage)) {
//			
//			return Cache.imageCacheList.get(Cache.imageCacheList.indexOf(currentImage));
//			
//		} else {
//			
//			Cache.imageCacheList.add(currentImage);
//			
//			return Cache.imageCacheList.get(Cache.imageCacheList.size() - 1);
//			
//		}
		
	}
	
	public static Image createLayeredSprite(List<Image> spriteList) {
		
		BufferedImage bufferedImage = new BufferedImage(TileObject.SIZE, TileObject.SIZE, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D bufferedImageGraphics = bufferedImage.createGraphics();
		
		for (Image image : spriteList) {
			
			bufferedImageGraphics.drawImage(image, 0, 0, null);
			
		}
		
		return bufferedImage;
		
	}
	
}