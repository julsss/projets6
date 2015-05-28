package Moteur;

import java.awt.Point;

public class DepPion extends Coup{
	private Point depart;
	private Point arrive;
	
	public DepPion(Point dep, Point arr){
		depart = dep;
		arrive = arr;
	}

	public Point getDepart() {
		return depart;
	}

	public void setDepart(Point depart) {
		this.depart = depart;
	}

	public Point getArrive() {
		return arrive;
	}

	public void setArrive(Point arrive) {
		this.arrive = arrive;
	}
	
	public String toString(){
		String res = new String();
		
		res += "Deplacement Pion =";
		res += " depart : "+ depart.x +":"+ depart.y;
		res += " arrive : "+ arrive.x +":"+ arrive.y;
		res += "\n";
		
		return res;
	}
	
}
