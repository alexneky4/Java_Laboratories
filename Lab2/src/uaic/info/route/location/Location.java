package uaic.info.route.location;

import java.util.Objects;

public abstract class Location {

	private String name;
	private int x;
	private int y;
	
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
	
	public Location(String name, int x, int y)
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
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
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
