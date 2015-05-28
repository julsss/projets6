package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class EcouteurReglage implements ActionListener{

	Reglages f;
	
	public EcouteurReglage(Reglages f)
	{
	    this.f = f;
	}
	/*
	JButton HvsIA = new JButton("Joueur vs IA");
	JButton HvsH = new JButton("Joueur vs Joueur");
	JButton IAvsIA = new JButton("IA vs IA");
	JButton Menu = new JButton("Revenir au menu");
	JButton Demarrer = new JButton("Demarrer la partie !");
	*/
	public void actionPerformed(ActionEvent e) {
        Object  source=e.getSource();
        
        if (source==f.Menu)
        {
            SwingUtilities.invokeLater(new MenuDemarrage());
            f.frame.dispose();
        } 
        else if(source==f.HvsIA) 
        {
            SwingUtilities.invokeLater( new Fenetre());
            f.frame.dispose();
        } 
        else if(source==f.Demarrer) 
        {
            SwingUtilities.invokeLater( new Fenetre());
            f.frame.dispose();
        }
        
    }
}
