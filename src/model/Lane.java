package model;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Lane {
	private Map<Float, Float> functionRepresentation;
	private boolean baseLane = false;
	private Point2D.Double start;
	private Point2D.Double end;
	public final static int STRAIGHT_LANE = 1;
	public final static int RoundAbout = 2;

	private Point2D.Double startPoint;
	private Point2D.Double endPoint;

	public Lane() {

	}

	public Lane(Point2D.Double start, Point2D.Double end, Map<Float, Float> fr) {
		this.functionRepresentation = fr;
	}

	public void setFunctionRepresentation(Map<Float, Float> fr) {
		this.functionRepresentation = fr;
	}

	public void setBaseLane(boolean bl) {
		this.baseLane = bl;
	}

	public void drawLane(Graphics g) {

	}
}
