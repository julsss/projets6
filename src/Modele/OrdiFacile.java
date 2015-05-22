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
		System.out.println("taille : " + taille);
		int val = rand.nextInt(taille-1);
		System.out.println("val : " +val);
		return liste.get(val);
	}
}
