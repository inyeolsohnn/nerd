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

		// update rate at 20ms
		float desiredDistance = targetDistance;

		Point2D.Float start = this.getStart();
		Point2D.Float end = this.getEnd();
		float laneSpan = calculateLaneSpan();
		Point2D.Float displacement = new Point2D.Float((end.x - start.x)
				/ laneSpan * desiredDistance, (end.y - start.y) / laneSpan
				* desiredDistance);
		System.out.println("New displacement: " + displacement);

		Point2D.Float newPoint = new Point2D.Float(car.getCoordinate().x
				+ displacement.x, car.getCoordinate().y + displacement.y);
		System.out.println("New point: " + newPoint);
		return newPoint;
	}

}
