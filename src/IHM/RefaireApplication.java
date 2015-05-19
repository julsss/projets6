package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefaireApplication implements ActionListener {

	private AireDeDessin aireDessin;
	
	public RefaireApplication(AireDeDessin a) {
		setAireDessin(a);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		aireDessin.getHisto().refaire();
		aireDessin.repaint();
	}

	public AireDeDessin getAireDessin() {
		return aireDessin;
	}

	public void setAireDessin(AireDeDessin aireDessin) {
		this.aireDessin = aireDessin;
	}

}
