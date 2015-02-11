package model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Lane {
	private static final float SMALL_NUM = (float) 0.00000001;
	private int laneId; // its id
	private int roadId; // id of the road it belongs to
	protected float laneSpan;
	protected Point2D.Float startPoint;
	protected Point2D.Float endPoint;

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

	}

	public abstract float calculateLaneSpan();

	public abstract Point2D.Float nextPosition(Car car, float targetDistance);

	public abstract float calculateDistance(Point2D.Float pointA,
			Point2D.Float pointB);

	public float getLaneSpan() {
		return laneSpan;
	}

	public final void setLaneSpan(float laneSpan) {
		this.laneSpan = laneSpan;
	}

	public static boolean isConnected(Lane currentLane, Lane otherLane) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isInLane(Car car) { // checks if car's movement is
										// legal(conforms to the
										// shape(functional representation) of
										// its current lane
		// todo

		return false;
	}

	// in case of roundabout this will return center point
	public final Point2D.Float getStart() {
		return this.startPoint;
	}

	public final Point2D.Float getEnd() {
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
		/*
		 * switch (types) { case 0:
		 */
		Point2D.Float aStart = laneA.getStart();
		Point2D.Float aEnd = laneA.getEnd();
		Point2D.Float bStart = laneB.getStart();
		Point2D.Float bEnd = laneB.getEnd();
		Line2D line1 = new Line2D.Float(aStart.x, aStart.y, aEnd.x, aEnd.y);
		Line2D line2 = new Line2D.Float(bStart.x, bStart.y, bEnd.x, bEnd.y);
		return line2.intersectsLine(line1);

		/*
		 * } return false;
		 */
	}

	public void addTrafficLight(TrafficLight light) {
		// needs to check if the point lies on the lane
		// not yet implemented

	}

	public TrafficLight getNextTrafficLight(Car car) {
		Point2D.Float carPos = car.getCoordinate();
		return null;
	}

	public float getSpan() {
		// TODO Auto-generated method stub
		return laneSpan;
	}
}
