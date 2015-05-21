package Moteur;

import java.awt.Point;

public class DepPion extends Coup{
	Point depart;
	Point arrive;
	
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
	
	
	
}
