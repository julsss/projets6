package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class EcouteurReglage implements ActionListener{

	Reglages f;
	int gameMode;
	/*
	 * gameMode = 0 => humain vs humain
	 * gameMode = 1 => humain vs IA
	 * gameMode = 2 => IA vs IA  
	 * gameMode = -1 => pas de choix de fait.
	 */
	public EcouteurReglage(Reglages f)
	{
		this.f = f;
		gameMode = -1;
	}
	/*
	JButton HvsIA, HvsH, IAvsIA, Menu, Demarrer;
	JTextField j3, j1, j2;
	JSlider slider, slider2, slider3;
	 */
	public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();

		if (source==f.Menu)
		{
			SwingUtilities.invokeLater(new MenuDemarrage());
			f.frame.dispose();
		} 
		else if(source==f.IAvsIA)
		{
			gameMode = 2;
			f.j1.setVisible(false);
			f.j2.setVisible(false);
			f.j3.setVisible(false);
			f.slider.setVisible(false);
			f.slider2.setVisible(true);
			f.slider3.setVisible(true);
		}
		else if(source==f.HvsIA)
		{
			gameMode = 1;
			f.j1.setVisible(false);
			f.j2.setVisible(false);
			f.j3.setVisible(true);
			f.slider.setVisible(true);
			f.slider2.setVisible(false);
			f.slider3.setVisible(false);
		}
		else if(source==f.HvsH)
		{
			gameMode = 0;
			f.j1.setVisible(true);
			f.j2.setVisible(true);
			f.j3.setVisible(false);
			f.slider.setVisible(false);
			f.slider2.setVisible(false);
			f.slider3.setVisible(false);
		}
		else if(source==f.Demarrer) 
		{
			if(gameMode == -1)
			{
				System.out.println("faire apparaitre une pop-up pour dire au joueur qu'il n'as rien choisi");
			}
			else if(gameMode == 0)
			{
				System.out.println("humain vs humain ");
				SwingUtilities.invokeLater( new Fenetre());
				f.frame.dispose();
			}
			else if(gameMode == 1)
			{
				System.out.println("humain vs IA ");
				SwingUtilities.invokeLater( new Fenetre());
				f.frame.dispose();
			}
			else if(gameMode == 2)
			{
				System.out.println("IA vs IA ");
				SwingUtilities.invokeLater( new Fenetre());
				f.frame.dispose();
			}
			
		}

	}
}
