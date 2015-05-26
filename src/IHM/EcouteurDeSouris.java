package IHM;


import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

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
		boolean passe = false;
		Point p = aire.calculPoint(new Point(e.getX(), e.getY()));
		
		System.out.println("X :" + p.x + " Y  " + p.y + "selection : " + pionSelected + "ok " + aire.mot.estCoupPossible(save, p));
		if(!pionSelected)
		{
			
			if(p.x == -1){
				if(aire.mot.estCoupPossible(new DepRang(Direction.DROITE, p.y)))
				{
					aire.decaleLigne(p.y,Direction.DROITE);//faire dans IHM
				}
			}
			else if(p.x ==  aire.N-2){
				if(aire.mot.estCoupPossible(new DepRang( Direction.GAUCHE, p.y))){
					aire.decaleLigne(p.y,Direction.GAUCHE);//faire dans IHM
				}
			}
			else if(p.y == -1)
			{

				if(aire.mot.estCoupPossible(new DepRang( Direction.BAS,p.x)))
				{
					aire.decaleLigne(p.x,Direction.BAS);//faire dans IHM
				}
			}
			else if(p.y == aire.N-2)
			{
				if(aire.mot.estCoupPossible(new DepRang( Direction.BAS,p.x)))
				{
					aire.decaleLigne(p.x,Direction.BAS);//faire dans IHM
				}
			}
			else
			{
				ArrayList<Point> pt = aire.mot.listeCoupPossible(new Point(p.y,p.x));
				while(!pt.isEmpty())
				{
					save = new Point(p.y,p.x);
					aire.afficherCoupPossible(pt);//IHM
					pionSelected = true;
					passe = true;
				}
			}
		}
		else if(aire.mot.estCoupPossible(save, p))
		{
			aire.mot.joue_coup(save , new Point(p.y,p.x));
			aire.afficherCoup(new Point(save.y+1, save.x+1), p.x+1, p.y+1);//IHM
			pionSelected = false;
		}
		if (!passe && pionSelected)
			pionSelected = false;
		
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
