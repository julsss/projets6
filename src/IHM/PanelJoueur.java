package IHM;

import Modele.Joueur;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelJoueur extends JPanel {

    Graphics2D drawable;
    TitledBorder border;
    public LineBorder activeLine, inactiveLine;
    BufferedImage pion1, pion2, pion0;
    String name;
    Fenetre f;
    int jNum;
    JLabel scores;

    public PanelJoueur(Fenetre f,SpringLayout alpha, String name, boolean active, int jNum){
        super(alpha);
        scores = new JLabel("Scores : ");
        this.add(scores);

        alpha.putConstraint(SpringLayout.NORTH, scores, 50, SpringLayout.NORTH, this);
        alpha.putConstraint(SpringLayout.HORIZONTAL_CENTER, scores, 0, SpringLayout.HORIZONTAL_CENTER, this);

        activeLine = new LineBorder(Color.black, 4, true);
        inactiveLine = new LineBorder(Color.white, 4, true);
        setPreferredSize(new Dimension(150, 175));
        this.name = name;
        this.f = f;
        this.jNum = jNum;
        try {
            pion1 = ImageIO.read(new File("res/pion1.png"));
            pion2 = ImageIO.read(new File("res/pion2.png"));
            pion0 = ImageIO.read(new File("res/pion0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setActive(active);
    }

    public void setActive(boolean active){
        if(active) {
            border = new TitledBorder(activeLine, name);
        }
        else{
            border = new TitledBorder(inactiveLine, name);
        }
        border.setTitleJustification(TitledBorder.CENTER);
        setBorder(border);
    }

    public void dessinerPion(Graphics2D drawable, int val){
        int dim = 50;
        switch (val){
            case 0:
                drawable.drawImage(pion0, 10, 110, dim, dim, null);
                drawable.drawImage(pion0, 50, 110, dim, dim, null);
                drawable.drawImage(pion0, 90, 110, dim, dim, null);
                break;
            case 1:
                if(jNum == 1) {
                    drawable.drawImage(pion1, 10, 110, dim, dim, null);
                }
                else{
                    drawable.drawImage(pion2, 10, 110, dim, dim, null);
                }
                break;
            case 2:
                if(jNum == 1) {
                    drawable.drawImage(pion1, 50, 110, dim, dim, null);
                }
                else{
                    drawable.drawImage(pion2, 50, 110, dim, dim, null);
                }
                break;
            case 3:
                if(jNum == 1) {
                    drawable.drawImage(pion1, 90, 110, dim, dim, null);
                }
                else{
                    drawable.drawImage(pion2, 90, 110, dim, dim, null);
                }
                break;
        }
    }
    public void paintComponent(Graphics g) {
        // Graphics 2D est le vrai type de l'objet passe en parametre
        // Le cast permet d'avoir acces a un peu plus de primitives de dessin
        drawable = (Graphics2D) g;
        Dimension d = getPreferredSize();
        drawable.setColor(Color.white);
        drawable.fillRect(0,0, (int)d.getWidth(),(int)d.getHeight());
        dessinerPion(drawable, 0); // tous
        Joueur jscore = f.mot.getJ1();
        if(jNum == 2) {
            jscore = f.mot.getJ2();
        }

        switch (jscore.getScore()) {
            case 3:
                dessinerPion(drawable, 3);
            case 2:
                dessinerPion(drawable, 2);
            case 1:
                dessinerPion(drawable, 1);
        }
        if(jNum == 1){
            setActive(f.mot.isTourj1());
        }
        else {
            setActive(!f.mot.isTourj1());
        }
    }

}