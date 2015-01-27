package model;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class Road {
	private final String roadId;
	private ArrayList<Lane> lanes = new ArrayList<Lane>();
	private Map<Float, Float> functionRepresentation;

	public Road(int startX, int startY, int endX, int endY) {
		Point2D.Double start = new Point2D.Double(startX, startY);
		Point2D.Double end = new Point2D.Double(endX, endY);

		Map<Float, Float> straightFunction = new HashMap<Float, Float>();
		roadId = UUID.randomUUID().toString();
		straightFunction.put((float) 0, (float) 2);
		Lane baseLane = new Lane(start, end, straightFunction);
		lanes.add(baseLane);
		System.out.println(toString());
	}

	public Road(int startX, int startY, int endX, int endY,
			Map<Float, Float> functionRepresentation) {
		Point2D.Double start = new Point2D.Double(startX, startY);
		Point2D.Double end = new Point2D.Double(endX, endY);
		Lane baseLane = new Lane(start, end, functionRepresentation);
		baseLane.setBaseLane(true);
		roadId = UUID.randomUUID().toString();

	}

	public String getId() {
		// TODO Auto-generated method stub
		return roadId;
	}

	public void updateRoad(Road road) {
		// TODO Auto-generated method stub

	}

	public void addLane(Lane lane) {
		this.lanes.add(lane);
	}

	public void setLane() {

	}

	public void drawRoad(Graphics g) {
		Iterator<Lane> laneIter = lanes.iterator();
		while (laneIter.hasNext()) {
			Lane currentLane = laneIter.next();
			currentLane.drawLane(g);
			;
		}
	}

	public String toString() {
		return "road id: " + roadId + "\n" + "Number of lanes: " + lanes.size();
	}
}
