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

		City iasiGasStation = new City("Iasi Gas Station", 35, 15, 3, 9);
		Airport clujAirPort = new Airport("Cluj Airport", 15, 13);
		City iasi = new City("Iasi", 34.7, 15.1,100, 500_000);
		City cluj =  new City("Cluj", 15.2, 12.9, 12000, 20000);
		City piatraNeamt = new City("Piatra Neamt", 28.4, 13.4,9000, 3000);
		City brasov = new City("Brasov", 25, 8.5,9000,6000);

		//System.out.println(iasi.toString());

		Street roadToIasiGasStation = new Street("Road to Iasi Gas Station",  iasi, iasiGasStation,50,1);
		Highway roadToClujAirport = new Highway("Road to Cluj Airport",   cluj, clujAirPort,50,1.2);
		Street roadIasiToPiatraNeamt = new Street("Iasi-Piatra Neamt Highway",  iasi, piatraNeamt,110,24);
		Highway roadIasiToBrasov = new Highway("Iasi-Brasov Highway",  iasi, brasov, 110,35);
		Street roadPiatraNeamtToCluj = new Street("Piatra Neamt-Cluj Highway",  piatraNeamt, cluj, 110,28);
		Highway roadBrasovToCluj = new Highway("Brasov-Cluj Highway",  brasov, cluj, 110,35);

		System.out.println(roadBrasovToCluj.toString());


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


		/*
		ProblemInstance instance = ProblemInstance.generateRandomInstance(10,7);
		for(Location location : instance.getLocations())
			System.out.println(location.toString());
		for(Road road : instance.getRoads())
			System.out.println(road.toString());

		 */

		System.gc();
		Runtime runtime = Runtime.getRuntime();
		long usedMemoryBefore =
				runtime.totalMemory() - runtime.freeMemory();
		long initialTime = System.currentTimeMillis();
		// Adaugare metodei ce vrem sa o masuram
		ArrayList<Road> roads = new Algorithm().getShortestPath(pb,iasiGasStation, clujAirPort);

		long runningTime = System.currentTimeMillis() - initialTime;
		long usedMemoryAfter =
				runtime.totalMemory() - runtime.freeMemory();
		long memoryIncrease = usedMemoryAfter - usedMemoryBefore;
		System.out.println(memoryIncrease);
		System.out.println(runningTime);
		if(roads != null)
			for(Road road : roads) {
				System.out.println(road.toString());
			}
		else
			System.out.println("There is no road");

	}

}
