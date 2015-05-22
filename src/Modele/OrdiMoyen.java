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
	
	//Fonction appeler avec profondeurMoyen en parametres
	public double alphabeta(int p, double alpha, double beta, Moteur m){
		if(m.getNbBillej1() == 2 || m.getNbBillej2() == 2 || p == 0){
			return eval(m);
		}
		Coup meilleur_coup;	//A QUEL MOMENT RENVOYE LE COUP
		int taille = m.listeCoupPossible(this).size();
		System.out.println("taille : " + taille);
		for(Coup c: m.listeCoupPossible(this)){
			
			m.joue_coup(c);
			double score = - alphabeta(p-1,-beta,-alpha,m);
			m.annuler();
			if(score >= alpha){
				alpha = score;
				meilleur_coup = c;
				this.coupOrdiMoyen = meilleur_coup;
				if(alpha >= beta){
					break;
				}
			}
		}
			
		return alpha;
	}

	double [][] evalPlacementJ1 = {
			{4,   3,   2,   1,   0},
			{4.5, 3.5, 2.5, 1.5, 1},
			{5,   4,   3,   2.5, 2},
			{5.5, 4.5, 4,   3.5, 3},
			{100, 5.5, 5,   4.5, 4}
		};

	double [][] evalPlacementJ2 = {
			{4, 4.5, 5,   5.5, 100},
			{3, 3.5, 4,   4.5, 5.5},
			{2, 2.5, 3,   4,     5},
			{1, 1.5, 2.5, 3.5, 4.5},
			{0, 1,   2,   3,     4}
		};
		
	//Passage d'un moteur en parametre : j1 -> m.j1, etc...
	private double eval(Moteur m) {
	
		double score = 0;
		
		//Nombre de billes perso
		score += 5 - m.getNbBillej1();
		
		//Nombre de billes adverses		
		score -= 5 - m.getNbBillej2();
		
		
		//Distance euclidienne des billes Joueur 1 et Joueur 2
		for(int i=0;i<m.N;i++){
			for(int j=0;j<m.N;j++){
				if(this == m.getJ1()){
					if(m.getPlateau().get(i).get(j) == Case.PJ1)
						score += evalPlacementJ1[i][j];
					
					else if(m.getPlateau().get(i).get(j) == Case.PJ2)
						score -= evalPlacementJ2[i][j];
				}
				else if(this == m.getJ2()){
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
