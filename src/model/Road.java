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
	private RepresentationFactory repFactory = new RepresentationFactory();
	private static int roadsCreated = 0;

	public Road() {
		this.roadId = roadsCreated;
	}

	public Road(int startX, int startY, int endX, int endY) {
		Point2D.Double start = new Point2D.Double(startX, startY);
		Point2D.Double end = new Point2D.Double(endX, endY);

		roadId = roadsCreated;
		String function = repFactory
				.createRepresentation(RepresentationFactory.STRAIGHT_LANE);
		Lane baseLane = new Lane(start, end, function, this.roadId);
		lanes.add(baseLane);
		System.out.println(toString());

		roadsCreated++;
	}

	public Road(int startX, int startY, int endX, int endY,
			String functionRepresentation) {
		Point2D.Double start = new Point2D.Double(startX, startY);
		Point2D.Double end = new Point2D.Double(endX, endY);

		roadId = roadsCreated;
		roadsCreated++;
		Lane baseLane = new Lane(start, end, functionRepresentation,
				this.roadId);
		baseLane.setBaseLane(true);

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
