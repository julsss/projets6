package Moteur;

import Modele.Historique;
import Modele.Plateau;

//import package ihm

public class Moteur{
  
	private Historique historique;
	private Plateau plateau;
	
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
		creationJoueur(j1); 
	 }
 }
  
 public Moteur(int nbJoueur, int j1, int j2, int j3, int j4){
	 plateau = new Plateau(5, 5, nbJoueur);
	 historique = new Historique();
	 if(nbJoueur == 2)
	 {
		creationJoueur(j1); 
	 }
 }
  
  
private void creationJoueur(int j1) {
	// TODO Auto-generated method stub
	
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
}
