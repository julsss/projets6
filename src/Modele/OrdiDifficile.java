package Modele;

import java.util.ArrayList;

import Moteur.Coup;
import Moteur.Moteur;
import Moteur.Moteur.Case;

public class OrdiDifficile extends Joueur{

	private Coup coupOrdiDiff;

	
	private double [][] evalPlacementJ1 = {
			{4, 4.5, 5,   5.5, 100},
			{3, 3.5, 4,   4.5, 5.5},
			{2, 2.5, 3,   4,     5},
			{1, 1.5, 2.5, 3.5, 4.5},
			{0, 1,   2,   3,     4}
	};

	private double [][] evalPlacementJ2 = {
			{4,   3,   2,   1,   0},
			{4.5, 3.5, 2.5, 1.5, 1},
			{5,   4,   3,   2.5, 2},
			{5.5, 4.5, 4,   3.5, 3},
			{100, 5.5, 5,   4.5, 4}
	};

	public OrdiDifficile(){
		super();
		profondeurDiff = 6;
	}


	@Override
	public Coup jouer(Moteur m) {
		alphabeta(profondeurDiff,Integer.MIN_VALUE,Integer.MAX_VALUE,new Moteur(m));
		return coupOrdiDiff;
	}
	
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
				//System.out.println(m.tourj1);
				alpha = score;
				if(p==profondeurDiff)
					this.coupOrdiDiff = c;
				if(alpha >= beta){
					break;
				}
			}
		}
		return alpha;
	}


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

	public int getProfondeurDiff() {
		return profondeurDiff;
	}

	public void setProfondeurDiff(int profondeurDiff) {
		this.profondeurDiff = profondeurDiff;
	}

	public Coup getCoupOrdiDiff() {
		return coupOrdiDiff;
	}

	public void setCoupOrdiDiff(Coup coupOrdiDiff) {
		this.coupOrdiDiff = coupOrdiDiff;
	}

	public double[][] getEvalPlacementJ2() {
		return evalPlacementJ2;
	}

	public void setEvalPlacementJ2(double[][] evalPlacementJ2) {
		this.evalPlacementJ2 = evalPlacementJ2;
	}

	public double[][] getEvalPlacementJ1() {
		return evalPlacementJ1;
	}

	public void setEvalPlacementJ1(double[][] evalPlacementJ1) {
		this.evalPlacementJ1 = evalPlacementJ1;
	}

	private int profondeurDiff;
}
