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

public class ReglesQuits implements Runnable,ActionListener{
    
    JFrame frame = new JFrame("Regles du Quits");
    
    public void run(){
        
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(350,700);
        frame.setVisible(true);
        
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        GridLayout gl = new GridLayout(3,1);
        panel.setLayout(gl);
        
        ImageIcon image = new ImageIcon("res/Quits.png");
        
        JButton menu = new JButton("Menu");
        JLabel label = new JLabel("");
        label.setText("<html>->Le premier joueur qui parvient à faire sortir 3 de ses billes de la case opposée à son camp de départ gagne immédiatement.<br>->Déplacer une de ses billes d'une case, à gauche, à droite ou en diagonale vers l'avant. Il est donc interdit de reculer.Ou faire glisser une rangée ou une colonne en prenant une case à une extrémité, puis en l'insérant de l'autre côté. Cela a pour effet de décaler les cases (donc les billes qui s'y trouvent). C'est de cette manière que l'on peut éjecter une bille du plateau.</html>");
        Font font = new Font("Arial",Font.BOLD,15);
        label.setFont(font);
        label.setOpaque(true);
        label.setBackground(Color.GREEN);
        panel.add(new JLabel(image));
        
        frame.setLocationRelativeTo(null);
    
        menu.addActionListener(this);
        panel.add(label);
        panel.add(menu);
        
        frame.add(panel);
        
        
    }
    
    public void actionPerformed(ActionEvent e){
        frame.dispose();
    }
}
