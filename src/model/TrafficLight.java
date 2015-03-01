package model;

import java.awt.geom.Point2D;
import java.util.Date;

public class TrafficLight {
	private Lane lane; // lane object it belongs to
	private String status;
	private long interval;
	private Date lastChanged;
	private Point2D.Double coordination;
	private int id;
	private static int totalLights = 0;

	// testing stubs
	public TrafficLight() {
		this.interval = 10000;
		this.lastChanged = new Date();
		this.status = "Green";
		this.id = totalLights;
		totalLights++;
	}

	public TrafficLight(Lane lane) {
		this.lane = lane;
		this.interval = 500;
		this.lastChanged = new Date();
		this.status = "Green";
		this.id = totalLights;
		totalLights++;
	}

	// testing stubs end

	public TrafficLight(Lane lane, long interval, Point2D.Double coordination) {
		this.lane = lane;
		this.interval = interval;
		this.lastChanged = new Date();
		this.status = "Green";
		this.coordination = coordination;
		this.id = totalLights;
		totalLights++;
	}

	public void setCoordinate(Point2D.Double coordination) {
		this.coordination = coordination;
	}

	public Point2D.Double getCoordinate() {
		return this.coordination;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public Lane getLane() {
		return this.lane;
	}

	public void update() {
		Date currentDate = new Date();
		if (currentDate.getTime() - lastChanged.getTime() > interval) {
			System.out.println("changing");

			lastChanged = currentDate;
			if (status.equals("Green")) {
				this.status = "Red";
			} else if (status.equals("Red")) {
				this.status = "Green";
			}
			System.out.println(this.status);
		}
	}

}
