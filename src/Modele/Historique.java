package Modele;

import java.util.*;

public class Historique{

  private LinkedList<Moteur.Coup> coup;
  private Stack<Moteur.Coup> annuler;
  
  public Historique(){
    coup = new LinkedList<Moteur.Coup>();
    annuler = new Stack<Moteur.Coup>();
  }

 public void ajouter(Moteur.Coup p){
	 annuler = new Stack<Moteur.Coup>();
	 coup.addLast(p);
 }

 public Moteur.Coup annuler()
 {
	  if(!coup.isEmpty())
	  {
		  annuler.push(coup.getLast());
		  return coup.removeLast();
		  
	  }
	  return null;
}	

public Moteur.Coup refaire()
{
	if(!annuler.isEmpty())
	{
		Moteur.Coup p = annuler.pop();
		coup.addLast(p);
		return p;
	}		
	return null;
}
}