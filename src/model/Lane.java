package model;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;
import java.util.Map;

public class Lane {
	private String functionRepresentation;
	private boolean baseLane = false;
	private Point2D.Double start;
	private Point2D.Double end;

	private Point2D.Double startPoint;
	private Point2D.Double endPoint;

	public Lane() {

	}

	public Lane(Point2D.Double start, Point2D.Double end, String fr) {
		functionRepresentation = adjustFunction(start, end, fr);
	}

	private String adjustFunction(Double start, Double end, String function) {
		// parse string, turn it into appropriate according to star e.g) y=x ->
		// y=x-5

		String newFunction = function;
		return newFunction;
	}

	public void setFunctionRepresentation(String function) {
		this.functionRepresentation = function;
	}

	public void setBaseLane(boolean bl) {
		this.baseLane = bl;
	}

	public void drawLane(Graphics g) {
		System.out.println("Lane drawn");
	}
}
