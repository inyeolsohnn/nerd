package control;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.CarWorld;
import view.CarSimView;

public class Main {
	public static void main(String[] args) {

		WorldController wControl = new WorldController();
		JFrame frame = new CarSimView("carSim", wControl);
		wControl.setView(frame);

		CarWorld cWorld = wControl.createWorld();
		RoadController rControl = new RoadController(frame, cWorld);

		try {
			wControl.simulate();
			((CarSimView) frame).mainMenu();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("simulation error");
			e.printStackTrace();
		}

	}

}
