package Modele;
import java.awt.*;
import java.util.*;

import Moteur.*;
import Moteur.Moteur.*;

public class OrdiMoyen extends Joueur {

	public Coup coupOrdiMoyen;
	public int profondeurMoyen;

	public OrdiMoyen(){
		super(true, false);
		profondeurMoyen = 3;
	}

	public OrdiMoyen(int score){
		super(score,true);
	}

	public Coup jouer(Moteur m){

		alphabeta(3,Integer.MIN_VALUE,Integer.MAX_VALUE,new Moteur(m));
		return coupOrdiMoyen;
	}

	//Fonction appeler avec profondeurMoyen en parametres
	public double alphabeta(int p, double alpha, double beta, Moteur m){
		//Moteur m2 = new Moteur(m);
		if(m.getNbBillej1() == 2 || m.getNbBillej2() == 2 || p == 0){
			return eval(m);
		}
		Coup meilleur_coup;
		ArrayList<Coup> cl = m.listeCoupPossible();
		Coup c;
		int taille = cl.size();
		//System.out.println("taille : " + taille);
		for(int i = 0; i < taille; i++){
			c = cl.get(i);
			m.joue_coup(c);
			double score = - alphabeta(p-1,-beta,-alpha,new Moteur(m));
			m.annuler();
			if(score > alpha){
				System.out.println(m.tourj1);
				alpha = score;
				if(p==3)
					this.coupOrdiMoyen = c;
				if(alpha >= beta){
					break;
				}
			}
		}
		return alpha;
	}

	double [][] evalPlacementJ1 = {
			{4, 4.5, 5,   5.5, 100},
			{3, 3.5, 4,   4.5, 5.5},
			{2, 2.5, 3,   4,     5},
			{1, 1.5, 2.5, 3.5, 4.5},
			{0, 1,   2,   3,     4}
	};

	double [][] evalPlacementJ2 = {
			{4,   3,   2,   1,   0},
			{4.5, 3.5, 2.5, 1.5, 1},
			{5,   4,   3,   2.5, 2},
			{5.5, 4.5, 4,   3.5, 3},
			{100, 5.5, 5,   4.5, 4}
	};

	//Passage d'un moteur en parametre : j1 -> m.j1, etc...
	private double eval(Moteur m) {
		//System.out.println("Eval()");
		double score = 0;
		if(m.tourj1)
		{
			if(m.getNbBillej1() == 2){
				return Integer.MIN_VALUE;	
			}
			//Nombre de billes perso
			score += 10*(5 - m.getNbBillej1());

			//Nombre de billes adverses		
			score -= 8*(5 - m.getNbBillej2());
		}
		else if (!m.tourj1) {
			//Nombre de billes perso
			if(m.getNbBillej2() == 2){
				return Integer.MIN_VALUE;

			}
			score += 10*(5 - m.getNbBillej2());

			//Nombre de billes adverses		
			score -= 8*(5 - m.getNbBillej1());
		}

		//Distance euclidienne des billes Joueur 1 et Joueur 2
		for(int i=0;i<m.N;i++){
			for(int j=0;j<m.N;j++){
				if(m.tourj1){
					if(m.getPlateau().get(i).get(j) == Case.PJ1)
						score += evalPlacementJ1[i][j];

					else if(m.getPlateau().get(i).get(j) == Case.PJ2)
						score -= evalPlacementJ2[i][j];
				}
				else if(!m.tourj1){
					if(m.getPlateau().get(i).get(j) == Case.PJ1)
						score -= evalPlacementJ1[i][j];

					else if(m.getPlateau().get(i).get(j) == Case.PJ2)
						score += evalPlacementJ2[i][j];
				}
			}
		}


		return score;
	}

}
