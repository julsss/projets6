package Moteur;

import java.awt.Point;

public class Gaufre{

	private int [][] gaufre;
	private int nbLigne, nbCol;

	public Gaufre(int n,int m)
	{
		nbLigne = n;
		nbCol = m;
		gaufre = new int[nbLigne][nbCol];
		gaufre[0][0] = Moteur.CASEEMPOISONNEE;
	}

	public void rayeCase(Point p){
		rayeCase(p.x, p.y);
	}
	
	public void rayeCase(int x, int y)
	{
		for(int i = x; i < nbLigne; i++)
		{
			for(int j = y; j < nbCol; j++)
			{
				gaufre[i][j] = Moteur.CASEOCCUPEE;
			}
		}
	}
	
	public void reinit(){
		gaufre = new int[nbLigne][nbCol];
		gaufre[0][0] = Moteur.CASEEMPOISONNEE;
	}
	
	public int[][] getGaufre() {
		return gaufre;
	}
	
	public void setGaufre(int[][] g){
		gaufre = g;
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
}
