package model;

import java.awt.Graphics;
import java.awt.geom.Point2D; //test//
import java.util.ArrayList;
import java.util.Random;

public class Car {
	private static int carsCreated = 0;
	private ArrayList<Road> plan = new ArrayList<Road>();
	private Point2D.Float coordinate;
	private final int id;
	private float currentSpeedLimit;
	private float maxSpeed;
	private float currentSpeed;
	private Random rng = new Random();
	private Road currentRoad;
	private Lane currentLane;
	private Lane targetLane;
	private Connection targetConnection;
	private CarWorld cWorld;
	private float distanceTravelled; // distance travelled on the current road
										// 0-roadSpan

	public Car() {
		// dummy constructor for testing
		this.id = carsCreated;
		carsCreated++;
	}

	public Car(Point2D.Float coordinate, float maxSpeed, Lane initialLane,
			CarWorld cWorld, Point2D.Float entryPoint) {
		this.id = Car.carsCreated;
		Car.carsCreated++;
		this.coordinate = coordinate;
		this.maxSpeed = maxSpeed;
		this.enterLane(initialLane, entryPoint);

		this.cWorld = cWorld;

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
		if (this.currentLane != null) {
			this.currentLane.carLeaves(this);
		}
		if (lane instanceof StraightLane) {
			this.setTravelled(lane.findDistance(this));
		} else if (lane instanceof Connection) {
			this.setTravelled(0);
		}
		this.coordinate = entryPoint;
		System.out.println("Entry lane point: " + entryPoint);
		Road tRoad = lane.getRoad();
		Lane previousLane = this.currentLane;
		Road previousRoad;
		if (previousLane == null) {
			previousRoad = null;
		} else {
			previousRoad = previousLane.getRoad();
		}
		this.currentLane = lane;
		lane.carEnters(this);
		if (!tRoad.equals(previousRoad)) {
			System.out.println("Entering road");
			enterRoad(tRoad);
		}

		// car entering a lane logic
	}

	private void enterRoad(Road tRoad) {
		// TODO Auto-generated method stub
		Connection dummy = new Connection();
		ArrayList<Connection> connections = currentLane.getSameConnections();// gets

		boolean ending = currentLane.isEnding(); // same lanes of this road has
													// ending lanes
		// connections
		// that are
		// in the
		// same
		// direction
		// lanes of
		// the same
		// road
		if (ending) {
			connections.add(dummy);
		}
		System.out.println("enter road: connections size" + connections.size());
		int random = rng.nextInt(connections.size());
		Connection chosen = connections.get(random);

		if (chosen.equals(dummy)) {
			ArrayList<Lane> sameLanes = currentLane.getSameLanes();
			System.out.println("dummy: true");
			int nr = rng.nextInt(sameLanes.size());
			Lane chosenLane = sameLanes.get(nr);
			this.targetLane = chosenLane;
			this.targetConnection = null;
		} else {
			System.out.println("dummy: false");
			this.targetLane = chosen.getStartLane();
			this.targetConnection = chosen;
		}
		this.currentRoad = tRoad;
	}

	public void move() {
		if (this.targetConnection != null) {
			System.out.println("dummy should be false");

		}
		detect();
		// ///Initial Belief section//////

		Car frontCar = this.currentLane.getFrontCar(this);
		if (frontCar == null) {
			System.out.println("Car id : " + this.id + " is the front car");
		} else {
			System.out.println("For car id : " + this.id + " car id : "
					+ frontCar.id + " is the front car");
			if (Car.distance(frontCar.getCoordinate(), this.getCoordinate()) < 20) {
				this.setCurrentSpeed(60f);
			} else {
				this.setCurrentSpeed(120);
			}
		}
		boolean onCourse = checkCourse();
		System.out.println("Car id : " + this.id + " on course " + onCourse);
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

			if ((this.currentLane instanceof StraightLane)
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
				this.remove();
			} else if ((this.currentLane instanceof Connection)) {
				Point2D.Float lastPoint = this.currentLane.getEnd();
				if (Car.distance(this.coordinate, lastPoint) < 2) {
					this.enterLane(
							((Connection) this.currentLane).getTargetLane(),
							((Connection) this.currentLane).getEnd());
				}
			} else {
			}
			this.coordinate = nextPosition;
			System.out.println("New Position: " + this.coordinate);

			if (this.targetConnection != null) {
				System.out.println("connection found");
				if (Car.distance(this.getCoordinate(),
						targetConnection.getStart()) < 1) {
					System.out.println("Entering connection");
					this.enterLane(targetConnection,
							targetConnection.getStart());
				}

			}

		}
	}

	private void remove() {
		// TODO Auto-generated method stub
		this.cWorld.removeCar(this);
		this.currentLane.removeCar(this);
		System.gc();

	}

	private void detect() {
		// TODO Auto-generated method stub

	}

	private boolean checkCourse() {
		// TODO Auto-generated method stub
		return (this.currentLane.equals(this.targetLane));
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

	public void paint(Graphics g) {
		g.drawOval((int) coordinate.x - 4, (int) coordinate.y - 4, 8, 8);

	}

	public static float distance(Point2D.Float p1, Point2D.Float p2) {
		return (float) Math.sqrt(Math.pow((p1.x - p2.x), 2.0)
				+ Math.pow(p1.y - p2.y, 2.0));
	}

	public static int totalCar() {
		return Car.carsCreated;
	}

	public Road getRoad() {
		return this.currentRoad;
	}

	public float getTravelled() {
		// TODO Auto-generated method stub
		return this.distanceTravelled;
	}
}
