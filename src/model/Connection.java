package model;

import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.math.BigDecimal;

public class Connection extends Lane {
	private Road sRoad, tRoad;
	private Lane sLane, tLane;
	private Point2D.Float interStartPoint;
	private Point2D.Float interEndPoint;
	private Point2D.Float intersectingPoint;
	private ConnectionPoint cp;
	private float[] bezierDistanceTable = new float[1001];
	private Point2D.Float[] bezierPointTable = new Point2D.Float[1001];

	// this is quadratic bezier curve that car will move onto while changing
	// lane
	public Connection() {
		// dummy

	}

	public Connection(Road sRoad, Lane sLane, Road tRoad, Lane tLane,
			ConnectionPoint cp, Point2D.Float interStartPoint,
			Point2D.Float interEndPoint, Point2D.Float intersectingPoint)
			throws UnknownConnectionError {
		super();
		this.sRoad = sRoad;
		this.sLane = sLane;
		this.tRoad = tRoad;
		this.tLane = tLane;
		this.cp = cp;
		this.interStartPoint = interStartPoint;
		this.interEndPoint = interEndPoint;
		this.intersectingPoint = intersectingPoint;
		setupBezier(interStartPoint, intersectingPoint, interEndPoint);

	}

	@Override
	public float calculateLaneSpan() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public Point2D.Float nextPosition(Car car, float targetDistance,
			float distanceTravelled) {
		// TODO Auto-generated method stub
		// bezier curve. Only look at the distanceTravelled+targetDistance;

		float finalDistance = targetDistance + distanceTravelled;
		System.out.println("Final distance" + finalDistance);
		float fdRound = round(finalDistance).floatValue();
		System.out.println("FdRound: " + fdRound);

		int index = binarySearch(this.bezierDistanceTable, fdRound,
				targetDistance);
		System.out.println("index : " + index);
		if (index == -1) {
			System.out.println("something went wrong");
			System.exit(0);
		}
		car.setTravelled(this.bezierDistanceTable[index]);
		return bezierPointTable[index];
	}

	@Override
	public Point2D.Float getStart() {
		return this.interStartPoint;
	}

	@Override
	public Point2D.Float getEnd() {
		return this.interEndPoint;

	}

	public Point2D.Float getIntersection() {
		return this.intersectingPoint;
	}

	public Road getTargetRoad() {
		return this.tRoad;
	}

	public Lane getTargetLane() {
		return this.tLane;
	}

	public Lane getStartLane() {
		return this.sLane;
	}

	public Road getRoad() {
		return this.sRoad;
	}

	private Point2D.Float calculateBezier(float t) {

		float x = (1 - t) * (1 - t) * this.interStartPoint.x + 2 * (1 - t) * t
				* this.intersectingPoint.x + t * t * this.interEndPoint.x;
		float y = (1 - t) * (1 - t) * this.interStartPoint.y + 2 * (1 - t) * t
				* this.intersectingPoint.y + t * t * this.interEndPoint.y;
		return new Point2D.Float(x, y);
	}

	private void setupBezier(Point2D.Float startingPoint,
			Point2D.Float controlPoint, Point2D.Float endPoint) {
		float t = 0.000f;
		float x;
		float y;
		float cumulativeDistance = 0;
		int i = 0;
		Point2D.Float bezierPoint;
		Point2D.Float currentPoint = startingPoint;
		for (; t <= 1; i++) {

			x = (1 - t) * (1 - t) * startingPoint.x + 2 * (1 - t) * t
					* controlPoint.x + t * t * endPoint.x;
			y = (1 - t) * (1 - t) * startingPoint.y + 2 * (1 - t) * t
					* controlPoint.y + t * t * endPoint.y;
			bezierPoint = new Point2D.Float(x, y);
			bezierPointTable[i] = bezierPoint; // t for point
			cumulativeDistance += (float) Math.sqrt(Math.pow(
					(bezierPoint.x - currentPoint.x), 2.0)
					+ Math.pow(bezierPoint.y - currentPoint.y, 2.0));
			bezierDistanceTable[i] = cumulativeDistance;
			currentPoint = bezierPoint;
			t += 0.001f;
		}
		System.out.println("bezier setup done");
		System.out.println("Total connection distance = "
				+ bezierDistanceTable[1000]);
		for (int j = 0; j < 1001; j++) {
			System.out.println(this.bezierDistanceTable[j]);
		}
		System.out.println("Last point = " + bezierPointTable[1000]);

	}

	private static BigDecimal round(float finalDistance) {

		BigDecimal bd = new BigDecimal(finalDistance);
		bd.setScale(4, BigDecimal.ROUND_HALF_UP);
		return bd;

	}

	private int binarySearch(float[] table, float target, float errorBound) {
		int firstKey = (table.length / 2);
		if (table[firstKey] - target < errorBound) {
			return firstKey;
		} else if (table[firstKey] > target) {
			// look for the values smaller
			return binarySearch(table, 0, firstKey - 1, target, errorBound);
		} else if (table[firstKey] < target) {
			// look for the values smaller
			return binarySearch(table, firstKey + 1, table.length, target,
					errorBound);
		}
		return -1;
	}

	private int binarySearch(float[] table, int lowerBound, int upperBound,
			float target, float errorBound) {
		System.out.println("lower bound: " + lowerBound);
		System.out.println("upper bound: " + upperBound);

		int halfPoint = (lowerBound + upperBound) / 2;
		if (lowerBound == upperBound) {
			return halfPoint;

		}

		else if (Math.abs(table[halfPoint] - target) < errorBound) {
			System.out.println(table[halfPoint] - target < errorBound);
			System.out.println("case2");
			return halfPoint;

		} else if (table[halfPoint] > target) {
			System.out.println("case3");
			return binarySearch(table, lowerBound, halfPoint - 1, target,
					errorBound);
		} else if (table[halfPoint] < target) {
			// look for bigger half
			System.out.println("case4");
			return binarySearch(table, halfPoint + 1, upperBound, target,
					errorBound);
		}
		return -1;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}
}
