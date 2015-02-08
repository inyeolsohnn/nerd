package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;

public class Lane {
	private String functionRepresentation;
	private boolean baseLane = false;

	private int laneId;
	private int roadId; // id of the road it belongs to
	private Point2D.Double startPoint;
	private Point2D.Double endPoint;
	private HashMap<Point2D.Double, Road> connectionPoints = new HashMap<Point2D.Double, Road>(); // Map
																									// of
																									// points
																									// connecting
																									// roads;

	public Lane() {
		// dummy constructor for testing
	}

	public Lane(Point2D.Double start, Point2D.Double end, String fr, int roadId) {
		functionRepresentation = adjustFunction(start, end, fr);
	}

	private String adjustFunction(Double start, Double end, String function) {
		// parse string, turn it into appropriate according to star e.g) y=x ->
		// y=x-5

		String newFunction = function;
		return newFunction;
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
		// TODO Auto-generated method stub
		return false;
	}

	public Point2D.Double getStart() {
		return this.startPoint;
	}

	public Double getEnd() {
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
			Point2D.Double aStart = laneA.getStart();
			Point2D.Double aEnd = laneA.getEnd();
			Point2D.Double bStart = laneB.getStart();
			Point2D.Double bEnd = laneB.getEnd();
			/*
			 * function doLineSegmentsIntersect(p, p2, q, q2) { var r =
			 * subtractPoints(p2, p); var s = subtractPoints(q2, q);
			 * 
			 * var uNumerator = crossProduct(subtractPoints(q, p), r); var
			 * denominator = crossProduct(r, s);
			 * 
			 * if (uNumerator == 0 && denominator == 0) { // They are coLlinear
			 * 
			 * // Do they touch? (Are any of the points equal?) if
			 * (equalPoints(p, q) || equalPoints(p, q2) || equalPoints(p2, q) ||
			 * equalPoints(p2, q2)) { return true } // Do they overlap? (Are all
			 * the point differences in either direction the same sign) // Using
			 * != as exclusive or return ((q.x - p.x < 0) != (q.x - p2.x < 0) !=
			 * (q2.x - p.x < 0) != (q2.x - p2.x < 0)) || ((q.y - p.y < 0) !=
			 * (q.y - p2.y < 0) != (q2.y - p.y < 0) != (q2.y - p2.y < 0)); }
			 * 
			 * if (denominator == 0) { // lines are paralell return false; }
			 * 
			 * var u = uNumerator / denominator; var t =
			 * crossProduct(subtractPoints(q, p), s) / denominator;
			 * 
			 * return (t >= 0) && (t <= 1) && (u >= 0) && (u <= 1); }
			 */

		

		}
		return false;
	}

	public static Point2D.Double subtractPoints(Point2D.Double p1,
			Point2D.Double p2) {

		double resultX = p1.x - p2.x;
		double resultY = p1.y - p2.y;
		Point2D.Double result = new Point2D.Double(resultX, resultY);
		return result;

	}

	public static double crossProduct(Point2D.Double p1, Point2D.Double p2) {
		return p1.x * p2.y - p1.y * p2.x;
	}

	public static boolean equalPoints(Point2D.Double p1, Point2D.Double p2) {
		return p1.x == p2.x && p1.y == p2.y;
	}
}
