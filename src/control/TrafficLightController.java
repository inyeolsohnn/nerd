package control;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.CarWorld;
import model.TrafficLight;

public class TrafficLightController {
	CarWorld world;
	JFrame mainFrame;

	public TrafficLightController(CarWorld cw) {
		this.world = cw;
	}

	public void setView(JFrame frame) {
		// TODO Auto-generated method stub
		mainFrame = frame;
	}

	public ArrayList<TrafficLight> getLights() {
		// TODO Auto-generated method stub
		return this.world.getLights();
	}

	public void setInterval(String color, int interval, int id) {
		ArrayList<TrafficLight> lights = getLights();
		TrafficLight selected;
		for (int i = 0; i < lights.size(); i++) {
			if (lights.get(i).getId() == id) {
				if (color.equals("red")) {
				} else if (color.equals("green")) {

				} else if (color.equals("initial")) {

				}
			}
		}
	}
}
