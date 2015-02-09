package model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.HashMap;

public class Lane {
	private String functionRepresentation;
	private boolean baseLane = false;
	private static final float SMALL_NUM = (float) 0.00000001;
	private int laneId; // its id
	private int roadId; // id of the road it belongs to
	private float laneSpan;
	private Point2D.Float startPoint;
	private Point2D.Float endPoint;
	private int noPoints;
	private ArrayList<TrafficLight> trafficLights = new ArrayList<TrafficLight>();
	private HashMap<Point2D.Float, Road> connectionPoints = new HashMap<Point2D.Float, Road>(); // Map
																								// of
																								// points
																								// connecting
																								// roads;

	public Lane() {
		// dummy constructor for testing
	}

	public Lane(Point2D.Float start, Point2D.Float end, int roadId) {
		this.startPoint = start;
		this.endPoint = end;
		this.roadId = roadId;
		this.laneSpan = calculateDistance(startPoint, endPoint);
		calculatePointCount();
	}

	public void calculatePointCount() {
		System.out.println("lane span: " + laneSpan);
		float points = (float) (this.laneSpan / 0.2);
		this.noPoints = (int) Math.ceil(points);
		System.out.println("Number of approximation points: " + noPoints);
	}

	public int getPointsCount() {
		return this.noPoints;
	}

	public float calculateDistance(Point2D.Float pointA, Point2D.Float pointB) {
		// TODO Auto-generated method stub
		return (float) Math.sqrt(Math.pow((pointB.y - pointA.y), 2)
				+ Math.pow((pointB.x - pointA.x), 2));
	}

	public void setFunctionRepresentation(String function) {
		this.functionRepresentation = function;
	}

	public void setBaseLane(boolean bl) {
		this.baseLane = bl;
	}

	public static boolean isConnected(Lane currentLane, Lane otherLane) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isInLane(Car car) { // checks if car's movement is
										// legal(conforms to the
										// shape(functional representation) of
										// its current lane

		Line2D.Float currentLane = new Line2D.Float(this.startPoint,
				this.endPoint);
		Point2D.Float currentPosition = car.getCoordinate();
		if (car.getCoordinate().x >= startPoint.x
				&& car.getCoordinate().x <= endPoint.x
				&& car.getCoordinate().y == startPoint.y) {
			return true;
		}
		return false;
	}

	public Point2D.Float getStart() {
		return this.startPoint;
	}

	public Float getEnd() {
		// TODO Auto-generated method stub
		return this.endPoint;
	}

	public static boolean doIntersect(Lane laneA, Lane laneB) {
		// method checks if two lanes from connected different roads A and B,
		// respectively, intersect with each other
		// straight line 0
		// roundabout 1
		// beizer 2
		// case1: straight line and straight line
		// case 2: straight line and roundabout
		// case 3: straight line and bezier
		// case 4: roundabout and bezier
		// case 5: bezier and bezier
		int types = 0;
		switch (types) {
		case 0:
			Point2D.Float aStart = laneA.getStart();
			Point2D.Float aEnd = laneA.getEnd();
			Point2D.Float bStart = laneB.getStart();
			Point2D.Float bEnd = laneB.getEnd();

			return doSegmentsIntersect(aStart, aEnd, bStart, bEnd);

		}
		return false;
	}

	public static boolean doSegmentsIntersect(Point2D.Float aStart,
			Point2D.Float aEnd, Point2D.Float bStart, Point2D.Float bEnd) {
		Line2D line1 = new Line2D.Float(aStart.x, aStart.y, aEnd.x, aEnd.y);
		Line2D line2 = new Line2D.Float(bStart.x, bStart.y, bEnd.x, bEnd.y);
		return line2.intersectsLine(line1);

	}

	public void addTrafficLight(TrafficLight light) {
		// needs to check if the point lies on the lane
		// not yet implemented

	}

	public Point2D.Float evaluateExpression(float t) {
		// t should range fro 0 to 1, % of the lane traversed
		float xDifference = this.endPoint.x - this.startPoint.x;
		float yDifference = this.endPoint.y - this.startPoint.y;
		float slope;
		try {
			// non-vertical
			slope = yDifference / xDifference;
			System.out.println("Slope: " + slope);

			float yCoordinate = (endPoint.y - startPoint.y) * t
					+ this.startPoint.y;
			float xCoordinate = (endPoint.x - startPoint.x) * t
					+ this.startPoint.x;
			System.out.println(new Point2D.Float(xCoordinate, yCoordinate));
			return new Point2D.Float(xCoordinate, yCoordinate);
		} catch (ArithmeticException ae) {
			// vertical
			return null;
		}
	}

	public TrafficLight checkTrafficLight(Point2D.Float coordinate) {
		// checking if trafficlight exists at the coordinate
		return null;
	}

	public TrafficLight getNextTrafficLight(Car car) {
		Point2D.Float carPos = car.getCoordinate();
		return null;
	}

	public Point2D.Float nextPosition(Car car) {

		float targetDistance = car.getCurrentSpeed() * (float) 0.1; // simulation
																	// updates
																	// every
																	// 100ms
		while (true) {
			Point2D.Float nextApproximation = evaluateExpression(car.getT());
			float currentDistance = calculateDistance(car.getCoordinate(),
					nextApproximation);
			if (currentDistance > targetDistance) {
				break;
			}
		}

		return endPoint;

	}

	public float getSpan() {
		// TODO Auto-generated method stub
		return laneSpan;
	}
}
