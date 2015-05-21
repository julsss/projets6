package Modele;

import java.util.*;

public class Historique{

  private LinkedList<Coup> coup;
  private Stack<Coup> annuler;
  
  public Historique(){
    coup = new LinkedList<Coup>();
    annuler = new Stack<Coup>();
  }

 public void ajouter(Coup p){
	 annuler = new Stack<Coup>();
	 coup.addLast(p);
 }

 public Coup annuler()
 {
	  if(!coup.isEmpty())
	  {
		  annuler.push(coup.getLast());
		  return coup.removeLast();
		  
	  }
	  return null;
}	

public Coup refaire()
{
	if(!annuler.isEmpty())
	{
		Coup p = annuler.pop();
		coup.addLast(p);
		return p;
	}		
	return null;
}
}