package IHM;

import Moteur.Coup;
import Moteur.DepPion;
import Moteur.DepRang;
import Moteur.Moteur;
import Moteur.Moteur.Case;
import Moteur.Moteur.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class AireDeDessin extends JComponent {
    private static final long serialVersionUID = 1L;
    Graphics2D drawable;
    Fenetre fen;
    public boolean movable;
    Point p1;
    ArrayList<Point> lsurvols;
    ArrayList<Coup> lsurvolsRanger;
    int N;
    BufferedImage pion1, pion2, imgBackground, fleche;
    public Moteur moteur;

    public AireDeDessin(Fenetre f, Moteur m) {
        fen = f;
        moteur = m;
        N = moteur.N + 2;
        p1 = null;
        initSurvols();

        try {
            pion1 = ImageIO.read(new File("res/pion1.png"));
            pion2 = ImageIO.read(new File("res/pion2.png"));
            imgBackground = ImageIO.read(new File("res/plateau.png"));
            fleche = ImageIO.read(new File("res/fleche.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initSurvols(){
        lsurvols = new ArrayList<>();
        lsurvolsRanger = new ArrayList<>();
    }

    public void doMove(Point p, boolean b){
        Dimension d = this.getSize();
        int x, y;
        int width, height;

        width = d.width / N;
        height = d.height / N;
        y = (p.x / width) - 1;
        x = (p.y / height) - 1;
        moteur.joue_coup(new DepPion(p1, new Point(x,y)));
        p1 = null;
        movable = false;
        lsurvols = new ArrayList<>();
        lsurvolsRanger = new ArrayList<>();
        repaint();
    }

    public void doMoveRanger(Point p){
        Dimension d = this.getSize();
        int x, y;
        int width, height;

        width = d.width / N;
        height = d.height / N;
        y = (p.x / width) - 1;
        x = (p.y / height) - 1;
        Direction dir = null;
        int ranger = 0;
        if(x == -1){
        	dir = Direction.BAS;
        	ranger = y;
        }
        else if(x == moteur.N){
        	dir = Direction.HAUT;
        	ranger = y;
        }
        else if(y == -1){
        	dir = Direction.DROITE;
        	ranger = x;
        }
        else if(y == moteur.N){
        	dir = Direction.GAUCHE;
        	ranger = x;
        }
        moteur.joue_coup(new DepRang(dir,ranger,false));
        repaint();
    }

    public void setSurvol(Point p) {
        if(p1 == null) {
            Dimension d = this.getSize();
            int x, y;
            int width, height;

            width = d.width / N;
            height = d.height / N;
            y = p.x / width;
            x = p.y / height;
            x--;
            y--;
            Point caseCurr = new Point(x, y);
            lsurvols = new ArrayList<>();
            lsurvolsRanger = new ArrayList<>();
//            if (fen.reglesJeu.estCasePossible(caseCurr)) {
//                for (Coup c : fen.reglesJeu.listCoupCaseSeul(fen.reglesJeu.jCurr, caseCurr)) {
//                    if (c.estDepPoint) {
//                        lsurvols.add(c.arr);
//                    }
//                }
//            }

//            lsurvolsRanger = fen.reglesJeu.listCoupRanger(fen.reglesJeu.jCurr, caseCurr);
            repaint();
        }
    }

    public void dessinerCase(Point p, Case caseCurr, Graphics2D drawable){
        Dimension d = this.getSize();
        int x, y;
        int width, height;

        width = d.width / N;
        height = d.height / N;
        y = p.x * width;
        x = p.y * height;
        dessinerCase(x, y, caseCurr, drawable);

    }

    public void dessinerCase(int x, int y, Case caseCurr,Graphics2D drawable){
        Dimension d = this.getSize();
        int width = d.width / N;
        int height = d.height / N;
        x = x + width;
        y = y + height;
        switch(caseCurr){
            case PJ1:
                drawable.drawImage(pion1, x, y, width, height, null);
                break;
            case PJ2:
                drawable.drawImage(pion2, x, y, width, height, null);
                break;
            default: // CASELIBRE
                break;
        }

    }

    void dessinerP1(Graphics2D drawable){
        Dimension d = this.getSize();
        int x, y;
        int width, height;

        width = d.width / N;
        height = d.height / N;
        y = p1.x * width;
        x = p1.y * height;
        x = x + width;
        y = y + height;
        drawable.setColor(Color.BLUE);
        drawable.fillRect(x, y, width, height);
        drawable.setColor(Color.BLACK);
    }

    void dessinerSurvol(Graphics2D drawable, Point p){
        Dimension d = this.getSize();
        int x, y;
        int width, height;

        width = d.width / N;
        height = d.height / N;
        y = p.x * width;
        x = p.y * height;
        x = x + width;
        y = y + height;
        drawable.setColor(Color.GREEN);
        drawable.fillRect(x, y, width, height);
        drawable.setColor(Color.BLACK);
    }

    void dessinerSurvolRanger(Graphics2D drawable, Coup c){
        Dimension d = this.getSize();
        int x, y;
        int width, height;

        width = d.width / N;
        height = d.height / N;
        int i = -1, j = -1;
//        if(c.deplacement == Coup.Deplacement.BAS){
//            j = c.ranger;
//        }
//        else if(c.deplacement == Coup.Deplacement.HAUT){
//            i = Moteur.Regles.N;
//            j = c.ranger;
//        }
//        else if(c.deplacement == Coup.Deplacement.DROITE){
//            i = c.ranger;
//        }
//        else if(c.deplacement == Coup.Deplacement.GAUCHE){
//            i = c.ranger;
//            j = Moteur.Regles.N;
//        }
        y = (i+1) * width;
        x = (j+1) * height;

//        if(c.deplacement == Coup.Deplacement.BAS){
//            drawable.drawImage(Fenetre.rotate(fleche, -90),x,y,width,height, null);
//        }
//        else if(c.deplacement == Coup.Deplacement.HAUT){
//            drawable.drawImage(Fenetre.rotate(fleche, 90),x,y,width,height, null);
//        }
//        else if(c.deplacement == Coup.Deplacement.DROITE){
//            drawable.drawImage(Fenetre.rotate(fleche, 180),x,y,width,height, null);
//        }
//        else if(c.deplacement == Coup.Deplacement.GAUCHE){
//            drawable.drawImage(fleche,x,y,width,height, null);
//        }
    }

    public Point calculPoint(Point p){
    	Dimension d = this.getSize();
        int x, y;
        int width, height;

        width = d.width / N;
        height = d.height / N;
        y = (p.x / width) - 1;
        x = (p.y / height) - 1;
        return new Point(x,y);
    }
    
    public void paintComponent(Graphics g) {
        // Graphics 2D est le vrai type de l'objet passe en parametre
        // Le cast permet d'avoir acces a un peu plus de primitives de dessin
        drawable = (Graphics2D) g;

        // On reccupere quelques infos provenant de la partie JComponent
        int width = getSize().width;
        int height = getSize().height;

        // On efface tout
        drawable.setColor(Color.BLACK);
        drawable.drawImage(imgBackground, 0, 0, width, height, null);

        // on dessine
        if(p1 != null){
            dessinerP1(drawable);
        }

        for (Point p : lsurvols) {
            dessinerSurvol(drawable, p);
        }

        for(Coup c : lsurvolsRanger){
            dessinerSurvolRanger(drawable, c);
        }

        for(int i =0; i < moteur.N; i++){
           for(int j =0; j < moteur.N; j++){
               dessinerCase(new Point(i,j),moteur.plateau.get(i).get(j), drawable);
           }
       }
    }

}