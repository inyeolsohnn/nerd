package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;

public class CarPark {
	public static final int START = 0;
	public static final int END = 1;
	private int parkId;
	private int type;
	private Point2D.Float coordinate;
	public static int parksCreated = 0;
	private Random rng = new Random();
	private double spawnRate;
	private Lane lane;
	private CarWorld world;
	private Car previousCar = null;

	public CarPark(Lane bLane, int type, CarWorld cWorld) {
		this.parkId = parksCreated;
		this.lane = bLane;
		this.spawnRate = 0.003d;
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

	public void setSpawn(int i) {
		this.spawnRate = ((double) i / (double) 1000);
	}

	public void update() {

		// if (Car.totalCar() < 1) {
		if (this.type == START) {
			// spawn cars
			double range = this.spawnRate / 2;
			double dice = Math.random();
			int speed = rng.nextInt((100 - 80) + 1) + 80;
			if (dice >= 0.5d - range && dice <= 0.5d + range
					&& this.world.getCars().size() < 80) {

				/*
				 * public Car(Point2D.Float coordinate, float maxSpeed, Lane
				 * initialLane, CarPark destinationPark, CarWorld cWorld,
				 * Point2D.Float entryPoint)
				 */
				if (previousCar == null) {
					Car c = new Car(this.lane.getStart(), speed, this.lane,
							this.world, this.lane.getStart());
					c.setCurrentSpeed(40);
					previousCar = c;
					this.world.addCar(c);
				} else {
					if (Car.distance(previousCar.getCoordinate(),
							this.coordinate) > 15) {
						Car c = new Car(this.lane.getStart(), speed, this.lane,
								this.world, this.lane.getStart());
						c.setCurrentSpeed(40);
						previousCar = c;
						this.world.addCar(c);
					}
				}
			}
		}
	}

	// }

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillOval(
				(int) (coordinate.x - Math.sqrt(2 * (Math.pow(7.5 / 2, 2)))),
				(int) (coordinate.y - Math.sqrt(2 * (Math.pow(7.5 / 2, 2)))),
				15, 15);

	}

	public Point2D.Float getCoordinate() {
		return this.coordinate;
	}
	
	public Lane getLane(){
		return this.lane;
	}

	public void reset() {
		// TODO Auto-generated method stub
		this.previousCar=null;
		
	}
}
