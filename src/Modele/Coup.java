package Modele;

import java.awt.Point;

public class Coup {
	public static final String mvPion = "pion";
	public static final String mvPlateau = "plateau";
	private String typeMouv;
	private Point depart, destination;
	private int couleur;
	
	public Coup(String mouv, Point dep, Point dest, int couleur)
	{
		typeMouv = mouv;
		depart = dep;
		destination = dest;
	}
	
	
	
	public String getTypeMouv() {
		return typeMouv;
	}
	public void setTypeMouv(String typeMouv) {
		this.typeMouv = typeMouv;
	}
	public Point getDepart() {
		return depart;
	}
	public void setDepart(Point depart) {
		this.depart = depart;
	}
	public Point getDestination() {
		return destination;
	}
	public void setDestination(Point destination) {
		this.destination = destination;
	}



	public int getCouleur() {
		return couleur;
	}



	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	
}
