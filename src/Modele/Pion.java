package Modele;

import java.awt.Point;

public class Pion {
	private boolean vide;
	private Point first;
	private int couleur;
	
	public Pion()
	{
		setVide(true);
	}
	public Pion(Point d, int color, int nbJoueur)
	{
		setVide(false);
		if (nbJoueur == 2)
		{
			if ((d.x == 0 || d.x == 1 || d.x == 2) && (d.y == 0 || d.y == 1 || d.y == 2))
			{
				first = new Point(0, 0);
			}
			else 
			{
				first = new Point(4,4);
			}
		}
		else if(nbJoueur == 4)
		{
			if ((d.x == 0 || d.x == 1) && (d.y == 0 || d.y == 1))
			{
				first = new Point(0, 0);
			}
			else if ((d.x == 4 || d.x == 3) && (d.y == 4 || d.y == 3))
			{
				first = new Point(4,4);
			}
			else if ((d.x == 0 || d.x == 1) && (d.y == 3 || d.y == 4))
			{
				first = new Point(0, 4);
			}
			else
			{
				first = new Point(4, 0);
			}
		}
		else
		{
			System.out.println("le nombre de Joueur n'est pas 2 ou 4(erreur pion)");
		}
		couleur = color;
	}
	public int getCouleur() {
		return couleur;
	}
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	public Point getFirst() {
		return first;
	}
	public void setFirst(Point first) {
		this.first = first;
	}
	public boolean isVide() {
		return vide;
	}
	public void setVide(boolean vide) {
		this.vide = vide;
	}

}
