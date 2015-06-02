package IHM;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.Font;
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
        
        frame.setBackground(Color.BLACK);
        SpringLayout sl = new SpringLayout();
        //panel.setLayout(gl);
        frame.setLayout(sl);
        ImageIcon image = new ImageIcon("res/Quits.png");
        JLabel test = new JLabel(image);
        
        JButton menu = new JButton("Menu");
        //JLabel label = new JLabel("");
        //label.setText("<html>->Le premier joueur qui parvient à faire sortir 3 de ses billes de la case opposée à son camp de départ gagne immédiatement.<br>->Déplacer une de ses billes d'une case, à gauche, à droite ou en diagonale vers l'avant. Il est donc interdit de reculer.Ou faire glisser une rangée ou une colonne en prenant une case à une extrémité, puis en l'insérant de l'autre côté. Cela a pour effet de décaler les cases (donc les billes qui s'y trouvent). C'est de cette manière que l'on peut éjecter une bille du plateau.</html>");
        Font font = new Font("Arial",Font.PLAIN, 15);
        //label.setFont(font);
        //label.setOpaque(true);
        //label.setBackground(Color.GREEN);
        //panel.add(new JLabel(image));
        JTextArea textArea = new JTextArea(12, 22);
        textArea.setEditable(false);
        textArea.setText("-> Le premier joueur qui parvient à faire sortir 3 de ses billes de la case opposée à son camp de départ remporte la partie. \n -> Déplacer une de ses billes d'une case, à gauche, à droite ou en diagonale vers l'avant. Il est donc interdit de reculer.Ou faire glisser une rangée ou une colonne en prenant une case à une extrémité, puis en l'insérant de l'autre côté. Cela a pour effet de décaler les cases (donc les billes qui s'y trouvent). C'est de cette manière que l'on peut éjecter une bille du plateau.");
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        menu.addActionListener(this);
        frame.add(test);
        frame.add(textArea);
        frame.add(menu);
        
        //positionnement des composants
        sl.putConstraint(SpringLayout.WEST, test, 80, SpringLayout.HORIZONTAL_CENTER, frame);
        sl.putConstraint(SpringLayout.WEST, textArea, 8, SpringLayout.WEST, frame);
        sl.putConstraint(SpringLayout.NORTH, textArea, 125, SpringLayout.NORTH, frame);
        sl.putConstraint(SpringLayout.WEST, menu, 130, SpringLayout.HORIZONTAL_CENTER, frame);
        sl.putConstraint(SpringLayout.NORTH, menu, 350, SpringLayout.SOUTH, frame);
        
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(350,700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e){
        frame.dispose();
    }
}
