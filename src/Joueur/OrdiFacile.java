package Joueur;


//import moteur.*;
import java.awt.*;
import java.*;
import java.util.*;
import java.lang.Math;
//import package ihm

import Moteur.Moteur;

public class OrdiFacile extends Joueur {
  
  public OrdiFacile(){
    super(true, false);
  }
  
  public OrdiFacile(int score){
    super(score,true);
  }
  
  private static Random rand = new Random();
  
  public static Point facile(int [][] gaufre, Moteur m){
    Point coup = new Point();
    boolean valide = false;
    //boolean perdant= false;
    while(!valide){
      coup.x= rand.nextInt(gaufre.length);
      coup.y = rand.nextInt(gaufre[0].length);
      
      //valide = m.coup_valide(gaufre, coup.x, coup.y);
      
      //perdant = m.coup_perdant(gaufre, coup.x, coup.y);
    }
    return coup;
  }
}
