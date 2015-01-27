package model;

import java.util.ArrayList;
import java.util.HashMap;

public class CarWorld  {
	private int height;
	private int width;
	private HashMap<String, Road> roads = new HashMap<String, Road>();

	public CarWorld() {
		this.height = 1000;
		this.width = 800;
	}

	public CarWorld(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	public void addRoad(Road road) {
		roads.put(road.getId(), road);
	}

	public void removeRoad(String roadId) {

	}

	public void changeRoad(Road road) {
		Road tempRoad = road;
		Road originalRoad = roads.get(road.getId());
		originalRoad.updateRoad(road);
	}

}
