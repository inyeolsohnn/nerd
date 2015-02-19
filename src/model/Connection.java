package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class Connection extends Lane {
	private Road sRoad, tRoad;
	private Lane sLane, tLane;
	private Point2D.Float intersectingPoint;
	private Point2D.Float targetPoint;
	private ConnectionPoint cp;

	// this is bezier curve that car will move onto while changing lane
	Connection(Road sRoad, Lane sLane, Road tRoad, Lane tLane,
			ConnectionPoint cp) {
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

	public Road returnTargetRoad() {
		return this.tRoad;
	}

	public Lane returnTargetLane() {
		return this.tLane;
	}

	private void setupConnection() {

	}
}
