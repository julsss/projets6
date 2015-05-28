package IHM;

import java.awt.*;
import javax.swing.*;
import java.util.Hashtable;

public class Reglages implements Runnable {
	
	int FPS_MIN, FPS_MAX, FPS_INIT;
	JFrame frame;
	JButton HvsIA, HvsH, IAvsIA, Menu, Demarrer;
	JTextField j3, j1, j2;
	JSlider slider, slider2, slider3;
	JLabel facile, moyen, dificile, A, B;
	EcouteurReglage rg;

	@SuppressWarnings("unused")
	public Reglages(){

		FPS_MIN = 0;
		FPS_MAX = 2;
		FPS_INIT = 1;
		
		frame = new JFrame("Reglages");
		HvsIA = new JButton("Joueur vs IA");
		HvsH = new JButton("Joueur vs Joueur");
		IAvsIA = new JButton("IA vs IA");
		Menu = new JButton("Revenir au menu");
		Demarrer = new JButton("Demarrer la partie !");
		j3 = new JTextField("Nom Joueur");
		j1 = new JTextField("Nom Joueur 1");
		j2 = new JTextField("Nom Joueur 2");
		slider = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT );
		slider2 = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT );
		slider3 = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT );
		facile = new JLabel("Facile");
		moyen = new JLabel("Moyen");
		dificile = new JLabel("Dificile");
		A = new JLabel("");
		B = new  JLabel("");
		rg = new EcouteurReglage(this);
	}

	public void run() {


		//Creation Frame, Panel & Layout
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		panel.setBackground(Color.BLACK);
		panel2.setBackground(Color.BLACK);
		panel3.setBackground(Color.BLACK);
		frame.setLocationRelativeTo(null);

		ImageIcon reglages = new ImageIcon("reglages.png");

		GridLayout gl = new GridLayout(9,1);
		GridLayout gl2 = new GridLayout(9,1);
		GridLayout gl3 = new GridLayout(9,1);
		panel.setLayout(gl);
		panel2.setLayout(gl2);
		panel3.setLayout(gl3);

		gl.setVgap(8);

		facile.setForeground(new Color(0,255,0));
		moyen.setForeground(new Color(0,0,255));
		dificile.setForeground(new Color(255,0,0));

		//slider1
		slider.setMajorTickSpacing(0);
		slider.setMajorTickSpacing(1);
		slider.setMajorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		//slider2
		slider2.setMajorTickSpacing(0);
		slider2.setMajorTickSpacing(1);
		slider2.setMajorTickSpacing(2);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);

		//slider3
		slider3.setMajorTickSpacing(0);
		slider3.setMajorTickSpacing(1);
		slider3.setMajorTickSpacing(2);
		slider3.setPaintTicks(true);
		slider3.setPaintLabels(true);

		//slider1
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		table.put (0, facile);
		table.put (1, moyen);
		table.put (2, dificile);
		slider.setLabelTable (table);
		slider.setVisible(false);

		//slide3
		Hashtable<Integer, JLabel> table2 = new Hashtable<Integer, JLabel>();
		table2.put (0, facile);
		table2.put (1, moyen);
		table2.put (2, dificile);
		slider2.setLabelTable (table2);
		slider2.setVisible(false);

		//slider3
		Hashtable<Integer, JLabel> table3 = new Hashtable<Integer, JLabel>();
		table3.put (0, facile);
		table3.put (1, moyen);
		table3.put (2, dificile);
		slider3.setLabelTable (table3);
		slider3.setVisible(false);

		panel.add(new JLabel(reglages));
		panel.add(HvsH);
		panel.add(HvsIA);
		panel.add(slider);
		panel.add(IAvsIA);
		panel.add(slider2);
		panel.add(slider3);
		panel.add(Demarrer);
		panel.add(Menu);

		panel2.add(A);
		panel2.add(j1);
		//j1.setEnabled(false);
		panel2.add(j3);
		j1.setVisible(false);
		j2.setVisible(false);
		j3.setVisible(false);
		
		
		panel3.add(B);
		panel3.add(j2);

		Menu.addActionListener(rg);
		Demarrer.addActionListener(rg);

		frame.add(panel3,BorderLayout.EAST);
		frame.add(panel2,BorderLayout.WEST);
		frame.add(panel,BorderLayout.CENTER);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,700);
		frame.setVisible(true);


	}

	public static void main (String[] args){
		
		SwingUtilities.invokeLater( new Reglages());


	}




}
