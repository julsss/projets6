package Moteur;


import Moteur.Moteur.Direction;

public class DepRang extends Coup {
	
	public Direction dir;
	public int rang;
	boolean validant;
	
	public DepRang(Direction dir,int rang, boolean v ){
		this.dir = dir;
		this.rang = rang;
		this.validant = v;
	}
	
	public String toString(){
		String res = new String();
		
		res += "Deplacement rang =";
		res += " range : "+ rang;
		res += " direction : "+ dir;
		res += " validant : "+ validant;
		res += "\n";
		
		return res;
	}
}
