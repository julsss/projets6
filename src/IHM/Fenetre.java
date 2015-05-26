package IHM;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

import Modele.Humain;
import Modele.OrdiFacile;
import Moteur.Moteur;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre implements Runnable,ActionListener {
	int dimension;
	Moteur mot;
	
	public void run() {
		// Creation d'une fenetre
		int largeur = 800;
		int hauteur = 400;
		mot = new Moteur(new Humain(), new OrdiFacile());
		JFrame frame = new JFrame("Quits");
        frame.setPreferredSize(new Dimension(800, 600));

		BufferedImage image = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);
		
		AireDeDessin aireDessin = new AireDeDessin(image, mot);
		aireDessin.setPreferredSize(new Dimension(400, 400));
		aireDessin.addMouseListener(new EcouteurDeSouris(aireDessin));
		//aireDessin.addMouseListener(new EcouteurDeSouris(aireDessin));
        // Ajout de notre composant de dessin dans la fenetre
		frame.add(aireDessin);
		
		
		/*---------  MENU --------*/
		
		JMenu jeu = new JMenu("Jeu");
		JMenuItem nouveau = new JMenuItem("Commencer une nouvelle partie");
		JMenuItem rejouer = new JMenuItem("Rejouer");
		JMenuItem svg = new JMenuItem("Sauvegarder");
        JMenuItem charger = new JMenuItem("Charger");
        JMenuItem quitter = new JMenuItem("Quitter");
        
        /*
        partie.addActionListener(new NouvelleApplication(aireDessin));
        chargerButton.addActionListener(new ChargerApplication(aireDessin));
        quitter.addActionListener(new QuitterApplication(aireDessin));
        */
        
        JMenu autres = new JMenu(" ? ");
        JMenuItem reglages = new JMenuItem("Réglages");
        JMenuItem scores = new JMenuItem("Scores");
        JMenuItem regles = new JMenuItem("Règles du jeu");
        JMenuItem apropos = new JMenuItem("A propos");
        JButton Quitter = new JButton("Quitter");
        
        
        //frame.add(quitter);
        jeu.add(nouveau);
        jeu.add(rejouer);
        jeu.add(svg);
        jeu.add(charger);
        jeu.add(quitter);
        
        autres.add(reglages);
        autres.add(scores);
        autres.add(regles);
        autres.add(apropos);
        
        JMenuBar barreMenu = new JMenuBar();
        barreMenu.add(jeu);
        barreMenu.add(autres);
        frame.setJMenuBar(barreMenu);
        
		// Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On fixe la taille et on demarre
		frame.setSize(largeur, hauteur);
        frame.setLocationRelativeTo(null);
        
		frame.setVisible(true);
	}

	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Fenetre());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
