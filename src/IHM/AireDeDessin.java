package IHM;
import java.awt.*;
import java.awt.image.*;

import Joueur.Humain;
import Joueur.Joueur;
import Joueur.OrdiFacile;
import Moteur.Moteur;

import javax.swing.*;

import Moteur.Plateau;


class AireDeDessin extends JComponent {

	private static final long serialVersionUID = 1L;
	private Plateau gaufre;
    private BufferedImage image;
    private Moteur moteur;
    private Historique histo;
    private OrdiFacile ordiFacile;
    private Humain humain;
	
	public AireDeDessin(int largeur, int hauteur, Plateau g) {
		image = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);;
// a déplacer dans moteur		histo = new Historique(gaufre);
		moteur = new Moteur();
// moteur		ordiFacile = new OrdiFacile();
// moteur		humain = new Humain(false, true);
    }
    
	public Point calculPoint(Point p){
		Point temp = new Point();
		
		Dimension d = getSize();
        int x,y, i, j;
        int largeurCase, hauteurCase;
		
		
		i = (int) p.getX() * gaufre.getNbLigne() / d.width;
		j = (int) p.getY() * gaufre.getNbCol() / d.height;
        
        largeurCase = d.width / gaufre.getNbLigne();
        hauteurCase = d.height / gaufre.getNbCol();
        
        x = (int) i * d.width/gaufre.getNbLigne();
        y = (int) j * d.height/gaufre.getNbCol();
        temp.x = x/largeurCase;
        temp.y = y/hauteurCase;
				
		return temp;
	}

/*
	public OrdiFacile getOrdiFacile() {
		return ordiFacile;
	}


	public void setOrdiFacile(OrdiFacile ordiFacile) {
		this.ordiFacile = ordiFacile;
	}


	public Humain getHumain() {
		return humain;
	}


	public void setHumain(Humain humain) {
		this.humain = humain;
	}

*/
	public void paintComponent(Graphics g) {
        // Graphics 2D est le vrai type de l'objet passe en parametre
        // Le cast permet d'avoir acces a un peu plus de primitives de dessin
/*        int largeurCase, hauteurCase;
        Graphics2D drawable = image.createGraphics();
		g.drawImage(image, 0, 0, null);
		
		Dimension d = getSize();
		largeurCase = d.width / gaufre.getNbLigne();
        hauteurCase = d.height / gaufre.getNbCol();
        
		int width = getSize().width;
        int height = getSize().height;

        drawable.setPaint(Color.white);
        drawable.fillRect(0, 0, width, height);
        drawable.setPaint(Color.black);
		
		//affichage de la grille
		for (int i=0; i<=gaufre.getNbLigne(); i++) {
			drawable.drawLine(i*width/gaufre.getNbLigne(), 0, i*width/gaufre.getNbLigne(), height);
        }
		
		for (int i=0; i<=gaufre.getNbCol(); i++) {
			drawable.drawLine(0, i*height/gaufre.getNbCol(), width, i*height/gaufre.getNbCol());
        }
                
        //affichage de la case avec le poison
        drawable.setPaint(Color.red);
		drawable.drawOval(40, 10, 15, 15);
		int[][] workspace = gaufre.getGaufre();
		
		for (int i = 0; i < gaufre.getNbLigne(); i++) {
			for(int j = 0; j < gaufre.getNbCol(); j++){
				if (workspace[i][j] == Moteur.CASEOCCUPEE) {
					drawable.setPaint(Color.black);
					drawable.drawLine(i*largeurCase, j*hauteurCase, i*largeurCase + largeurCase, j*hauteurCase+hauteurCase);
					drawable.drawLine(i*largeurCase, j*hauteurCase+hauteurCase, i*largeurCase + largeurCase, j*hauteurCase);
				}
			}
		}
		
		g.drawImage(image, 0, 0, null);
*/		
	}
/*
	public Plateau getPateau() {
		return gaufre;
	}


	public void setPlateau(Plateau gaufre) {
		this.gaufre = gaufre;
	}

*/
	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}


	public Moteur getMoteur() {
		return moteur;
	}


	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}

/*
	public Historique getHisto() {
		return histo;
	}


	public void setHisto(Historique histo) {
		this.histo = histo;
	}
	
	*/
}
