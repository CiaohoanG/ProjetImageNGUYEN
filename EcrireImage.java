import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class EcrireImage {

	static void write(BufferedImage img, String format, String name)throws IOException {

		try { 
			ImageIO.write(img, format, new File(name));
		} catch (IOException e) {
			System.out.println("ERREUR");
			e.printStackTrace();
		}
	}
}
