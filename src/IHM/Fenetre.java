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
import java.awt.event.KeyEvent;
import static java.awt.event.ActionEvent.CTRL_MASK;

public class Fenetre implements Runnable{
        int dimension;
        Moteur mot;
        PanelJoueur panelJ1, panelJ2;
        public JFrame frame;
        public JButton annuler, refaire;
        JMenuItem nouveau, rejouer, svg, quitter, charger;
        JMenuItem reglages, scores, regles, aPropos, quitterM;
        EcouteurFenetre f;

        public Fenetre(Moteur m) {
			mot = m;
			
		}

		public void run() {
                // Creation d'une fenetre
			f = new EcouteurFenetre(this);
                frame = new JFrame("Quits");
                frame.setPreferredSize(new Dimension(1000, 600));
                SpringLayout alpha = new SpringLayout();

                frame.setLayout(alpha);

                AireDeDessin aireDessin = new AireDeDessin(this, mot);
                aireDessin.setPreferredSize(new Dimension(500, 500));
                EcouteurDeSouris eds = new EcouteurDeSouris(this,aireDessin);
                aireDessin.addMouseListener(eds);
                aireDessin.addMouseMotionListener(eds);

                panelJ1 = new PanelJoueur(this,alpha,"Joueur 1",true,1);
                panelJ2 = new PanelJoueur(this,alpha,"Joueur 2",false,2);

                annuler = new JButton("Annuler");
                refaire = new JButton("Refaire");

                annuler.addActionListener(f);
                refaire.addActionListener(f);
                // Ajout de notre composant de dessin dans la fenetre
                frame.add(panelJ1);
                frame.add(panelJ2);
                frame.add(annuler);
                frame.add(refaire);
                frame.add(aireDessin);

                alpha.putConstraint(SpringLayout.NORTH, panelJ1, 20, SpringLayout.NORTH, frame);
                alpha.putConstraint(SpringLayout.WEST, panelJ1, 20, SpringLayout.WEST, frame);
                alpha.putConstraint(SpringLayout.NORTH, panelJ2, 0, SpringLayout.NORTH, panelJ1);
                alpha.putConstraint(SpringLayout.EAST, panelJ2, -20, SpringLayout.EAST, frame);
                alpha.putConstraint(SpringLayout.SOUTH, annuler, -80, SpringLayout.SOUTH, frame);
                alpha.putConstraint(SpringLayout.WEST, annuler, 20, SpringLayout.WEST, panelJ1);
                alpha.putConstraint(SpringLayout.NORTH, refaire, 0, SpringLayout.NORTH, annuler);
                alpha.putConstraint(SpringLayout.EAST, refaire, -20, SpringLayout.EAST, panelJ2);
                alpha.putConstraint(SpringLayout.VERTICAL_CENTER, aireDessin, 0, SpringLayout.VERTICAL_CENTER, frame);
                alpha.putConstraint(SpringLayout.HORIZONTAL_CENTER, aireDessin, 0, SpringLayout.HORIZONTAL_CENTER, frame);

		/*---------  MENU --------*/

                JMenu jeu = new JMenu("Jeu");
                JMenuItem nouveau = new JMenuItem("Commencer une nouvelle partie");
                JMenuItem rejouer = new JMenuItem("Rejouer");
                JMenuItem svg = new JMenuItem("Sauvegarder");
                JMenuItem charger = new JMenuItem("Charger");
                JMenuItem quitter = new JMenuItem("Quitter l'application");
          
        /*
        partie.addActionListener(new NouvelleApplication(aireDessin));
        chargerButton.addActionListener(new ChargerApplication(aireDessin));
        quitter.addActionListener(new QuitterApplication(aireDessin));
        */

                JMenu autres = new JMenu(" ? ");
                JMenuItem reglages = new JMenuItem("Réglages");
                JMenuItem scores = new JMenuItem("Scores");
                JMenuItem regles = new JMenuItem("Règles du jeu");
                JMenuItem aPropos = new JMenuItem("A propos");

                jeu.add(nouveau);
                jeu.add(rejouer);
                jeu.add(svg);
                jeu.add(charger);
                jeu.add(quitter);
                
                nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, CTRL_MASK));
                rejouer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, CTRL_MASK));
                svg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, CTRL_MASK));
                charger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, CTRL_MASK));
                quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, CTRL_MASK));
                
                autres.add(reglages);
                autres.add(scores);
                autres.add(regles);
                autres.add(aPropos);

                JMenuBar barreMenu = new JMenuBar();
                barreMenu.add(jeu);
                barreMenu.add(autres);
                frame.setJMenuBar(barreMenu);

                // Un clic sur le bouton de fermeture clos l'application
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);

                // On fixe la taille et on demarre
                frame.setSize(frame.getPreferredSize());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.repaint(1000,0,0,frame.getWidth(),frame.getHeight());
                
                
                nouveau.addActionListener(f);
                rejouer.addActionListener(f);
                svg.addActionListener(f);
                charger.addActionListener(f);
                quitter.addActionListener(f);
                reglages.addActionListener(f);
                scores.addActionListener(f);
                aPropos.addActionListener(f);
                quitterM.addActionListener(f);
        }
}