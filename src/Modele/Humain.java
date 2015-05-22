package Modele;

import Moteur.Moteur;

public class Humain extends Joueur{
	
	public Humain(){
		super(false);
	}
	
	public Humain(boolean b, boolean t, Moteur m){
		super(false, true,m);
	}
	
	public Humain(int score, boolean b) {
		super(score, b);
	}

}
