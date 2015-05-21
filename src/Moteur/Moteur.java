package Moteur;

import java.awt.Point;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;

import Modele.Historique;
import Modele.Joueur;
import Modele.Plateau;

//import package ihm

public class Moteur{

	public static enum Direction {
		HAUT,
		BAS,
		GAUCHE,
		DROITE
	}

	public static enum Case {
		LIBRE,
		PJ1,
		PJ2
	}
	int nbBillej1;
	int nbBillej2;
	Joueur j1;
	Joueur j2;
	public static final int N=5;
	ArrayList<ArrayList<Case>> plateau;
	public Moteur ( Joueur j1, Joueur j2){
		init_plateau();
		nbBillej1 = 5;
		nbBillej2 = 5;
		this.j1=j1;
		this.j2=j2;
	}

	public int getNbBillej1() {
		return nbBillej1;
	}

	public void setNbBillej1(int nbBillej1) {
		this.nbBillej1 = nbBillej1;
	}

	public int getNbBillej2() {
		return nbBillej2;
	}

	public void setNbBillej2(int nbBillej2) {
		this.nbBillej2 = nbBillej2;
	}

	private void init_plateau() {

		plateau = new ArrayList<ArrayList<Case>>();

		for(int i=0; i<N; i++){
			ArrayList<Case> tmp = new ArrayList<Case>();
			for(int j=0; j<N; j++){
				switch(i){
				case 0 : 
					if(j == 2 || j == 3){
						tmp.add(Case.PJ2);
					}
					else{
						tmp.add(Case.LIBRE);
					}

					break;
				case 1 : 
					if( j == 3 || j == 4){
						tmp.add(Case.PJ2);
					}
					else{
						tmp.add(Case.LIBRE);
					}
					break;
				case 2 : 
					if( j == 0){
						tmp.add(Case.PJ1);
					}
					else if( j == 4){
						tmp.add(Case.PJ2);
					}
					else{
						tmp.add(Case.LIBRE);
					}
					break;
				case 3 :
					if(j == 0 || j == 1){
						tmp.add(Case.PJ1);
					}
					else{
						tmp.add(Case.LIBRE);
					}
					break;
				case 4 : 
					if(j == 1 || j == 2){
						tmp.add(Case.PJ1);
					}
					else{
						tmp.add(Case.LIBRE);
					}
					break;
				default : break;

				}
				plateau.add(tmp);
			}
			System.out.println(plateau);
		}
	}

	public boolean estCoupPossible(Joueur j1, Coup c){
		return estCoupPossible(j1,(DepPion)c) || estCoupPossible(j1,(DepRang)c);
	}

	public boolean estCoupPossible(Joueur j1, DepPion c){


		if(c.getDepart().x< N && c.getDepart().y< N && c.getDepart().x> 0 && c.getDepart().y> 0
				&& (plateau.get(c.getDepart().x).get(c.getDepart().y) == Case.PJ1 && j1 == this.j1
				|| plateau.get(c.getDepart().x).get(c.getDepart().y) == Case.PJ2 && j1 == this.j2)
				&& plateau.get(c.getArrive().x).get(c.getArrive().y) == Case.LIBRE)
		{
			//Verif coup pour joueur1
			if(j1 == this.j1 && c.getDepart().y == c.getArrive().y && c.getDepart().x-1 == c.getArrive().x)
				return true;
			if(j1 == this.j1 && c.getDepart().x == c.getArrive().x && c.getDepart().y+1 == c.getArrive().y)
				return true;
			if(j1 == this.j1 && c.getDepart().x-1 == c.getArrive().x && c.getDepart().y+1 == c.getArrive().y)
				return true;

			//Verif coup pour joueur2
			if(j1 == this.j2 && c.getDepart().y == c.getArrive().y && c.getDepart().x+1 == c.getArrive().x)
				return true;
			if(j1 == this.j2 && c.getDepart().x == c.getArrive().x && c.getDepart().y-1 == c.getArrive().y)
				return true;
			if(j1 == this.j2 && c.getDepart().x+1 == c.getArrive().x && c.getDepart().y-1 == c.getArrive().y)
				return true;

		}
		return false;
	}

	public boolean estCoupPossible(Joueur j1, DepRang c){

		if( c.i > 0 && c.i < N && c.j > 0 && c.j <N){
			//ajout if avec fonction teste joueur + pion dans la rangé
			if(billedansrange(j1,c)){
				if(c.dir == Direction.BAS && plateau.get(N-1).get(c.j) == Case.LIBRE){
					return true;
				}
				if(c.dir == Direction.HAUT && plateau.get(0).get(c.j) == Case.LIBRE){
					return true;
				}
				if(c.dir == Direction.GAUCHE && plateau.get(c.i).get(0) == Case.LIBRE){
					return true;
				}
				if(c.dir == Direction.DROITE && plateau.get(c.i).get(N-1) == Case.LIBRE){
					return true;
				}
			}
			
		}
		return false;
	}

	private boolean billedansrange(Joueur j1, DepRang c) {
		for(int i=0;i<N;i++){
			if((c.dir == Direction.BAS || c.dir == Direction.HAUT) && plateau.get(i).get(c.j) == Case.PJ1 && j1 == this.j1 
					||( c.dir == Direction.BAS || c.dir == Direction.HAUT) && plateau.get(i).get(c.j) == Case.PJ2 && j1 == this.j2){
				return true;
			}
			else if((c.dir == Direction.GAUCHE || c.dir == Direction.DROITE) && plateau.get(c.i).get(i) == Case.PJ1 && j1 == this.j1 
					||( c.dir == Direction.GAUCHE || c.dir == Direction.DROITE) && plateau.get(c.i).get(i) == Case.PJ2 && j1 == this.j2){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Coup> listeCoupPossible(Joueur j1){
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		ArrayList<Point> listeCase =  listeCaseJoueur(j1);
		Point c;
		
		for(int i = 0; i < listeCase.size(); i++){
			c = listeCase.get(i);
			if(plateau.get(0).get(c.y) == Case.LIBRE){
				listeCoup.add(new DepRang(Direction.HAUT, c.x, c.y));
			}
			else if(plateau.get(N-1).get(c.y) == Case.LIBRE){
				listeCoup.add(new DepRang(Direction.BAS, c.x, c.y));
			}
			else if(plateau.get(c.x).get(0) == Case.LIBRE){
				listeCoup.add(new DepRang(Direction.GAUCHE, c.x, c.y));
			}
			else if(plateau.get(c.x).get(N-1) == Case.LIBRE){
				listeCoup.add(new DepRang(Direction.DROITE, c.x, c.y));
			}
			if(j1 == this.j1 && plateau.get(c.x).get(c.y+1) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x,c.y +1)));
			if(j1 == this.j1 && plateau.get(c.x+1).get(c.y+1) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x+1,c.y +1)));
			if(j1 == this.j1 && plateau.get(c.x+1).get(c.y) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x+1,c.y)));
			//Verif coup pour joueur2
			if(j1 == this.j2 && plateau.get(c.x).get(c.y-1) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x,c.y -1)));
			if(j1 == this.j2 && plateau.get(c.x-1).get(c.y-1) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x-1,c.y -1)));
			if(j1 == this.j2 && plateau.get(c.x-1).get(c.y) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x-1,c.y)));
		}
		return listeCoup;
	}
	
	
	public ArrayList<Point> listeCaseJoueur(Joueur j1){
		ArrayList<Point> listeCase = new ArrayList<Point>();
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(this.j1 == j1 && plateau.get(i).get(j) == Case.PJ1){
					listeCase.add(new Point(i,j));
				}
			}
		}
		
		return listeCase;
	}
}

