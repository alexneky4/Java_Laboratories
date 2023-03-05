package uaic.info.route.location;

public class City extends Location{
	private int population;
	private int area;

	public City(String name, int x, int y, int population, int area) {
		super(name, x, y);
		this.population = population;
		this.area = area;
	}
	public City(String name,int x, int y)
	{
		super(name,x,y);
	}

	public City(String name)
	{
		super(name);
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
