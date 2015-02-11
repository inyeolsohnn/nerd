package model;

import java.awt.geom.Point2D; //test//
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Iterator;

import control.WorldController;

public class Car implements CarI {
	private Point2D.Float coordinate;
	private final int id;
	private float currentSpeedLimit;
	private float maxSpeed;
	private float currentSpeed;
	private int destinationRoadId;;
	private int currentRoadId;
	private ArrayList<Road> plan;
	private TrafficLight nextTrafficLight;
	private static int carsCreated = 0;
	private Road currentRoad;
	private Lane currentLane;
	private float currentT;
	private float distanceCovered;

	public Car() {
		// dummy constructor for testing
		this.id = carsCreated;
		carsCreated++;
	}

	public Car(Point2D.Float coordinate, float maxSpeed, int initialRoadId,
			int destinationRoadId) {
		this.coordinate = coordinate;
		this.maxSpeed = maxSpeed;
		this.destinationRoadId = destinationRoadId;
		this.currentRoadId = initialRoadId;
		this.id = Car.carsCreated;
		this.distanceCovered = 0;
		Car.carsCreated++;
		plan = WorldController.bfsRoads(currentRoadId, destinationRoadId);
	}

	public Point2D.Float getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point2D.Float coordinate) {
		this.coordinate = coordinate;
	}

	public float getCurrentSpeedLimit() {
		return currentSpeedLimit;
	}

	public void setCurrentSpeedLimit(float currentSpeedLimit) {
		this.currentSpeedLimit = currentSpeedLimit;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public float getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
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
				currentT = 0;
			}
		}

	}

	public void enterLane(Lane lane, Point2D.Float entryPoint) {
		this.currentLane = lane;
		this.coordinate = entryPoint;
		this.distanceCovered = currentLane.calculateDistance(
				currentLane.getStart(), entryPoint);
		System.out.println("Currently covered distance of the lane: "
				+ distanceCovered);
	}

	public void move() {
		float tempDistance = distanceCovered + this.currentSpeed * 0.02f;
		System.out.println("Temp distance: " + tempDistance);

		if (tempDistance < this.currentLane.getSpan()) {
			this.setCoordinate(this.currentLane
					.nextPosition(this, tempDistance));
			distanceCovered = tempDistance;
			System.out.println("Current  coordinate: " + this.getCoordinate());
		} else {
			this.setCoordinate(this.currentLane.getEnd());
			this.setCurrentSpeed(0f);
			distanceCovered = currentLane.getLaneSpan();
			System.out.println("Car ID:" + this.id
					+ " has reached the end of the lane");
			System.out.println("Current  coordinate: " + this.getCoordinate());

		}

	}

	public float getDistanceCovered() {
		return distanceCovered;
	}

	public void setDistanceCovered(float distanceCovered) {
		this.distanceCovered = distanceCovered;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void update() {

	}

	public float getT() {
		return this.currentT;
	}

}
