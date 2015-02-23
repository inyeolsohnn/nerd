package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class Road {
	// knows which lane is connecting to which road, pair of (lane number, and
	// the road it connects to).

	// some logical constrictions
	// base lane decides the type of the lanes that can be added
	// bilateral vs unilateral state
	// definition of connection: contiguous legal movements possible
	protected final int roadId;
	protected final int roadType;
	protected final static int roadWidth = 30; // each roads are 30 pixels wide
	private static int roadsCreated = 0;
	public static final int STRAIGHT_LANE = 0;
	public static final int ROUNDABOUT = 1;
	public static final int CURVE = 2;
	protected HashMap<Integer, Lane> lanes = new HashMap<Integer, Lane>();
	private boolean bilateral;
	private static final int[][] perpenMat = new int[][] { { 0, -1 }, { 1, 0 } };

	public Road(int roadType) {
		this.roadId = roadsCreated;
		roadsCreated++;
		this.roadType = roadType;
	}

	/**** road type dependent ****/

	// contaaining all lanes

	/***********************/

	/***** road type independent *******/
	public int getId() {
		return this.roadId;

	}

	public HashMap<Integer, Lane> getLanes() {
		return lanes;// should return an arraylist
	}

	public void update() {

	}

	public int getType() {
		return this.roadType;
	}

	public boolean getBilateral() {
		return this.bilateral;
	}

	protected void setBilateral(boolean bl) {

	}

	public static void connectLane(Road currentRoad, int currentLane,
			Road targetRoad, int targetLane) throws UnknownConnectionError {

		if ((currentRoad instanceof StraightRoad && targetRoad instanceof StraightRoad)) {

		} else if ((currentRoad instanceof StraightRoad && targetRoad instanceof RoundRoad)) {

		} else if (currentRoad instanceof RoundRoad
				&& targetRoad instanceof StraightRoad) {

		} else {
			throw new UnknownConnectionError();
		}
		// sets connection state between the lane corresponding to the
		// currentLane in currentRoad and the corresponding targetLane, in
		// targetRoad

		/**
		 * First: check if both the road contains a lane with the right number
		 * inside it. * Second: check if the roads are logically connectible; at
		 * least one end of a road(doesn't matter which) needs to be completely
		 * overlapped Third: get starting Point; comparing most outer boundaries
		 * and find the points that clips the overlapping lanes Fourth: get
		 * intersecting point Fifth: get end point. Vector of the destination
		 * lane starting at intersecting point with the distance between
		 * starting and intersecting point
		 * 
		 **/

	}

	private static void lineLineConnect(Road currentRoad, int currentLane,
			Road targetRoad, int targetLane) throws UnknownConnectionError {
		HashMap<Integer, Lane> sLanes = currentRoad.getLanes();
		HashMap<Integer, Lane> tLanes = targetRoad.getLanes();
		// checks if the roads actually has the lanes that are needed to be
		// connected
		if (sLanes.containsKey(currentLane) && tLanes.containsKey(targetLane)) {
			// get two most outer roads-> get most outer boundaries of the two
			// roads
			// check if the closest outer boundary intersects with the
			// boundaries of the other road.
			// if it doesn't check if it works other way.
			// case where they are all even or all odd->
			// for one of the roads a1-a2, a3-a4
			Point2D.Float a1;
			Point2D.Float a2;
			Point2D.Float a3;
			Point2D.Float a4;

			// for another road b1-b2, b3-b4
			Point2D.Float b1;
			Point2D.Float b2;
			Point2D.Float b3;
			Point2D.Float b4;

			// for sLanes
			ArrayList<Integer> sortedS = new ArrayList<Integer>(sLanes.keySet());
			Collections.sort(sortedS);
			int highest = sortedS.get(sortedS.size() - 1);
			int modulus = highest % 2;
			int nextHighest = -1;

			// ////////
			// ////////
			// start if
			if (currentRoad.getBilateral()) {
				// bilateral get one lane with highest even key and one with
				// highest odd key

				// start checking for nextHighest
				if (modulus == 0) {
					// highest is even-> next highest should be odd since
					// bilateral
					for (int i = sortedS.size() - 2; i >= 0; i--) {
						if (sortedS.get(i) % 2 == 1) {
							nextHighest = sortedS.get(i);
							break;
						}
					}
					// highest is even the outer line is another addition of the
					// half offset vector
					// nextHighest is odd the outer line is another subtraction
					// of the half offset vector

					Point2D.Float base1 = sLanes.get(highest).getStart();
					Point2D.Float base2 = sLanes.get(highest).getEnd();
					Point2D.Float halfVector = getHalf(base1, base2);

					Lane h = sLanes.get(highest);
					Lane nh = sLanes.get(nextHighest);
					a1 = new Point2D.Float(h.getStart().x + halfVector.x,
							h.getStart().y + halfVector.y);
					a2 = new Point2D.Float(h.getEnd().x + halfVector.x,
							h.getEnd().y + halfVector.y);
					a3 = new Point2D.Float(nh.getStart().x - halfVector.x,
							nh.getStart().y - halfVector.y);
					a4 = new Point2D.Float(nh.getEnd().x - halfVector.x,
							nh.getEnd().y - halfVector.y);

				} else {
					// highest is odd-> next highest should be even since
					// bilateral
					for (int i = sortedS.size() - 2; i >= 0; i--) {
						if (sortedS.get(i) % 2 == 0) {
							nextHighest = sortedS.get(i);
							break;
						}
					}
					// highest is odd the outer line is another subtraction of
					// the half offset vector
					// nextHighest is even the outer line is another addition
					// of the half offset vector
					Point2D.Float base1 = sLanes.get(nextHighest).getStart();
					Point2D.Float base2 = sLanes.get(nextHighest).getEnd();
					Point2D.Float halfVector = getHalf(base1, base2);

					Lane h = sLanes.get(highest);
					Lane nh = sLanes.get(nextHighest);
					a1 = new Point2D.Float(h.getStart().x - halfVector.x,
							h.getStart().y - halfVector.y);
					a2 = new Point2D.Float(h.getEnd().x - halfVector.x,
							h.getEnd().y - halfVector.y);
					a3 = new Point2D.Float(nh.getStart().x + halfVector.x,
							nh.getStart().y + halfVector.y);
					a4 = new Point2D.Float(nh.getEnd().x + halfVector.x,
							nh.getEnd().y + halfVector.y);
				}
				// end of bilateral sLane.

			} else {
				// unilateral sLAne->get a lane with highest key and one with
				// the lowest key
				nextHighest = sortedS.get(0);
				if (nextHighest % 2 == 0) {
					// even lanes
					// one outer line is highest + offset
					// one outer line is nextHighest - offset
					Point2D.Float base1 = sLanes.get(nextHighest).getStart();
					Point2D.Float base2 = sLanes.get(nextHighest).getEnd();
					Point2D.Float halfVector = getHalf(base1, base2);
					Lane h = sLanes.get(highest);
					Lane nh = sLanes.get(nextHighest);
					a1 = new Point2D.Float(h.getStart().x + halfVector.x,
							h.getStart().y + halfVector.y);
					a2 = new Point2D.Float(h.getEnd().x + halfVector.x,
							h.getEnd().y + halfVector.y);
					a3 = new Point2D.Float(nh.getStart().x - halfVector.x,
							nh.getStart().y - halfVector.y);
					a4 = new Point2D.Float(nh.getEnd().x - halfVector.x,
							nh.getEnd().y - halfVector.y);
				} else {
					// odd lanes
					// one outer line is highest - offset
					// one outer line is nextHighest + offset
					Point2D.Float base1 = sLanes.get(nextHighest).getEnd();
					Point2D.Float base2 = sLanes.get(nextHighest).getStart();
					Point2D.Float halfVector = getHalf(base1, base2);
					Lane h = sLanes.get(highest);
					Lane nh = sLanes.get(nextHighest);
					a1 = new Point2D.Float(h.getStart().x - halfVector.x,
							h.getStart().y - halfVector.y);
					a2 = new Point2D.Float(h.getEnd().x - halfVector.x,
							h.getEnd().y - halfVector.y);
					a3 = new Point2D.Float(nh.getStart().x + halfVector.x,
							nh.getStart().y + halfVector.y);
					a4 = new Point2D.Float(nh.getEnd().x + halfVector.x,
							nh.getEnd().y + halfVector.y);
				}

			}
			if (nextHighest == -1)
				throw new UnknownConnectionError();
			// END for sLane
			// //////////////
			// //////////////

			// for targetRoad
			ArrayList<Integer> sortedT = new ArrayList<Integer>(tLanes.keySet());
			Collections.sort(sortedT);
			int tHighest = sortedT.get(sortedT.size() - 1);
			int tModulus = tHighest % 2;
			int tNextHighest = -1;
			if (targetRoad.getBilateral()) {
				// bilateral get one lane with highest even key and one with
				// highest odd key

				if (tModulus == 0) {
					// highest is even-> next highest should be odd since
					// bilateral

					for (int i = sortedT.size() - 2; i >= 0; i--) {
						if (sortedT.get(i) == 1)
							tNextHighest = sortedT.get(i);
						break;
					}
					// highest is even the outer line is another addition of the
					// half offset vector
					// nextHighest is odd the outer line is another subtraction
					// of the half offset vector
					Point2D.Float base1 = tLanes.get(tHighest).getStart();
					Point2D.Float base2 = tLanes.get(tHighest).getEnd();
					Point2D.Float halfVector = getHalf(base1, base2);

					Lane h = tLanes.get(tHighest);
					Lane nh = tLanes.get(tNextHighest);
					b1 = new Point2D.Float(h.getStart().x + halfVector.x,
							h.getStart().y + halfVector.y);
					b2 = new Point2D.Float(h.getEnd().x + halfVector.x,
							h.getEnd().y + halfVector.y);
					b3 = new Point2D.Float(nh.getStart().x - halfVector.x,
							nh.getStart().y - halfVector.y);
					b4 = new Point2D.Float(nh.getEnd().x - halfVector.x,
							nh.getEnd().y - halfVector.y);
				} else {
					// highest is odd-> next highest should be even since
					// bilateral
					for (int i = sortedT.size() - 2; i >= 0; i--) {
						if (sortedT.get(i) == 0)
							tNextHighest = sortedT.get(i);
						break;
					}
					// highest is odd the outer line is another subtraction of
					// the half offset vector
					// nextHighest is even the outer line is another addition
					// of the half offset vector
					Point2D.Float base1 = tLanes.get(tNextHighest).getStart();
					Point2D.Float base2 = tLanes.get(tNextHighest).getEnd();
					Point2D.Float halfVector = getHalf(base1, base2);

					Lane h = tLanes.get(tHighest);
					Lane nh = tLanes.get(tNextHighest);
					b1 = new Point2D.Float(h.getStart().x - halfVector.x,
							h.getStart().y - halfVector.y);
					b2 = new Point2D.Float(h.getEnd().x - halfVector.x,
							h.getEnd().y - halfVector.y);
					b3 = new Point2D.Float(nh.getStart().x + halfVector.x,
							nh.getStart().y + halfVector.y);
					b4 = new Point2D.Float(nh.getEnd().x + halfVector.x,
							nh.getEnd().y + halfVector.y);

				}
			} else {
				// unilateral get a lane with highest key and one with the
				// lowest key
				tNextHighest = sortedT.get(0);
				if (tNextHighest % 2 == 0) {
					// even lanes
					// one outer line is highest + offset
					// one outer line is nextHighest - offset
					Point2D.Float base1 = sLanes.get(tNextHighest).getStart();
					Point2D.Float base2 = sLanes.get(tNextHighest).getEnd();
					Point2D.Float halfVector = getHalf(base1, base2);
					Lane h = sLanes.get(tHighest);
					Lane nh = sLanes.get(tNextHighest);
					b1 = new Point2D.Float(h.getStart().x + halfVector.x,
							h.getStart().y + halfVector.y);
					b2 = new Point2D.Float(h.getEnd().x + halfVector.x,
							h.getEnd().y + halfVector.y);
					b3 = new Point2D.Float(nh.getStart().x - halfVector.x,
							nh.getStart().y - halfVector.y);
					b4 = new Point2D.Float(nh.getEnd().x - halfVector.x,
							nh.getEnd().y - halfVector.y);
				} else {
					// odd lanes
					// one outer line is highest - offset
					// one outer line is nextHighest + offset
					Point2D.Float base1 = sLanes.get(tNextHighest).getEnd();
					Point2D.Float base2 = sLanes.get(tNextHighest).getStart();
					Point2D.Float halfVector = getHalf(base1, base2);
					Lane h = sLanes.get(tHighest);
					Lane nh = sLanes.get(tNextHighest);
					b1 = new Point2D.Float(h.getStart().x - halfVector.x,
							h.getStart().y - halfVector.y);
					b2 = new Point2D.Float(h.getEnd().x - halfVector.x,
							h.getEnd().y - halfVector.y);
					b3 = new Point2D.Float(nh.getStart().x + halfVector.x,
							nh.getStart().y + halfVector.y);
					b4 = new Point2D.Float(nh.getEnd().x + halfVector.x,
							nh.getEnd().y + halfVector.y);
				}

			}
		} else {
			throw new UnknownConnectionError();
		}
		// end if
		// //////////
		// ////////////

	}

	public Point2D.Float lineAndLineIntersect(StraightLane sLane,
			StraightLane tLane) throws UnknownConnectionError {
		return null;
	}

	private static Point2D.Float getHalf(Point2D.Float base1,
			Point2D.Float base2) {
		Point2D.Float vector = new Point2D.Float(base2.x - base1.x, base2.y
				- base1.y);
		// System.out.println("Vector: " + vector);
		float vectorLength = (float) Math.sqrt(Math.pow(vector.x, 2.0)
				+ Math.pow(vector.y, 2.0));
		// System.out.println("Length: " + vectorLength);
		Point2D.Float normalVector = new Point2D.Float(vector.x / vectorLength,
				vector.y / vectorLength);
		// System.out.println("x calculation: " + normalVector.x +
		// "*"
		// + perpenMat[0][0] + " + " + normalVector.y + "*"
		// + perpenMat[1][0]);
		Point2D.Float perpenVector = new Point2D.Float(normalVector.x
				* perpenMat[0][0] + normalVector.y * perpenMat[1][0],
				normalVector.x * perpenMat[0][1] + normalVector.y
						* perpenMat[1][1]);
		// System.out.println("Perpen vector: " + perpenVector);
		Point2D.Float halfScaled = new Point2D.Float(perpenVector.x
				* Road.roadWidth / 2, perpenVector.y * Road.roadWidth / 2);
		return halfScaled;

	}
	/****************************/

}
