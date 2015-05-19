package Joueur;

public class Humain extends Joueur{
	
	public Humain(){
		super(false);
	}
	
	public Humain(boolean b, boolean t){
		super(false, true);
	}
	
	public Humain(int score, boolean b) {
		super(score, b);
	}

}
