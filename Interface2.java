
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.io.File;

public class Interface2 extends JFrame implements ActionListener  {

	static JFrame fenetre=new JFrame();
	static JPanel panPrincipal=new JPanel();
	static JPanel pan1=new JPanel();
	static JPanel pan2=new JPanel();
	static String titre = "IK3 Visage";

	static JButton bouton1=new JButton(" Choisir une image ");
	static JButton bouton2=new JButton(" Choisir un dossier ");
	
	static JButton bdetection=new JButton(" Detection yeux ");
	static JButton boeild=new JButton(" Oeil droit ");
	static JButton boeilg=new JButton(" Oeil gauche ");
	static JButton bpermutation=new JButton(" Permutation yeux ");
	static JButton bsymetrie=new JButton(" Symetrie facial ");
	
	
	static JFileChooser chooser = new JFileChooser( );
	static String emp="";
	static String pEmp1="";
	static String rect=pEmp1+"(REC_o).jpg";
	static String oeilG= pEmp1+"(o_g).jpg";
	static String oeilD= pEmp1+"(o_d).jpg";
	static int num1;
	static String chemin;
	
	public static String getChemin() {
		return chemin;
	}

	public static void setChemin(String chemin) {
		Interface2.chemin = chemin;
	}

	void interaction() {

		fenetre.setTitle("Projet Visage");
		fenetre.setSize(1100, 700);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.add(panPrincipal);
		panPrincipal.setBorder(new TitledBorder(null, titre, TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION));
		panPrincipal.setBackground(Color.white);
		panPrincipal.setLayout(null);

		panPrincipal.add(bouton1);
		panPrincipal.add(bouton2);
		bouton1.setBounds(350, 50, 200, 30);
		bouton2.setBounds(550, 50, 200, 30);
		bouton1.addActionListener(this);
		bouton2.addActionListener(this);

		panPrincipal.add(pan1);
		panPrincipal.add(pan2);


		pan1.setBackground(Color.black);
		pan1.setBounds(30, 100,500,500);

		pan2.setBackground(Color.gray);
		pan2.setBounds(550, 100,500,500);

		/*
		 * Ajout des boutons
		 */
		
		panPrincipal.add(bdetection);
		panPrincipal.add(boeild);
		panPrincipal.add(boeilg);
		panPrincipal.add(bpermutation);
		panPrincipal.add(bsymetrie);
		bdetection.setBounds(220, 610, 150, 30);
		boeild.setBounds(380, 610, 150, 30);
		boeilg.setBounds(540, 610, 150, 30);
		bpermutation.setBounds(700, 610, 150, 30);
		bsymetrie.setBounds(860, 610, 150, 30);
		
		bdetection.setVisible(false);
		boeild.setVisible(false);
		boeilg.setVisible(false);
		bsymetrie.setVisible(false);
		bpermutation.setVisible(false);
		
		bdetection.addActionListener(this);
		boeild.addActionListener(this);
		boeilg.addActionListener(this);
		bsymetrie.addActionListener(this);
		bpermutation.addActionListener(this);
		
	}

	/*
	 * Deux choix au debut : Choisir une image ou choisir un dossier
	 * 	*Si on choisi une image on doit apres avoir choisi l'image faire les etape suivante 
	 * dans l'ordre : clique sur Detection Yeux d'abord
	 * 		ensuite : clique sur Oeil Droit
	 * 		ensuite : clique sur Oeil Gauche
	 * 		ensuite : clique sur Permutation Yeux
	 * 		ensuite : clique sur Symetrie faciale
	 * 
	 *	*Si on choisi un dossier :
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		/*
		 *  Action quand on clique sur Choisir image
		 */
		if(e.getSource()==bouton1){
			System.out.println("Boutonnnnnnnnnnn 1");
			
			int returnVal = chooser.showOpenDialog(fenetre);
			 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	pan1.removeAll();
                JOptionPane.showMessageDialog(null,"Vous avez selectionne: " + 
 
                    chooser.getSelectedFile( ).getPath( ));
 
                this.emp =chooser.getSelectedFile( ).getPath( );                
              
				try {
					//donner une taille fixe au image 500*500
					EcrireImage.write ( Utilitaires.resize(emp,500,500),"jpg", emp);
					
					//on affiche les boutons
					bdetection.setVisible(true);
					boeild.setVisible(true);
					boeilg.setVisible(true);
					bsymetrie.setVisible(true);
					bpermutation.setVisible(true);
					
					/*
					 * Dessiner l'image selectionee dans pan1
					 */
					
					BufferedImage monImage=null;
					monImage = ImageIO.read(new File (emp));
					JLabel p=new JLabel(new ImageIcon(monImage));
			       
			        pan1.setLayout(new BorderLayout());
			        pan1.add(p,BorderLayout.CENTER);
			      
					pan1.validate();
				     
				} catch (IOException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
            } else {
            	JOptionPane.showMessageDialog(null,"Vous n'avez rien selectionne.");
            }
		
		/*
		 * action quand on clique sur detection Yeux
		 */
		}else if(e.getSource()==bdetection){
			
			System.out.println("Detection yeux");
			BufferedImage monImage=null;
			try {
				
				//Etape 1 : on redimensionne l'image pour enlever le fond
				this.pEmp1=emp+"(bis).jpg";
				EcrireImage.write ( Utilitaires.redimensionImage (emp),"jpg", pEmp1);
				
				//Etape 2 : obtenir rectangle oeil
				this.rect=pEmp1+"(REC_o).jpg"; // car format image utilise est jpg
				this.num1=Getligne.getLigne (pEmp1);;
				EcrireImage.write (Yeux.obtenirRecOeil(pEmp1,num1),"jpg", rect);
			
				
				monImage = ImageIO.read(new File (this.rect));
			
				JLabel p=new JLabel(new ImageIcon(monImage));
				

		        pan2.setLayout(new BorderLayout());
		        pan2.removeAll();
		        pan2.add(p,BorderLayout.CENTER);
		        
		        pan2.validate();
		        pan2.repaint();
		        
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		/*
		 * lorsqu'on clique sur les bouton Oeil Gauche	
		 */
		}else if(e.getSource()==boeilg){
			
			//Etape 3 : obtenir oeil_gauche
			 this.oeilG=this.pEmp1+"(o_g).jpg";
			 BufferedImage monImage=null;
			try {
				EcrireImage.write (Yeux.Oeil(this.rect,"G",num1),"jpg", this.oeilG);
			
				monImage = ImageIO.read(new File (this.oeilG));
				
				JLabel p=new JLabel(new ImageIcon(monImage));
				

		        pan2.setLayout(new BorderLayout());
		        pan2.removeAll();
		        pan2.add(p,BorderLayout.CENTER);
		        
		        pan2.validate();
		        pan2.repaint();
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		/*
		 * lorsqu'on clique sur les bouton Oeil Droit	
		 */
		}else if(e.getSource()==boeild){
			//Etape 3 : obtenir oeil_gauche
			 this.oeilD=this.pEmp1+"(o_d).jpg";
			 BufferedImage monImage=null;
			try {
				
				EcrireImage.write (Yeux.Oeil(this.rect,"D",num1),"jpg", this.oeilD);
			
				monImage = ImageIO.read(new File (this.oeilD));
				
				JLabel p=new JLabel(new ImageIcon(monImage));
				

		        pan2.setLayout(new BorderLayout());
		        pan2.removeAll();
		        pan2.add(p,BorderLayout.CENTER);
		        
		        pan2.validate();
		        pan2.repaint();
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		/*
		 * lorsqu'on clique sur le bouton  Permutation des Yeux	
		 */
		}else if(e.getSource()==bpermutation){
			
			//Etape 4 : permutation yeux
			BufferedImage monImage=null;
			try {
				Traitements.permute_Yeux(rect,oeilD,oeilG);
				Traitements.permute_Yeux2(rect+"(permutation1).jpg",oeilG,oeilD);
				
				monImage = ImageIO.read(new File (this.rect+"(permutation1).jpg(mpermutation2).jpg"));
				
				JLabel p=new JLabel(new ImageIcon(monImage));
				

		        pan2.setLayout(new BorderLayout());
		        pan2.removeAll();
		        pan2.add(p,BorderLayout.CENTER);
		        
		        pan2.validate();
		        pan2.repaint();
			
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
		/*
		 * lorqu'on clique sur le bouton Symetrie facial	
		 */
		}else if(e.getSource()==bsymetrie){
			
			// Etape 5 : symetrie faciale
			Traitements.symetrie_faciale(emp); 
			
			/*
			 * Dessiner l'image selectionee dans pan1
			 */
			
			BufferedImage monImage=null;
			try {
				monImage = ImageIO.read(new File (emp+"(symetrie).jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JLabel p=new JLabel(new ImageIcon(monImage));
	       
	        pan2.setLayout(new BorderLayout());
	        pan2.removeAll();
	        pan2.add(p,BorderLayout.CENTER);
	      
			pan2.validate();
		     
		
			
		}else if(e.getSource()==bouton2){
			
			this.chemin=btnChoixDossier(e);
			
			//System.out.println(chemin);
			
			File F=new File(this.chemin);
			File[] list=F.listFiles();
			String str[] =  F.list();
			if (list != null){
                for ( int i = 0; i < str.length; i++) {
                        
                     str[i]=this.chemin+"\\"+str[i];
                     System.out.println(str[i]);
                } 
                
                //System.out.println(str);
                BufferedImage monImage=null;
            	//Etape 6 : Mosaiques d'images
        		try {
				
        			Traitements.mosaique(str);
        			String a=this.chemin+"\\"+"Zmosaique.jpg";
        			BufferedImage xy=Utilitaires.resize(a,500,500);
        			EcrireImage.write ( xy,"jpg", a);
        			monImage = ImageIO.read(new File (this.chemin+"\\"+"Zmosaique.jpg"));
        			JLabel p=new JLabel(new ImageIcon(monImage));
        		       
        	        pan1.setLayout(new BorderLayout());
        	        pan1.removeAll();
        	        
        	        pan1.add(p,BorderLayout.CENTER);
        	      
        			pan1.validate();
        			
        			/*
        			 * Les traitements
        			 */
        			for ( int i = 0; i < str.length; i++) {
                        
        				//donner une taille fixe au image 500*500
        				EcrireImage.write ( Utilitaires.resize(str[i],500,500),"jpg", str[i]);
        				
        				//Etape 1 : on redimensionne l'image pour enlever le fond
        				pEmp1=str[i]+"(bis).jpg";
        				EcrireImage.write ( Utilitaires.redimensionImage (str[i]),"jpg", pEmp1);
        				
        				
        				
        				
        				//Etape 2 : obtenir rectangle oeil
        				String rect=pEmp1+"(REC_o).jpg"; // car format image utilise est jpg
        				int num_1=Getligne.getLigne (pEmp1);;
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
        				Traitements.symetrie_faciale(str[i]); 
        				
                        
                   }
        			
        			/*
        			 * afficher la mosaique avec les resultats obtenue
        			 */
        			
        			
				
        			
        		} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
        		
        		
            } else {
                System.err.println(F + " : Erreur de lecture.");
            }
				
		}
		
		
		
		
		
	}
	

	public String btnChoixDossier( ActionEvent evt)	{
		JFileChooser choix = new JFileChooser();
		choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int retour = choix.showOpenDialog(new JFrame());
		if(retour == JFileChooser.APPROVE_OPTION) {
			// un fichier a ete choisi ( sortie par OK)
			return choix.getSelectedFile().getAbsolutePath();// chemin absolu du dossier choisi
		} else {
			return "Le dossier n'a pas ete choisi!"; 
		}
	}
		
}



