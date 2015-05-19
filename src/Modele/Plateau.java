package Modele;

import java.awt.Point;

import Moteur.Moteur;

public class Plateau{

	private Pion [][] plateau;
	private int nbLigne, nbCol;
	private int nbPionNoirWin, nbPionBlancWin, nbPionBleuWin, nbPionVertWin;
	private int nbJoueur;

	public Plateau(int n,int m, int nbJoueur)
	{
		nbLigne = n;
		nbCol = m;
		setNbPionBlancWin(0);
		setNbPionBlancWin(0);
		setNbPionBleuWin(0);
		setNbPionVertWin(0);
		setNbJoueur(nbJoueur);
		plateau = new Pion[nbLigne][nbCol];
	}

	public void posePionCase(Point p, int couleur){
		posePionCase(p.x, p.y, couleur);
	}
	
	public void posePionCase(int x, int y, int couleur)
	{
		if(couleur == Moteur.PIONNOIR)
		{
			plateau[x][y] = new Pion(new Point(x,y),Moteur.PIONNOIR, nbJoueur);
		}
		else if(couleur == Moteur.PIONBLANC)
		{
			plateau[x][y] = new Pion(new Point(x,y),Moteur.PIONBLANC, nbJoueur);
		}
		else if(couleur == Moteur.PIONBLEU)
		{
			plateau[x][y] = new Pion(new Point(x,y),Moteur.PIONBLEU, nbJoueur);
		}
		else if(couleur == Moteur.PIONVERT)
		{
			plateau[x][y] = new Pion(new Point(x,y),Moteur.PIONVERT, nbJoueur);
		}else
		{
			plateau[x][y] = new Pion();
		}
	}
	
	public void movePion(Point coorPion, Point coorTarget)
	{
		boolean okX = false;
		boolean okY = false;
		Pion pion = plateau[coorPion.x][coorPion.y];
		if(coorPion.x == coorTarget.x && coorTarget.y == coorPion.y)
		{
			System.out.println("les coordonnée de la cible et celle du pion sont les même");
		}
		else
		{
			if(!pion.isVide() && plateau[coorTarget.x][coorTarget.y].isVide())
			{
				Point first = pion.getFirst();
				if(first.x == 4)
				{
					if(coorPion.x >= coorTarget.x)
					{
						okX = true;
					}
				}
				else if(first.x == 0)
				{
					if(coorPion.x <= coorTarget.x)
					{
						okX = true;
					}
				}
				if(first.y == 4)
				{
					if(coorPion.y >= coorTarget.y)
					{
						okY = true;
					}
				}
				else if(first.y == 0)
				{
					if(coorPion.y <= coorTarget.y)
					{
						okY = true;
					}
				}
				if(okX && okY && !(coorPion.x == coorTarget.x && coorPion.y == coorTarget.y))
				{
					plateau[coorTarget.x][coorTarget.y] = plateau[coorPion.x][coorPion.y];
					plateau[coorPion.x][coorPion.y] = new Pion();
				}
			}
			else
			{
				System.out.println("le pion n'existe pas(GameBoard)");
			}
			deleteWinPawn();
		}
	}
	
	private void deleteWinPawn() {
		Pion zeroZero = plateau[0][0];
		Pion zeroQuatre = plateau[0][4];
		Pion quatreZero = plateau[4][0];
		Pion quatreQuatre = plateau[4][4];
		
		if(!zeroZero.isVide() && zeroZero.getFirst().x == 4 && zeroZero.getFirst().y == 4)
		{
			if(zeroZero.getCouleur() == Moteur.PIONBLANC)
				nbPionBlancWin++;
			else if (zeroZero.getCouleur() == Moteur.PIONBLEU)
				nbPionBleuWin++;
			else if (zeroZero.getCouleur() == Moteur.PIONNOIR)
				nbPionNoirWin++;
			else if (zeroZero.getCouleur() == Moteur.PIONVERT)
				nbPionVertWin++;
			plateau[0][0] = new Pion();
		}
		else if(!zeroQuatre.isVide() && zeroQuatre.getFirst().x == 4 && zeroQuatre.getFirst().y == 0)
		{
			if(zeroQuatre.getCouleur() == Moteur.PIONBLANC)
				nbPionBlancWin++;
			else if (zeroQuatre.getCouleur() == Moteur.PIONBLEU)
				nbPionBleuWin++;
			else if (zeroQuatre.getCouleur() == Moteur.PIONNOIR)
				nbPionNoirWin++;
			else if (zeroQuatre.getCouleur() == Moteur.PIONVERT)
				nbPionVertWin++;
			plateau[0][4] = new Pion();
		}
		else if(!quatreQuatre.isVide() && quatreQuatre.getFirst().x == 4 && quatreQuatre.getFirst().y == 0)
		{
			if(quatreQuatre.getCouleur() == Moteur.PIONBLANC)
				nbPionBlancWin++;
			else if (quatreQuatre.getCouleur() == Moteur.PIONBLEU)
				nbPionBleuWin++;
			else if (quatreQuatre.getCouleur() == Moteur.PIONNOIR)
				nbPionNoirWin++;
			else if (quatreQuatre.getCouleur() == Moteur.PIONVERT)
				nbPionVertWin++;
			plateau[0][0] = new Pion();
		}
		else if(!quatreZero.isVide() && quatreZero.getFirst().x == 0 && quatreZero.getFirst().y == 4)
		{
			if(quatreZero.getCouleur() == Moteur.PIONBLANC)
				nbPionBlancWin++;
			else if (quatreZero.getCouleur() == Moteur.PIONBLEU)
				nbPionBleuWin++;
			else if (quatreZero.getCouleur() == Moteur.PIONNOIR)
				nbPionNoirWin++;
			else if (quatreZero.getCouleur() == Moteur.PIONVERT)
				nbPionVertWin++;
			plateau[0][4] = new Pion();
		}
	}

	public void moveGameBoard(Point p, Point p1, int color)
	{
		 moveGameBoard(p.x, p.y, p1.x, p1.y, color);
	}
	/*
	 * x et y sont les coordonée de la premiere case qui doit etre pousse 
	 * x1 et y1 sont les coordonnée de la suivante sur la ligne que l'on doit pousse
	 * color est la couleur du joueur qui joue le coup
	 */
	public void moveGameBoard(int x, int y, int x1, int y1, int color)
	{
		if ((x == 0 && y == y1) && autorizeMove(color, x, y, x1, y1))
		{			
			for (int i = nbLigne -2; i >= 0; i--)
			{
				plateau[i+1][y] = plateau[i][y];
			}
			plateau[x][y] = new Pion();
		}
		else if(x == nbLigne -1  && y == y1 && autorizeMove(color, x, y, x1, y1))
		{			
			for (int i = 1; i < nbLigne; i++)
			{
				plateau[i-1][y] = plateau[i][y];
			}
			plateau[x][y] = new Pion();
		}
		else if (x == x1 && y == 0 && autorizeMove(color, x, y, x1, y1))
		{			
			for (int i = nbCol -2; i >= 0; i--)
			{
				plateau[x][i+1] = plateau[x][i];
			}
			plateau[x][y] = new Pion();
		}
		else if(x == x1 && y == nbCol - 1 && autorizeMove(color, x, y, x1, y1))
		{			
			for (int i = 1; i < nbCol; i++)
			{
				plateau[x][i-1] = plateau[x][i];
			}
			plateau[x][y] = new Pion();
		}
		else 
		{
			System.out.println("coup erroné");
		}
	}
	
	private boolean autorizeMove(int color, int x, int y, int x1, int y1) {
		if ((x == 0 && y == y1) && plateau[nbLigne-1][y].isVide())
		{
			for (int i = nbLigne -1; i >= 0; i--)
			{
				if((plateau[i][y]).getCouleur() == color)
					return true;
			}
		}
		else if(x == nbLigne -1  && y == y1 && plateau[0][y].isVide())
		{
			for (int i = 0; i < nbLigne; i++)
			{
				if((plateau[i][y]).getCouleur() == color)
					return true;
			}
		}
		else if (x == x1 && y == 0 && plateau[x][nbCol-1].isVide())
		{
			for (int i = nbCol -1; i >= 0; i--)
			{
				if((plateau[i][y]).getCouleur() == color)
					return true;
			}
		}
		else if(x == x1 && y == nbCol - 1 && plateau[x][0].isVide())
		{
			for (int i = 0; i < nbCol; i++)
			{
				if((plateau[i][y]).getCouleur() == color)
					return true;
			}
		}
		return false;
	}
	
	public void reinit(){
		plateau = new Pion[nbLigne][nbCol];
		setNbPionBlancWin(0);
		setNbPionNoirWin(0);
		setNbPionBleuWin(0);
		setNbPionVertWin(0);
	}
	
	public Pion[][] getPlateau() {
		return plateau;
	}
	
	public void setPlateau(Pion[][] g){
		plateau = g;
	}
	
	public int getNbLigne() {
		return nbLigne;
	}

	public void setNbLigne(int nbLigne) {
		this.nbLigne = nbLigne;
	}

	public int getNbCol() {
		return nbCol;
	}

	public void setNbCol(int nbCol) {
		this.nbCol = nbCol;
	}

	public int getNbPionNoirWin() {
		return nbPionNoirWin;
	}

	public void setNbPionNoirWin(int nbPionNoirWin) {
		this.nbPionNoirWin = nbPionNoirWin;
	}

	public int getNbPionBlancWin() {
		return nbPionBlancWin;
	}

	public void setNbPionBlancWin(int nbPionBlancWin) {
		this.nbPionBlancWin = nbPionBlancWin;
	}

	public int getNbPionBleuWin() {
		return nbPionBleuWin;
	}

	public void setNbPionBleuWin(int nbPionBleuWin) {
		this.nbPionBleuWin = nbPionBleuWin;
	}

	public int getNbPionVertWin() {
		return nbPionVertWin;
	}

	public void setNbPionVertWin(int nbPionVertWin) {
		this.nbPionVertWin = nbPionVertWin;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}
}
