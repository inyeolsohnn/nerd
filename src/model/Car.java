package model;

import java.awt.geom.Point2D; //test//
import java.awt.geom.Point2D.Double;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
	private CarWorld cWorld;
	private float distanceTravelled; //distance travelled on the current road 0-roadSpan

	public Car() {
		// dummy constructor for testing
		this.id = carsCreated;
		carsCreated++;
	}

	public Car(Point2D.Float coordinate, float maxSpeed, int initialRoadId,
			int destinationRoadId, CarWorld cWorld) {
		this.coordinate = coordinate;
		this.maxSpeed = maxSpeed;
		this.destinationRoadId = destinationRoadId;
		this.currentRoadId = initialRoadId;
		this.id = Car.carsCreated;
		this.cWorld = cWorld;
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
		/*
		 * 
		 * Iterator<Lane> laneIter = ((List<Road>) road.getLanes()).iterator();
		 * while (laneIter.hasNext()) { Lane otherLane = laneIter.next(); if
		 * (Lane.isConnected(this.currentLane, otherLane)) { currentRoad = road;
		 * currentLane = otherLane; currentT = 0; } }
		 */

		this.setCurrentRoadId(road.getId());
		// currently arbitrarily chosen lane entered

		if (!(road instanceof RoundRoad)) {
			HashMap<Integer, Lane> laneMap = road.getLanes();
			Lane basicLane = laneMap.get(0);
			this.enterLane(basicLane, basicLane.getStart());
		} else {
			System.out.println("Car enters roundabout");
			HashMap<Integer, Lane> laneMap = road.getLanes();
			RoundAbout basicLane = (RoundAbout) laneMap.get(0);
			this.enterLane(basicLane, new Point2D.Float(basicLane.getStart().x
					- basicLane.getRadius(), basicLane.getStart().y));
		}

	}

	public void enterLane(Lane lane, Point2D.Float entryPoint) {
		this.currentLane = lane;
		this.coordinate = entryPoint;
		System.out.println("Entry lane point: " + entryPoint);
		// car entering a lane logic
	}

	public void move() {
		if (this.currentSpeed == 0) {
			System.out.println("Car is not moving");
		} else {
			boolean speedUp = false;
			if (speedUp) {
				/** speed up meta level control **/
			}
			float tempDistance = this.currentSpeed * 0.02f;
			System.out.println("Temp distance: " + tempDistance);
			boolean isObstacle = this.checkObstacles();
			if (isObstacle) {
				// appropriate reaction to obstacle required
				/**
				 * obstacle can include other cars in vicinity -> that are
				 * currently in that's connected to the road the car is on ( if
				 * straight distance is below certain value obstacle detected)
				 **/
				/**
				 * obstacle can also be traffic lights that are currently red
				 * and stopping signs(connection points) -> requires appropriate
				 * logic added
				 **/

				/** work in progress reaction to obstacles */
			} else {
				Point2D.Float nextPosition = this.currentLane.nextPosition(
						this, tempDistance, this.distanceTravelled);
				Point2D.Float nextDisplacement = new Point2D.Float(
						Math.abs(nextPosition.x - this.coordinate.x),
						Math.abs(nextPosition.y - this.coordinate.y));
				/*
				 * System.out.println("From car, next displacement: " +
				 * nextDisplacement);
				 */
				Point2D.Float dToEnd = new Point2D.Float(Math.abs(currentLane
						.getEnd().x - this.getCoordinate().x),
						Math.abs(currentLane.getEnd().y
								- this.getCoordinate().y));
				/*
				 * System.out.println("From car, dte: " + dToEnd);
				 * System.out.println(this.currentLane.getClass().getName());
				 */
				if (!(this.currentLane instanceof RoundAbout)
						&& (dToEnd.x < nextDisplacement.x || dToEnd.y < nextDisplacement.y)) {
					this.coordinate = this.currentLane.getEnd(); // reached the
																	// end
																	// of the
																	// lane
																	// additional
																	// appropriate
																	// logic
																	// required
					/*
					 * System.out
					 * .println("Car has reached the end of it's current lane");
					 */
					this.setCurrentSpeed(0);
				} else {
					this.coordinate = nextPosition;
					System.out.println("New Position: " + this.coordinate);
				}
			}
		}
	}

	private boolean checkObstacles() {
		// TODO Auto-generated method stub
		return false;
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
