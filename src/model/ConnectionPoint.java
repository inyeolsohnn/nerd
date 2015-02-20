package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ConnectionPoint {
	ArrayList<Connection> connections = new ArrayList<Connection>();
	private Point2D.Float pointCoordinate;
	private Road road;
	private Lane lane;

	public ConnectionPoint(Road road, Lane lane, Point2D.Float coordinate) {
		this.road = road;
		this.lane = lane;
		this.pointCoordinate = coordinate;

	}

	public Point2D.Float getPointCoordinate() {
		return pointCoordinate;
	}

	public void setPointCoordinate(Point2D.Float pointCoordinate) {
		this.pointCoordinate = pointCoordinate;
	}

	public Road getRoad() {
		return road;
	}

	public Lane getLane() {
		return lane;
	}

	public void createConnection(Road targetRoad, Lane targetLane)
			throws UnknownConnectionError {
		Connection newConnection = new Connection(this.road, this.lane,
				targetRoad, targetLane, this);
		connections.add(newConnection);
	}
}
