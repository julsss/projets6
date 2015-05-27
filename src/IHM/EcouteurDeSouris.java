package IHM;


import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

class EcouteurDeSouris implements MouseListener, MouseMotionListener {
	AireDeDessin aire;

	// Ecouteur de souris a besoin de connaitre l'aire de dessin car elle doit
	// changer le message que celle ci affiche
	public EcouteurDeSouris(AireDeDessin a) {
		aire = a;
	}

	public void mouseClicked(MouseEvent e) {}

	// Lors d'une pression de bouton, on change de message
	public void mousePressed(MouseEvent e) {
		Point p2 = aire.calculPoint(new Point(e.getX(), e.getY()));
		if(aire.p1 != null){
			aire.doMove(p2);
		}else{
			ArrayList<Point> l = aire.moteur.listeCaseJoueur();
			if(l.contains(p2))
				aire.p1 = p2;
		}
		aire.repaint();
	}

	// Il faut aussi une implementation pour les autres methodes de l'interface
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point p2 = aire.calculPoint(new Point(e.getX(), e.getY()));
		ArrayList<Point> l = aire.moteur.listeCaseJoueur();
		if(l.contains(p2)){
			aire.setSurvol(p2);
		}
		else{
			aire.initSurvols();
		}
	}
}