package uaic.info.route.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import uaic.info.route.location.Location;
import uaic.info.route.road.Road;

public class ProblemInstance {
	private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<Road> roads = new ArrayList<>();
	
    
	public ProblemInstance()
	{
		locations = null;
		roads = null;
	}
	
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
	
	public void addLocation(Location location)
	{
		if(locations.contains(location) == false)
			locations.add(location);
		else
			System.out.println("The location " + location.toString() + " is already in the instance");
	}
	
	public void addRoad(Road road)
	{
		if(roads.contains(road) == false)
			roads.add(road);
		else
			System.out.println("The road " + road.toString() + " is already in the instance");
	}

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
	public boolean existsLocation(Location location)
	{
		return locations.contains(location);
	}
	
	public boolean existsRoad(Road road)
	{
		return roads.contains(road);
	}
	

	
}
