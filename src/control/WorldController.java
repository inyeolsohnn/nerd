package control;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import view.CarSimView;
import model.CarWorld;
import model.Road;
import model.Car;

public class WorldController {
	private CarWorld cWorld; // core model
	private JFrame carView; // JFrame

	public WorldController(JFrame frame, CarWorld cWorld) {
		this.carView = frame;
		this.cWorld = cWorld;
		((CarSimView) frame).setController(this);

		Road road = new Road(100, 100, 400, 500, Road.STRAIGHT_LANE);
		Road endRoad = new Road(400, 500, 700, 900, Road.STRAIGHT_LANE);
		cWorld.addRoad(road);
		Car car = new Car(new Point2D.Float((float) 100.0, (float) 100.0), 0,
				road.getId(), endRoad.getId());
		Car car1 = new Car(new Point2D.Float((float) 400.0, (float) 500.0),
				100, road.getId(), endRoad.getId());
		cWorld.addCar(car);
		cWorld.addCar(car1);
		frame.setSize(cWorld.getWidth(), cWorld.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

	}

	public CarWorld getcWorld() {
		return cWorld;
	}

	public void setcWorld(CarWorld cWorld) {
		this.cWorld = cWorld;
	}

	public String toString() {
		return cWorld.toString();

	}

	public void setView(JFrame frame) {
		this.carView = frame;
	}

	public void pause() {
		this.cWorld.setStatus(false);
	}

	public void start() {
		this.cWorld.setStatus(true);
	}

	// program loop
	public void simulate() throws InterruptedException {
		while (true) {
			if (cWorld.getStatus() == true) {
				update();
				render();
				Thread.sleep(20); // the timing mechanism
									// needs improvement
			}
			if (cWorld.getStatus() == false) {
				Thread.sleep(20);
			}
		}
	}

	public HashMap<Integer, Car> getCars() {
		// TODO Auto-generated method stub
		return this.cWorld.getCars();
	}

	public HashMap<Integer, Road> getRoads() {
		// TODO Auto-generated method stub
		return this.cWorld.getRoads();
	}

	private void update() {
		System.out.println("updated");
		HashMap<Integer, Car> cars = getCars();
		HashMap<Integer, Road> roads = getRoads();
		for (Integer carKey : cars.keySet()) {
			Car currentCar = cars.get(carKey);
			currentCar.update();
			System.out.println("updating car " + currentCar.getId());
		}
		for (Integer roadKey : roads.keySet()) {
			Road currentRoad = roads.get(roadKey);
			currentRoad.update();
			System.out.println("updating road: " + currentRoad.getId());
		}

	}

	private void render() {
		this.carView.repaint();

	}

	public static ArrayList<Road> bfsRoads(int currentRoadId,
			int destinationRoadId) {
		// TODO Auto-generated method stub
		return null;
	}

}
