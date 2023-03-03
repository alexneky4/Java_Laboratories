package uaic.info.route.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import uaic.info.route.location.Location;
import uaic.info.route.road.Road;

public class Algorithm {
	
	HashMap<Location,ArrayList<Location>> adjacencyList = new HashMap<>();
	public boolean validateInstance(ProblemInstance instance)
	{
		ArrayList<Location> locations = instance.getLocations();
		ArrayList<Road> roads = instance.getRoads();
		
		for(int i = 0; i < locations.size() - 1; i++)
			for(int j = i + 1; j < locations.size(); j++)
				if(locations.get(i).equals(locations.get(j)))
					return false;
		for(int i = 0; i < roads.size() - 1; i++)
			for(int j = i + 1; j < roads.size(); j++)
				if(roads.get(i).equals(roads.get(j)))
					return false;
		
		return true;
	}
	
	public void createAdjacencyList(ProblemInstance instance)
	{
		adjacencyList.clear();
		if(validateInstance(instance) == false)
			return;
		for(Location location : instance.getLocations())
		{
			adjacencyList.put(location, new ArrayList<>());
		}
		for(Road road : instance.getRoads())
		{
			Location l1 = road.getFirstEndPoint();
			Location l2 = road.getSecondEndPoint();
			adjacencyList.get(l1).add(l2);
			adjacencyList.get(l2).add(l1);
		}
	}
	
	public boolean existRoute(ProblemInstance instance, Location l1, Location l2)
	{
		createAdjacencyList(instance);
		if(adjacencyList.containsKey(l1) == false)
			return false;
		else
			
			
	}
	
	
}