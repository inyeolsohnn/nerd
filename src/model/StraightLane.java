package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class StraightLane extends Lane {

	private int noPoints;

	public StraightLane(Point2D.Float startingPoint, Point2D.Float endPoint,
			int id) {
		super(startingPoint, endPoint, id);
		this.setLaneSpan(calculateLaneSpan());
	}

	@Override
	public float calculateLaneSpan() {
		return (float) Math.sqrt(Math.pow(
				(this.getEnd().x - this.getStart().x), 2.0)
				+ Math.pow(this.getEnd().y - this.getStart().y, 2.0));
	}

	@Override
	public Point2D.Float nextPosition(Car car, float targetDistance) {

		float tragetDistance = targetDistance; // update rate at 20ms
		float desiredDistance = tragetDistance;
		float percentageCovered = desiredDistance / this.getSpan();
		Point2D.Float start = this.getStart();
		Point2D.Float end = this.getEnd();

		try {
			// non vertical straight line
			if (end.x - start.x == 0)
				throw new ArithmeticException();

			float slope = (end.y - start.y) / (end.x - start.x);
			float newX = (end.x - start.x) * percentageCovered + start.x;
			float newY = (end.y - start.y) * percentageCovered + start.y;
			return new Point2D.Float(newX, newY);
		} catch (ArithmeticException ae) {
			// vertical straight line detected
			float newY = (end.y - start.y) * percentageCovered + start.y;
			System.out.println("New Y: " + newY);
			return new Point2D.Float(start.x, newY);
		}

	}

	@Override
	public float calculateDistance(Point2D.Float pointA, Point2D.Float pointB) {
		// distance covered from point A to point B
		return (float) Math.sqrt(Math.pow((pointB.x - pointA.x), 2.0)
				+ Math.pow(pointB.y - pointA.y, 2.0));
	}

}
