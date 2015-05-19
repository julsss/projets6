package IHM;
import java.awt.image.*;

import javax.swing.*;

import Moteur.GameBoard;


public class Fenetre implements Runnable {
	private int nbLigne = 5, nbCol = 5;
	private AireDeDessin aireDessin;
	
	
	public void run() {
		// Creation d'une fenetre
		int largeur = 500;
		int hauteur = 250;
		
		JFrame frame = new JFrame("Gaufre empoisonnee");

		
		
		GameBoard g = new GameBoard(nbLigne,nbCol);
		
		aireDessin = new AireDeDessin(largeur,hauteur,g);
		aireDessin.addMouseListener(new EcouteurDeSouris(aireDessin));
		
		// Ajout de notre composant de dessin dans la fenetre
		frame.add(aireDessin);

		JMenu principal = new JMenu("Principal");
		JMenuItem nouveau = new JMenuItem("Nouveau");
		JMenuItem svg = new JMenuItem("Sauvegarder");
        JMenuItem charger = new JMenuItem("Charger");
        JMenuItem quitter = new JMenuItem("Quitter");
        
        
        nouveau.addActionListener(new NouvelleApplication(aireDessin));
        svg.addActionListener(new SauvegarderApplication(aireDessin));
        charger.addActionListener(new ChargerApplication(aireDessin));
        quitter.addActionListener(new QuitterApplication(aireDessin));
        
        JMenu edition = new JMenu("Edition");
        JMenuItem annuler = new JMenuItem("Annuler");
        JMenuItem refaire = new JMenuItem("Refaire");
        
        annuler.addActionListener(new AnnulerApplication(aireDessin));
        refaire.addActionListener(new RefaireApplication(aireDessin));
        
        principal.add(nouveau);
        principal.add(svg);
        principal.add(charger);
        principal.add(quitter);
        
        edition.add(annuler);
        edition.add(refaire);
        
        JMenuBar barre = new JMenuBar();
        barre.add(principal);
        barre.add(edition);
        frame.setJMenuBar(barre);
        
		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(500, 250);
		frame.setVisible(true);
	}

	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Fenetre());
	}

	public int getNbLigne() {
		return nbLigne;
	}

	public void setNbLigne(int nbLigne) {
		this.nbLigne = nbLigne;
	}
	
	public int getNbCol() {
		return nbCol;
	}

	public void setNbCol(int nbCol) {
		this.nbCol = nbCol;
	}
}
