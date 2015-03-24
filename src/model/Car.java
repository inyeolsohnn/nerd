package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D; //test//
import java.util.ArrayList;
import java.util.Random;

public class Car {
	private static int carsCreated = 0;

	private Point2D.Float coordinate;
	private final int id;
	private float desiredSpeed;
	private float currentSpeed;
	private Random rng = new Random();
	private Road currentRoad;
	private Lane currentLane;
	private Lane targetLane;
	private Connection targetConnection;
	private CarWorld cWorld;
	private float distanceTravelled; // distance travelled on the current road
										// 0-roadSpan

	public Car(Point2D.Float coordinate, float ds, Lane initialLane,
			CarWorld cWorld, Point2D.Float entryPoint) {
		this.id = Car.carsCreated;
		Car.carsCreated++;
		this.coordinate = coordinate;
		this.desiredSpeed = ds;
		this.enterLane(initialLane, entryPoint);
		this.cWorld = cWorld;

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

	public static void setCarsCreated(int i) {
		// TODO Auto-generated method stub
		carsCreated = i;

	}

	private boolean checkCourse() {
		// TODO Auto-generated method stub
		return (this.currentLane.equals(this.targetLane));
	}

	public int getId() {
		return this.id;
	}

	public void setTravelled(float f) {
		this.distanceTravelled = f;
	}

	public Point2D.Float getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point2D.Float coordinate) {
		this.coordinate = coordinate;
	}

	public float getDesiredSpeed() {
		return desiredSpeed;
	}

	public void setDesiredSpeed(float ds) {
		this.desiredSpeed = ds;
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
			this.setTravelled(1);

		}
		this.coordinate = entryPoint;

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

		int random = rng.nextInt(connections.size());
		Connection chosen = connections.get(random);

		if (chosen.equals(dummy)) {
			ArrayList<Lane> sameLanes = currentLane.getSameLanes();

			int nr = rng.nextInt(sameLanes.size());
			Lane chosenLane = sameLanes.get(nr);
			this.targetLane = chosenLane;
			this.targetConnection = null;
		} else {

			this.targetLane = chosen.getStartLane();
			this.targetConnection = chosen;
		}
		this.currentRoad = tRoad;
	}

	public void move() {

		// ///Initial Belief section//////

		Car frontCar = this.currentLane.getFrontCar(this);
		TrafficLight tfl = this.currentLane.getNextTrafficLight(this);
		float cd = 100;
		float td = 100;
		if (frontCar != null) {
			cd = Car.distance(frontCar.getCoordinate(), this.getCoordinate());
		}
		if (tfl != null) {
			td = Car.distance(tfl.getCoordinate(), this.getCoordinate());
		}
		// current---car---trafficlight(red/green)----100m :: react to the car
		// current----trafficlight(green)---car--100m :: react to the car
		// current---trafficlight(red)----car ---100m :: react to the light
		// current -----100m----x-----y :: free run
		if (cd >= 100 && td >= 100) {
			// free run

			accelerate();
		} else if (td < cd && td < 100 && tfl.getStatus().equals("red")) {
			// react to the light

			if (td < 15)
				this.setCurrentSpeed(0);
			else if (td < 70)
				this.setCurrentSpeed(td);

		} else if ((td < cd && td < 100 && tfl.getStatus().equals("green"))) {

			if (this.targetConnection != null
					&& Car.distance(this.coordinate,
							this.targetConnection.getStart()) < 100) {
				if (td < 70)
					if (currentSpeed < 60)
						this.accelerate();
					else if (currentSpeed > 80) {
						this.decelerate();
					}
			} else {
				if (cd < 15) {
					this.setCurrentSpeed(0);
				} else if (cd < 70) {
					this.setCurrentSpeed(cd);
				} else if (cd > 90) {
					
					accelerate();
				}
			}
			// slow down for intersection

		} else if ((cd < td && cd < 100)) {
			// react to the car
			if (cd < 15) {
				this.setCurrentSpeed(0);
			} else if (cd < 70) {
				this.setCurrentSpeed(cd);
			} else if (cd > 90 ) {
				accelerate();
			}
		}

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
			if (frontCar != null) {
				System.out.println("front car : " + frontCar.coordinate);
			}
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
				if (Car.distance(this.coordinate, lastPoint) < 5) {
					this.enterLane(
							((Connection) this.currentLane).getTargetLane(),
							((Connection) this.currentLane).getEnd());
				}
			} else {
			}
			this.coordinate = nextPosition;

			if (this.targetConnection != null) {

				if (Car.distance(this.getCoordinate(),
						targetConnection.getStart()) < 1) {

					this.enterLane(targetConnection,
							targetConnection.getStart());
				}

			}

		}
	}

	private void decelerate() {
		// TODO Auto-generated method stub
		this.currentSpeed -= 80 * 0.02;
	}

	private void accelerate() {
		// TODO Auto-generated method stub
		if (this.currentSpeed < this.desiredSpeed) {
			this.currentSpeed += 20 * 0.02;
		}
	}

	public void remove() {
		// TODO Auto-generated method stub
		this.cWorld.removeCar(this);
		this.currentLane.removeCar(this);
		System.gc();

	}

	public void paint(Graphics g) {
		if (this.currentSpeed >= 120) {
			g.setColor(Color.RED);
		} else if (this.currentSpeed < 120 && this.currentSpeed >= 90) {
			g.setColor(Color.ORANGE);
		} else if (this.currentSpeed < 90 && this.currentSpeed >= 60) {
			g.setColor(Color.YELLOW);
		} else if (this.currentSpeed < 60 && this.currentSpeed >= 30) {
			g.setColor(Color.MAGENTA);
		} else if (this.currentSpeed < 30 && this.currentSpeed > 0) {
			g.setColor(Color.BLUE);
		} else if (this.currentSpeed == 0) {
			g.setColor(Color.BLACK);
		}
		g.fillOval((int) coordinate.x - 4, (int) coordinate.y - 4, 8, 8);

	}

	public static float distance(Point2D.Float p1, Point2D.Float p2) {
		return (float) Math.sqrt(Math.pow((p1.x - p2.x), 2.0)
				+ Math.pow(p1.y - p2.y, 2.0));
	}

}
