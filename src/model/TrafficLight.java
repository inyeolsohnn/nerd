package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Date;

public class TrafficLight {
	private Lane lane; // lane object it belongs to
	private String status;
	private float greenInterval;
	private float redInterval;
	private Date lastChanged;
	private Point2D.Float coordination;
	private int id;
	private static int totalLights = 0;
	private double tempInterval=0.0;
	private int lightBoxLength = 50;
	private int lightBoxWidth = 25;
	// testing stubs
	public TrafficLight() {
		this.greenInterval = 100;
		this.redInterval = 200;
		this.lastChanged = new Date();
		System.out.println(this.lastChanged);
		this.status = "Green";
		this.id = totalLights;
		totalLights++;
	}

	public TrafficLight(Lane lane) {
		this.lane = lane;
		
		this.lastChanged = new Date();
		this.status = "Green";
		this.id = totalLights;
		totalLights++;
	}

	// testing stubs end

	public TrafficLight(Lane lane,String status, float greenInterval,float redInterval, Point2D.Float coordination) {
		this.lane = lane;
		System.out.println("traffic coord"+coordination);
		this.redInterval = redInterval;
		this.greenInterval = greenInterval; 
		this.lastChanged = new Date();
		this.status = status;
		this.coordination = coordination;
		this.id = totalLights;
		totalLights++;
	}

	public void setCoordinate(Point2D.Float coordination) {
		this.coordination = coordination;
	}

	public Point2D.Float getCoordinate() {
		return this.coordination;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRedInterval(long interval) {
		this.redInterval = interval;
	}
	

	public Lane getLane() {
		return this.lane;
	}
	public void paint(Graphics g){
		if(status.equalsIgnoreCase("green")){
			 g.setColor(Color.BLACK);
				g.fillOval((int)coordination.x-5, (int)coordination.y-5, 15, 15);
				
		}
	}
	public void update() {
	/*	Date currentDate = new Date();
		if (currentDate.getTime() - lastChanged.getTime() > interval) {
			System.out.println("changing");
			System.out.println(currentDate.getTime() - lastChanged.getTime());
			lastChanged = currentDate;
			if (status.equals("Green")) {
				this.status = "Red";
			} else if (status.equals("Red")) {
				this.status = "Green";
			}
			System.out.println(this.status);
		}*/
	
		if(this.status.equalsIgnoreCase("green")){
			
			if(tempInterval==greenInterval){
				this.status ="red";
				tempInterval=0.0;
			}
			else{
				tempInterval+=20;
			}
		}
		else{
			if(tempInterval==redInterval){
				this.status ="green";
				tempInterval=0.0;
			}
			else{
				tempInterval+=20;
			}
		}
		//System.out.println(getStatus());
	}

}
