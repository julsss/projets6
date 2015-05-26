package IHM;


import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import Modele.OrdiFacile;
import Moteur.DepRang;
import Moteur.Moteur.Direction;

class EcouteurDeSouris implements MouseListener {
	AireDeDessin aire;
	Point save;
	boolean pionSelected;

	// Ecouteur de souris a besoin de connaitre l'aire de dessin car elle doit
	// changer le message que celle ci affiche
	public EcouteurDeSouris(AireDeDessin a) {
		aire = a;
		pionSelected = false;
		save = new Point();
	}

	public void mouseClicked(MouseEvent e)
	{
		if(!pionSelected)
		{

			if(e.getX() == 0){
				if(aire.mot.estCoupPossible(new DepRang(Direction.DROITE, e.getX())))
				{
					aire.decaleLigne(e.getY(),Direction.DROITE);//faire dans IHM
				}
			}
			else if(e.getX() ==  aire.N-1){
				if(aire.mot.estCoupPossible(new DepRang( Direction.GAUCHE, e.getY()))){
					aire.decaleLigne(e.getY(),Direction.GAUCHE);//faire dans IHM
				}
			}
			else if(e.getY() == 0)
			{

				if(aire.mot.estCoupPossible(new DepRang( Direction.BAS,e.getX())))
				{
					aire.decaleLigne(e.getX(),Direction.BAS);//faire dans IHM
				}
			}
			else if(e.getY() == aire.N-1)
			{
				if(aire.mot.estCoupPossible(new DepRang( Direction.BAS,e.getX())))
				{
					aire.decaleLigne(e.getX(),Direction.BAS);//faire dans IHM
				}
			}
			else
			{
				ArrayList<Point> pt = aire.mot.listeCoupPossible(new Point(e.getX(),e.getY()));
				if(!pt.isEmpty())
				{
					save = new Point(e.getX(),e.getY());
					aire.afficherCoupPossible(pt);//IHM
					pionSelected = true;

				}
			}
		}
		else if(aire.mot.estCoupPossible(save, new Point( e.getX(), e.getY())))
		{
			aire.mot.joue_coup(save , new Point(e.getX(), e.getY()));
			aire.afficherCoup(save, e.getX(), e.getY());//IHM
			pionSelected = false;
		}
		aire.repaint();
		/*if (p.x != 0 || p.y != 0) {		

			try {
				Thread.sleep(2000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			aire.repaint();
		}*/
	}

	// Lors d'une pression de bouton, on change de message
	public void mousePressed(MouseEvent e) {}

	// Il faut aussi une implementation pour les autres methodes de l'interface
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}
