package tests;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import model.Car;
import model.Road;
import model.RoundRoad;
import model.StraightRoad;

public class RoundAboutTest {
	public static void main(String[] args) throws InterruptedException {
		Road ra = new RoundRoad(new Point2D.Float(100, 100), 20, 1);
		Car c1 = new Car();
		c1.setCurrentSpeed(10);
		c1.enterRoad(ra);
		while (true) {
			c1.move();
			Thread.sleep(20);
		}
	}
}
