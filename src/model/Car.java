package model;

import java.awt.Graphics;
import java.awt.geom.Point2D; //test//

public class Car implements CarI {
	private Point2D.Double coordinate;
	private int id;
	private double currentSpeedLimit;
	private double maxSpeed;
	private double currentSpeed;
	private int destinationId;
	private String currentRoadId;

	private static int carsCreated = 0;

	private Lane currentLane;

	public Car() {

	}

	public Car(Point2D.Double coordinate, double maxSpeed, int destinationId) {
		this.coordinate = coordinate;
		this.maxSpeed = maxSpeed;
		this.destinationId = destinationId;
		this.id = Car.carsCreated;
		Car.carsCreated++;
	}

	public void setId(int id) {
		this.id = id;
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
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public String getCurrentRoadId() {
		return currentRoadId;
	}

	public void setCurrentRoadId(String currentRoadId) {
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

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	
}
