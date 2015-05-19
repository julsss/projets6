package Modele;

import java.util.*;
import java.awt.*;

//TODO a refaire
public class Historique{

  private LinkedList<Point> coup;
  private Stack<Point> annuler;
  Plateau gaufre;
  
  public Historique(Plateau g){
	gaufre = g;
    coup = new LinkedList<Point>();
    annuler = new Stack<Point>();
  }

  public void ajouter(Point p){
    coup.addLast(p);
  }

  public void annuler(){
	if(!coup.isEmpty()){
		annuler.push(coup.removeLast());
	    gaufre.reinit();
	    Iterator<Point> iter = coup.iterator();
	
	    while(iter.hasNext()){
	    	Point tmp = (Point) iter.next();
	    	//gaufre.rayeCase(tmp.x, tmp.y);
	    }
	}
  }

  public void refaire(){
	if(!annuler.isEmpty()){
	    Point p = annuler.pop();
	    ajouter(p);
	   // gaufre.rayeCase(p);
	}
  }
}