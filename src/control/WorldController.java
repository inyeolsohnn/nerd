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

	public WorldController(JFrame frame, CarWorld cWorld) {
		this.carView = frame;
		this.cWorld = cWorld;
		((CarSimView) frame).setController(this);

		
		frame.setSize(cWorld.getWidth(), cWorld.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

	}

	public CarWorld getcWorld() {
		return cWorld;
	}

	public void addRoad(Road road) throws UnknownConnectionError{
		cWorld.addRoad(road);
		cWorld.connectRoads();
		//******call to connection network logic required here*********//
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
		this.cWorld.setStatus(false);
	}

	public void start() {
		this.cWorld.setStatus(true);
	}

	// program loop
	public void simulate() throws InterruptedException {
		while (true) {
			if (cWorld.getStatus() == true) {
				update();
				render();
				Thread.sleep(20); // the timing mechanism
									// needs improvement
			}
			if (cWorld.getStatus() == false) {
				Thread.sleep(20);
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
		System.out.println("updated");
		ArrayList<Car> cars = getCars();
		ArrayList<Road> roads = getRoads();
		

	}

	private void render() {
		this.carView.repaint();

	}

	public static ArrayList<Road> bfsRoads(int currentRoadId,
			int destinationRoadId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//example case setup
	public void setTJunction(){
		
	}
	
	public void setRoundAbout(){
		
	}

}
