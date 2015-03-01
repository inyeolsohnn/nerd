package model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

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
	Connection(Road sRoad, Lane sLane, Road tRoad, Lane tLane,
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
	public Float nextPosition(Car car, float targetDistance,
			float distanceTravelled) {
		// TODO Auto-generated method stub
		return null;
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
		System.out.println("Last point = " + bezierPointTable[1000]);
	}

	private float findT(float distance) {
		// takes the current distance travelled, returns T
		return 0.1f;
	}

	private float findT(Point2D.Float point) {
		// takes the current point, returns T
		return 0.1f;
	}

}
