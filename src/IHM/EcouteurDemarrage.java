package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class EcouteurDemarrage implements ActionListener{
	MenuDemarrage f;
	
	public EcouteurDemarrage(MenuDemarrage f) {
		this.f = f;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == f.quitter || source == f.Quitter) {
            System.exit(0);
        }
        else if (source == f.nvpartie) {

        }
        else if (source == f.rjeu || source == f.regles) {
            SwingUtilities.invokeLater(new ReglesQuits());
        }
        else if (source == f.prapide || source == f.demarrer) {
            SwingUtilities.invokeLater(new Fenetre());
            f.frame.setVisible(false);
        }
        else if (source == f.charger || source == f.Charger) {
            JFileChooser f = new JFileChooser();
            f.setDialogTitle("Importer une partie");
            f.setSelectedFile(new File("save1"));
            f.setCurrentDirectory(new File("Save"));
            int returnVal = f.showOpenDialog(new JFrame());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedReader in = new BufferedReader(new FileReader(f.getSelectedFile()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if (source == f.scores) {

        }
        else if (source == f.rejouer){

        }



    }
}
