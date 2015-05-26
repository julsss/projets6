package IHM;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;

class AireDeDessin extends JComponent {
	int N = 5;
	Plateau pl;
    BufferedImage image;
	
	public AireDeDessin(BufferedImage nouvelleImage) {
		image = nouvelleImage;
		pl = new Plateau(N);
    }
    
   public void afficherCroix(Point p) {
		//Graphics2D drawable = image.createGraphics();
		Dimension d = getSize();
        int x,y, i, j;
        int largeurCase, hauteurCase;
		
		i = (int) p.getX() * N / d.width;
		j = (int) p.getY() * N / d.height;
        
        largeurCase = d.width / N;
        hauteurCase = d.height / N;
        
        x = (int) i * largeurCase;
        y = (int) j * hauteurCase;	
        
        System.out.println("Coordonnees : " + i + " " + j);
    }

	public void paintComponent(Graphics g) {
        // Graphics 2D est le vrai type de l'objet passe en parametre
        // Le cast permet d'avoir acces a un peu plus de primitives de dessin
        int largeurCase, hauteurCase;
        Graphics2D drawable = image.createGraphics();
		g.drawImage(image, 0, 0, null);
		
		Dimension d = getSize();
		largeurCase = d.width / N;
        hauteurCase = d.height / N;
        
		int width = getSize().width;
        int height = getSize().height;

        drawable.setPaint(Color.white);
        drawable.fillRect(0, 0, width, height);
        drawable.setPaint(Color.black);
		
		//affichage de la grille
		for (int i=0; i<=N; i++) {
			drawable.drawLine(i*width/N, 0, i*width/N, height);
			drawable.drawLine(0, i*height/N, width, i*height/N);
        }
		
		for (int k = 0; k < N; k++) {
			for(int l = 0; l < N; l++){
				if (pl.plateau[k][l] == 1) {
					//joueur 1
					drawable.setPaint(Color.red);
					drawable.fillOval((k*largeurCase) + largeurCase/2, (l*hauteurCase) + hauteurCase/2, 15, 15);
				} else if (pl.plateau[k][l] == 2) {
					//joueur 2
					drawable.setPaint(Color.gray);
					drawable.fillOval((k*largeurCase) + largeurCase/2, (l*hauteurCase) + hauteurCase/2, 15, 15);
				}
			}
		}

		g.drawImage(image, 0, 0, null);
		
	}
}
