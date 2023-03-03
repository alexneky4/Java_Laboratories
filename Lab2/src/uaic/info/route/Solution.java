package uaic.info.route;

import java.util.ArrayList;
import java.util.Collections;

import uaic.info.route.algorithm.ProblemInstance;
import uaic.info.route.location.Location;
import uaic.info.route.road.Road;

public class Solution {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Location locationOne = new Location("Iasi", 120, 250);
		Location locationTwo = new Location("Vaslui");
		locationTwo.setX(370);
		locationTwo.setY(689);
		
		Road roadOneTwo = new Road("Iasi-Vaslui",locationOne,locationTwo);
		Road emptyRoad = new Road();
		
		
		System.out.println(roadOneTwo.toString());
		System.out.println(locationOne.toString());
		System.out.println(locationTwo.toString());
		System.out.println(emptyRoad.toString());
	}

}
