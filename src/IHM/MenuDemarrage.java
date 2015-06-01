package IHM;

import static java.awt.event.ActionEvent.CTRL_MASK;

import javax.swing.BoxLayout;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuDemarrage implements Runnable{
	public JFrame frame;
    public JButton nvpartie, prapide, charger, rjeu, scores, quitter;
    public JMenu jeu, options, Scores, PInterro;
    public JMenuItem demarrer, rejouer, Charger, svg, Quitter, reglages, afficher, regles, apropos;

    public MenuDemarrage(){
        frame = new JFrame("Quits");
        nvpartie = new JButton("Nouvelle Partie");
        prapide = new JButton("Partie Rapide");
        charger = new JButton("Charger");
        rjeu = new JButton("Règles du jeu");
        scores = new JButton("Scores");
        quitter = new JButton("Quitter");
        JLabel Bienvenue = new JLabel("Bienvenue !");

        jeu = new JMenu("Jeu");
        demarrer = new JMenuItem("Commencer une nouvelle partie");
        rejouer = new JMenuItem("Rejouer");
        Charger = new JMenuItem("Charger");
        svg = new JMenuItem("Sauvegarder");
        Quitter = new JMenuItem("Quitter l'application");
        svg.setEnabled(false);
        rejouer.setEnabled(false);
        
        demarrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, CTRL_MASK));
        rejouer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, CTRL_MASK));
        svg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, CTRL_MASK));
        Charger.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, CTRL_MASK));
        Quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, CTRL_MASK));
        
        //options = new JMenu("Options");
        //Scores = new JMenu("Scores");
        
        PInterro = new JMenu("?");
        regles = new JMenuItem("Règles du jeu");
        apropos = new JMenuItem(" A propos");
        reglages = new JMenuItem("Réglages");
        afficher = new JMenuItem("Scores");
    }

	public void run(){

        //JFrame frame = new JFrame("Quits");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250,500);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        //Creation de la grille
        GridLayout gl = new GridLayout(7,7);
        panel.setLayout(gl);

        EcouteurDemarrage ed = new EcouteurDemarrage(this);

        //Creation d'espace entre les bouton (Verticale)
        gl.setVgap(8);

        ImageIcon image = new ImageIcon("res/Quits.png");


        jeu.add(demarrer);
        jeu.add(rejouer);
        jeu.add(svg);
        jeu.add(Charger);
        jeu.add(Quitter);

        PInterro.add(reglages);
        PInterro.add(regles);
        PInterro.add(afficher);
        PInterro.add(apropos);

        panel.add(new JLabel(image));

        frame.setLocationRelativeTo(null);

        JMenuBar barre = new JMenuBar();
        barre.add(jeu);
        //barre.add(options);
        //barre.add(Scores);
        barre.add(PInterro);
        frame.setJMenuBar(barre);

		//Creation de Boutons
				/*JButton nvpartie = new JButton("Nouvelle Partie");
				JButton prapide = new JButton("Partie Rapide");
				JButton charger = new JButton("Charger");
				JButton rjeu = new JButton("Regles du jeu");
				JButton scores = new JButton("Scores");
				JButton quitter = new JButton("Quitter");
                JLabel Bienvenue = new JLabel("Bienvenue !");*/

		//Ajout des boutons
        panel.add(nvpartie);
        panel.add(prapide);
        panel.add(charger);
        panel.add(rjeu);
        panel.add(scores);
        panel.add(quitter);

        frame.add(panel);

        nvpartie.addActionListener(ed);
        prapide.addActionListener(ed);
        charger.addActionListener(ed);
        rjeu.addActionListener(ed);
        scores.addActionListener(ed);
        quitter.addActionListener(ed);

        demarrer.addActionListener(ed);
        rejouer.addActionListener(ed);
        Charger.addActionListener(ed);
        svg.addActionListener(ed);
        Quitter.addActionListener(ed);
        reglages.addActionListener(ed);
        afficher.addActionListener(ed);
        regles.addActionListener(ed);
        apropos.addActionListener(ed);

        //Fermeture de la fenetre en un clic
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,700);
        frame.setVisible(true);
	}

	public static void main(String[] args){

		SwingUtilities.invokeLater( new MenuDemarrage());


    }

}