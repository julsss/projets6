package IHM;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class FenetreWin implements Runnable{
	JFrame frame;
	Fenetre f;
	JButton mPrincipal, plateau;
	int joueur;
	
	public FenetreWin(int joueur, Fenetre fe)
	{
		f = fe;
		this.joueur = joueur;
	}
	
	public void run() {
		EcouteurFenetreWin ecf = new EcouteurFenetreWin(this, f);
		frame = new JFrame("Winner");
		frame.setPreferredSize(new Dimension(500, 200));
		
		SpringLayout alpha = new SpringLayout();
		frame.setLayout(alpha);
		
		JTextArea txt = new JTextArea("Le joueur " + joueur + " a gagné");
		txt.setBackground(frame.getBackground());
		txt.setEnabled(false);
		frame.add(txt);
		mPrincipal = new JButton("Menu Principal");
		plateau = new JButton("retour au plateau");
		mPrincipal.addActionListener(ecf);
		plateau.addActionListener(ecf);
		frame.add(plateau);
		frame.add(mPrincipal);
		
		
		alpha.putConstraint(SpringLayout.SOUTH, mPrincipal, -80, SpringLayout.SOUTH, frame);
		alpha.putConstraint(SpringLayout.WEST, mPrincipal, 10, SpringLayout.WEST, frame);
		alpha.putConstraint(SpringLayout.SOUTH, plateau, -80, SpringLayout.SOUTH, frame);
		alpha.putConstraint(SpringLayout.EAST, plateau, -20, SpringLayout.EAST, frame);
		alpha.putConstraint(SpringLayout.VERTICAL_CENTER, txt, -60, SpringLayout.VERTICAL_CENTER, frame);
		alpha.putConstraint(SpringLayout.HORIZONTAL_CENTER, txt, -20, SpringLayout.HORIZONTAL_CENTER, frame);
		
		
		frame.setSize(frame.getPreferredSize());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.repaint(1000,0,0,frame.getWidth(),frame.getHeight());
		
	}
}
