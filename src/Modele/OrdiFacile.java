package Modele;
import java.awt.*;
import java.util.*;

import Moteur.*;

public class OrdiFacile extends Joueur {

	public OrdiFacile(){
		super(true, false);
	}

	public OrdiFacile(int score){
		super(score,true);
	}

	private static Random rand = new Random();

	public Coup facile(Moteur m){	
		ArrayList<Coup> liste = m.listeCoupPossible(this);
		
		int taille = liste.size();
		int val = rand.nextInt(taille);
		
		return liste.get(val);
	}
}
