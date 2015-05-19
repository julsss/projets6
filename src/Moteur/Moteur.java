package Moteur;

//import package ihm

public class Moteur{
  
	private Historique historique;
	private Plateau plateau;
	public static final int PIONNOIR = 0;
	public static final int PIONBLANC = 1;
	public static final int PIONBLEU = 2;
	public static final int PIONVERT = 3;
  public Moteur(int nbJoueur){
	  plateau = new Plateau(5, 5, nbJoueur);
	  historique = new Historique(plateau);
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
