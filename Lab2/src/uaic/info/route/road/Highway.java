package uaic.info.route.road;

public class Highway extends Road{
	private float maximSpeed;
	private float timeDelay;
	
	public Highway() {
		super();
	}


	public Highway(float maximSpeed,float timeDelay) {
		super();
		this.maximSpeed = maximSpeed;
		this.timeDelay = timeDelay;
	}


	public float getTimeDelay() {
		return timeDelay;
	}


	public void setTimeDelay(float timeDelay) {
		this.timeDelay = timeDelay;
	}


	public float getMaximSpeed() {
		return maximSpeed;
	}


	public void setMaximSpeed(float maximSpeed) {
		this.maximSpeed = maximSpeed;
	}
	
	
	
}
