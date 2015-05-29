package Modele;
import java.awt.*;
import java.util.*;

import Moteur.*;

public class OrdiFacile extends Joueur {

	public OrdiFacile(){
		super();
	}

	private static Random rand = new Random();

	public Coup facile(Moteur m){	
		ArrayList<Coup> liste = m.listeCoupPossible();
		int val = 0;
		int taille = liste.size();
		System.out.println("taille : " + taille);
		if(taille > 1)
			val = rand.nextInt(taille-1);
		else
			val =0;
		System.out.println("val : " +val);
		
		return liste.get(val);
	}

	@Override
	public Coup jouer(Moteur m) {
		
		// TODO Auto-generated method stub
		return facile(m);
	}
}
