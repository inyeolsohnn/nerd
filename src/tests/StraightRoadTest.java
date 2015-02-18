package tests;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import model.Road;
import model.StraightRoad;

public class StraightRoadTest {
	public static void main(String[] args){
		Road sr= new StraightRoad(new Point2D.Float(100,200), new Point2D.Float(100,300), 2, 2);
	}
}
