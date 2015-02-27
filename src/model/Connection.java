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
	private float[] bezierTable = new float[1000];

	// this is quadratic bezier curve that car will move onto while changing
	// lane
	Connection(Road sRoad, Lane sLane, Road tRoad, Lane tLane,
			ConnectionPoint cp, Point2D.Float interStartPoint,
			Point2D.Float interEndPoint, Point2D.Float intersectingPoint)
			throws UnknownConnectionError {
		super(sRoad.getId());
		this.sRoad = sRoad;
		this.sLane = sLane;
		this.tRoad = tRoad;
		this.tLane = tLane;
		this.cp = cp;
		this.interStartPoint = interStartPoint;
		this.interEndPoint = interEndPoint;
		this.intersectingPoint = intersectingPoint;
		setupBezier(interStartPoint, intersectingPoint, interEndPoint);
		this.setLaneSpan(calculateLaneSpan());

	}

	@Override
	public float calculateLaneSpan() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Float nextPosition(Car car, float targetDistance) {
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

	public Point2D.Float getInter() {
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

	}
}
