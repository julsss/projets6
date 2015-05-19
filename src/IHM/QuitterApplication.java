package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitterApplication implements ActionListener {

	private AireDeDessin aireDessin;
	
	public QuitterApplication(AireDeDessin a) {
		setAireDessin(a);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

	public AireDeDessin getAireDessin() {
		return aireDessin;
	}

	public void setAireDessin(AireDeDessin aireDessin) {
		this.aireDessin = aireDessin;
	}

}
