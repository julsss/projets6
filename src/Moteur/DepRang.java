package Moteur;


import Moteur.Moteur.Direction;

public class DepRang extends Coup {
	
	private Direction dir;
	private int rang;

	public boolean isValidant() {
		return validant;
	}

	public void setValidant(boolean validant) {
		this.validant = validant;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	private boolean validant;
	
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
