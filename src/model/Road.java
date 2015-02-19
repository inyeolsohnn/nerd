package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

	public static void connectLane(Road currentRoad, int currentLane,
			Road targetRoad, int targetLane) {
		// sets connection state between the lane corresponding to the
		// currentLane in currentRoad and the corresponding targetLane, in
		// targetRoad
	}

	/****************************/

}
