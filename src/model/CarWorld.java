package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CarWorld {
	private String status = "paused";
	private int height;
	private int width;
	private ArrayList<Road> roads = new ArrayList<Road>();
	private ArrayList<Car> cars = new ArrayList<Car>();
	private ArrayList<TrafficLight> lights = new ArrayList<TrafficLight>();

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
		roads.add(road);
	}

	public void addCar(Car car) {
		cars.add(car);
	}

	public void removeRoad(String roadId) {
		roads.remove(roadId);
	}

	public String toString() {

		return "Number of roads: " + roads.size() + "Number of cars"
				+ cars.size();
	}

	public void setStatus(String s) {
		this.status = s;

	}

	public String getStatus() {
		return this.status;
	}

	public ArrayList<Car> getCars() {
		// TODO Auto-generated method stub
		return cars;
	}

	public Road getRoad(int roadId) {
		Road tRoad = null;
		for (int i = 0; i < roads.size(); i++) {
			Road cRoad = roads.get(i);
			if (cRoad.getId() == roadId) {
				tRoad = cRoad;
			}
		}
		return tRoad;
	}

	public ArrayList<Road> getRoads() {
		// TODO Auto-generated method stub
		return roads;
	}
	public ArrayList<TrafficLight> getLights() {
		// TODO Auto-generated method stub
		return lights;
	}

	public void connectRoads() throws UnknownConnectionError {
		// connection logic for each type of different pair of roads
		// straight straight =0
		// straight round= 1
		// striahgt curve= 2
		// round straight =1
		// round round = 3
		// round curve =5
		// curve straight = 2
		// curve round =5
		// curve curve = 8
		// 6 different logic in total

		for (int i = 0; i < roads.size(); i++) {
			Road currentRoad = roads.get(i);
			for (int j = 0; j < roads.size(); j++) {
				Road targetRoad = roads.get(j);
				if (currentRoad.getId() != targetRoad.getId()) {
					// first check what type of roads we are comparing against
					int currentRoadType = currentRoad.getType();
					int targetRoadType = targetRoad.getType();

					int connectionLogic = (currentRoadType + targetRoadType)
							+ currentRoadType * targetRoadType;
					if (connectionLogic != 0 || connectionLogic != 1
							|| connectionLogic != 2 || connectionLogic != 3
							|| connectionLogic != 5 || connectionLogic != 8) {
						throw new UnknownConnectionError();

					}
					switch (connectionLogic) {
					case 0:
					case 1:
					case 2:
					case 3:
					case 5:
					case 8:

					}

				}
			}
		}

	}

	public void flush() {
		roads=new ArrayList<Road>();
		cars=new ArrayList<Car>();
		// TODO Auto-generated method stub
		
	}
	public void paint(Graphics g){
		for(int i=0; i<roads.size(); i++)
		{
			roads.get(i).paint(g);
		}
		for(int i=0; i<cars.size(); i++){
			cars.get(i).paint(g);
		}
	}

}
