package Moteur;


import Moteur.Moteur.Direction;

public class DepRang extends Coup {
	
	Direction dir;
	int rang;
	
	public DepRang(Direction dir,int rang ){
		this.dir = dir;
		this.rang = rang;
	}
}
