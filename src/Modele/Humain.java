package Modele;

import Moteur.Coup;
import Moteur.Moteur;

public class Humain extends Joueur{
	String pseudo;
	public Humain(String pseudo){
		super();
		this.pseudo = pseudo;
	}
	
	public Humain(int score,String pseudo) {
		super(score);
		this.pseudo = pseudo;
	}
	@Override
	public Coup jouer(Moteur m) {
		
		// TODO Auto-generated method stub
		return null;
	}

}
