package Moteur;


import Moteur.Moteur.Direction;

public class DepRang extends Coup {
	
	Direction dir;
	int rang;
	
	public DepRang(Direction dir,int rang ){
		this.dir = dir;
		this.rang = rang;
	}
	
	public String toString(){
		String res = new String();
		
		res += "Deplacement rang =";
		res += " range : "+ rang;
		res += " direction : "+ dir;
		res += "\n";
		
		return res;
	}
}
