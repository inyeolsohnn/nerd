package model;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class RoundAbout extends Lane {
	private Point2D.Float center;
	private float radius;

	public RoundAbout(Point2D.Float center, float radius, Road cRoad, CarWorld world, int lk) {
		super(center, center, cRoad, world, lk);
		this.center = center;
		this.radius = radius;

	}

	@Override
	public float calculateLaneSpan() {
		// TODO Auto-generated method stub

		return (float) (Math.PI * 2 * this.radius);
	}

	@Override
	public Point2D.Float getStart() {
		return getCenter();
	}

	@Override
	public Point2D.Float getEnd() {
		return getCenter();
	}

	public Point2D.Float getCenter() {
		return this.center;
	}

	@Override
	public Point2D.Float nextPosition(Car car, float targetDistance,
			float distanceTravelled) {
		// TODO Auto-generated method stub

		double angle = 360 * targetDistance / calculateLaneSpan();

		double rad = Math.toRadians(angle);

		float newX = (float) (this.getStart().x + (Math.cos(rad)
				* (car.getCoordinate().x - this.getStart().x) + Math.sin(rad)
				* (car.getCoordinate().y - this.getStart().y)));

		float newY = (float) (this.getStart().y
				- (Math.sin(rad) * (car.getCoordinate().x - this.getStart().x)) + Math
				.cos(rad) * (car.getCoordinate().y - this.getStart().y));

		return new Point2D.Float(newX, newY);
	}

	public float getRadius() {
		return this.radius;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
