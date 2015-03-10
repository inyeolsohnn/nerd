package model;

import java.awt.geom.Point2D;

public class CarPark {
	public static final int START = 0;
	public static final int END = 1;
	private int parkId;
	private int type;
	private Point2D.Float coordinate;
	private static int parksCreated = 0;
	private double spawnRate;
	private Lane lane;
	private CarWorld world;

	public CarPark(Lane bLane, int type, CarWorld cWorld) {
		this.parkId = parksCreated;
		this.lane = bLane;
		this.spawnRate = 0.6d;
		this.type = type;
		this.world = cWorld;
		if (type == START) {
			this.coordinate = lane.getStart();
		} else if (type == END) {
			this.coordinate = lane.getEnd();
		} else {
			System.out.println("Error");
			System.exit(0);
		}
		parksCreated++;
	}

	public int getId() {
		return this.parkId;
	}

	public void update() {
		if (this.type == START) {
			// spawn cars
			double range = this.spawnRate / 2;
			double dice = Math.random();
			if (dice >= 0.5d - range && dice <= 0.5d + range) {
				/*
				 * public Car(Point2D.Float coordinate, float maxSpeed, Lane
				 * initialLane, CarPark destinationPark, CarWorld cWorld,
				 * Point2D.Float entryPoint)
				 */
				Car newCar = new Car();
			}
		}
	}
}
