import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Getligne {
	
	static int getLigne (String emp) {
		BufferedImage img=null;
		try {
			img = ImageIO.read (new File (emp));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Color c;
		int r,g,bl,i,j;
		int a=img.getWidth();
		int b=img.getHeight()/2;
		int sommegris=0;
		int sum;

		int[] tab= new int[a];
		int[] tabS=new int[b];
		int[] tabD=new int[b];

		for (j=0;j<b;j=j+1) {
			for (i=0;i<a;i=i+1) {
				c = new Color(img.getRGB(i,j)); 
				r=c.getRed();
				g=c.getGreen();
				bl=c.getBlue();
				//int nivgris=(6*r+3*g+bl)/10;
				int nivgris=(r+g+bl)/3;
				tab[i] =nivgris;
				sommegris=sommegris+tab[i];

			}
			sum=sommegris/(a+1);
			tabS[j]=sum;
			//System.out.println(tabS[j]);
			sommegris=0; /*pour remettre somme a zero avant chaque nouvelle boucle*/
		}

		for(j=1;j<b;j++) {
			tabD[j]=tabS[j]-tabS[j-1];
			//System.out.println(tabD[j]);
		}
		return (minimum(tabD));
	}
	
	

	//Fonction qui retourne le min du tableau
	static int minimum(int [] tab) {
		int min=tab[0] ;
		int i;
		int n=0;

		for (i=0;i<tab.length;i++) {
			if (tab[i]<min) {
				min=tab [i];
				n=i;
			}
		}

		return n+1; // il manque une ligne qd on a fait la difference.
	}
}
