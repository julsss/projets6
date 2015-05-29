package Moteur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

import Modele.*;

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

	private int nbBillej1;
	private int nbBillej2;
	private int [][] platIHM;
	private Point savePtIHM;
	boolean savePtIHMValide;

	private Joueur j1;
	private Joueur j2;

	private Historique histo;
	public static final int N=5;
	public boolean tourj1;



    public static int getN() {
        return N;
    }

    public ArrayList<ArrayList<Case>> plateau = new ArrayList<ArrayList<Case>>();

	public Moteur ( Joueur j11, Joueur j22){
		init_plateau();
		nbBillej1 = 5;
		nbBillej2 = 5;
		j1=j11;
		j2=j22;
		histo = new Historique();
		tourj1 = true;
		init_platIHM();
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
		this.platIHM = m.platIHM;
		histo = m.histo;
		tourj1 = m.tourj1;
	}

	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j) {
		j1 = j;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j) {
		j2 =  j;
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


	private void init_platIHM(){
		savePtIHMValide = false;
		platIHM = new int[7][7];
		for(int i = 0; i<7;i++){
			platIHM[0][i] = -4;
			platIHM[i][0] = -2;
			platIHM[6][i] = -16;
			platIHM[i][6] = -8;		
		}

		platIHM[0][0] = Integer.MIN_VALUE;
		platIHM[0][6] = Integer.MIN_VALUE;
		platIHM[6][0] = Integer.MIN_VALUE;
		platIHM[6][6] = Integer.MIN_VALUE;
		platIHM[3][1] = 1;
		platIHM[4][1] = 1;
		platIHM[4][2] = 1;
		platIHM[5][2] = 1;
		platIHM[5][3] = 1;
		platIHM[1][3] = 2;
		platIHM[1][4] = 2;
		platIHM[2][4] = 2;
		platIHM[2][5] = 2;
		platIHM[3][5] = 2;

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

	public Case getPlateau(int i, int j){
		return plateau.get(i).get(j);
	}
	public Case setPlateau(Case c,int i,int j){
		return plateau.get(i).set(j,c);
	}

	public boolean estCoupPossible(Point p1 , Point p2){
		return estCoupPossible(new DepPion(p1,p2));
	}

    public boolean estCoupPossible(Coup m){
        if(m instanceof DepPion)
            return  estCoupPossible((DepPion) m);
        else
            return estCoupPossible((DepRang) m);
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

		//ajout if avec fonction teste joueur + pion dans la range
		if( this.billeDansRange(c) ){
			if(c.getDir() == Direction.BAS && plateau.get(N-1).get(c.getRang()) == Case.LIBRE ){

				if(!histo.getCoup().isEmpty() && histo.getCoup().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getCoup().peek();
					if(dr.getDir() == Direction.HAUT && dr.getRang() == c.getRang())
						return false;
				}
				return true;
			}
			if(c.getDir() == Direction.HAUT && plateau.get(0).get(c.getRang()) == Case.LIBRE){
				if(!histo.getCoup().isEmpty() && histo.getCoup().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getCoup().peek();
					if(dr.getDir() == Direction.BAS && dr.getRang() == c.getRang())
						return false;
				}
				return true;
			}
			if(c.getDir() == Direction.GAUCHE && plateau.get(c.getRang()).get(0) == Case.LIBRE){
				if(!histo.getCoup().isEmpty() && histo.getCoup().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getCoup().peek();
					if(dr.getDir() == Direction.DROITE && dr.getRang() == c.getRang())
						return false;
				}
				return true;
			}
			if(c.getDir() == Direction.DROITE && plateau.get(c.getRang()).get(N-1) == Case.LIBRE){
				if(!histo.getCoup().isEmpty() && histo.getCoup().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getCoup().peek();
					if(dr.getDir() == Direction.GAUCHE && dr.getRang() == c.getRang())
						return false;
				}
				return true;
			}
		}
		return false;
	}

	private boolean billeDansRange(DepRang c) {
		for(int i=0;i<N;i++){
			if(((c.getDir() == Direction.BAS || c.getDir() == Direction.HAUT) && plateau.get(i).get(c.getRang()) == Case.PJ1 && tourj1 )
					||(( c.getDir() == Direction.BAS || c.getDir() == Direction.HAUT) && plateau.get(i).get(c.getRang()) == Case.PJ2 && !tourj1)){
				return true;
			}
			else if(((c.getDir() == Direction.GAUCHE || c.getDir() == Direction.DROITE) && plateau.get(c.getRang()).get(i) == Case.PJ1 && tourj1)
					||(( c.getDir() == Direction.GAUCHE || c.getDir() == Direction.DROITE) && plateau.get(c.getRang()).get(i) == Case.PJ2 && !tourj1)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Coup> listeCoupPossible(){
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		ArrayList<Point> listeCase =  listeCaseJoueur();
		Point c;
		boolean deja_jouer[][] = new boolean[N][N];
        //Tableaux[i][j] repertoriant les coups rangs deja possible;
        //i = 0 -> Direction haut
        //i=1 -> direction bas
        //i=2 -> direction gauche
        //i=3 -> direction droite
        //j represente le rang
        for(int i = 0; i<N ; i++){
            for(int j = 0; j<N; j++){
                deja_jouer[i][j] = false;
            }
        }
		for(int i = 0; i < listeCase.size(); i++){
			c = listeCase.get(i);
			//Ajout Rang

			if(plateau.get(0).get(c.y) == Case.LIBRE && estCoupPossible(new DepRang(Direction.HAUT, c.y, false))){
				listeCoup.add(new DepRang(Direction.HAUT, c.y, false));
                deja_jouer[0][c.y] = true;
			}	
			if(plateau.get(N-1).get(c.y) == Case.LIBRE && estCoupPossible(new DepRang(Direction.BAS, c.y, false))){
				listeCoup.add(new DepRang(Direction.BAS, c.y, false));
                deja_jouer[1][c.y] = true;
			}
			if(plateau.get(c.x).get(0) == Case.LIBRE && estCoupPossible(new DepRang(Direction.GAUCHE, c.x, false))){
				listeCoup.add(new DepRang(Direction.GAUCHE, c.x, false));
                deja_jouer[2][c.y] = true;
			}
			if(plateau.get(c.x).get(N-1) == Case.LIBRE && estCoupPossible(new DepRang(Direction.DROITE, c.x, false))){
				listeCoup.add(new DepRang(Direction.DROITE, c.x, false));
                deja_jouer[3][c.y] = true;
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

	public void coupPossible(Point c){

		//Verif joueur1
		if(c.y+1 < N && tourj1 && plateau.get(c.x).get(c.y+1) == Case.LIBRE) 
			platIHM[c.x][c.y+1] = -1;
		if(c.x > 0 && c.y+1 < N  && tourj1 && plateau.get(c.x-1).get(c.y+1) == Case.LIBRE) 
			platIHM[c.x-1][c.y+1] = -1;
		if(c.x > 0 && tourj1 && plateau.get(c.x-1).get(c.y) == Case.LIBRE) 
			platIHM[c.x-1][c.y] = -1;

		//Verif coup pour joueur2
		if(c.y > 0 && !tourj1 && plateau.get(c.x).get(c.y-1) == Case.LIBRE) 
			platIHM[c.x][c.y-1] = -1;
		if(c.y > 0 && c.x+1 < N && !tourj1 && plateau.get(c.x+1).get(c.y-1) == Case.LIBRE) 
			platIHM[c.x+1][c.y-1] = -1;
		if(c.x+1 < N && !tourj1 && plateau.get(c.x+1).get(c.y) == Case.LIBRE) 
			platIHM[c.x+1][c.y] = -1;

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

	public int[][] joue_coup(Coup m){
        if(estCoupPossible(m)) {
            ArrayList<Case> tmp = new ArrayList<Case>();
            if (m instanceof DepRang) {

                DepRang d = new DepRang(((DepRang) m).getDir(), ((DepRang) m).getRang(), false);
                if (d.getDir() == Direction.BAS) {

                    if (d.getRang() == 0 && plateau.get(N - 2).get(d.getRang()) == Case.PJ2 && this.plateau.get(0).get(4) == Case.LIBRE) {
                        this.nbBillej2--;
                        ((DepRang) m).setValidant(true);
                        plateau.get(3).set(0, Case.LIBRE);
                    }

                    for (int i = N - 1; i > 0; i--) {
                        tmp = plateau.get(i);
                        tmp.set(d.getRang(), plateau.get(i - 1).get(d.getRang()));
                        plateau.set(i, tmp);
                        tmp = new ArrayList<Case>();
                        platIHM[i + 1][d.getRang()] = platIHM[i][d.getRang()];
                    }
                    platIHM[1][d.getRang()] = 0;
                    tmp = plateau.get(0);
                    tmp.set(d.getRang(), Case.LIBRE);
                    plateau.set(0, tmp);
                } else if (d.getDir() == Direction.HAUT) {
                    if (d.getRang() == N - 1 && plateau.get(1).get(d.getRang()) == Case.PJ1 && this.plateau.get(0).get(4) == Case.LIBRE) {
                        this.nbBillej1--;
                        ((DepRang) m).setValidant(true);
                        plateau.get(1).set(4, Case.LIBRE);
                    }
                    for (int i = 0; i < N - 1; i++) {
                        tmp = plateau.get(i);
                        tmp.set(d.getRang(), plateau.get(i + 1).get(d.getRang()));
                        plateau.set(i, tmp);
                        tmp = new ArrayList<Case>();
                        platIHM[i][d.getRang()] = platIHM[i + 1][d.getRang()];
                    }
                    platIHM[5][d.getRang()] = 0;
                    tmp = plateau.get(N - 1);
                    tmp.set(d.getRang(), Case.LIBRE);
                    plateau.set(N - 1, tmp);
                } else if (d.getDir() == Direction.DROITE) {
                    tmp = plateau.get(d.getRang());

                    if (d.getRang() == 0 && plateau.get(d.getRang()).get(3) == Case.PJ1) {
                        this.nbBillej1--;
                        ((DepRang) m).setValidant(true);
                        tmp.set(3, Case.LIBRE);
                    }

                    for (int i = N - 1; i > 0; i--) {
                        tmp.set(i, tmp.get(i - 1));
                        platIHM[d.getRang()][i + 1] = platIHM[d.getRang()][i];
                    }
                    platIHM[d.getRang()][1] = 0;
                    tmp.set(0, Case.LIBRE);
                    plateau.set(d.getRang(), tmp);
                } else if (d.getDir() == Direction.GAUCHE) {
                    tmp = plateau.get(d.getRang());

                    if (d.getRang() == 4 && plateau.get(4).get(1) == Case.PJ2) {
                        this.nbBillej2--;
                        ((DepRang) m).setValidant(true);
                        tmp.set(1, Case.LIBRE);
                    }

                    for (int i = 0; i < N - 1; i++) {
                        tmp.set(i, tmp.get(i + 1));
                        platIHM[d.getRang()][i] = platIHM[d.getRang()][i + 1];
                    }
                    platIHM[d.getRang()][5] = 0;
                    tmp.set(N - 1, Case.LIBRE);
                    plateau.set(d.getRang(), tmp);
                }
                histo.ajouter(d);
            } else if (m instanceof DepPion) {

                DepPion pion = new DepPion(((DepPion) m).getDepart(), ((DepPion) m).getArrive());
                if (pion.getArrive().x == 4 && pion.getArrive().y == 0 && this.plateau.get(4).get(0) == Case.LIBRE) {
                    setNbBillej2(getNbBillej2() - 1);
                    tmp = plateau.get(pion.getDepart().x);
                    tmp.set(pion.getDepart().y, Case.LIBRE);
                    plateau.set(pion.getDepart().x, tmp);
                    platIHM[pion.getDepart().x][pion.getDepart().y] = 0;

                } else if (pion.getArrive().x == 0 && pion.getArrive().y == 4 && this.plateau.get(0).get(4) == Case.LIBRE) {
                    setNbBillej1(getNbBillej1() - 1);
                    tmp = plateau.get(pion.getDepart().x);
                    tmp.set(pion.getDepart().y, Case.LIBRE);
                    plateau.set(pion.getDepart().x, tmp);
                    platIHM[pion.getDepart().x][pion.getDepart().y] = 0;
                } else {
                    tmp = plateau.get(pion.getArrive().x);
                    tmp.set(pion.getArrive().y, plateau.get(pion.getDepart().x).get(pion.getDepart().y));
                    plateau.set(pion.getArrive().x, tmp);
                    tmp = plateau.get(pion.getDepart().x);
                    tmp.set(pion.getDepart().y, Case.LIBRE);
                    plateau.set(pion.getDepart().x, tmp);
                    platIHM[pion.getArrive().x][pion.getArrive().y] = platIHM[pion.getDepart().x][pion.getDepart().y];
                    platIHM[pion.getDepart().x][pion.getDepart().y] = 0;
                }
                histo.ajouter(pion);
            }
            tourj1 = !tourj1;
            //correctionPlatIHM();
			int scoreJ1 = N, scoreJ2 = N;
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++){
					switch (plateau.get(i).get(j)){
						case PJ1:
							scoreJ1--;
							break;
						case PJ2:
							scoreJ2--;
							break;
					}
				}
			j1.setScore(scoreJ1);
			j2.setScore(scoreJ2);

        }
		return platIHM;
	}

	private void correctionPlatIHM() {
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				if(platIHM[i][j] == -1)
					platIHM[i][j] = 0;
			}
		}

	}
	//a modif-------------------------
	public void annuler(){
		Coup m = histo.annuler();
		ArrayList<Case> tmp = new ArrayList<Case>();
		if(m instanceof DepRang){

			DepRang d = new DepRang(((DepRang) m).getDir(), ((DepRang) m).getRang(), ((DepRang) m).isValidant());
			if(d.getDir() == Direction.BAS){
				for(int i = 0; i < N-1; i++){
					tmp = plateau.get(i);
					tmp.set(d.getRang(),plateau.get(i+1).get(d.getRang()));
					plateau.set(i, tmp);
					tmp = new ArrayList<Case>();
				}
				if(d.isValidant()){
					this.nbBillej2++;
					((DepRang) m).setValidant(false);
					tmp = plateau.get(3);
					tmp.set(0, Case.PJ2);
				}	
				tmp = plateau.get(N-1);
				tmp.set(d.getRang(), Case.LIBRE);
				plateau.set(N-1, tmp);
			}
			else if(d.getDir() == Direction.HAUT){
				for(int i = N-1; i > 0; i--){
					tmp = plateau.get(i);
					tmp.set(d.getRang(),plateau.get(i-1).get(d.getRang()));
					plateau.set(i, tmp);
					tmp = new ArrayList<Case>();
				}
				if(d.isValidant()){
					this.nbBillej1++;
					((DepRang) m).setValidant(false);
					tmp = plateau.get(1);
					tmp.set(N-1, Case.PJ1);
				}	
				tmp = plateau.get(0);
				tmp.set(d.getRang(), Case.LIBRE);
				plateau.set(0, tmp);
			}
			else if(d.getDir() == Direction.DROITE){
				tmp = plateau.get(d.getRang());
				for(int i = 0; i < N-1; i++){
					tmp.set(i, tmp.get(i+1));
				}
				if(d.isValidant()){
					this.nbBillej1++;
					((DepRang) m).setValidant(false);
					tmp.set(N-2, Case.PJ1);
				}	
				tmp.set(N-1, Case.LIBRE);
				plateau.set(d.getRang(), tmp);
			}
			else if(d.getDir() == Direction.GAUCHE){
				tmp = plateau.get(d.getRang());
				for(int i = N-1; i > 0; i--){
					tmp.set(i, tmp.get(i-1));
				}
				if(d.isValidant()){
					this.nbBillej2++;
					((DepRang) m).setValidant(false);
					tmp.set(1, Case.PJ2);
				}	
				tmp.set(0, Case.LIBRE);
				plateau.set(d.getRang(), tmp);
			}
		}
		else if(m instanceof DepPion){
			DepPion pion = new DepPion(((DepPion) m).getArrive(),((DepPion) m).getDepart());
			tmp = plateau.get(pion.getDepart().x);
			Case c = tmp.get(pion.getDepart().y);
			tmp.set(pion.getDepart().y, Case.LIBRE);
			plateau.set(pion.getDepart().x, tmp);
			tmp = plateau.get(pion.getArrive().x);
			tmp.set(pion.getArrive().y, c);
			plateau.set(pion.getArrive().x, tmp);
			if(pion.getDepart().x == 4 && pion.getDepart().y == 0){
				this.nbBillej2++;
			} else if(pion.getDepart().x == 0 && pion.getDepart().y == 4){
				this.nbBillej1++;
			}
		}
		tourj1 = !tourj1;
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
	public boolean isTourj1() {
		return tourj1;
	}

	public void setTourj1(boolean tourj1) {
		this.tourj1 = tourj1;
	}

	public int[][] getPlatIHM() {

		return platIHM;
	}

	public void setPlatIHM(int[][] platIHM) {
		this.platIHM = platIHM;
	}

	public Point getSavePtIHM() {
		return savePtIHM;
	}

	public void setSavePtIHM(Point savePtIHM) {
		this.savePtIHM = savePtIHM;
	}

	public boolean isSavePtIHMValide() {
		return savePtIHMValide;
	}

	public void setSavePtIHMValide(boolean savePtIHMValide) {
		this.savePtIHMValide = savePtIHMValide;
	}

	public Historique getHisto() {
		return histo;
	}

	public void setHisto(Historique histo) {
		this.histo = histo;
	}
	public static void main(String[] args){
		Moteur m = new Moteur(new OrdiFacile(),new OrdiDifficile());
		Coup c = new Coup();
		boolean coup_valide = false;
		m.afficher();

		while(m.nbBillej1 > 2 && m.nbBillej2 >2){
			/*Recuperation coup humain
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
					c = new DepRang(dire,rand,false);
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
			 */
/*
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
*/
			//IA Facile
			c = m.j1.jouer(new Moteur(m));
			m.joue_coup(c);
			m.afficher();
			//Fin IA Facile

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//IA Moyen
//			if(m.getNbBillej1() > 2){
//				//System.out.println();
//				Coup ordi =m.j2.jouer(new Moteur(m));
//				System.out.print("tourj1 : "+m.tourj1);
//				System.out.println(" "+ordi);
//				m.joue_coup(ordi);
//				m.afficher();
//				System.out.println("billej1 : " + m.getNbBillej1());
//				System.out.println("billej2 : " + m.getNbBillej2());
//			}
			//Fin IAMoyen
			
			//IA Difficile
			if(m.getNbBillej2() > 2){
				//System.out.println();
				Coup ordi =m.j2.jouer(new Moteur(m));
				System.out.print("tourj1 : "+m.tourj1);
				System.out.println(" "+ordi);
				m.joue_coup(ordi);
				m.afficher();
				System.out.println("billej1 : " + m.getNbBillej1());
				System.out.println("billej2 : " + m.getNbBillej2());
			}
			//Fin IA Difficile
		}
		
		System.out.println("nbbillej1 : " + (5-m.nbBillej1) + "nbbillej2 : " + (5-m.nbBillej2));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////Gestion de l'IHM////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * utilisation pour l'IHM
	 */
	/*
	public int[][] getPlatIHM() {
		return platIHM;
	}
	public int[][] ClickBlow(Point p) {
		if(savePtIHMValide)
		{
			if(estCoupPossible(new DepPion(savePtIHM, p)))
			{
				joue_coup(new DepPion(savePtIHM, p));
				return platIHM;
			}
			correctionPlatIHM();
			savePtIHMValide = false;

		}

		if(platIHM[p.x][p.y] == 1 || platIHM[p.x][p.y] == 2 )
		{
			coupPossible(p);
			savePtIHMValide = true;
			savePtIHM = p;
			if(savePtIHMValide)
				System.out.println("point valide  = " + savePtIHM.x + "," + savePtIHM.y);
			return platIHM;
		}
		else if (platIHM[p.x][p.y] == -2)
		{
			if(estCoupPossible(new DepRang(Direction.BAS,p.x)))
					joue_coup(new DepRang(Direction.BAS,p.x) );
		}
		else if (platIHM[p.x][p.y] == -4)
		{
			if(estCoupPossible(new DepRang(Direction.GAUCHE,p.y)))
				joue_coup(new DepRang(Direction.GAUCHE,p.y) );
		}else if (platIHM[p.x][p.y] == -8)
		{
			if(estCoupPossible(new DepRang(Direction.HAUT,p.x)))
				joue_coup(new DepRang(Direction.HAUT,p.x) );
		}else if (platIHM[p.x][p.y] == -16)
		{
			if(estCoupPossible(new DepRang(Direction.DROITE,p.y)))
				joue_coup(new DepRang(Direction.DROITE,p.y) );
		}
		return platIHM;
	}*/

}
