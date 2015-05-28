package IHM;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        rjeu = new JButton("Regles du jeu");
        scores = new JButton("Scores");
        quitter = new JButton("Quitter");
        JLabel Bienvenue = new JLabel("Bienvenue !");

        jeu = new JMenu("Jeu");
        demarrer = new JMenuItem("Demarrer");
        rejouer = new JMenuItem("Rejouer");
        Charger = new JMenuItem("Charger");
        svg = new JMenuItem("Sauvegarder");
        Quitter = new JMenuItem("Quitter");
        svg.setEnabled(false);
        rejouer.setEnabled(false);

        options = new JMenu("Options");
        reglages = new JMenuItem("Reglages");

        Scores = new JMenu("Scores");
        afficher = new JMenuItem("Afficher");

        PInterro = new JMenu("?");
        regles = new JMenuItem("Règles");
        apropos = new JMenuItem(" A propos");
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

        options.add(reglages);

        Scores.add(afficher);

        PInterro.add(regles);
        PInterro.add(apropos);

        panel.add(new JLabel(image));

        frame.setLocationRelativeTo(null);

        JMenuBar barre = new JMenuBar();
        barre.add(jeu);
        barre.add(options);
        barre.add(Scores);
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
