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
    Fenetre f;
    Point p1;
    ArrayList<Point> lsurvols;
    ArrayList<Coup> lsurvolsRanger;
    int N;
    BufferedImage pion1, pion2, imgBackground, fleche, survol, caseselect;
    public Moteur moteur;

    public AireDeDessin(Fenetre f, Moteur m) {
        this.f = f;
        moteur = m;
        N = Moteur.N + 2;
        p1 = null;
        initSurvols();

        try {
            pion1 = ImageIO.read(new File("res/pion1.png"));
            pion2 = ImageIO.read(new File("res/pion2.png"));
            imgBackground = ImageIO.read(new File("res/plateau.png"));
            fleche = ImageIO.read(new File("res/fleche.png"));
            survol = ImageIO.read(new File("res/survol.png"));
            caseselect = ImageIO.read(new File("res/CaseSelect.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initSurvols(){
        lsurvols = new ArrayList<>();
        lsurvolsRanger = new ArrayList<>();
        repaint();
    }

    public void doMove(Point p){
        DepPion d = new DepPion(p1, p);
        if(moteur.estCoupPossible(d)) {
            moteur.joue_coup(d);
        }
        p1 = null;
        lsurvols = new ArrayList<>();
        lsurvolsRanger = new ArrayList<>();
        repaint();
    }

    public void doMoveRanger(Point p){
        Direction dir = null;
        int ranger = 0;
        if(p.x == -1){
            dir = Direction.BAS;
            ranger = p.y;
        }
        else if(p.x == Moteur.N){
            dir = Direction.HAUT;
            ranger = p.y;
        }
        else if(p.y == -1){
            dir = Direction.DROITE;
            ranger = p.x;
        }
        else if(p.y == Moteur.N){
            dir = Direction.GAUCHE;
            ranger = p.x;
        }
        moteur.joue_coup(new DepRang(dir,ranger,false));
        repaint();
    }

    public void setSurvol(Point p) {
        lsurvols = new ArrayList<>();
        lsurvolsRanger = new ArrayList<>();
        ArrayList<Coup> l = moteur.listeCoupPossible();

        //System.out.println(p + " " + l);
        for(Coup c : l) {
            if(c instanceof DepPion) {
                Point tmp = ((DepPion) c).getDepart();
                if(tmp.x == p.x && tmp.y == p.y)
                    lsurvols.add(((DepPion) c).getArrive());
            }
            else {
                if( ((DepRang) c).getRang() == p.x || ((DepRang) c).getRang() == p.y )
                    lsurvolsRanger.add(c);
            }
        }
        //System.out.println(lsurvolsRanger);
        repaint();
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
        drawable.drawImage(caseselect, x, y, width, height, null);
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
        drawable.drawImage(survol, x, y, width, height, null);
    }

    void dessinerSurvolRanger(Graphics2D drawable, DepRang c){
        Dimension d = this.getSize();
        int x, y;
        int width, height;

        width = d.width / N;
        height = d.height / N;
        int i = -1, j = -1;

        if(c.getDir() == Direction.BAS){
            j = c.getRang();
        }
        else if(c.getDir() == Direction.HAUT){
            i = Moteur.N;
            j = c.getRang();
        }
        else if(c.getDir() == Direction.DROITE){
            i = c.getRang();
        }
        else if(c.getDir() == Direction.GAUCHE){
            i = c.getRang();
            j = Moteur.N;
        }
        y = (i+1) * width;
        x = (j+1) * height;

        if(c.getDir() == Direction.BAS){
            drawable.drawImage(rotate(fleche, -90),x,y,width,height, null);
        }
        else if(c.getDir() == Direction.HAUT){
            drawable.drawImage(rotate(fleche, 90),x,y,width,height, null);
        }
        else if(c.getDir() == Direction.DROITE){
            drawable.drawImage(rotate(fleche, 180),x,y,width,height, null);
        }
        else if(c.getDir() == Direction.GAUCHE){
            drawable.drawImage(fleche,x,y,width,height, null);
        }
    }

    public static BufferedImage rotate(BufferedImage bImage, int angle) {
        int w = bImage.getWidth(null);
        int h = bImage.getHeight(null);
        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(angle), w / 2, h / 2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage bImage2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        op.filter(bImage, bImage2);
        return bImage2;
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
            dessinerSurvolRanger(drawable, (DepRang) c);
        }

        for(int i =0; i < Moteur.N; i++){
            for(int j =0; j < Moteur.N; j++){
                dessinerCase(new Point(i,j),moteur.plateau.get(i).get(j), drawable);
            }
        }
    }

}
