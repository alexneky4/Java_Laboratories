package uaic.info.route.location;

import java.util.Objects;

/**
 *  Abstract class representing the Location. For any new type of location, all it needs to do is
 *  to extend this class
 *
 */
public abstract class Location {

	private String name;
	private double x;
	private double y;
	
	public Location()
	{
		this.name = "";
		this.x = 0;
		this.y = 0;
	}
	
	public Location(String name)
	{
		this.name = name;
		this.x = 0;
		this.y = 0;
	}
	
	public Location(String name, double x, double y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Location [name=" + name + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, x, y);
	}

	/**
	 * This method verifies if the given object is equal with the current Location. They are equals
	 * if they have the same name and are at the same coordinates
	 * @param obj
	 * @return true if the object is equal to the Location, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(name, other.name) && (x == other.x && y == other.y);
	}

	
}
