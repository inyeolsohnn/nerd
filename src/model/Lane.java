package model;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Lane {

	private static final float SMALL_NUM = (float) 0.00000001;

	private int laneId; // its id
	private int laneKey;
	protected Road contained;
	protected float laneSpan;
	protected HashMap<Point2D.Float, ConnectionPoint> connectionPoints = new HashMap<Point2D.Float, ConnectionPoint>();
	protected HashMap<Integer, Car> carsInLane = new HashMap<Integer, Car>();
	private boolean hasPark = false;
	private CarWorld world;
	protected Point2D.Float startPoint;
	protected Point2D.Float endPoint;
	private boolean isEnding = false;

	private static int lanesCreated = 0;

	// store traffic lights that belong to this lane.
	ArrayList<TrafficLight> trafficLights = new ArrayList<TrafficLight>();

	public Lane() {
		// for special lanes
	}

	public Lane(Point2D.Float start, Point2D.Float end, Road cRoad,
			CarWorld cWorld, int lk) {
		this.startPoint = start;
		this.endPoint = end;
		this.contained = cRoad;
		this.laneId = lanesCreated;
		this.world = cWorld;
		lanesCreated++;
		this.laneKey = lk;

	}

	public abstract float calculateLaneSpan();

	public abstract Point2D.Float nextPosition(Car car, float targetDistance,
			float distanceTravelled);

	public float getLaneSpan() {
		return laneSpan;
	}

	public int getLaneId() {
		return this.laneId;
	}

	public abstract Road getRoad();

	public final void setLaneSpan(float laneSpan) {
		this.laneSpan = laneSpan;
	}

	// in case of roundabout this will return center point
	public Point2D.Float getStart() {
		return this.startPoint;
	}

	// in case of roundabout this will return center point
	public Point2D.Float getEnd() {
		// TODO Auto-generated method stub
		return this.endPoint;
	}

	public void addTrafficLight(TrafficLight light) {
		// needs to check if the point lies on the lane
		// not yet implemented
		this.trafficLights.add(light);
		this.world.addLight(light);

	}

	public void update() {
		// update car parks, and traffic lights belonging to this lane
	}

	public abstract TrafficLight getNextTrafficLight(Car car);

	public float getSpan() {
		// TODO Auto-generated method stub
		return laneSpan;
	}

	public void addCar(Car car) {
		int carId = car.getId();
		carsInLane.put(carId, car);
	}

	public void removeCar(Car car) {
		int carId = car.getId();
		carsInLane.remove(carId);
	}

	public boolean addConnectionPoint(ConnectionPoint cp, Connection cn)
			throws UnknownConnectionError {

		// if cp starting point and cn starting point is not equal throw an
		// error
		// if they are the same, but not on the lane throw an error
		// start checking

		// if failed return false
		// end checking

		// check if the connection point starting at the same coordinate in the
		// starting Lane exists

		// exist
		// if it doesn't add connection to the connection point and add it to
		// the lane
		// if it does add connection to the existing connection point

		if (connectionPoints.get(new Point2D.Float((int) cp
				.getPointCoordinate().x, (int) cp.getPointCoordinate().y)) == null) {

			System.out
					.println("Connection point not found, adding new connection point");
			cp.addConnection(cn);
			connectionPoints.put(
					new Point2D.Float(((int) cp.getPointCoordinate().x),
							((int) cp.getPointCoordinate().y)), cp);
			TrafficLight tl = new TrafficLight(cp.getLane(), "green", 10f, 10f,
					1f, cp.getPointCoordinate());
			cp.getLane().addTrafficLight(tl);

			return true;
		} else {
			System.out
					.println("connection point found, adding new connection to the existing connection point");
			ConnectionPoint existingCp = connectionPoints
					.get(new Point2D.Float((int) cp.getPointCoordinate().x,
							(int) cp.getPointCoordinate().y));
			existingCp.addConnection(cn);
			return true;
		}
	}

	public HashMap<Point2D.Float, ConnectionPoint> getConnectionPoints() {
		return this.connectionPoints;
	}

	public abstract void paint(Graphics g);

	public void paintTrafficLights(Graphics g) {
		for (int i = 0; i < this.trafficLights.size(); i++) {
			trafficLights.get(i).paint(g);
		}
	}

	public void carEnters(Car car) {
		// TODO Auto-generated method stub
		int carId = car.getId();
		carsInLane.put(carId, car);
		System.out.println("car added. Current numbers of cars in lane : "
				+ carsInLane.size() + " car id : " + car.getId());

	}

	public void carLeaves(Car car) {
		carsInLane.remove(car.getId());
	}

	public abstract Car getFrontCar(Car car);

	public int getLaneKey() {
		return this.laneKey;
	}

	public ArrayList<Connection> getSameConnections() {
		// TODO Auto-generated method stub
		int currentKey = this.getLaneKey();
		ArrayList<Connection> cal = new ArrayList<Connection>();
		Iterator<Entry<Integer, Lane>> lit = this.contained.lanes.entrySet()
				.iterator();
		while (lit.hasNext()) {
			Map.Entry<Integer, Lane> lp = lit.next();
			Lane currentLane = lp.getValue();
			if (currentLane.laneKey % 2 == this.laneKey % 2) {
				Iterator<Entry<Point2D.Float, ConnectionPoint>> cit = currentLane
						.getConnectionPoints().entrySet().iterator();
				while (cit.hasNext()) {
					Map.Entry<Point2D.Float, ConnectionPoint> cp = cit.next();
					ConnectionPoint currentPoint = cp.getValue();
					Iterator<Entry<Lane, Connection>> conIt = currentPoint
							.getConnections().entrySet().iterator();
					while (conIt.hasNext()) {
						Map.Entry<Lane, Connection> conP = conIt.next();
						Connection currentConnection = conP.getValue();
						cal.add(currentConnection);
					}
				}
			}
		}
		return cal;

	}

	public void setHasPark(boolean b) {
		this.hasPark = b;
	}

	public boolean getHasPark() {
		return this.hasPark;
	}

	public abstract float findDistance(Car car);

	public void setEnding(boolean b) {
		this.isEnding = b;
	}

	public boolean isEnding() {
		return this.isEnding;
	}

	public ArrayList<Lane> getSameLanes() {
		int currentKey = this.getLaneKey();
		ArrayList<Lane> sal = new ArrayList<Lane>();
		Iterator<Entry<Integer, Lane>> lit = this.contained.lanes.entrySet()
				.iterator();
		while (lit.hasNext()) {
			Map.Entry<Integer, Lane> lp = lit.next();
			Lane currentLane = lp.getValue();
			if (currentKey % 2 == this.laneKey % 2) {
				sal.add(currentLane);
			}
		}
		return sal;
	}
}
