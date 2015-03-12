package control;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Car;
import model.CarPark;
import model.CarWorld;
import model.Road;
import model.StraightRoad;
import model.TrafficLight;

public class WorldController {
	private CarWorld cWorld; // core model
	private JFrame carView; // JFrame

	public WorldController() {

		this.cWorld = this.createWorld();

	}

	public CarWorld getcWorld() {
		return cWorld;
	}

	public void setcWorld(CarWorld cWorld) {
		this.cWorld = cWorld;
	}

	public String toString() {
		return cWorld.toString();

	}

	public void setView(JFrame frame) {
		this.carView = frame;
	}

	public void pause() {
		this.cWorld.setStatus("paused");
	}

	public void start() {
		this.cWorld.setStatus("running");
	}

	public void exit() {
		this.cWorld.setStatus("exit");
	}

	// program loop
	public void simulate() throws InterruptedException {

		while (true) {
			
			if (cWorld.getStatus().equals("running")) {
				update();
			
				
				Thread.sleep(20); // the timing mechanism
									// needs improvement
			} else if (cWorld.getStatus().equals("paused")) {
				Thread.sleep(20);
			} else if (cWorld.getStatus().equals("exit")) {
				return;
			}
		}
	}

	public ArrayList<Car> getCars() {
		// TODO Auto-generated method stub
		return this.cWorld.getCars();
	}

	public ArrayList<Road> getRoads() {
		// TODO Auto-generated method stub
		return this.cWorld.getRoads();
	}
	public ArrayList<TrafficLight> getLights() {
		// TODO Auto-generated method stub
		return this.cWorld.getLights();
	}

	private void update() {
		System.out.println("updating everything in the world");
		for(int i=0;i<cWorld.getCars().size();i++){
			cWorld.getCars().get(i).move();
			
		}
		this.carView.repaint();

	}

	public static ArrayList<Road> bfsRoads(Road road, Road destinationRoad) {
		// TODO Auto-generated method stub
		return null;
	}

	// example case setup
	public void setTJunction() {
		// construct the world with road network
		this.cWorld.setStatus("paused");
		this.cWorld.flush();
		// add new roads and such
		Road sr = new StraightRoad(new Point2D.Float(100, 100),
				new Point2D.Float(800, 100), 1, 1, this.getcWorld());
		Road ar = new StraightRoad(new Point2D.Float(450, 130),
				new Point2D.Float(450, 830), 1, 1, this.getcWorld());
		try{
		Road.connectLane(sr, 0, ar, 0);
		}catch(Exception e){
			
		}
		CarPark cp = null;
	
		
		
		Car c1 = new Car(sr.getLanes().get(0).getStart(),10,sr.getLanes().get(0),
				cp,this.cWorld,sr.getLanes().get(0).getStart());
		c1.setCurrentSpeed(30);
		this.cWorld.addCar(c1);
		this.cWorld.addRoad(sr);
		this.cWorld.addRoad(ar);

	}
	public void setIntersection(){
		this.cWorld.setStatus("paused");
		this.cWorld.flush();
		// add new roads and such
		Road r1 = new StraightRoad(new Point2D.Float(50, 350),
				new Point2D.Float(760, 350), 2, 1, this.getcWorld());
		Road r2 = new StraightRoad(new Point2D.Float(380, 20),
				new Point2D.Float(380, 700), 2, 1, this.getcWorld());
		Road r3 = new StraightRoad(new Point2D.Float(790, 20),
				new Point2D.Float(790, 700), 2, 1, this.getcWorld());
		this.cWorld.addRoad(r1);
		this.cWorld.addRoad(r2);
		this.cWorld.addRoad(r3);
	}

	public void setRoundAbout() {
		// first construct the world with road network

	}

	public CarWorld createWorld() {
		if (this.cWorld == null) {
			CarWorld world = new CarWorld();
			this.cWorld = world;
			return cWorld;
		} else {
			return this.cWorld;
		}
	}
}
