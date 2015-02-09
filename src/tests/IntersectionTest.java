package tests;

import java.awt.geom.Point2D;
import model.Lane;
import junit.*;

public class IntersectionTest {
	public static void main(String[] args) {
		Point2D.Double aStart = new Point2D.Double(1, 1);
		Point2D.Double aEnd = new Point2D.Double(3, 3);
		Point2D.Double bStart = new Point2D.Double(5,5);
		Point2D.Double bEnd = new Point2D.Double(7, 7);
		boolean intersect = Lane
				.doSegmentsIntersect(aStart, aEnd, bStart, bEnd);
		System.out.println(intersect);
	}
}
