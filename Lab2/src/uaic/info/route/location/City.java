package uaic.info.route.location;

public class City extends Location{
	private int population;
	private int area;
	
	public City(int population, int area) {
		super();
		this.population = population;
		this.area = area;
	}
	public City() {
		super();
	}
	
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}

	
	
}
