package control;

import java.awt.Graphics;

import model.CarWorld;
import model.Road;

public class WorldController {
	private CarWorld cWorld;

	public WorldController() {
		cWorld = new CarWorld();
		Road road = new Road(100, 100, 400, 500);
		cWorld.addRoad(road);
	}

	public CarWorld getcWorld() {
		return cWorld;
	}

	public void setcWorld(CarWorld cWorld) {
		this.cWorld = cWorld;
	}

	public void drawCars(Graphics g) {

	}

	public void drawRoads(Graphics g) {

	}
}
