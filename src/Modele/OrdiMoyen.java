package Modele;
import java.awt.*;
import java.util.*;
//import Moteur.Coup;
import Moteur.Moteur;


public class OrdiMoyen extends Joueur {

	public OrdiMoyen(){
		super(true, false);
	}

	public OrdiMoyen(int score){
		super(score,true);
	}

	/*public int alphabeta(int p, int alpha, int beta, Moteur m){
		alpha = Integer.MIN_VALUE;
		beta = Integer.MAX_VALUE;
		if(plateau.getNbPionNoirWin() == 3 || plateau.getNbPionBlancWin() == 3 || p == 0){
			return eval();
		}
		Coup meilleur_coup;
		Coup m;
		//for(){
			plateau.joue_coup(m);
			int score = - alphabeta(p-1,-beta,-alpha,plateau);
			plateau.annuler_coup(m);
			if(score >= alpha){
				alpha = score;
				meilleur_coup = m;
				if(alpha >= beta){
					break;
				}
			}
		//}
			
		return alpha;
	}
*/
	

	double [][] evalPlacementJ1 = {
			{4,   3,   2,   1,   0},
			{4.5, 3.5, 2.5, 1.5, 1},
			{5,   4,   3,   2.5, 2},
			{5.5, 4.5, 4,   3.5, 3},
			{6,   5.5, 5,   4.5, 4}
		};

	double [][] evalPlacementJ2 = {
			{4,4,4,4,4},
			{3,3,3,3,4},
			{2,2,2,3,4},
			{1,1,2,3,4},
			{0,1,2,3,4}
		};
		
	//Passage d'un moteur en parametre : j1 -> m.j1, etc...
	private int eval(Moteur m) {
	
		int score = 0;
		
		//Nombre de billes perso
		score += 5 - m.j1.getNbBilles();
		
		//Nombre de billes adverses		
		score -= 5 - j2.getNbBilles();
		
		
		//Distance euclidienne des billes Joueur 1 et Joueur 2
		for(int i=0;i<p.getNbCol();i++){
			for(int j=0;i<p.getNbLigne();j++){
				if(p.get(c.i).get(c.j) == Case.PJ1 && j1 == this.j1)
					score += evalPlacementJ1[i][j];
				else if(p.get(c.i).get(c.j) == Case.PJ2 && j1 == this.j2)
					score -= evalPlacementJ2[i][j];
			}
		}
		
		
		return 0;
	}

}
