package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class EcouteurReglage implements ActionListener{

	Reglages f;
	
	public EcouteurReglage(Reglages f)
	{
	    this.f = f;
	}
	/*
	JButton HvsIA, HvsH, IAvsIA, Menu, Demarrer;
	JTextField j3, j1, j2;
	JSlider slider, slider2, slider3;(non-Javadoc)
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
            f.j3.setVisible(true);
        } 
        else if(source==f.Demarrer) 
        {
            SwingUtilities.invokeLater( new Fenetre());
            f.frame.dispose();
        }
        
    }
}
