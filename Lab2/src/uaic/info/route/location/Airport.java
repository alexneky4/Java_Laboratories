package uaic.info.route.location;

public class Airport extends Location{
	private int numberOfAirstrips;
	private int area;
	
	
	public Airport() {
		super();
	}

	public Airport(String name) {
		super(name);
	}

	public Airport(String name, int x, int y) {
		super(name, x, y);
	}

	public Airport(String name, int x, int y, int numberOfAirstrips, int area) {
		super(name, x, y);
		this.numberOfAirstrips = numberOfAirstrips;
		this.area = area;
	}

	public int getNumberOfAirstrips() {
		return numberOfAirstrips;
	}
	public void setNumberOfAirstrips(int numberOfAirstrips) {
		this.numberOfAirstrips = numberOfAirstrips;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	
	
}
