package tests;

import java.awt.geom.Point2D;

import model.Car;
import model.Lane;
import model.Road;
import model.TrafficLight;

public class SimpleStraightRoadWithLightTest {
	public static void main(String[] args) throws InterruptedException {
		Point2D.Float startingPoint = new Point2D.Float(80, 60);
		Point2D.Float endPoint = new Point2D.Float(0, 0);
		Road firstRoad = new Road();
		System.out.println(startingPoint.x);
		Lane straightLane = new Lane(startingPoint, endPoint, firstRoad.getId());
		System.out.println(straightLane.getStart());

		firstRoad.addLane(straightLane);

		Car firstCar = new Car();
		firstCar.setCoordinate(new Point2D.Float(80, 60));
		firstCar.setCurrentSpeed(50);
		System.out.println(firstCar.getCoordinate());
		firstCar.enterLane(straightLane);
		while (true) {
			firstCar.move();

			Thread.sleep(20);
		}

	}
}
