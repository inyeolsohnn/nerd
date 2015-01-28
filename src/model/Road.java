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
	private final int roadId;
	private ArrayList<Lane> lanes = new ArrayList<Lane>();
	private RepresentationFactory repFactory = new RepresentationFactory();
	private static int roadsCreated=0;

	public Road(int startX, int startY, int endX, int endY) {
		Point2D.Double start = new Point2D.Double(startX, startY);
		Point2D.Double end = new Point2D.Double(endX, endY);

		roadId = roadsCreated;
		String function = repFactory
				.createRepresentation(RepresentationFactory.STRAIGHT_LANE);
		Lane baseLane = new Lane(start, end, function);
		lanes.add(baseLane);
		System.out.println(toString());
		
		roadsCreated++;
	}

	public Road(int startX, int startY, int endX, int endY,
			String functionRepresentation) {
		Point2D.Double start = new Point2D.Double(startX, startY);
		Point2D.Double end = new Point2D.Double(endX, endY);
		Lane baseLane = new Lane(start, end, functionRepresentation);
		baseLane.setBaseLane(true);
		roadId = roadsCreated;
		roadsCreated++;

	}

	public int getId() {
		// TODO Auto-generated method stub
		return roadId;
	}

	public void addLane(Lane lane) {
		this.lanes.add(lane);
	}


	public String toString() {
		return "road id: " + roadId + "\n" + "Number of lanes: " + lanes.size();
	}

	class RepresentationFactory {
		public final static int STRAIGHT_LANE = 1;
		public final static int RoundAbout = 2;

		public RepresentationFactory() {

		}

		public String createRepresentation(int i) {
			String representation = "";
			if (i == 1) {
				representation = "y=0";
			} else if (i == 2) {
			} else if (i == 3) {

			} else if (i == 4) {

			} else {
				// base case
				// straight road
				// error catch (irregular i)
			}

			return representation;
		}
	}
}
