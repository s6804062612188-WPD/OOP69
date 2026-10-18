package week09;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

class ImageLoader {
	// IMAGE PATHS
	public static final String BACKGROUND_PATH = "src/week09/images/bg.png";
	public static final String BIRD_PATH       = "src/week09/images/red.png";
	public static final String PIG_PATH        = "src/week09/images/kingpig.png";

	public static Image load(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (Exception e) {
			System.out.println("Could not load image: " + path);
			return null;
		}
	}
}
