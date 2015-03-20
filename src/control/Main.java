package control;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.CarWorld;
import view.CarSimView;

public class Main {
	public static void main(String[] args) {

		WorldController wControl = new WorldController();
		CarWorld cWorld = wControl.createWorld();
		TrafficLightController tlc = new TrafficLightController(cWorld);
		JFrame frame = new CarSimView("carSim", wControl, tlc);
		wControl.setView(frame);
		tlc.setView(frame);

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
