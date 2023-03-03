package uaic.info.route.road;

import java.util.Objects;

import uaic.info.route.location.Location;
import uaic.info.route.types.RoadType;

public abstract class Road {
	
	private String name;
	private Location firstEndPoint;
	private Location secondEndPoint;
	private RoadType type;
	
	public Road()
	{
		this.name = "";
		this.firstEndPoint = null;
		this.secondEndPoint = null;
		this.type = null;
	}
	
	
	public Road(String name, Location first, Location second)
	{
		this.name = name;
		this.firstEndPoint = first;
		this.secondEndPoint = second;
		this.type = RoadType.STREET;
	}
	
	public Road(String name, Location first, Location second,RoadType type)
	{
		this.name = name;
		this.firstEndPoint = first;
		this.secondEndPoint = second;
		this.type = type;
	}

	public RoadType getType() {
		return type;
	}


	public void setType(RoadType type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getFirstEndPoint() {
		return firstEndPoint;
	}

	public void setFirstEndPoint(Location firstEndPoint) {
		this.firstEndPoint = firstEndPoint;
	}

	public Location getSecondEndPoint() {
		return secondEndPoint;
	}

	public void setSecondEndPoint(Location secondEndPoint) {
		this.secondEndPoint = secondEndPoint;
	}

	@Override
	public String toString() {
		return "Road [name=" + name + ", firstEndPoint=" + firstEndPoint + ", secondEndPoint=" + secondEndPoint +  ", type=" + type + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstEndPoint, name, secondEndPoint);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)	 
			return false;
		if (getClass() != obj.getClass())
			return false;
		Road other = (Road) obj;
		return Objects.equals(name, other.name) && ( Objects.equals(firstEndPoint, other.firstEndPoint)
				&& Objects.equals(secondEndPoint, other.secondEndPoint));
	}

	
}
