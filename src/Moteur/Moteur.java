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

	private Joueur j1;
	private Joueur j2;

	private Historique histo;
	public static final int N=5;
	public boolean tourj1;
	public Case[][] plateau;


    public static int getN() {
        return N;
    }



	public Moteur ( Joueur j11, Joueur j22){
		init_plateau();
		nbBillej1 = 5;
		nbBillej2 = 5;
		j1=j11;
		j2=j22;
		histo = new Historique();
		tourj1 = true;
	}
	public Moteur ( Moteur m){
		plateau = new Case[N][N];
		setPlateau(m.getPlateau());
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
		j1 = j;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j) {
		j2 =  j;
	}

	//public ArrayList<ArrayList<Case>> getPlateau() {
	//	return plateau;
	//}

//	public void setPlateau(ArrayList<ArrayList<Case>> plateauset) {
//		plateau = plateauset;
//	}

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

		plateau = new Case[N][N];

		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				switch(i){
				case 0 : 
					if(j == 2 || j == 3){
						plateau[i][j] = Case.PJ2;
					}
					else{
						plateau[i][j] = Case.LIBRE;
					}

					break;
				case 1 : 
					if( j == 3 || j == 4){
						plateau[i][j] = Case.PJ2;
					}
					else{
						plateau[i][j] = Case.LIBRE;
					}
					break;
				case 2 : 
					if( j == 0){
						plateau[i][j] = Case.PJ1;
					}
					else if( j == 4){
						plateau[i][j] = Case.PJ2;
					}
					else{
						plateau[i][j] = Case.LIBRE;
					}
					break;
				case 3 :
					if(j == 0 || j == 1){
						plateau[i][j] = Case.PJ1;
					}
					else{
						plateau[i][j] = Case.LIBRE;
					}
					break;
				case 4 : 
					if(j == 1 || j == 2){
						plateau[i][j] = Case.PJ1;
					}
					else{
						plateau[i][j] = Case.LIBRE;
					}
					break;
				default : break;

				}
			}
		}
	}

	public Case getCase(int i, int j){
		return plateau[i][j];
	}
	public void setCase(Case c,int i,int j){
		plateau[i][j] = c;
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
				&& (plateau[c.getDepart().x][c.getDepart().y] == Case.PJ1 && tourj1
				|| plateau[c.getDepart().x][c.getDepart().y] == Case.PJ2 && !tourj1)
				&& plateau[c.getArrive().x][c.getArrive().y] == Case.LIBRE)
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
			if(c.getDir() == Direction.BAS && plateau[N-1][c.getRang()] == Case.LIBRE ){

				if(!histo.getCoup().isEmpty() && histo.getCoup().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getCoup().peek();
					if(dr.getDir() == Direction.HAUT && dr.getRang() == c.getRang())
						return false;
				}
				return true;
			}
			if(c.getDir() == Direction.HAUT && plateau[0][c.getRang()] == Case.LIBRE){
				if(!histo.getCoup().isEmpty() && histo.getCoup().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getCoup().peek();
					if(dr.getDir() == Direction.BAS && dr.getRang() == c.getRang())
						return false;
				}
				return true;
			}
			if(c.getDir() == Direction.GAUCHE && plateau[c.getRang()][0] == Case.LIBRE){
				if(!histo.getCoup().isEmpty() && histo.getCoup().peek() instanceof DepRang ){
					DepRang dr = (DepRang) histo.getCoup().peek();
					if(dr.getDir() == Direction.DROITE && dr.getRang() == c.getRang())
						return false;
				}
				return true;
			}
			if(c.getDir() == Direction.DROITE && plateau[c.getRang()][N-1] == Case.LIBRE){
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
			if(((c.getDir() == Direction.BAS || c.getDir() == Direction.HAUT) && plateau[i][c.getRang()] == Case.PJ1 && tourj1 )
					||(( c.getDir() == Direction.BAS || c.getDir() == Direction.HAUT) && plateau[i][c.getRang()] == Case.PJ2 && !tourj1)){
				return true;
			}
			else if(((c.getDir() == Direction.GAUCHE || c.getDir() == Direction.DROITE) && plateau[c.getRang()][i] == Case.PJ1 && tourj1)
					||(( c.getDir() == Direction.GAUCHE || c.getDir() == Direction.DROITE) && plateau[c.getRang()][i] == Case.PJ2 && !tourj1)){
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

			if(plateau[0][c.y] == Case.LIBRE && estCoupPossible(new DepRang(Direction.HAUT, c.y, false))){
				listeCoup.add(new DepRang(Direction.HAUT, c.y, false));
                deja_jouer[0][c.y] = true;
			}	
			if(plateau[N-1][c.y] == Case.LIBRE && estCoupPossible(new DepRang(Direction.BAS, c.y, false))){
				listeCoup.add(new DepRang(Direction.BAS, c.y, false));
                deja_jouer[1][c.y] = true;
			}
			if(plateau[c.x][0] == Case.LIBRE && estCoupPossible(new DepRang(Direction.GAUCHE, c.x, false))){
				listeCoup.add(new DepRang(Direction.GAUCHE, c.x, false));
                deja_jouer[2][c.y] = true;
			}
			if(plateau[c.x][N-1] == Case.LIBRE && estCoupPossible(new DepRang(Direction.DROITE, c.x, false))){
				listeCoup.add(new DepRang(Direction.DROITE, c.x, false));
                deja_jouer[3][c.y] = true;
			}

			//Verif joueur1
			if(c.y+1 < N && tourj1 && plateau[c.x][c.y+1] == Case.LIBRE)
				listeCoup.add(new DepPion(c,new Point(c.x,c.y +1)));
			if(c.x > 0 && c.y+1 < N  && tourj1 && plateau[c.x-1][c.y+1] == Case.LIBRE)
				listeCoup.add(new DepPion(c,new Point(c.x-1,c.y +1)));
			if(c.x > 0 && tourj1 && plateau[c.x-1][c.y] == Case.LIBRE)
				listeCoup.add(new DepPion(c,new Point(c.x-1,c.y)));

			//Verif coup pour joueur2
			if(c.y > 0 && !tourj1 && plateau[c.x][c.y-1] == Case.LIBRE)
				listeCoup.add(new DepPion(c,new Point(c.x,c.y-1)));
			if(c.y > 0 && c.x+1 < N && !tourj1 && plateau[c.x+1][c.y-1] == Case.LIBRE)
				listeCoup.add(new DepPion(c,new Point(c.x+1,c.y -1)));
			if(c.x+1 < N && !tourj1 && plateau[c.x+1][c.y] == Case.LIBRE)
				listeCoup.add(new DepPion(c,new Point(c.x+1,c.y)));
		}
		return listeCoup;
	}


	public ArrayList<Point> listeCoupPossible(Point c){
		ArrayList<Point> listeCoup = new ArrayList<Point>();

		//Verif joueur1
		if(c.y+1 < N && tourj1 && plateau[c.x][c.y+1] == Case.LIBRE)
			listeCoup.add(new Point(c.x,c.y +1));
		if(c.x > 0 && c.y+1 < N  && tourj1 && plateau[c.x-1][c.y+1] == Case.LIBRE)
			listeCoup.add(new Point(c.x-1,c.y +1));
		if(c.x > 0 && tourj1 && plateau[c.x-1][c.y] == Case.LIBRE)
			listeCoup.add(new Point(c.x-1,c.y));

		//Verif coup pour joueur2
		if(c.y > 0 && !tourj1 && plateau[c.x][c.y-1] == Case.LIBRE)
			listeCoup.add(new Point(c.x,c.y-1));
		if(c.y > 0 && c.x+1 < N && !tourj1 && plateau[c.x+1][c.y-1] == Case.LIBRE)
			listeCoup.add(new Point(c.x+1,c.y -1));
		if(c.x+1 < N && !tourj1 && plateau[c.x+1][c.y] == Case.LIBRE)
			listeCoup.add(new Point(c.x+1,c.y));

		return listeCoup;
	}




	public ArrayList<Point> listeCaseJoueur(){
		ArrayList<Point> listeCase = new ArrayList<Point>();

		for(int i = 0; i < N; i++){
			for(int l = 0; l < N; l++){
				if(tourj1 && plateau[i][l] == Case.PJ1){
					listeCase.add(new Point(i,l));
				}
				if(!tourj1 && plateau[i][l] == Case.PJ2){
					listeCase.add(new Point(i,l));
				}
			}
		}

		return listeCase;
	}

	public void joue_coup(Coup m){
        if(estCoupPossible(m)) {
            if (m instanceof DepRang) {

                DepRang d = new DepRang(((DepRang) m).getDir(), ((DepRang) m).getRang(), false);
                if (d.getDir() == Direction.BAS) {

                    if (d.getRang() == 0 && plateau[N - 2][d.getRang()] == Case.PJ2) {
                        this.nbBillej2--;
                        ((DepRang) m).setValidant(true);
                        plateau[3][0] = Case.LIBRE;
                    }

                    for (int i = N - 1; i > 0; i--) {
                        plateau[i][d.getRang()] = plateau[i-1][d.getRang()];
                    }
					plateau[0][d.getRang()] = Case.LIBRE;
                } else if (d.getDir() == Direction.HAUT) {
                    if (d.getRang() == N - 1 && plateau[1][d.getRang()] == Case.PJ1) {
                        this.nbBillej1--;
                        ((DepRang) m).setValidant(true);
                        plateau[1][4]= Case.LIBRE;
                    }
                    for (int i = 0; i < N - 1; i++) {
						plateau[i][d.getRang()] = plateau[i+1][d.getRang()];
                    }
                    plateau[N-1][d.getRang()] = Case.LIBRE;
                } else if (d.getDir() == Direction.DROITE) {

                    if (d.getRang() == 0 && plateau[d.getRang()][3] == Case.PJ1) {
                        this.nbBillej1--;
                        ((DepRang) m).setValidant(true);
						plateau[d.getRang()][3] = Case.LIBRE;
                    }

                    for (int i = N - 1; i > 0; i--) {
						plateau[d.getRang()][i] = plateau[d.getRang()][i-1];
                    }
					plateau[d.getRang()][0] = Case.LIBRE;
                } else if (d.getDir() == Direction.GAUCHE) {

                    if (d.getRang() == 4 && plateau[4][1] == Case.PJ2) {
                        this.nbBillej2--;
                        ((DepRang) m).setValidant(true);
						plateau[d.getRang()][1] = Case.LIBRE;
                    }

                    for (int i = 0; i < N - 1; i++) {
						plateau[d.getRang()][i] = plateau[d.getRang()][i+1];
                    }
					plateau[d.getRang()][N-1] = Case.LIBRE;
                }
                histo.ajouter(d);
            } else if (m instanceof DepPion) {

                DepPion pion = new DepPion(((DepPion) m).getDepart(), ((DepPion) m).getArrive());
                if (pion.getArrive().x == 4 && pion.getArrive().y == 0 && this.plateau[4][0] == Case.LIBRE) {
                    setNbBillej2(getNbBillej2() - 1);
					plateau[pion.getDepart().x][pion.getDepart().y] = Case.LIBRE;

                } else if (pion.getArrive().x == 0 && pion.getArrive().y == 4 ) {
                    setNbBillej1(getNbBillej1() - 1);
					plateau[pion.getDepart().x][pion.getDepart().y] = Case.LIBRE;
                } else {
					plateau[pion.getArrive().x][pion.getArrive().y] = plateau[pion.getDepart().x][pion.getDepart().y];
                    plateau[pion.getDepart().x][pion.getDepart().y] = Case.LIBRE;
                }
                histo.ajouter(pion);
            }
            tourj1 = !tourj1;
            //correctionPlatIHM();
			int scoreJ1 = N, scoreJ2 = N;
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++){
					switch (plateau[i][j]){
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
	}

	//a modif-------------------------
	public void annuler(){
		Coup m = histo.annuler();
		if(m instanceof DepRang){

			DepRang d = new DepRang(((DepRang) m).getDir(), ((DepRang) m).getRang(), ((DepRang) m).isValidant());
			if(d.getDir() == Direction.BAS){
				for(int i = 0; i < N-1; i++){
					plateau[i][d.getRang()] = plateau[i+1][d.getRang()];
				}
				if(d.isValidant()){
					this.nbBillej2++;
					((DepRang) m).setValidant(false);
					plateau[3][0] = Case.PJ2;
				}
				plateau[N-1][d.getRang()] = Case.LIBRE;
			}
			else if(d.getDir() == Direction.HAUT){
				for(int i = N-1; i > 0; i--){
					plateau[i][d.getRang()] = plateau[i-1][d.getRang()];
				}
				if(d.isValidant()){
					this.nbBillej1++;
					((DepRang) m).setValidant(false);
					plateau[1][N-1] = Case.PJ1;
				}
				plateau[0][d.getRang()] = Case.LIBRE;
			}
			else if(d.getDir() == Direction.DROITE){

				for(int i = 0; i < N-1; i++){
					plateau[d.getRang()][i] = plateau[d.getRang()][i+1];
				}
				if(d.isValidant()){
					this.nbBillej1++;
					((DepRang) m).setValidant(false);
					plateau[d.getRang()][N-2] = Case.PJ1;
				}
				plateau[d.getRang()][N-1] = Case.LIBRE;
			}
			else if(d.getDir() == Direction.GAUCHE){
				for(int i = N-1; i > 0; i--){
					plateau[d.getRang()][i] = plateau[d.getRang()][i-1];
				}
				if(d.isValidant()){
					this.nbBillej2++;
					((DepRang) m).setValidant(false);
					plateau[d.getRang()][1] = Case.PJ2;
				}
				plateau[d.getRang()][0] = Case.LIBRE;
			}
		}
		else if(m instanceof DepPion){
			DepPion pion = new DepPion(((DepPion) m).getArrive(),((DepPion) m).getDepart());

			Case c = plateau[pion.getDepart().x][pion.getDepart().y];
			plateau[pion.getDepart().x][pion.getDepart().y] = Case.LIBRE;
			plateau[pion.getArrive().x][pion.getArrive().y] = c;
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
				if(plateau[i][j]==Case.LIBRE)
					System.out.print(". | ");
				else if(plateau[i][j]==Case.PJ1)
					System.out.print("1 | ");
				else if(plateau[i][j] == Case.PJ2)
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
	public void setPlateau(Case[][] tc){
		for (int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				plateau[i][j] = tc[i][j];
			}
		}
	}

	public Case[][] getPlateau(){
		return plateau;
	}

	public Historique getHisto() {
		return histo;
	}

	public void setHisto(Historique histo) {
		this.histo = histo;
	}
	public static void main(String[] args){
		Moteur m = new Moteur(new OrdiMoyen(),new OrdiDifficile());
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


}
