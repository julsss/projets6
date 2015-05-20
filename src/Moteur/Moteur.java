package Moteur;

import Modele.Historique;
import Modele.Humain;
import Modele.Joueur;
import Modele.OrdiFacile;
import Modele.Plateau;

//import package ihm

public class Moteur{
  
	private Historique historique;
	private Plateau plateau;
	private Joueur joueur1, joueur2, joueur3, joueur4;
	public static final int PIONNOIR = 0;
	public static final int PIONBLANC = 1;
	public static final int PIONBLEU = 2;
	public static final int PIONVERT = 3;
	
	public static final int HUMANPLAYER = 100;
	public static final int IAEasy = 101;
	/*
	 * les boolean j1, j2, j3, j4 sont égale à HUMANPLAYER si et seulement le joueur est un humain
	 * l'IA facile est désignait par IAEasy
	 * rajoueter une constante pour les autres IA
	 */
 public Moteur(int nbJoueur, int j1, int j2){
	 plateau = new Plateau(5, 5, nbJoueur);
	 historique = new Historique();
	 if(nbJoueur == 2)
	 {
		joueur1 = creationJoueur(j1); 
		joueur2 = creationJoueur(j2);
	 }
 }
  
 public Moteur(int nbJoueur, int j1, int j2, int j3, int j4){
	 plateau = new Plateau(5, 5, nbJoueur);
	 historique = new Historique();
	 if(nbJoueur == 4)
	 {
		joueur1 = creationJoueur(j1); 
		joueur2 = creationJoueur(j2);
		joueur3 = creationJoueur(j3);
		joueur4 = creationJoueur(j4);
	 }
 }
  
  
private Joueur creationJoueur(int j) {
	if(j == HUMANPLAYER)
	{
		return new Humain(false, true);
	}
	else if(j == IAEasy)
	{
		return new OrdiFacile();
	}
	System.out.println("erreur type de joueur inconnu");
	return null;
	
}




public Historique getHistorique() {
	return historique;
}
public void setHistorique(Historique historique) {
	this.historique = historique;
}
public Plateau getPlateau() {
	return plateau;
}
public void setPlateau(Plateau plateau) {
	this.plateau = plateau;
}

public Joueur getJoueur1() {
	return joueur1;
}

public void setJoueur1(Joueur joueur1) {
	this.joueur1 = joueur1;
}

public Joueur getJoueur2() {
	return joueur2;
}

public void setJoueur2(Joueur joueur2) {
	this.joueur2 = joueur2;
}

public Joueur getJoueur3() {
	return joueur3;
}

public void setJoueur3(Joueur joueur3) {
	this.joueur3 = joueur3;
}

public Joueur getJoueur4() {
	return joueur4;
}

public void setJoueur4(Joueur joueur4) {
	this.joueur4 = joueur4;
}
}
