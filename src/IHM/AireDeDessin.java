package IHM;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

import Moteur.Moteur.Direction;

import java.util.*;

class AireDeDessin extends JComponent {
	int N = 7;
    int[][] pl;
    BufferedImage image;
	
	public AireDeDessin(BufferedImage nouvelleImage) {
		image = nouvelleImage;
		pl = new int[7][7];
        pl[1][4] = 1;
        pl[1][3] = 1;
        pl[2][4] = 1;
        pl[2][5] = 1;
        pl[3][5] = 1;
        pl[3][1] = 2;
        pl[4][1] = 2;
        pl[4][2] = 2;
        pl[5][2] = 2;
        pl[5][3] = 2;
        
    }
    
    public Point calculPoint(Point p){
        Point temp = new Point();
        
        Dimension d = getSize();
        int x,y, i, j;
        int largeurCase, hauteurCase;
        
        
        i = (int) p.getX() * N/ d.width;
        j = (int) p.getY() * N / d.height;
        
        largeurCase = d.width / N;
        hauteurCase = d.height / N;
        
        x = (int) i * d.width/N;
        y = (int) j * d.height/N;
        temp.x = x/largeurCase;
        temp.y = y/hauteurCase;
        
        return temp;
    }
    
    public void afficherCoup(Point p, int x, int y,int joueur)
    {
        pl[p.x][p.y] = 0;
        pl[x][y] = joueur;
        
    }
    
    public void afficherCoupPossible(ArrayList<Point>pt)
    {
        Point p = new Point();
        while(!pt.isEmpty())
        {
            p = pt.remove(pt.size()-1);
            pl[p.x][p.y] = -1;
        }
    }
    
    public void decaleLigne(int i,Direction d)
    {
        if(d == Direction.DROITE)
        {
            for(int j  = N-2; j > 1; j--)
            {
                pl[i][j] = pl[i][j-1];
            }
            pl[i][1] = 0 ;
        }
        else if(d == Direction.GAUCHE)
        {
            for(int j  = 1; j < N-3; j++)
            {
                pl[i][j] = pl[i][j+1];
            }
            pl[i][N-2] = 0 ;
        }
        else if(d == Direction.HAUT)
        {
            for(int j  = N-2; j > 1; j--)
            {
                pl[j][i] = pl[j-1][i];
            }
            pl[1][i] = 0 ;
        }
        else if(d == Direction.BAS)
        {
            for(int j  = 1; j < N-3; j++)
            {
                pl[j][i] = pl[j+1][i];
            }
            pl[N-2][i] = 0 ;
        }
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
				if (pl[k][l] == 1) {
					//joueur 1
					drawable.setPaint(Color.red);
					drawable.fillOval((k*largeurCase) + largeurCase/2, (l*hauteurCase) + hauteurCase/2, 15, 15);
				} else if (pl[k][l] == 2) {
					//joueur 2
					drawable.setPaint(Color.gray);
					drawable.fillOval((k*largeurCase) + largeurCase/2, (l*hauteurCase) + hauteurCase/2, 15, 15);
				}
                else if (pl[k][l] == -1)
                {
                    drawable.setPaint(Color.blue);
                    drawable.fillRect(k*largeurCase, l * hauteurCase, width, height);
                }
			}
		}

		g.drawImage(image, 0, 0, null);
		
	}
}
