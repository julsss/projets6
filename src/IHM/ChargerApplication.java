package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChargerApplication implements ActionListener {

	private AireDeDessin aireDessin;
	
	public ChargerApplication(AireDeDessin a) {
		setAireDessin(a);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public AireDeDessin getAireDessin() {
		return aireDessin;
	}

	public void setAireDessin(AireDeDessin aireDessin) {
		this.aireDessin = aireDessin;
	}

}
