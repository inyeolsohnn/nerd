package control;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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

		Road road = new Road(100, 100, 400, 500);
		cWorld.addRoad(road);
		Car car = new Car(new Point2D.Double(100.0, 100.0), 0, road.getId());
		Car car1 = new Car(new Point2D.Double(400.0, 500.0), 100, road.getId());
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

	public void render() {
		this.carView.repaint();

	}

	public void pause() {
		this.cWorld.setStatus(false);
	}

	public void start() {
		this.cWorld.setStatus(true);
	}

	public void simulate() throws InterruptedException {
		while (true) {
			if (cWorld.getStatus() == true) {
				render();

				Thread.sleep(1); // the timing mechanism
			}
			if (cWorld.getStatus() == false) {
				Thread.sleep(1);
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
}
