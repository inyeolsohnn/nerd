package tests;

import java.awt.geom.Point2D;

import model.Car;
import model.Lane;
import model.Road;
import model.StraightLane;
import model.TrafficLight;

public class SimpleStraightRoadWithLightTest {
	public static void main(String[] args) throws InterruptedException {
		Point2D.Float startingPoint = new Point2D.Float(80, 60);
		Point2D.Float endPoint = new Point2D.Float(0, 0);
		Road firstRoad = new Road();

		Lane straightLane = new StraightLane(startingPoint, endPoint,
				firstRoad.getId());

		firstRoad.addLane(straightLane);
		System.out.println("lane span: " + straightLane.getSpan());
		Car firstCar = new Car();
		Car secondCar = new Car();

		firstCar.setCurrentSpeed(10);

		firstCar.enterLane(straightLane, straightLane.getStart());
		secondCar.enterLane(straightLane, straightLane.getStart());
		while (true) {
			firstCar.move();

			Thread.sleep(20);
		}

	}
}
