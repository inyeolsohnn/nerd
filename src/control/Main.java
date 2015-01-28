package control;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.CarWorld;
import view.CarSimView;

public class Main {
	public static void main(String[] args) {

		CarWorld cWorld = new CarWorld();
		JFrame frame = new CarSimView("carSim");
		WorldController wControl = new WorldController(frame, cWorld);
		try {
			cWorld.simulate();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
