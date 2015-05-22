package Modele;

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

}
