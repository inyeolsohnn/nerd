package model;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class CarWorld {
	private String status = "paused";
	private int height;
	private int width;
	private ArrayList<Road> roads = new ArrayList<Road>();
	private HashMap<Integer, Car> cars = new HashMap<Integer, Car>();
	private ArrayList<TrafficLight> lights = new ArrayList<TrafficLight>();
	private ArrayList<CarPark> parks = new ArrayList<CarPark>();
	private QuadTree quad;

	public CarWorld() {
		this.height = 1000;
		this.width = 800;
		quad = new QuadTree(0, new Rectangle(this.width, this.height));
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
		roads.add(road);
	}

	public void addCar(Car car) {
		cars.put(car.getId(), car);
	}

	public void removeRoad(String roadId) {
		roads.remove(roadId);
	}

	public String toString() {

		return "Number of roads: " + roads.size() + "Number of cars"
				+ cars.size();
	}

	public void setStatus(String s) {
		this.status = s;

	}

	public String getStatus() {
		return this.status;
	}

	public HashMap<Integer, Car> getCars() {

		return cars;
	}

	public void removeCar(Car car) {
		cars.remove(car.getId());
	}

	public Road getRoad(int roadId) {
		Road tRoad = null;
		for (int i = 0; i < roads.size(); i++) {
			Road cRoad = roads.get(i);
			if (cRoad.getId() == roadId) {
				tRoad = cRoad;
			}
		}
		return tRoad;
	}

	public ArrayList<Road> getRoads() {
		// TODO Auto-generated method stub
		return roads;
	}

	public ArrayList<TrafficLight> getLights() {
		// TODO Auto-generated method stub
		return lights;
	}

	public void flush() {
		roads = new ArrayList<Road>();
		cars = new HashMap<Integer, Car>();
		parks = new ArrayList<CarPark>();
		// TODO Auto-generated method stub

	}

	public void addPark(CarPark cp) {
		System.out.println("adding car park");
		parks.add(cp);
	}

	public ArrayList<CarPark> getParks() {
		// TODO Auto-generated method stub
		return this.parks;
	}

	public void addLight(TrafficLight light) {
		// TODO Auto-generated method stub
		this.lights.add(light);

	}

	public ArrayList<Car> checkCollision() {
		quad.clear();
		ArrayList<Car> collided = new ArrayList<Car>();
		Iterator<Entry<Integer, Car>> carIt = cars.entrySet().iterator();
		while (carIt.hasNext()) {
			quad.insert(carIt.next().getValue());
		}
		ArrayList<Car> returnObjects = new ArrayList<Car>();
		carIt = cars.entrySet().iterator();

		while (carIt.hasNext()) {
			returnObjects.clear();
			Car currentCar = carIt.next().getValue();
			quad.retrieve(returnObjects, currentCar);
			for (int i = 0; i < returnObjects.size(); i++) {
				if ((!returnObjects.get(i).equals(currentCar))
						&& Car.distance(returnObjects.get(i).getCoordinate(),
								currentCar.getCoordinate()) < 7) {
					System.out.println("Collision detected");
					this.status = "paused";
					collided.add(currentCar);
					collided.add(returnObjects.get(i));
					return collided;

				}
			}
		}
		return collided;
	}

	public void reset() {
		// TODO Auto-generated method stub
		// remove cars from car world

		// remove cars from straight lanes
		// remove cars from connections

		for (int i = 0; i < roads.size(); i++) {

			Road cr = roads.get(i);

			Iterator<Entry<Integer, Lane>> lIt = cr.lanes.entrySet().iterator();
			while (lIt.hasNext()) {
				Lane currentLane = lIt.next().getValue();
				currentLane.carsInLane.clear();
				Iterator<Entry<Point2D.Float, ConnectionPoint>> cpIt = currentLane.connectionPoints
						.entrySet().iterator();
				while (cpIt.hasNext()) {
					Iterator<Entry<Lane, Connection>> conIt = cpIt.next()
							.getValue().connections.entrySet().iterator();
					while (conIt.hasNext()) {
						conIt.next().getValue().carsInLane.clear();
					}
				}
			}
		}
		this.cars.clear();

		// remove previous car from car park
		// reset time intervals in traffic lights

	}

}
