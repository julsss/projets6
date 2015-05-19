package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Plateau;

public class NouvelleApplication implements ActionListener {

	private AireDeDessin aireDessin;
	
	public NouvelleApplication(AireDeDessin a) {
		setAireDessin(a);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//aireDessin.getGaufre().reinit();
		//aireDessin.setHisto(new Historique(aireDessin.getGaufre()));
		aireDessin.repaint();
	}

	public AireDeDessin getAireDessin() {
		return aireDessin;
	}

	public void setAireDessin(AireDeDessin aireDessin) {
		this.aireDessin = aireDessin;
	}

}
