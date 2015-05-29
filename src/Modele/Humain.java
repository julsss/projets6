package Modele;

import Moteur.Coup;
import Moteur.Moteur;

public class Humain extends Joueur{
	String pseudo;
	public Humain(String pseudo){
		
		super(false);	
		this.pseudo = pseudo;
		
	}
	
	public Humain(boolean b, boolean t, String pseudo){
		super(b, t);
		this.pseudo = pseudo;
	}
	
	public Humain(int score, boolean b, String pseudo) {
		super(score, b);
		this.pseudo = pseudo;
	}

	@Override
	public Coup jouer(Moteur m) {
		
		// TODO Auto-generated method stub
		return null;
	}

}
