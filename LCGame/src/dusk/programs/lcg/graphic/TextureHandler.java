package dusk.programs.lcg.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TextureHandler {
	
	public static HashMap<String,BufferedImage> textures = new HashMap<String,BufferedImage>();
	public static BufferedImage spritesheet;
	
	public static void init() {
		try {
			spritesheet = ImageIO.read(new File("resources/textures/spritesheet.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadTexture(String textureName, int x, int y, int w, int h) {
		try {
			textures.put(textureName, spritesheet.getSubimage(x, y, w, h));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getTexture(String textureName) {
		return textures.get(textureName);
	}
	
	public static void handleTextures() {
		loadTexture("Floor", 0, 0, 32, 32);
		loadTexture("Door_Up", 0, 32, 32, 32);
		loadTexture("Door_Right", 32, 32, 32, 32);
		loadTexture("Door_Down", 64, 32, 32, 32);
		loadTexture("Door_Left", 96, 32, 32, 32);
	}

}
