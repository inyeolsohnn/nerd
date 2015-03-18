package control;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JFrame;

import view.CarSimView;
import model.Car;
import model.CarPark;
import model.CarWorld;
import model.Road;
import model.StraightRoad;
import model.TrafficLight;

public class WorldController {
	private CarWorld cWorld; // core model
	private JFrame carView; // JFrame

	public WorldController() {

		this.cWorld = this.createWorld();

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
		this.cWorld.setStatus("paused");
	}

	public void start() {
		this.cWorld.setStatus("running");
	}

	public void exit() {
		this.cWorld.setStatus("exit");
	}

	// program loop
	public void simulate() throws InterruptedException {

		while (true) {

			if (cWorld.getStatus().equals("running")) {
				update();

				Thread.sleep(20); // the timing mechanism
									// needs improvement
			} else if (cWorld.getStatus().equals("paused")) {
				Thread.sleep(20);
			} else if (cWorld.getStatus().equals("exit")) {
				return;
			}
		}
	}

	public HashMap<Integer, Car> getCars() {
		// TODO Auto-generated method stub
		return this.cWorld.getCars();
	}

	public ArrayList<Road> getRoads() {
		// TODO Auto-generated method stub
		return this.cWorld.getRoads();
	}

	public ArrayList<TrafficLight> getLights() {
		// TODO Auto-generated method stub
		return this.cWorld.getLights();
	}

	private void update() {

		for (int i = 0; i < cWorld.getParks().size(); i++) {
			cWorld.getParks().get(i).update();
		}

		ArrayList<Car> cars = new ArrayList<Car>(cWorld.getCars().values());
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).move();

		}
		((CarSimView) carView).getDynamicChart().updateData();

		this.carView.repaint();

	}

	// example case setup
	public void setTJunction() {
		// construct the world with road network
		this.cWorld.setStatus("paused");
		this.cWorld.flush();
		// add new roads and such
		Road sr = new StraightRoad(new Point2D.Float(100, 200),
				new Point2D.Float(800, 400), 2, 2, this.getcWorld());
		Road ar = new StraightRoad(new Point2D.Float(300, 100),
				new Point2D.Float(500, 600), 2, 2, this.getcWorld());

		try {

			Road.connectLane(sr, 0, ar, 0);
			Road.connectLane(sr, 2, ar, 3);
			Road.connectLane(sr, 3, ar, 2);
			Road.connectLane(sr, 1, ar, 1);

			Road.connectLane(ar, 0, sr, 1);
			Road.connectLane(ar, 2, sr, 2);
			Road.connectLane(ar, 3, sr, 3);
			Road.connectLane(ar, 1, sr, 0);

			/*
			 * Road.connectLane(sr, 1, ar, 0); Road.connectLane(sr, 1, ar, 1);
			 * 
			 * Road.connectLane(ar, 1, sr, 0); Road.connectLane(ar, 0, sr, 0);
			 * Road.connectLane(ar, 1, sr, 1); Road.connectLane(ar, 0, sr, 1);
			 */

		} catch (Exception e) {
			System.out.println("error while connecting");
			e.printStackTrace();

		}
		sr.setCarParks(0);
		sr.setCarParks(1);
		sr.setCarParks(2);
		sr.setCarParks(3);
		ar.setCarParks(1);
		ar.setCarParks(0);
		ar.setCarParks(2);
		ar.setCarParks(3);

		sr.setEnding(0, true);
		sr.setEnding(1, true);
		sr.setEnding(2, true);
		sr.setEnding(3, true);
		ar.setEnding(0, true);
		ar.setEnding(1, true);
		ar.setEnding(2, true);
		ar.setEnding(3, true);

		this.cWorld.addRoad(sr);
		this.cWorld.addRoad(ar);

	}

	public void setIntersection() {
		this.cWorld.setStatus("paused");
		this.cWorld.flush();
		// add new roads and such
		Road r1 = new StraightRoad(new Point2D.Float(50, 350),
				new Point2D.Float(760, 350), 2, 1, this.getcWorld());
		Road r2 = new StraightRoad(new Point2D.Float(380, 20),
				new Point2D.Float(380, 700), 2, 1, this.getcWorld());
		Road r3 = new StraightRoad(new Point2D.Float(790, 20),
				new Point2D.Float(790, 700), 2, 1, this.getcWorld());
		this.cWorld.addRoad(r1);
		this.cWorld.addRoad(r2);
		this.cWorld.addRoad(r3);
	}

	public void setRoundAbout() {
		// first construct the world with road network

	}

	public CarWorld createWorld() {
		if (this.cWorld == null) {
			CarWorld world = new CarWorld();
			this.cWorld = world;
			return cWorld;
		} else {
			return this.cWorld;
		}
	}

	public double getAverageSpeed() {
		// TODO Auto-generated method stub
		Iterator<Entry<Integer, Car>> cit = this.cWorld.getCars().entrySet()
				.iterator();
		float sum = 0;
		float count = 0;
		while (cit.hasNext()) {
			Car currentCar = cit.next().getValue();
			sum += currentCar.getCurrentSpeed();
			count++;

		}
		if (count == 0)
			return 0;
		else
			return sum / count;
	}
}
