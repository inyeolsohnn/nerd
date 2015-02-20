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
		this.sRoad = sRoad;
		this.sLane = sLane;
		this.tRoad = tRoad;
		this.tLane = tLane;
		this.cp = cp;
		setupConnection();
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

	private void setupConnection() throws UnknownConnectionError {
		this.intersectingPoint = findIntersectionPoint(sLane, tLane);
		/**
		 * find starting point(stopping point==point of ConnectionPoint that
		 * this connection belongs to) in slane and ending point(a point on
		 * ending lane, distance from this point in tLane to intersecting point
		 * = distance between starting point in sLane and intersecting point
		 **/

	}

	private Point2D.Float findIntersectionPoint(Lane sLane, Lane tLane)
			throws UnknownConnectionError {
		// TODO Auto-generated method stub
		if ((sLane instanceof StraightLane && tLane instanceof StraightLane)) {
			return lineAndLineIntersect((StraightLane) sLane,
					(StraightLane) tLane);
		} else if ((sLane instanceof StraightLane && tLane instanceof RoundAbout)) {
			return lineAndCircleIntersect((StraightLane) sLane,
					(RoundAbout) tLane);
		} else if (sLane instanceof RoundAbout && tLane instanceof StraightLane) {
			return lineAndCircleIntersect((StraightLane) tLane,
					(RoundAbout) sLane);
		} else {
			throw new UnknownConnectionError();
		}

	}

	private Point2D.Float lineAndCircleIntersect(StraightLane sLane,
			RoundAbout tLane) {
		// TODO Auto-generated method stub
		return null;
	}

	public Point2D.Float lineAndLineIntersect(StraightLane sLane,
			StraightLane tLane) throws UnknownConnectionError {
		Point2D.Float sVec = new Point2D.Float(sLane.getEnd().x
				- sLane.getStart().x, sLane.getEnd().y - sLane.getStart().y);
		Point2D.Float tVec = new Point2D.Float(tLane.getEnd().x
				- tLane.getStart().x, tLane.getEnd().y - tLane.getStart().y);

		if (sLane.getStart().x == sLane.getEnd().x
				&& tLane.getStart().x == tLane.getEnd().x) {
			// if both lanes are vertical either they don't connect, or if
			// they connect and vertical it should be one single lane-> error.
			throw new UnknownConnectionError();

		} else if (sLane.getStart().y == sLane.getEnd().y
				&& tLane.getStart().y == tLane.getEnd().y) {
			// if both lanes are horizontal either they don't connect, or if
			// they connect and horizontal it should be one single lane-> error.
			throw new UnknownConnectionError();
		} else if (new Line2D.Float(sLane.getStart(), sLane.getEnd())
				.intersectsLine(new Line2D.Float(tLane.getStart(), tLane
						.getEnd()))) {
			float x1 = sLane.getStart().x;
			float x2 = sLane.getEnd().x;
			float x3 = tLane.getStart().x;
			float x4 = tLane.getEnd().x;
			float y1 = sLane.getStart().y;
			float y2 = sLane.getEnd().y;
			float y3 = tLane.getStart().y;
			float y4 = tLane.getEnd().y;

			float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
			if (d == 0)
				throw new UnknownConnectionError();
			else {
				float xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2)
						* (x3 * y4 - y3 * x4))
						/ d;
				float yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2)
						* (x3 * y4 - y3 * x4))
						/ d;
				System.out.println(xi + ", " + yi);
				return new Point2D.Float(xi, yi);

			}

		}
		throw new UnknownConnectionError();
	}
}
