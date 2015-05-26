package IHM;


import java.awt.Point;
import java.awt.event.*;

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
				if(estCoupPossible(new DepRang(e.getX(), Direction.DROITE)))
				{
					decaleLigne(y,Direction.DROITE);//faire dans IHM
				}
			}
			else(e.x = N-1)
			{
				if(verif(x,y, Direction.GAUCHE))
				{
					decaleLigne(y,Direction.GAUCHE);//faire dans IHM
				}
			}
			else if(e.y = 0)
			{

				if(verif(x,y, Direction.BAS))
				{
					decaleLigne(x,Direction.BAS);//faire dans IHM
				}
			}
			else(e.x = N-1)
			{
				if(verif(x,y, Direction.BAS))
				{
					decaleLigne(x,Direction.BAS);//faire dans IHM
				}
			}
			else
			{
				liste<Point> pt = coupJoueur(e.getX(),e.getY());
				if(pt != vide)
				{
					save = new Point(e.getX(),e.getY());
					afficherCoupPossible(pt);//IHM
					pionSelected = true;

				}
			}
		aire.repaint();
		}
		else
		{
			int result = joue_Coup(save, e.getX(), e.getY());
			if(result != 0)
			{
				afficherCoup(save, e.getX(), e.getY(), result);//IHM
				pionSelected = false;
			}
		}
	}

	if (p.x != 0 || p.y != 0) {		

		try {
			Thread.sleep(2000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		aire.repaint();
	}
}

// Lors d'une pression de bouton, on change de message
public void mousePressed(MouseEvent e) {}

// Il faut aussi une implementation pour les autres methodes de l'interface
public void mouseEntered(MouseEvent e) {}
public void mouseExited(MouseEvent e) {}

public void mouseReleased(MouseEvent e) {}
}
