package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class StraightLane extends Lane {

	private int noPoints;
	private static final int[][] perpenMat = new int[][] { { 0, -1 }, { 1, 0 } };
	public StraightLane(Point2D.Float startingPoint, Point2D.Float endPoint,
			Road cRoad, CarWorld cWorld, int lk) {
		super(startingPoint, endPoint, cRoad, cWorld, lk);
		this.setLaneSpan(calculateLaneSpan());
	}

	@Override
	public float calculateLaneSpan() {
		return (float) Math.sqrt(Math.pow(
				(this.getEnd().x - this.getStart().x), 2.0)
				+ Math.pow(this.getEnd().y - this.getStart().y, 2.0));
	}

	@Override
	public Point2D.Float nextPosition(Car car, float targetDistance,
			float distanceTravelled) {

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

	@Override
	public void paint(Graphics g) {
		Point2D.Float start = this.getStart();
		Point2D.Float end = this.getEnd();
		Point2D.Float halfVector = gethalf(start, end);
		Point2D.Float p1 = new Point2D.Float(end.x - halfVector.x,
				end.y - halfVector.y);
		Point2D.Float p2 = new Point2D.Float(start.x - halfVector.x,
				start.y - halfVector.y);
		
		Point2D.Float p3 = new Point2D.Float(start.x + halfVector.x,
				start.y+ halfVector.y);
		Point2D.Float p4 = new Point2D.Float(end.x+ halfVector.x,
				end.y + halfVector.y);
		int xpoints[] = {(int)p1.x, (int)p2.x, (int)p3.x, (int)p4.x};
		int ypoints[] = {(int)p1.y, (int)p2.y, (int)p3.y, (int)p4.y};
		Graphics2D g2D = (Graphics2D) g;
        RenderingHints qualityHints = new RenderingHints(
                                          RenderingHints.KEY_ANTIALIASING,
                                          RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
        g2D.setRenderingHints(qualityHints);
		g.drawPolygon(xpoints, ypoints, 4);
		
		this.paintTrafficLights(g);

		
	}

	private Float gethalf(Float start, Float end) {
		Point2D.Float vector = new Point2D.Float(end.x - start.x, end.y
				- start.y);
		
		float vectorLength = (float) Math.sqrt(Math.pow(vector.x, 2.0)
				+ Math.pow(vector.y, 2.0));
		
		Point2D.Float normalVector = new Point2D.Float(vector.x / vectorLength,
				vector.y / vectorLength);
		Point2D.Float perpenVector = new Point2D.Float(normalVector.x
				* perpenMat[0][0] + normalVector.y * perpenMat[1][0],
				normalVector.x * perpenMat[0][1] + normalVector.y
						* perpenMat[1][1]);
		// System.out.println("Perpen vector: " + perpenVector);
		Point2D.Float halfScaled = new Point2D.Float(perpenVector.x
				* Road.roadWidth / 2, perpenVector.y * Road.roadWidth / 2);
		return halfScaled;

	}

	

}
