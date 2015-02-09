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

	public void enterLane(Lane lane) {
		this.currentLane = lane;
	}

	public void move() {
		// check if the lane is continuing
		// ->(1.lane is not continuing)
		// -> is it end of the run?
		// -> if not how do i move to the next lane;
		// nextTrafficLight = currentLane.getNextTrafficLight(this);
		// ->(1.lane is continuing)
		// ->is it okay to move to the next point on lane
		// -> check traffic light status, obstacle status

		// approximation point every 0.5 m;
		float deltaT = (float) (1.0 / currentLane.getPointsCount());
		System.out.println("delta T: "+deltaT);

		currentT += deltaT;
		System.out.println("Current T: " + currentT);
		Point2D.Float newCoordinate;
		if (currentT > 0.99) {
			// move to next road or stop if destination
		} else {
			float targetDistance = (float) (this.getCurrentSpeed() * 0.02); // simulation
																	// updates
																	// every
																	// 20ms ->50
																	// updates
																	// per
																	// second;
			System.out.println("TargetDistance: " + targetDistance);

			while (true) {
				Point2D.Float nextApproximation = currentLane
						.evaluateExpression(this.currentT);
				System.out.println(nextApproximation);
				float currentDistance = currentLane.calculateDistance(
						this.coordinate, nextApproximation);
				System.out.println("Current distance: " + currentDistance);
				if (currentDistance > targetDistance) {
					System.out.println("Break happened");
					newCoordinate = nextApproximation;
					break;
				} else {
					currentT += deltaT;
				}
			}
			System.out.println("new coordinate");
			this.coordinate = currentLane.evaluateExpression(currentT);
		}
		
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
