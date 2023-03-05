package uaic.info.route;

import java.util.ArrayList;
import java.util.Collections;

import uaic.info.route.algorithm.Algorithm;
import uaic.info.route.algorithm.ProblemInstance;
import uaic.info.route.location.City;
import uaic.info.route.location.Location;
import uaic.info.route.road.Highway;
import uaic.info.route.road.Road;
import uaic.info.route.road.Street;

public class Solution {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		City locationOne = new City("Iasi", 120, 250);
		City locationTwo = new City("Vaslui");
		locationTwo.setX(370);
		locationTwo.setY(689);
		
		Street roadOneTwo = new Street("Iasi-Vaslui",locationOne,locationTwo,70f, 1000f);
		Highway emptyRoad = new Highway();

		ProblemInstance instance = new ProblemInstance(locationOne,locationTwo,roadOneTwo);
		/*
		System.out.println(roadOneTwo.toString());
		System.out.println(locationOne.toString());
		System.out.println(locationTwo.toString());
		System.out.println(emptyRoad.toString());

		 */
		ArrayList<Road> roads = new Algorithm().getShortestPath(instance,locationOne,locationTwo);
		if( roads != null)
		{
			for(Road road : roads)
			{
				System.out.println(road.toString());
				System.out.println(road.getFirstEndPoint().toString());
				System.out.println(road.getSecondEndPoint().toString());
			}
		}
		else
			System.out.println("There is no road from " + locationOne.toString() + " to "  + locationTwo.toString());
	}

}
