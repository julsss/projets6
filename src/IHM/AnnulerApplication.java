package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnulerApplication implements ActionListener {

	private AireDeDessin aireDessin;
	
	public AnnulerApplication(AireDeDessin a) {
		setAireDessin(a);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		aireDessin.getHisto().annuler();
		aireDessin.repaint();

	}

	public AireDeDessin getAireDessin() {
		return aireDessin;
	}

	public void setAireDessin(AireDeDessin aireDessin) {
		this.aireDessin = aireDessin;
	}

}
