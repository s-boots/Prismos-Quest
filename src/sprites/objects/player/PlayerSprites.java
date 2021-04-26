package sprites.objects.player;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JLabel;

import graphics.AnimationCycle;

import main.Globals;

public class PlayerSprites {
	
	public static AnimationCycle getAnimationCycle() {
		
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		
		Image imageUp1 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "7" + Globals.PNG));
		Image imageUp2 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "6" + Globals.PNG));
		Image imageUp3 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "0" + Globals.PNG));
//		Image imageUp4 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "6" + Globals.PNG));
		Image imageUp4 = imageUp2;
//		Image imageUp5 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "7" + Globals.PNG));
		Image imageUp5 = imageUp1;
		Image imageUp6 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "26" + Globals.PNG));
		Image imageUp7 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "15" + Globals.PNG));
//		Image imageUp8 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "26" + Globals.PNG));
		Image imageUp8 = imageUp6;
		
		Image imageDown1 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "13" + Globals.PNG));
		Image imageDown2 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "4" + Globals.PNG));
		Image imageDown3 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "30" + Globals.PNG));
//		Image imageDown4 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "4" + Globals.PNG));
		Image imageDown4 = imageDown2;
//		Image imageDown5 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "13" + Globals.PNG));
		Image imageDown5 = imageDown1;
		Image imageDown6 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "5" + Globals.PNG));
		Image imageDown7 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "19" + Globals.PNG));
//		Image imageDown8 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "5" + Globals.PNG));
		Image imageDown8 = imageDown6;
		
		Image imageLeft1 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "18" + Globals.PNG));
		Image imageLeft2 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "31" + Globals.PNG));
		Image imageLeft3 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "22" + Globals.PNG));
		Image imageLeft4 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "9" + Globals.PNG));
		Image imageLeft5 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "14" + Globals.PNG));
		Image imageLeft6 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "10" + Globals.PNG));
		Image imageLeft7 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "2" + Globals.PNG));
		Image imageLeft8 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "23" + Globals.PNG));
		
		Image imageRight1 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "27" + Globals.PNG));
		Image imageRight2 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "16" + Globals.PNG));
		Image imageRight3 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "11" + Globals.PNG));
		Image imageRight4 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "8" + Globals.PNG));
		Image imageRight5 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "1" + Globals.PNG));
		Image imageRight6 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "25" + Globals.PNG));
		Image imageRight7 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "21" + Globals.PNG));
		Image imageRight8 = defaultToolkit.createImage(PlayerSprites.class.getResource(Globals.PLAYER_SPRITES + "17" + Globals.PNG));
		
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
				
				imageLeft1,
				imageLeft2,
				imageLeft3,
				imageLeft4,
				imageLeft5,
				imageLeft6,
				imageLeft7,
				imageLeft8,
				
				imageRight1,
				imageRight2,
				imageRight3,
				imageRight4,
				imageRight5,
				imageRight6,
				imageRight7,
				imageRight8
				
		};
		
		// Should Probably Make a Class + Method That Does This Automatically...
		
		MediaTracker mediaTracker = new MediaTracker(new JLabel());
		
		for (int i = 0; i < images.length; i++) {
			
			mediaTracker.addImage(images[i], i);
			
		}
		
		try {
			
			mediaTracker.waitForAll();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
		return new AnimationCycle(images, 85, new int[] {8, 24, 16, 0});
		
	}
	
}