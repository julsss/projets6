package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class EcouteurFenetreWin implements ActionListener{
	FenetreWin f;
	Fenetre f1;
	public EcouteurFenetreWin(FenetreWin fe, Fenetre  fi)
	{
		f = fe;
		f1 = fi;
	}
	
	public void actionPerformed(ActionEvent e) {
		 Object source = e.getSource();

		 if(source == f.mPrincipal)
		 {
			 SwingUtilities.invokeLater(new MenuDemarrage());
			 f.frame.dispose();
			 f1.frame.dispose();
		 }
		 else if(source == f.plateau)
		 {
			 f1.frame.setEnabled(true);
			 f.frame.dispose();
			 
			 
		 }
		
	}

}
