package Modele;
import java.awt.*;
import java.util.*;
//import Moteur.Coup;
import Moteur.Moteur;


public class OrdiFacile extends Joueur {

	public OrdiFacile(){
		super(true, false);
	}

	public OrdiFacile(int score){
		super(score,true);
	}

	private static Random rand = new Random();

	public static Point facile(int [][] gaufre, Moteur m){
		Point coup = new Point();
		boolean valide = false;
		//boolean perdant= false;
		while(!valide){
			coup.x= rand.nextInt(gaufre.length);
			coup.y = rand.nextInt(gaufre[0].length);

			//valide = m.coup_valide(gaufre, coup.x, coup.y);

			//perdant = m.coup_perdant(gaufre, coup.x, coup.y);
		}
		return coup;
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
