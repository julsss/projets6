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
	

	private int eval() {
		// TODO Auto-generated method stub
		return 0;
	}

}
