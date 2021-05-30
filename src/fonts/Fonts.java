package fonts;

import java.awt.Font;
import java.awt.FontFormatException;

import java.io.IOException;

import main.Globals;

public class Fonts {
	
	public static Font pixeloidSans16Font     = createFont("pixeloid_sans", 16);
	public static Font pixeloidSansBold16Font = new Font("Courier New", Font.TRUETYPE_FONT, 16);//createFont("pixeloid_sans_bold", 16);
	
	public static Font createFont(String fontName, int fontSize) {
		
		try {
			
//			return Font.createFont(Font.TRUETYPE_FONT, new File(Fonts.class.getResource(Globals.FONTS + fontName + Globals.OTF).getPath()));
			return Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream(Globals.FONTS + fontName + Globals.OTF));
			
		} catch (IOException | FontFormatException e) {
			
			e.printStackTrace();
			
			return null;
			
		}
		
	}
	
}