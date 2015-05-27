package Modele;

import Moteur.Coup;
import Moteur.Moteur;

public class Humain extends Joueur{
	
	public Humain(){
		super(false);	
	}
	
	public Humain(boolean b, boolean t){
		super(b, t);
	}
	
	public Humain(int score, boolean b) {
		super(score, b);
	}

	@Override
	public Coup jouer(Moteur m) {
		
		// TODO Auto-generated method stub
		return null;
	}

}
