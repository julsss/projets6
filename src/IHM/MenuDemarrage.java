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

public class MenuDemarrage implements Runnable,ActionListener {
	JFrame frame = new JFrame("Quits");
    JButton nvpartie = new JButton("Nouvelle Partie");
				JButton prapide = new JButton("Partie Rapide");
				JButton charger = new JButton("Charger");
				JButton rjeu = new JButton("Regles du jeu");
				JButton scores = new JButton("Scores");
				JButton quitter = new JButton("Quitter");
    JLabel Bienvenue = new JLabel("Bienvenue !");
    
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
        
        //Creation d'espace entre les bouton (Verticale)
        gl.setVgap(8);
        
        ImageIcon image = new ImageIcon("quits-titre.gif");
        
        JMenu jeu = new JMenu("Jeu");
        JMenuItem demarrer = new JMenuItem("Demarrer");
        JMenuItem rejouer = new JMenuItem("Rejouer");
        JMenuItem Charger = new JMenuItem("Charger");
        JMenuItem svg = new JMenuItem("Sauvegarder");
        JMenuItem Quitter = new JMenuItem("Quitter");
        svg.setEnabled(false);
        rejouer.setEnabled(false);
        
        JMenu options = new JMenu("Options");
        JMenuItem reglages = new JMenuItem("Reglages");
        
        JMenu Scores = new JMenu("Scores");
        JMenuItem afficher = new JMenuItem("Afficher");
        
        JMenu PInterro = new JMenu("?");
        JMenuItem regles = new JMenuItem("Règles");
        JMenuItem apropos = new JMenuItem(" A propos");
        
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
        
                nvpartie.addActionListener(this);
                prapide.addActionListener(this);
                charger.addActionListener(this);
                rjeu.addActionListener(this);
                scores.addActionListener(this);
                quitter.addActionListener(this);
        
                //Fermeture de la fenetre en un clic
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(350,700);
		        frame.setVisible(true);
				
		
		 
	}

    public void actionPerformed(ActionEvent e) {
        Object  source=e.getSource();
        
        if  (source==quitter){
            System.exit(0);}
        else if (source==nvpartie){
            }
        else if (source==rjeu){
            SwingUtilities.invokeLater( new ReglesQuits());}
        else if (source==prapide){
        SwingUtilities.invokeLater(new Fenetre());
            frame.setVisible(false);}
        
        else if (source==charger){}
                        //
        else if (source==scores){}
                            //
    }
    
	public static void main(String[] args){
        
		SwingUtilities.invokeLater( new MenuDemarrage());
		
	
    }
	
}
