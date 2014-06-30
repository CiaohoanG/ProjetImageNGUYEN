import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utilitaires {
	
	//fonction qui enleve le fond de l'image
	static BufferedImage redimensionImage (String emp) {
		BufferedImage img=null;  
		int w=0,h=0;
		double x=0f,y=0,z=0,t=0;

		try {
			img = ImageIO.read (new File (emp));
			
			w=img.getWidth();
			h=img.getHeight();
			x=w*(1-0.77);// va faire moins 77%
			y=x;
			z=w*(1-0.45); // va faire moins 45%
			t=h*(1-0.30); // va faire moins 30%
			img=img.getSubimage((int)x, (int)y, (int)z, (int)t);

			
		
		} catch (IOException e) {
			System.out.println("ERREUR");
			e.printStackTrace();
		}

		return img;
	}
	
	//fonction pour redimensionner mon BufferedImage
	public static BufferedImage resize(String emp, int newW, int newH) throws IOException {  
		BufferedImage img = null;
		
		img = ImageIO.read (new File (emp));
		

		int w = img.getWidth();  
		int h = img.getHeight();  
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
		Graphics2D g = dimg.createGraphics();  
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
		g.dispose();  
		return dimg;  
	} 
}
