package Modele;

import java.util.*;

public class Historique{

	private boolean supprAnnuler;
	private LinkedList<Moteur.Coup> coup;
	private Stack<Moteur.Coup> annuler;


	public Historique(){
		coup = new LinkedList<Moteur.Coup>();
		annuler = new Stack<Moteur.Coup>();
		supprAnnuler = true;
	}

	public void ajouter(Moteur.Coup p){
		if(supprAnnuler)
		{
			annuler = new Stack<Moteur.Coup>();
		}
		coup.addFirst(p);
		supprAnnuler = false;
	}

	public LinkedList<Moteur.Coup> getCoup() {
		return coup;
	}

	public void setCoup(LinkedList<Moteur.Coup> coup) {
		this.coup = coup;
	}

	public Stack<Moteur.Coup> getAnnuler() {
		return annuler;
	}

	public void setAnnuler(Stack<Moteur.Coup> annuler) {
		this.annuler = annuler;
	}

	public Moteur.Coup annuler()
	{
		if(!coup.isEmpty())
		{
			annuler.push(coup.getFirst());
			return coup.removeFirst();

		}
		return null;
	}	

	public Moteur.Coup refaire()
	{
		if(!annuler.isEmpty())
		{
			Moteur.Coup p = annuler.pop();
			coup.addFirst(p);
			supprAnnuler = true;
			return p;
		}		
		return null;
	}

	public boolean isSupprAnnuler() {
		return supprAnnuler;
	}

	public void setSupprAnnuler(boolean supprAnnuler) {
		this.supprAnnuler = supprAnnuler;
	}
}