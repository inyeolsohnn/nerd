package model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class Connection extends Lane {
	private Road sRoad, tRoad;
	private Lane sLane, tLane;
	private Point2D.Float intersectingPoint;
	private Point2D.Float targetPoint;
	private ConnectionPoint cp;

	// this is quadratic bezier curve that car will move onto while changing
	// lane
	Connection(Road sRoad, Lane sLane, Road tRoad, Lane tLane,
			ConnectionPoint cp) throws UnknownConnectionError {
		super();
		this.sRoad = sRoad;
		this.sLane = sLane;
		this.tRoad = tRoad;
		this.tLane = tLane;
		this.cp = cp;

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
		return cp.getPointCoordinate();

	}

	@Override
	public Point2D.Float getEnd() {
		return this.intersectingPoint;

	}

	public Road returnTargetRoad() {
		return this.tRoad;
	}

	public Lane returnTargetLane() {
		return this.tLane;
	}
}
