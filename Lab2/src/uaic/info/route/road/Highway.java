package uaic.info.route.road;

import uaic.info.route.location.Location;

public class Highway extends Road{
	private float timeDelay;
	
	public Highway() {
		super();
	}

	public Highway(String name, Location first, Location second, double speedLimit, double roadLength) {
		super(name, first, second, speedLimit, roadLength);
	}

	public Highway(String name, Location first, Location second, double speedLimit, double roadLength, float timeDelay) {
		super(name, first, second, speedLimit, roadLength);
		this.timeDelay = timeDelay;
	}

	public float getTimeDelay() {
		return timeDelay;
	}


	public void setTimeDelay(float timeDelay) {
		this.timeDelay = timeDelay;
	}
	
}
