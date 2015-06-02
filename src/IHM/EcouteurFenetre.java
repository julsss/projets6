package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import Modele.Humain;
import Moteur.Moteur;

public class EcouteurFenetre implements ActionListener{
	Fenetre fe;
	public EcouteurFenetre(Fenetre f)
	{
		this.fe = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();
		
		if (source ==  fe.annuler)
		{
			fe.mot.annuler();
			fe.frame.repaint();
		}
		else if(source == fe.refaire)
		{
			fe.mot.refaire();
			fe.frame.repaint();
		}
		else if(source == fe.playIA)
		{
			//TODO à remplir
		}
		else if(source == fe.nouveau || source == fe.reglages)
		{
			 SwingUtilities.invokeLater(new Reglages());
			 fe.frame.dispose();
		}
		else if(source == fe.rejouer)
		{
			SwingUtilities.invokeLater(new Fenetre(new Moteur(new Humain("joueur1"), new Humain("joueur2"))));
            fe.frame.setVisible(false);
		}
		else if(source == fe.svg)
		{
			;//TODO à faire
		}
		else if(source == fe.charger)
		{
			;// TODO à faire
		}
		else if(source == fe.quitter || source == fe.quitterM)
		{
			System.exit(0);
		}
		else if(source == fe.aPropos)
		{
			;
		}
		else if(source == fe.scores)
		{
			;
		}
	}
}
