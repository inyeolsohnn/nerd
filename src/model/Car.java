package model;

import java.awt.Graphics;
import java.awt.geom.Point2D; //test//
import java.util.ArrayList;

public class Car {
	private static int carsCreated = 0;
	private ArrayList<Road> plan = new ArrayList<Road>();
	private Point2D.Float coordinate;
	private final int id;
	private float currentSpeedLimit;
	private float maxSpeed;
	private float currentSpeed;

	private Road currentRoad;
	private Lane currentLane;
	private CarWorld cWorld;
	private float distanceTravelled; // distance travelled on the current road
										// 0-roadSpan
	private float changeInSpeed;
	private float acceleration;
	private float breaking;
	private boolean signalled = false;

	public Car() {
		// dummy constructor for testing
		this.id = carsCreated;
		carsCreated++;
	}

	public Car(Point2D.Float coordinate, float maxSpeed, Lane initialLane,
			CarPark destinationPark, CarWorld cWorld, Point2D.Float entryPoint) {
		this.coordinate = coordinate;
		this.maxSpeed = maxSpeed;
		this.enterLane(initialLane, entryPoint);
		this.id = Car.carsCreated;
		this.cWorld = cWorld;
		Car.carsCreated++;
		plan = Road.bfsParks(currentRoad, destinationPark);
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

	

	public Lane getCurrentLane() {
		return currentLane;
	}

	public void setCurrentLane(Lane currentLane) {
		this.currentLane = currentLane;
	}


	public void enterLane(Lane lane, Point2D.Float entryPoint) {
		this.currentLane = lane;
		this.currentRoad= lane.getRoad();
		lane.carEnters(this);
		this.coordinate = entryPoint;
		System.out.println("Entry lane point: " + entryPoint);
		// car entering a lane logic
	}

	public void move() {
		// ///Initial Belief section//////
		float currS = this.currentSpeed;
		Car frontCar = this.currentLane.getFrontCar(this);
		Car tailCar = this.currentLane.getTailCar(this);
		boolean onCourse = checkCourse();
		// ////Initial Beleif section/////

		// if oncourse & acceptable speed//
		// -> keep this state-> just move along

		// if unacceptable speed: ; and on course//
		// ->if due to front car; if not just increase the speed till due to
		// ->if canOverTake (checks gaps: allowed distance infront of the front
		// car, allowed gap in side lane)-> change lane

		// if !onCourse-> change lane: requires checking where to change to and
		// if it can change,

		if (this.currentSpeed == 0) {
			System.out.println("Car is not moving");
		} else {
			float speedChange = checkSpeedChange();
			if (speedChange != 0) {

			}
			float tempDistance = this.currentSpeed * 0.02f;

			Point2D.Float nextPosition = this.currentLane.nextPosition(this,
					tempDistance, this.distanceTravelled);
			Point2D.Float nextDisplacement = new Point2D.Float(
					Math.abs(nextPosition.x - this.coordinate.x),
					Math.abs(nextPosition.y - this.coordinate.y));
			/*
			 * System.out.println("From car, next displacement: " +
			 * nextDisplacement);
			 */
			Point2D.Float dToEnd = new Point2D.Float(Math.abs(currentLane
					.getEnd().x - this.getCoordinate().x), Math.abs(currentLane
					.getEnd().y - this.getCoordinate().y));
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
			// taking connection point logic
			if (this.currentLane.getConnectionPoints().size() == 0) {
				System.out.println("no connection");
			} else {
				if (!(currentLane.getConnectionPoints().get(
						new Point2D.Float((int) this.coordinate.x,
								(int) this.coordinate.y)) == null)) {
					System.out.println("connection found");

					this.setCoordinate(new Point2D.Float(
							(int) this.coordinate.x, (int) this.coordinate.y));

				}
			}
		}
	}

	private boolean checkCourse() {
		// TODO Auto-generated method stub
		return false;
	}

	private float checkSpeedChange() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getId() {
		return this.id;
	}

	public void setTravelled(float f) {
		this.distanceTravelled = f;
	}
	public void paint(Graphics g){
		g.drawOval((int)coordinate.x-1, (int)coordinate.y-1, 2, 2);
	}
}
