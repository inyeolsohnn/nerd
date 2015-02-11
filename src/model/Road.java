package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Road {
	private final int roadId;
	private ArrayList<Lane> lanes = new ArrayList<Lane>();
	private HashMap<Integer, Road> connectioNetwork = new HashMap<Integer, Road>(); // knows
																					// which
																					// lane
																					// is
																					// connecting
																					// to
																					// which
																					// road,
																					// pair
																					// of
																					// (lane
																					// number,
																					// and
																					// the
																					// road
																					// it
																					// connects
																					// to)

	private static int roadsCreated = 0;
	public static final int STRAIGHT_LANE = 0;
	public static final int ROUNDABOUT = 1;
	public static final int CURVE = 2;

	public Road() {
		this.roadId = roadsCreated;
	}

	public Road(int startX, int startY, int endX, int endY, int laneType) {
		Point2D.Float start = new Point2D.Float(startX, startY);
		Point2D.Float end = new Point2D.Float(endX, endY);
		roadId = roadsCreated;
		Lane baseLane = new StraightLane(start, end, this.roadId);
		switch (laneType) {
		case 0:
			// default case-> leave as it is
		case 1:
			baseLane = new RoundAbout();
		case 2:
			baseLane = new BezierCurve();

		}
		lanes.add(baseLane);
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

	public int getLaneCount() {
		return lanes.size();
	}

	public ArrayList<Lane> getLanes() {
		return this.lanes;
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public ArrayList<Lane> connectingLane(Road targetRoad) {
		ArrayList<Lane> tempList = new ArrayList<Lane>();
		Iterator<Lane> lanesIter = lanes.iterator();
		while (lanesIter.hasNext()) {
			Lane currentLane = lanesIter.next();
			for (int i = 0; i < targetRoad.getLanes().size(); i++) {
				if (Lane.doIntersect(currentLane, targetRoad.getLanes().get(i))) {
					tempList.add(targetRoad.getLanes().get(i));
				}
			}

		}
		return null; // returns the lanes that
						// connect this road and
						// the target road;

	}

}
