package Moteur;

import java.*;
import java.util.*;
//import package ihm

public class Moteur{
  
	public static final int CASELIBRE = 0;
	public static final int CASEEMPOISONNEE = 1;
	public static final int CASEOCCUPEE = 2;
  
  public Moteur(){
  
  }
  
  public boolean coup_valide(int [][] gaufre, int i,int j){
    if ( i<0 || j<0  || i > gaufre.length || j> gaufre[0].length || gaufre[i][j] == CASEOCCUPEE) {
      return false;
    }
    else if(i == 0 && j ==0 && (gaufre[i+1][j]==CASELIBRE || gaufre[i][j+1]==CASELIBRE) ){
    	return false;
    }
    else{
      return true;
    }
  }
  
  public boolean coup_perdant(int [][] gaufre, int i, int j){
    if ( i<0 || j<0  || i > gaufre.length || j> gaufre[0].length || gaufre[i][j] != CASEEMPOISONNEE) {
      return false;
    }
    else{
      return true;
    }
  }
}
