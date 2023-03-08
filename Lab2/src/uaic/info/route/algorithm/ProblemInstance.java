package uaic.info.route.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import uaic.info.route.location.City;
import uaic.info.route.location.Location;
import uaic.info.route.road.Road;
import uaic.info.route.road.Street;

/**
 *  This class represents an instance of the problem
 */
public class ProblemInstance {
	/**
	 * This array keeps all the locations that the problem instance has
	 */
	private ArrayList<Location> locations;
	/**
	 *  This array keeps all the roads that the problem instance has
	 */
    private ArrayList<Road> roads;
	
    
	public ProblemInstance()
	{
		locations = new ArrayList<>();
		roads = new ArrayList<>();
	}

	/**
	 * In this constructor, we take a list of objects as parameters. We verify if they are of
	 * the type Location or Road and add them to their respective arrays
	 * @param objects
	 */
	public ProblemInstance(Object...objects)
	{
		for(int i = 0; i < objects.length; i++)
		{
			if(objects[i] instanceof Location)
			{
				if(locations.contains(objects[i]) == false)
					locations.add((Location) objects[i]);
			}
			else if(objects[i] instanceof Road)
			{
				if(roads.contains(objects[i]) == false)
					roads.add((Road) objects[i]);
			}
			else
			{
				System.out.println("The " + i + " parameter is not a location, nor a road");
			}
		}
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}
	/**
	 * To add a Location to the instance, we have to make sure that the Location is not already in the array
	 */
	public void addLocation(Location location)
	{
		if(locations.contains(location) == false)
			locations.add(location);
		else
			System.out.println("The location " + location.toString() + " is already in the instance");
	}

	/**
	 * To add a Road to the instance, we have to make sure that the Road is not already in the array
	 */
	public void addRoad(Road road)
	{
		if(roads.contains(road) == false)
			roads.add(road);
		else
			System.out.println("The road " + road.toString() + " is already in the instance");
	}

	/**
	 *  Auxiliary method that gets all the Roads from the instance that have as end point
	 *  the given location
	 * @param location
	 * @return Array of Roads (can be null)
	 */
	public ArrayList<Road> getRoadsForLocation(Location location)
	{
		ArrayList<Road> locationRoads = new ArrayList<>();
		for(Road road : roads)
		{
			if(road.getFirstEndPoint().equals(location)
					|| road.getSecondEndPoint().equals(location))
				locationRoads.add(road);
		}

		return locationRoads;
	}

	/**
	 *  Static method that generates a random instance of the problem. It takes the number of locations
	 *  and the number of roads. We verify that the number of roads do not exceed the maximum possible number.
	 *  After that, we randomly generate locations and roads.
	 * @param maxNumberOfLocations
	 * @param maxNumberOfRoads
	 * @return
	 */
	public static ProblemInstance generateRandomInstance(int maxNumberOfLocations, int maxNumberOfRoads)
	{
		ProblemInstance instance = new ProblemInstance();
		if(maxNumberOfRoads > maxNumberOfLocations * (maxNumberOfLocations + 1) / 2)
		{
			System.out.println("Please give a right number of locations and roads");
			return null;
		}
		for(int i = 1; i <= maxNumberOfLocations; i++)
		{
			City city = new City(Integer.toString(i), Math.random() * 500, Math.random() * 500);
			instance.locations.add(city);
		}

		int[][] adjacencyMatrix = new int[maxNumberOfLocations][maxNumberOfLocations];
		for(int i = 1; i <= maxNumberOfRoads; i++)
		{
			int point1 = (int)(Math.random() * maxNumberOfLocations);
			int point2 = (int)(Math.random() * maxNumberOfLocations);
			while(point1 == point2 && adjacencyMatrix[point1][point2] == 1)
			{
				point2 = (int)(Math.random() * maxNumberOfLocations + 1);
			}

			adjacencyMatrix[point1][point2] = adjacencyMatrix[point2][point1] = 1;
			Street street = new Street(new StringBuilder().append(Integer.toString(point1))
							.append(Integer.toString(point2)).toString(),instance.locations.get(point1),
					instance.locations.get(point2),Math.random() * 120 + 10, Math.random()* 500 + 500 );

			instance.roads.add(street);
		}

		return instance;
	}
	
}
