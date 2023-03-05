package uaic.info.route.road;

import uaic.info.route.location.Location;

public class Street extends Road{
	private int numberOfTrafficLights;

	public Street() {
		super();
	}

	public Street(String name, Location first, Location second, float speedLimit, float roadLength) {
		super(name, first, second, speedLimit, roadLength);
	}

	public Street(String name, Location first, Location second, float speedLimit, float roadLength, int numberOfTrafficLights) {
		super(name, first, second, speedLimit, roadLength);
		this.numberOfTrafficLights = numberOfTrafficLights;
	}

	public int getNumberOfTrafficLights() {
		return numberOfTrafficLights;
	}

	public void setNumberOfTrafficLights(int numberOfTrafficLights) {
		this.numberOfTrafficLights = numberOfTrafficLights;
	}
	
	
	
}
