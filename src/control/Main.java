package control;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Car Sim");
				WorldController worldCtrl = new WorldController();
				frame.setSize(worldCtrl.getcWorld().getWidth(), worldCtrl.getcWorld().getHeight());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});

	}
}
