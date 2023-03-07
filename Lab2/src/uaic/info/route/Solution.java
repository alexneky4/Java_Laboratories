package uaic.info.route;

import java.util.ArrayList;
import java.util.Collections;

import uaic.info.route.algorithm.Algorithm;
import uaic.info.route.algorithm.ProblemInstance;
import uaic.info.route.location.Airport;
import uaic.info.route.location.City;
import uaic.info.route.location.Location;
import uaic.info.route.road.Highway;
import uaic.info.route.road.Road;
import uaic.info.route.road.Street;

public class Solution {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		City iasiGasStation = new City("Iasi Gas Station", 35, 15, 3, 900);
		Airport clujAirPort = new Airport("Cluj Airport", 4, 100);
		City iasi = new City("Iasi", 34, 15, 1000, 3000);
		City cluj = new City("Cluj", 15, 12, 3000, 20000);
		City piatraNeamt = new City("Piatra Neamt", 28, 13, 9000, 3000);
		City brasov = new City("Brasov", 25, 8, 9000,6000);

		Street roadToIasiGasStation = new Street("Road to Iasi Gas Station",  iasi, iasiGasStation,1, 50);
		Highway roadToClujAirport = new Highway("Road to Cluj Airport",   cluj, clujAirPort,12, 50);
		Street roadIasiToPiatraNeamt = new Street("Iasi-Piatra Neamt Highway",  iasi, piatraNeamt,24, 110);
		Highway roadIasiToBrasov = new Highway("Iasi-Brasov Highway",  iasi, brasov, 35, 110);
		Street roadPiatraNeamtToCluj = new Street("Piatra Neamt-Cluj Highway",  piatraNeamt, cluj, 28, 110);
		Highway roadBrasovToCluj = new Highway("Brasov-Cluj Highway",  brasov, cluj, 35, 110);

		ProblemInstance pb = new ProblemInstance();
		pb.addLocation(iasiGasStation);
		pb.addLocation(clujAirPort);
		pb.addLocation(iasi);
		pb.addLocation(cluj);
		pb.addLocation(brasov);
		pb.addLocation(piatraNeamt);

		pb.addRoad(roadToClujAirport);
		pb.addRoad(roadToIasiGasStation);
		pb.addRoad(roadIasiToPiatraNeamt);
		pb.addRoad(roadIasiToBrasov);
		pb.addRoad(roadPiatraNeamtToCluj);
		pb.addRoad(roadBrasovToCluj);

		ArrayList<Road> roads = new Algorithm().getShortestPath(pb,iasiGasStation, clujAirPort);
		if(roads != null)
			for(Road road : roads) {
				System.out.println(road.toString());
			}
		else
			System.out.println("There is no road");

		 */
		ProblemInstance instance = ProblemInstance.generateRandomInstance(10,7);
		for(Location location : instance.getLocations())
			System.out.println(location.toString());
		for(Road road : instance.getRoads())
			System.out.println(road.toString());
	}

}
