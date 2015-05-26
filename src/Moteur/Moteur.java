package Moteur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

import Modele.Historique;
import Modele.Humain;
import Modele.Joueur;
import Modele.OrdiFacile;
import Modele.OrdiMoyen;

//import package ihm

public class Moteur{

	public enum Direction {
		HAUT,
		BAS,
		GAUCHE,
		DROITE
	}

	public enum Case {
		LIBRE,
		PJ1,
		PJ2
	}

	int nbBillej1;
	int nbBillej2;

	Humain j1;
	OrdiFacile j2;

	Historique histo;
	public static final int N=5;
	boolean tourj1;
	ArrayList<ArrayList<Case>> plateau = new ArrayList<ArrayList<Case>>();

	public Moteur ( Humain j11, OrdiFacile j22){
		init_plateau();
		nbBillej1 = 5;
		nbBillej2 = 5;
		j1=j11;
		j2=j22;
		histo = new Historique();
		tourj1 = true;
	}
	public Moteur ( Moteur m){
		for(int i=0; i<N; i++){
			ArrayList<Case> tmp = new ArrayList<Case>();
			for(int j=0; j<N; j++){
				tmp.add(m.plateau.get(i).get(j));
			}
			plateau.add(tmp);
		}
		nbBillej1 = m.getNbBillej1();
		nbBillej2 = m.getNbBillej2();
		j1=m.j1;
		j2=m.j2;
		histo = m.histo;
		tourj1 = m.tourj1;
	}

	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j) {
		j1 = (Humain) j;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j) {
		j2 = (OrdiFacile) j;
	}

	public ArrayList<ArrayList<Case>> getPlateau() {
		return plateau;
	}

	public void setPlateau(ArrayList<ArrayList<Case>> plateauset) {
		plateau = plateauset;
	}

	public int getNbBillej1() {
		return nbBillej1;
	}

	public void setNbBillej1(int nbBillej) {
		nbBillej1 = nbBillej;
	}

	public int getNbBillej2() {
		return nbBillej2;
	}

	public void setNbBillej2(int nbBillej) {
		nbBillej2 = nbBillej;
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
			}
			plateau.add(tmp);
		}
	}

	public boolean estCoupPossible(Point p1 , Point p2){
		return estCoupPossible(new DepPion(p1,p2));
	}

	public boolean estCoupPossible(DepPion c){


		if(c.getDepart().x< N && c.getDepart().y< N && c.getDepart().x>= 0 && c.getDepart().y>= 0
				&& c.getArrive().x< N && c.getArrive().y< N && c.getArrive().x>= 0 && c.getArrive().y>= 0
				&& (plateau.get(c.getDepart().x).get(c.getDepart().y) == Case.PJ1 && tourj1
				|| plateau.get(c.getDepart().x).get(c.getDepart().y) == Case.PJ2 && !tourj1)
				&& plateau.get(c.getArrive().x).get(c.getArrive().y) == Case.LIBRE)
		{
			//Verif coup pour joueur1
			if(tourj1 && c.getDepart().y == c.getArrive().y && c.getDepart().x-1 == c.getArrive().x)
				return true;
			if(tourj1 && c.getDepart().x == c.getArrive().x && c.getDepart().y+1 == c.getArrive().y)
				return true;
			if(tourj1 && c.getDepart().x-1 == c.getArrive().x && c.getDepart().y+1 == c.getArrive().y)
				return true;

			//Verif coup pour joueur2
			if(!tourj1 && c.getDepart().y == c.getArrive().y && c.getDepart().x+1 == c.getArrive().x)
				return true;
			if(!tourj1 && c.getDepart().x == c.getArrive().x && c.getDepart().y-1 == c.getArrive().y)
				return true;
			if(!tourj1 && c.getDepart().x+1 == c.getArrive().x && c.getDepart().y-1 == c.getArrive().y)
				return true;

		}
		return false;
	}

	public boolean estCoupPossible(DepRang c){

		//ajout if avec fonction teste joueur + pion dans la rang�
		if(this.billeDansRange(j1,c)){
			if(c.dir == Direction.BAS && plateau.get(N-1).get(c.rang) == Case.LIBRE ){
				if(!histo.getAnnuler().isEmpty() && histo.getAnnuler().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getAnnuler().peek();
					if(dr.dir == Direction.HAUT && dr.rang == c.rang)
						return false;
				}
				return true;
			}
			if(c.dir == Direction.HAUT && plateau.get(0).get(c.rang) == Case.LIBRE){
				if(!histo.getAnnuler().isEmpty() && histo.getAnnuler().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getAnnuler().peek();
					if(dr.dir == Direction.BAS && dr.rang == c.rang)
						return false;
				}
				return true;
			}
			if(c.dir == Direction.GAUCHE && plateau.get(c.rang).get(0) == Case.LIBRE){
				if(!histo.getAnnuler().isEmpty() && histo.getAnnuler().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getAnnuler().peek();
					if(dr.dir == Direction.DROITE && dr.rang == c.rang)
						return false;
				}
				return true;
			}
			if(c.dir == Direction.DROITE && plateau.get(c.rang).get(N-1) == Case.LIBRE){
				if(!histo.getAnnuler().isEmpty() && histo.getAnnuler().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getAnnuler().peek();
					if(dr.dir == Direction.GAUCHE && dr.rang == c.rang)
						return false;
				}
				return true;
			}
		}
		return false;
	}

	private boolean billeDansRange(Joueur j, DepRang c) {
		for(int i=0;i<N;i++){
			if((c.dir == Direction.BAS || c.dir == Direction.HAUT) && plateau.get(i).get(c.rang) == Case.PJ1 && j == j1 
					||( c.dir == Direction.BAS || c.dir == Direction.HAUT) && plateau.get(i).get(c.rang) == Case.PJ2 && j == j2){
				return true;
			}
			else if((c.dir == Direction.GAUCHE || c.dir == Direction.DROITE) && plateau.get(c.rang).get(i) == Case.PJ1 && j == j1 
					||( c.dir == Direction.GAUCHE || c.dir == Direction.DROITE) && plateau.get(c.rang).get(i) == Case.PJ2 && j == j2){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Coup> listeCoupPossible(){
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		ArrayList<Point> listeCase =  listeCaseJoueur();
		Point c;

		for(int i = 0; i < listeCase.size(); i++){
			c = listeCase.get(i);
			//Ajout Rang

			if(plateau.get(0).get(c.y) == Case.LIBRE && estCoupPossible(new DepRang(Direction.HAUT, c.y))){
				listeCoup.add(new DepRang(Direction.HAUT, c.y));
			}
			else if(plateau.get(N-1).get(c.y) == Case.LIBRE && estCoupPossible(new DepRang(Direction.BAS, c.y))){
				listeCoup.add(new DepRang(Direction.BAS, c.y));
			}
			else if(plateau.get(c.x).get(0) == Case.LIBRE && estCoupPossible(new DepRang(Direction.GAUCHE, c.y))){
				listeCoup.add(new DepRang(Direction.GAUCHE, c.x));
			}
			else if(plateau.get(c.x).get(N-1) == Case.LIBRE && estCoupPossible(new DepRang(Direction.DROITE, c.y))){
				listeCoup.add(new DepRang(Direction.DROITE, c.x));
			}

			//Verif joueur1
			if(c.y+1 < N && tourj1 && plateau.get(c.x).get(c.y+1) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x,c.y +1)));
			if(c.x > 0 && c.y+1 < N  && tourj1 && plateau.get(c.x-1).get(c.y+1) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x-1,c.y +1)));
			if(c.x > 0 && tourj1 && plateau.get(c.x-1).get(c.y) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x-1,c.y)));

			//Verif coup pour joueur2
			if(c.y > 0 && !tourj1 && plateau.get(c.x).get(c.y-1) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x,c.y-1)));
			if(c.y > 0 && c.x+1 < N && !tourj1 && plateau.get(c.x+1).get(c.y-1) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x+1,c.y -1)));
			if(c.x+1 < N && !tourj1 && plateau.get(c.x+1).get(c.y) == Case.LIBRE) 
				listeCoup.add(new DepPion(c,new Point(c.x+1,c.y)));
		}
		return listeCoup;
	}


	public ArrayList<Point> listeCoupPossible(Point c){
		ArrayList<Point> listeCoup = new ArrayList<Point>();

		//Verif joueur1
		if(c.y+1 < N && tourj1 && plateau.get(c.x).get(c.y+1) == Case.LIBRE) 
			listeCoup.add(new Point(c.x,c.y +1));
		if(c.x > 0 && c.y+1 < N  && tourj1 && plateau.get(c.x-1).get(c.y+1) == Case.LIBRE) 
			listeCoup.add(new Point(c.x-1,c.y +1));
		if(c.x > 0 && tourj1 && plateau.get(c.x-1).get(c.y) == Case.LIBRE) 
			listeCoup.add(new Point(c.x-1,c.y));

		//Verif coup pour joueur2
		if(c.y > 0 && !tourj1 && plateau.get(c.x).get(c.y-1) == Case.LIBRE) 
			listeCoup.add(new Point(c.x,c.y-1));
		if(c.y > 0 && c.x+1 < N && !tourj1 && plateau.get(c.x+1).get(c.y-1) == Case.LIBRE) 
			listeCoup.add(new Point(c.x+1,c.y -1));
		if(c.x+1 < N && !tourj1 && plateau.get(c.x+1).get(c.y) == Case.LIBRE) 
			listeCoup.add(new Point(c.x+1,c.y));

		return listeCoup;
	}


	public ArrayList<Point> listeCaseJoueur(){
		ArrayList<Point> listeCase = new ArrayList<Point>();

		for(int i = 0; i < N; i++){
			for(int l = 0; l < N; l++){
				if(tourj1 && plateau.get(i).get(l) == Case.PJ1){
					listeCase.add(new Point(i,l));
				}
				if(!tourj1 && plateau.get(i).get(l) == Case.PJ2){
					listeCase.add(new Point(i,l));
				}
			}
		}

		return listeCase;
	}

	public void joue_coup(Point p1, Point p2){
		
	}

	public void joue_coup(Coup m){
		ArrayList<Case> tmp = new ArrayList<Case>();
		if(m instanceof DepRang){

			DepRang d = new DepRang(((DepRang) m).dir, ((DepRang) m).rang);
			if(d.dir == Direction.BAS){
				for(int i = N-1; i > 0; i--){
					tmp = plateau.get(i);
					tmp.set(d.rang,plateau.get(i-1).get(d.rang));
					plateau.set(i, tmp);
					tmp = new ArrayList<Case>();
				}
				tmp = plateau.get(0);
				tmp.set(d.rang, Case.LIBRE);
				plateau.set(0, tmp);
			}
			else if(d.dir == Direction.HAUT){
				for(int i = 0; i < N-1; i++){
					tmp = plateau.get(i);
					tmp.set(d.rang,plateau.get(i+1).get(d.rang));
					plateau.set(i, tmp);
					tmp = new ArrayList<Case>();
				}
				tmp = plateau.get(N-1);
				tmp.set(d.rang, Case.LIBRE);
				plateau.set(N-1, tmp);
			}
			else if(d.dir == Direction.DROITE){
				tmp = plateau.get(d.rang);
				for(int i = N-1; i > 0; i--){
					tmp.set(i, tmp.get(i-1));
				}
				tmp.set(0, Case.LIBRE);
				plateau.set(d.rang, tmp);
			}
			else if(d.dir == Direction.GAUCHE){
				tmp = plateau.get(d.rang);
				for(int i = 0; i < N-1; i++){
					tmp.set(i, tmp.get(i+1));
				}
				tmp.set(N-1, Case.LIBRE);
				plateau.set(d.rang, tmp);
			}
		}
		else if(m instanceof DepPion){

			DepPion pion = new DepPion(((DepPion) m).depart,((DepPion) m).arrive);
			if(pion.arrive.x == 4 && pion.arrive.y == 0){
				setNbBillej2(getNbBillej2()-1);
				tmp = plateau.get(pion.depart.x);
				tmp.set(pion.depart.y, Case.LIBRE);
				plateau.set(pion.depart.x, tmp);
			}
			else if(pion.arrive.x == 0 && pion.arrive.y == 4){
				setNbBillej1(getNbBillej1()-1);
				tmp = plateau.get(pion.depart.x);
				tmp.set(pion.depart.y, Case.LIBRE);
				plateau.set(pion.depart.x, tmp);
			}
			else{
				tmp = plateau.get(pion.arrive.x);
				tmp.set(pion.arrive.y, plateau.get(pion.depart.x).get(pion.depart.y));
				plateau.set(pion.arrive.x, tmp);
				tmp = plateau.get(pion.depart.x);
				tmp.set(pion.depart.y, Case.LIBRE);
				plateau.set(pion.depart.x, tmp);
			}
		}
		tourj1 = !tourj1;
		histo.ajouter(m);
	}
	//a modif-------------------------
	public void annuler(){
		Coup m = histo.annuler();
		ArrayList<Case> tmp = new ArrayList<Case>();
		if(m instanceof DepRang){

			DepRang d = new DepRang(((DepRang) m).dir, ((DepRang) m).rang);
			if(d.dir == Direction.BAS){
				for(int i = 0; i < N-1; i++){
					tmp = plateau.get(i);
					tmp.set(d.rang,plateau.get(i+1).get(d.rang));
					plateau.set(i, tmp);
					tmp = new ArrayList<Case>();
				}
				tmp = plateau.get(N-1);
				tmp.set(d.rang, Case.LIBRE);
				plateau.set(N-1, tmp);
			}
			else if(d.dir == Direction.HAUT){
				for(int i = N-1; i > 0; i--){
					tmp = plateau.get(i);
					tmp.set(d.rang,plateau.get(i-1).get(d.rang));
					plateau.set(i, tmp);
					tmp = new ArrayList<Case>();
				}
				tmp = plateau.get(0);
				tmp.set(d.rang, Case.LIBRE);
				plateau.set(0, tmp);
			}
			else if(d.dir == Direction.DROITE){
				tmp = plateau.get(d.rang);
				for(int i = 0; i < N-1; i++){
					tmp.set(i, tmp.get(i+1));
				}
				tmp.set(N-1, Case.LIBRE);
				plateau.set(d.rang, tmp);
			}
			else if(d.dir == Direction.GAUCHE){
				tmp = plateau.get(d.rang);
				for(int i = N-1; i > 0; i--){
					tmp.set(i, tmp.get(i-1));
				}
				tmp.set(0, Case.LIBRE);
				plateau.set(d.rang, tmp);
			}
		}
		else if(m instanceof DepPion){
			DepPion pion = new DepPion(((DepPion) m).arrive,((DepPion) m).depart);
			tmp = plateau.get(pion.depart.x);
			Case c = tmp.get(pion.depart.y);
			tmp.set(pion.depart.y, Case.LIBRE);
			plateau.set(pion.depart.x, tmp);
			tmp = plateau.get(pion.arrive.x);
			tmp.set(pion.arrive.y, c);
			plateau.set(pion.arrive.x, tmp);
		}
	}

	public void refaire(){
		Coup m = histo.refaire();
		joue_coup(m);
	}

	public void afficher(){
		for(int i =0; i<N; i++){
			for(int j =0; j< N; j++){
				if(plateau.get(i).get(j)==Case.LIBRE)
					System.out.print(". | ");
				else if(plateau.get(i).get(j)==Case.PJ1)
					System.out.print("1 | ");
				else if(plateau.get(i).get(j) == Case.PJ2)
					System.out.print("2 | ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		Humain j11 = new Humain();
		OrdiFacile j22 = new OrdiFacile();
		Moteur m = new Moteur(j11,j22);
		Coup c = new Coup();
		boolean coup_valide = false;
		m.afficher();
		while(m.nbBillej1 > 2 && m.nbBillej2 >2){
			//Recuperation coup humain
			Scanner sc = new Scanner(System.in);
			coup_valide = false;
			while(!coup_valide){
				int typeDep = 0;
				while(typeDep != 1 && typeDep != 2) {
					System.out.print("Saisir le type de coup, dep pion(1), dep rang(2) : ");
					typeDep = sc.nextInt();
				}

				if(typeDep == 2){
					int dir = 0;
					int rand = -1;
					while(dir != 1 && dir != 2 && dir !=3 && dir !=4){
						System.out.println("Saisir rang entre 0 et 4 : ");
						rand = sc.nextInt();
						System.out.println("Saisir la direction, haut(1) bas(2) gauche(3) droite(4) :");
						dir = sc.nextInt();
					}
					Direction dire;
					switch(dir){
					case 1: dire = Direction.HAUT ; break;
					case 2: dire = Direction.BAS ; break;
					case 3: dire = Direction.GAUCHE ; break;
					default: dire = Direction.DROITE ; break;
					}
					c = new DepRang(dire,rand);
					coup_valide = m.estCoupPossible((DepRang) c);
				}
				else if(typeDep == 1){
					System.out.print("Saisir ligne et colonne : ");
					int ld = sc.nextInt();
					int cd = sc.nextInt();
					System.out.println("Saisir ligne et colonne arrive : ");
					int la = sc.nextInt();
					int ca = sc.nextInt();
					c=new DepPion(new Point(ld,cd),new Point(la,ca));
					coup_valide = m.estCoupPossible((DepPion)c);
				}


				System.out.println(coup_valide);
			}
			m.joue_coup(c );
			m.afficher();

			c = m.j2.facile(new Moteur(m));
			m.joue_coup(c);
			m.afficher();
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//m.annuler();
			//m.afficher();
			/*if(m.getNbBillej1() > 2){
				System.out.println();
				m.j2.alphabeta(4,Integer.MIN_VALUE,Integer.MAX_VALUE,new Moteur(m),false);
				m.joue_coup(m.j2.coupOrdiMoyen);
				m.afficher();
				System.out.println("billej1 : " + m.getNbBillej1());
				System.out.println("billej2 : " + m.getNbBillej2());
			}*/
		}

	}

}

