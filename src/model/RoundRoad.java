package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class RoundRoad extends Road {

	// can only hold RoundAbout
	// centerPoint= center point of the roundabout
	// radius= radius of center circle
	// numLanes= number of lanes in the roundabout
	public RoundRoad(Point2D.Float centerPoint, int radius, int numLanes, CarWorld world) {
		super(1, world);
		// TODO Auto-generated constructor stub
		setUpLanes(centerPoint, radius, numLanes);
		Iterator it = this.lanes.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Lane currentLane = (Lane) pair.getValue();
			System.out.println("Lane found: " + currentLane.getStart() + ", "
					+ currentLane.getEnd());
		}
		this.setBilateral(false);
	}

	private void setUpLanes(Float centerPoint, int radius, int numLanes) {
		int firstRadius = radius + 15;
		for (int i = 0; i < numLanes; i++) {
			Lane ra = new RoundAbout(centerPoint, firstRadius + (i * 30),
					this, this.getWorld(), i);
			this.lanes.put(i, ra);
		}

	}

}
