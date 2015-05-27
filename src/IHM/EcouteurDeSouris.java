package IHM;


import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import Moteur.DepRang;
import Moteur.Moteur.Direction;

class EcouteurDeSouris implements MouseListener {
	AireDeDessin aire;
	Point pion1;

	// Ecouteur de souris a besoin de connaitre l'aire de dessin car elle doit
	// changer le message que celle ci affiche
	public EcouteurDeSouris(AireDeDessin a) {
		aire = a;
	}

	public void mouseClicked(MouseEvent e)
	{
		boolean passe = false;
		Point p = aire.calculPoint(new Point(e.getX(), e.getY()));
		System.out.println("x = " + p.x + " y = " + p.y + " val = " + aire.moteur.plateau.get(p.x).get(p.y));
		if(pion1 != null){
			aire.doMove(pion1,);
		}else{

		}
		
		aire.repaint();
	}

	// Lors d'une pression de bouton, on change de message
	public void mousePressed(MouseEvent e) {}

	// Il faut aussi une implementation pour les autres methodes de l'interface
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}
