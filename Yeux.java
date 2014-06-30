import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Yeux {
	static BufferedImage obtenirRecOeil(String emp, int n) {
		BufferedImage img=null;

		try {
			img = ImageIO.read (new File (emp));
		
			int a=img.getWidth(); // largeur 
			int b=img.getHeight()/2; // hauteur 
		
			img= img.getSubimage(0, n, a, b-n );  /*coupe l'image */
		
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	
	static BufferedImage Oeil(String emp, String str, int n){
		
		BufferedImage img=null;
		
		try {
			img = ImageIO.read (new File (emp));
		
		
			int a=img.getWidth()/2; // largeur 
			int b=img.getHeight(); // hauteur 
		
		
			if(str=="D"){
				img= img.getSubimage(a, 0, a, (int)(b*(0.5)) );
			}else if(str=="G"){
				img= img.getSubimage(0, 0, a, (int)(b*(0.5)) );
			}
		
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return img;
		
	}


}
