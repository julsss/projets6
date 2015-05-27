package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class EcouteurDemarrage implements ActionListener{
	MenuDemarrage f;
	
	public EcouteurDemarrage(MenuDemarrage f) {
		this.f = f;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        
        if (source==f.quitter){
            System.exit(0);
        }
        else if (source==f.nvpartie){
            }
        else if (source==f.rjeu){
            SwingUtilities.invokeLater( new ReglesQuits());}
        else if (source==f.prapide){
        SwingUtilities.invokeLater(new Fenetre());
            f.frame.setVisible(false);}
        
        else if (source==f.charger){}
                        //
        else if (source==f.scores){}
                            //
    }
}
