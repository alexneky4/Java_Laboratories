package uaic.info.route.road;

public class Street extends Road{
	private int maximSpeed;
	private int numberOfTrafficLights;
	
	public Street(int maximSpeed, int numberOfTrafficLights) {
		super();
		this.maximSpeed = maximSpeed;
		this.numberOfTrafficLights = numberOfTrafficLights;
	}

	public Street() {
		super();
	}

	public int getMaximSpeed() {
		return maximSpeed;
	}

	public void setMaximSpeed(int maximSpeed) {
		this.maximSpeed = maximSpeed;
	}

	public int getNumberOfTrafficLights() {
		return numberOfTrafficLights;
	}

	public void setNumberOfTrafficLights(int numberOfTrafficLights) {
		this.numberOfTrafficLights = numberOfTrafficLights;
	}
	
	
	
}
