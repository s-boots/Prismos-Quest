package sprites.gui;

import java.awt.Image;
import java.awt.Toolkit;

import main.Globals;

public class CursorSprites {
	
	public static Image cursorSprite = Toolkit.getDefaultToolkit().createImage(CursorSprites.class.getResource(Globals.GUI_SPRITES + "cursor" + Globals.PNG));
	
}