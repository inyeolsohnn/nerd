package model;

import java.awt.geom.Point2D; //test//
import java.util.ArrayList;
import java.util.Iterator;

import control.WorldController;

public class Car implements CarI {
	private Point2D.Double coordinate;
	private final int id;
	private double currentSpeedLimit;
	private double maxSpeed;
	private double currentSpeed;
	private int destinationRoadId;;
	private int currentRoadId;
	private ArrayList<Road> plan;

	private static int carsCreated = 0;
	private Road currentRoad;
	private Lane currentLane;

	public Car() {
		// dummy constructor for testing
		this.id = carsCreated;
		carsCreated++;
	}

	public Car(Point2D.Double coordinate, double maxSpeed, int initialRoadId,
			int destinationRoadId) {
		this.coordinate = coordinate;
		this.maxSpeed = maxSpeed;
		this.destinationRoadId = destinationRoadId;
		this.currentRoadId = initialRoadId;
		this.id = Car.carsCreated;
		Car.carsCreated++;
		plan = WorldController.bfsRoads(currentRoadId, destinationRoadId);
	}

	public Point2D.Double getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point2D.Double coordinate) {
		this.coordinate = coordinate;
	}

	public double getCurrentSpeedLimit() {
		return currentSpeedLimit;
	}

	public void setCurrentSpeedLimit(double currentSpeedLimit) {
		this.currentSpeedLimit = currentSpeedLimit;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public int getDestinationId() {
		return this.destinationRoadId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationRoadId = destinationId;
	}

	public int getCurrentRoadId() {
		return currentRoadId;
	}

	public void setCurrentRoadId(int currentRoadId) {
		this.currentRoadId = currentRoadId;
	}

	public Lane getCurrentLane() {
		return currentLane;
	}

	public void setCurrentLane(Lane currentLane) {
		this.currentLane = currentLane;
	}

	@Override
	public void accelerate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void decelerate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	public void enterRoad(Road road) {

		Iterator<Lane> laneIter = road.getLanes().iterator();
		while (laneIter.hasNext()) {
			Lane otherLane = laneIter.next();
			if (Lane.isConnected(this.currentLane, otherLane)) {
				currentRoad = road;
				currentLane = otherLane;
			}
		}

	}

	public void move() {
		if (currentLane.isInLane(this)) {

		}
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void update() {

	}
}
