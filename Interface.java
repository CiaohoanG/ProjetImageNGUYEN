import java.io.IOException;

public class Interface {

	static void interaction(String [] ag) throws IOException {

		System.out.println("********Bienvenue sur Visacrea********\n");

		String pEmp=" ",pEmp1="";
		int num_1=0;
		//pour faire de la mosaique
		String chemin[]=new String [ag.length];

		// boucle sur les arguments (les adresses de visages)
		for (int i=0;i<ag.length;i++) {
			pEmp=ag[i];
			System.out.println(pEmp);
			
			chemin[i]=pEmp;
			
			
			try {
				//donner une taille fixe au image 500*500
				EcrireImage.write ( Utilitaires.resize(pEmp,500,500),"jpg", pEmp);
				
				//Etape 1 : on redimensionne l'image pour enlever le fond
				pEmp1=pEmp+"(bis).jpg";
				EcrireImage.write ( Utilitaires.redimensionImage (pEmp),"jpg", pEmp1);
				
				
				
				
				//Etape 2 : obtenir rectangle oeil
				String rect=pEmp1+"(REC_o).jpg"; // car format image utilise est jpg
				num_1=Getligne.getLigne (pEmp1);;
				EcrireImage.write (Yeux.obtenirRecOeil(pEmp1,num_1),"jpg", rect);
			
				
				//Etape 3 : obtenir oeil_gauche
				String oeilG=pEmp1+"(o_g).jpg"; // car format image utilise est jpg
				 
				EcrireImage.write (Yeux.Oeil(rect,"G",num_1),"jpg", oeilG);
				
				String oeilD=pEmp1+"(o_d).jpg";
				EcrireImage.write (Yeux.Oeil(rect,"D",num_1),"jpg", oeilD);
				
				
				//Etape 4 : permutation yeux
				
				Traitements.permute_Yeux(rect,oeilD,oeilG);
				Traitements.permute_Yeux2(rect+"(permutation1).jpg",oeilG,oeilD);
				
				// Etape 5 : symetrie faciale
				Traitements.symetrie_faciale(pEmp); 
				
				
				
			} catch (IOException e) {
				System.out.println("ERREUR");
				e.printStackTrace();
			}

			
		}
		//Etape 6 : Mosaiques d'images
		Traitements.mosaique(chemin);
		
	}


}
