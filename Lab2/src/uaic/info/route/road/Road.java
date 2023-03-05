package uaic.info.route.road;

import java.util.Objects;

import uaic.info.route.location.Location;
import uaic.info.route.types.RoadType;

public abstract class Road {
	
	private String name;
	private Location firstEndPoint;
	private Location secondEndPoint;
	private float speedLimit;
	private float roadLength;
	private RoadType type;
	
	public Road()
	{
		this.name = "";
		this.firstEndPoint = null;
		this.secondEndPoint = null;
		this.type = null;
	}
	public Road(String name, Location first, Location second,float speedLimit, float roadLength)
	{
		this.name = name;
		this.firstEndPoint = first;
		this.secondEndPoint = second;
		this.speedLimit = speedLimit;
		this.type = RoadType.STREET;
		this.roadLength = roadLength;
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

	public float getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(float speedLimit) {
		this.speedLimit = speedLimit;
	}

	public float getRoadLength() {
		return roadLength;
	}

	public void setRoadLength(float roadLength) {
		this.roadLength = roadLength;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Road road = (Road) o;
		return Objects.equals(name, road.name) && Objects.equals(firstEndPoint, road.firstEndPoint) && Objects.equals(secondEndPoint, road.secondEndPoint);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, firstEndPoint, secondEndPoint);
	}

	@Override
	public String toString() {
		return "Road{" +
				"name='" + name + '\'' +
				", firstEndPoint=" + firstEndPoint +
				", secondEndPoint=" + secondEndPoint +
				", speedLimit=" + speedLimit +
				'}';
	}
}
