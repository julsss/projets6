package IHM;


import java.awt.Point;
import java.awt.event.*;

import Joueur.OrdiFacile;

class EcouteurDeSouris implements MouseListener {
    AireDeDessin aire;

    // Ecouteur de souris a besoin de connaitre l'aire de dessin car elle doit
    // changer le message que celle ci affiche
    public EcouteurDeSouris(AireDeDessin a) {
        aire = a;
    }
	
	 public void mouseClicked(MouseEvent e) {
	}
	
    // Lors d'une pression de bouton, on change de message
    public void mousePressed(MouseEvent e) {
    	
    }
    // Il faut aussi une implementation pour les autres methodes de l'interface
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
   
    public void mouseReleased(MouseEvent e) {	
		
    }
}
