package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import control.WorldController;

public class CarWorld {
	private WorldController wCtrl;
	private boolean status = false;
	private int height;
	private int width;
	private HashMap<Integer, Road> roads = new HashMap<Integer, Road>();
	private HashMap<Integer, Car> cars = new HashMap<Integer, Car>();

	public CarWorld() {
		this.height = 1000;
		this.width = 800;
	}

	public CarWorld(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	public void addRoad(Road road) {
		roads.put(road.getId(), road);
	}

	public void addCar(Car car) {
		cars.put(car.getId(), car);
	}

	public void removeRoad(String roadId) {
		roads.remove(roadId);
	}

	public void drawWorld(Graphics g) {

	}

	public void setController(WorldController wCtrl) {
		this.wCtrl = wCtrl;
	}

	public String toString() {

		return "Number of roads: " + roads.size() + "Number of cars"
				+ cars.size();
	}

	public void simulate() throws InterruptedException {
		while (true) {
			if (status == true) {
				wCtrl.render();

				Thread.sleep(1); // the timing mechanism
			}
			if (status == false) {
				Thread.sleep(1);
			}
		}
	}

	public void setStatus(boolean b) {
		this.status = b;

	}

	public HashMap<Integer, Car> getCars() {
		// TODO Auto-generated method stub
		return cars;
	}

	public HashMap<Integer, Road> getRoads() {
		// TODO Auto-generated method stub
		return roads;
	}

}
