import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Traitements {
	public static void permute_Yeux(String emp, String oeild,String oeilg) throws IOException {


		BufferedImage img1 = ImageIO.read (new File (emp));
		BufferedImage oeilD = ImageIO.read (new File (oeild));
		BufferedImage oeilG = ImageIO.read (new File (oeilg));

		Graphics2D g2d = img1.createGraphics();
		/*		Graphics2D g2d1 = oeilD.createGraphics();
		Graphics2D g2d2 = oeilG.createGraphics();
		 */
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);


		g2d.drawImage(oeilD, 0, 0, null); //dessin de l'oeil droit sur le gauche
		g2d.drawImage(oeilG, img1.getWidth()/2, 0, null);// copie de l'oeil gauche sur le droit

		g2d.dispose();

		ImageIO.write(img1, "jpg", new File(emp+"(permutation1).jpg"));


	}

	public static void permute_Yeux2(String emp, String oeild,String oeilg) throws IOException {


		BufferedImage img1 = ImageIO.read (new File (emp));
		BufferedImage oeilG = ImageIO.read (new File (oeilg));
		BufferedImage oeilD = ImageIO.read (new File (oeild));

		Graphics2D g2d = img1.createGraphics();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

		//Symetrie de l'oeil droit qui a ete dessiner
		g2d.drawImage (oeilD, img1.getWidth(),0, (img1.getWidth()/2),img1.getHeight(), // la place de la destination
				0,0,(img1.getWidth())/2, img1.getHeight(), null);
		//Symetrie de l'oeil gauche qui a ete dessiner
		g2d.drawImage (oeilG, (img1.getWidth()/2),0, 0,img1.getHeight(), // la place de la destination
				0,0,(img1.getWidth())/2, img1.getHeight(), null);

		g2d.dispose();

		ImageIO.write(img1, "jpg", new File(emp+"(mpermutation2).jpg"));


	}


	public static void symetrie_faciale(String emp) {
		BufferedImage image = null;

		try {
			image = ImageIO.read (new File (emp));

			// recuperer un objet Graphics pour pouvoir dessiner sur l'image
			// nous recuperons en fait un objet Graphics2D, qui offre bien plus
			// de fonctionnalite qu'un simple objet Graphics
			Graphics2D g = (Graphics2D)image.getGraphics();

			// active le lissage des formes
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			/*
			 * 2 - Dessin dans l'image
			 */
			int a=image.getWidth();
			int b=image.getHeight();

			/*copie de la partie gauche vers la partie droite*/
			g.drawImage (image, a,0, (a/2),b, // la place de la destination
					0,0,(a)/2, b, null);  // la place de la source

			// types possibles : jpeg, png, gif
			ImageIO.write(image, "jpg", new File(emp+"(symetrie).jpg"));

		} catch(IOException e) {
			System.err.println("Erreur lors de l'ecriture'image.");
		}
	}


	static void mosaique(String emp[]) throws IOException{


		String emp1=Interface2.getChemin();
		BufferedImage img=null;
		String tab[]=new String [emp.length];

		// boucle sur les arguments (les adresses de visages)

		int x=0;
		for (int i=0;i<emp.length;i++) {
			tab[i]=emp[i];
			
		}


		int a=(int)(Math.sqrt(tab.length));
		int j=0;
		BufferedImage imgfinal=new BufferedImage (a*50,a*50, BufferedImage.TYPE_INT_RGB);
		for (int i=0;i<a+2;i++) {

			for(j=0;j<a;j++){
				if(x>=emp.length)
					break;
				img=ImageIO.read(new File (tab[x]));
				img=Utilitaires.resize((tab[x]),30,30);

				Graphics2D g = imgfinal.createGraphics();  
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
				g.drawImage(img, i*40, j*40, null);  
				g.dispose();

				x++;

			}
		}
		ImageIO.write(imgfinal, "jpg", new File(emp1+"\\Zmosaique.jpg"));
	}
}
