package uaic.info.route.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import uaic.info.route.location.Location;
import uaic.info.route.road.Road;

public class Algorithm {
	
	HashMap<Location,ArrayList<Location>> adjacencyList = new HashMap<>();
	public boolean validateInstance(ProblemInstance instance)
	{
		ArrayList<Location> locations = instance.getLocations();
		ArrayList<Road> roads = instance.getRoads();
		
		for(Road road : roads)
		{
			if(locations.contains(road.getFirstEndPoint()) == false
					|| locations.contains(road.getSecondEndPoint()) == false)
				return false;
			int x1 = road.getFirstEndPoint().getX();
			int x2 = road.getSecondEndPoint().getX();
			int y1 = road.getFirstEndPoint().getY();
			int y2 = road.getSecondEndPoint().getY();

			if(road.getRoadLength() < Math.sqrt(((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2))))
				return false;

		}

		return true;
	}

	public void createAdjacencyList(ProblemInstance instance) {
		adjacencyList.clear();
		if (validateInstance(instance) == false)
			return;
		for (Location location : instance.getLocations()) {
			adjacencyList.put(location, new ArrayList<>());
		}
		for (Road road : instance.getRoads()) {
			Location l1 = road.getFirstEndPoint();
			Location l2 = road.getSecondEndPoint();
			adjacencyList.get(l1).add(l2);
			adjacencyList.get(l2).add(l1);
		}
	}

	private boolean DFS(Location l1, Location l2, HashMap<Location, Boolean> visited) {
		if (l1.equals(l2))
			return true;
		if (visited.get(l1) == true)
			return false;
		visited.put(l1, true);
		for (Location location : adjacencyList.get(l1))
			if (DFS(location, l2, visited) == true) {
				return true;
			}
		return false;
	}

	public boolean existRoute(ProblemInstance instance, Location l1, Location l2) {
		createAdjacencyList(instance);
		if (adjacencyList.containsKey(l1) == false)
			return false;
		else {
			HashMap<Location, Boolean> visited = new HashMap<>();
			for (Location location : instance.getLocations())
				visited.put(location, false);
			return DFS(l1, l2, visited);
		}

	}

	public ArrayList<Road> getShortestPath(ProblemInstance instance,Location l1,Location l2)
	{
		if(existRoute(instance,l1,l2) == false)
			return null;
		else
		{
			HashMap<Location,Float> costs = new HashMap<>();
			for(Location location : instance.getLocations())
			{
				costs.put(location, Float.MAX_VALUE);
			}
			//costs.put(l1,0f);
			Location smallestLocation = l1;
			ArrayList<Location> availableLocations = new ArrayList<>();
			availableLocations.add(l1);

			ArrayList<Road> availableRoads = instance.getRoadsForLocation(l1);
			ArrayList<Road> usedRoads = new ArrayList<>();
			Road addedRoad = availableRoads.get(0);
			Location aux = null;
			while(availableLocations.contains(l2) == false)
			{
				float smallestCost = Float.MAX_VALUE;
				boolean found = false;
				for(Road road : availableRoads)
				{

					if(availableLocations.contains(road.getFirstEndPoint()))
					{
						aux = road.getSecondEndPoint();
						found = true;
					}
					else if(availableLocations.contains(road.getSecondEndPoint()))
					{
						aux = road.getFirstEndPoint();
						found = true;
					}
					if(found == false)
						break;
					float previousCost = costs.get(aux);
					if(previousCost != Float.MAX_VALUE)
					{
						if(road.getRoadLength()  + previousCost< smallestCost)
						{
							smallestLocation = aux;
							smallestCost = road.getRoadLength() + previousCost;
							addedRoad = road;
						}
					}
					else if(road.getRoadLength() < smallestCost)
					{
						smallestLocation = aux;
						smallestCost = road.getRoadLength() + previousCost;
						addedRoad = road;
					}

					if(previousCost == Float.MAX_VALUE)
						costs.put(aux,road.getRoadLength());
					else
						costs.put(aux,road.getRoadLength() + previousCost);
				}
				if(found == true)
				{
					availableLocations.add(smallestLocation);
					availableRoads.addAll(instance.getRoadsForLocation(smallestLocation));
					usedRoads.add(addedRoad);
				}

			}

			return usedRoads;
		}
	}
/*
	private ArrayList<Road> createMST(ProblemInstance instance, Location l1) {
		ArrayList<Location> locations = new ArrayList<>();
		ArrayList<Road> availableRoads = new ArrayList<>();
		ArrayList<Road> usedRoads = new ArrayList<>();
		locations.add(l1);
		availableRoads.addAll(instance.getRoadsForLocation(l1));
		Road chosenRoad = null;
		do{
			chosenRoad = null;
			double minimumCost = Integer.MAX_VALUE;
			for (Road road : availableRoads) {
				if (road.getRoadLength() < minimumCost &&
						!(locations.contains(road.getFirstEndPoint())
								&& locations.contains(road.getSecondEndPoint()))) {
					minimumCost = road.getRoadLength();
					chosenRoad = road;
				}
			}
			if(chosenRoad != null)
			{
				usedRoads.add(chosenRoad);
				if (locations.contains(chosenRoad.getFirstEndPoint())) {
					locations.add(chosenRoad.getSecondEndPoint());
					availableRoads.addAll(instance.getRoadsForLocation(chosenRoad.getSecondEndPoint()));
				}
				else{
					locations.add(chosenRoad.getFirstEndPoint());
					availableRoads.addAll(instance.getRoadsForLocation(chosenRoad.getFirstEndPoint()));
				}
			}
		}while(chosenRoad != null);

		return usedRoads;
	}
	private ArrayList<Location> DFS(Location start, Location finish, ArrayList<Location> visited, ArrayList<Road> roads)
	{
		if(visited.contains(start))
			return null;
		if(start.equals(finish))
			return new ArrayList<>(Arrays.asList(finish));
		ArrayList<Location> returned = new ArrayList<>();
		ArrayList<Location> aux = new ArrayList<>();

		returned.add(start);
		visited.add(start);

		for(Road road : roads)
		{
			if(road.getFirstEndPoint().equals(start))
				aux = DFS(road.getSecondEndPoint(),finish,visited,roads);
			else if(road.getSecondEndPoint().equals(start))
				aux = DFS(road.getFirstEndPoint(),finish,visited,roads);
			if(aux != null && aux.contains(finish))
			{
				returned.addAll(aux);
				break;
			}
		}
		return returned;
	}
	public HashMap<ArrayList<Location>, ArrayList<Road>> findShortestPath(ProblemInstance instance, Location l1, Location l2)
	{
		ArrayList<Road> roads = createMST(instance,l1);
		ArrayList<Location> locations = DFS(l1,l2,new ArrayList<>(),roads);
		if(locations.contains(l2) == false)
		{
			System.out.println("There is no road from " + l1.toString() + " to " + l2.toString());
			return null;
		}
		HashMap<ArrayList<Location>, ArrayList<Road>> set = new HashMap<>();
		set.put(locations,roads);

		return set;
	}

*/

}