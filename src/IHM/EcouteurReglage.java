package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import Modele.*;
import Moteur.Moteur;

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
		Moteur m;
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
				m = new Moteur(new Humain(f.j1.getText()), new Humain(f.j2.getText()));
				SwingUtilities.invokeLater( new Fenetre(m));
				f.frame.dispose();
			}
			else if(gameMode == 1)
			{
				System.out.println("humain vs IA ");
				Humain h = new Humain(f.j3.getText());
				if (f.slider.getValue() == 0)//IA facile
				{
					m = new Moteur(h, new OrdiFacile());
				}
				else if (f.slider.getValue() == 1)//IA moyen
				{
					m = new Moteur(h, new OrdiMoyen());
				}
				else //IA difficile
				{
					m = new Moteur(h, new OrdiDifficile());
				}
				SwingUtilities.invokeLater( new Fenetre(m));
				f.frame.dispose();
			}
			else if(gameMode == 2)
			{
				
				if (f.slider.getValue() == 0)//IA facile
				{
					OrdiFacile of = new OrdiFacile();
					if (f.slider.getValue() == 0)//IA facile
					{
						m = new Moteur(of, new OrdiFacile());
					}
					else if (f.slider.getValue() == 1)//IA moyen
					{
						m = new Moteur(of, new OrdiMoyen());
					}
					else //IA difficile
					{
						m = new Moteur(of, new OrdiDifficile());
					}
				}
				else if (f.slider.getValue() == 1)//IA moyen
				{
					OrdiMoyen om = new OrdiMoyen();
					if (f.slider.getValue() == 0)//IA facile
					{
						m = new Moteur(om, new OrdiFacile());
					}
					else if (f.slider.getValue() == 1)//IA moyen
					{
						m = new Moteur(om, new OrdiMoyen());
					}
					else //IA difficile
					{
						m = new Moteur(om, new OrdiDifficile());
					}
				}
				else //IA difficile
				{
					OrdiDifficile od = new OrdiDifficile();
					if (f.slider.getValue() == 0)//IA facile
					{
						m = new Moteur(od, new OrdiFacile());
					}
					else if (f.slider.getValue() == 1)//IA moyen
					{
						m = new Moteur(od, new OrdiMoyen());
					}
					else //IA difficile
					{
						m = new Moteur(od, new OrdiDifficile());
					}
				}
				SwingUtilities.invokeLater( new Fenetre(m));
				f.frame.dispose();
			}
			
		}

	}
}
