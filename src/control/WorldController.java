package control;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import view.CarSimView;
import model.CarWorld;
import model.Road;
import model.Car;
import model.UnknownConnectionError;

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
	public void exit(){
		this.cWorld.setStatus("exit");
	}

	// program loop
	public void simulate() throws InterruptedException {
		
		while (true) {
			if (cWorld.getStatus().equals("running")) {
				update();
				render();
				Thread.sleep(20); // the timing mechanism
									// needs improvement
			}
			else if (cWorld.getStatus().equals("paused")) {
				Thread.sleep(20);
			}
			else if(cWorld.getStatus().equals("exit")){
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

	private void update() {
		System.out.println("updating everything in the world");
		ArrayList<Car> cars = getCars();
		ArrayList<Road> roads = getRoads();
		

	}

	private void render() {
		this.carView.repaint();

	}
	
	
	

	public static ArrayList<Road> bfsRoads(Road road,
			Road destinationRoad) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//example case setup
	public void setTJunction(){
		//construct the world with road network
		this.cWorld.setStatus("paused");
		this.cWorld.flush();
		//add new roads and such
		
		
	}
	
	public void setRoundAbout(){
		//first construct the world with road network
	
	}
	public CarWorld createWorld(){
		if(this.cWorld==null){
			CarWorld world= new CarWorld();
			this.cWorld=world;
			return cWorld;
		}else{
			return this.cWorld;
		}
	}
}
