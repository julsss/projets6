package Moteur;


import Moteur.Moteur.Direction;

public class DepRang extends Coup {
	
	Direction dir;
	int i,j;
	
	public DepRang(Direction dir,int i, int j ){
		this.dir = dir;
		this.i = i;
		this.j = j;
	}
}
