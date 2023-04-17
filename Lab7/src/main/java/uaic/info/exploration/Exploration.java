package uaic.info.exploration;

import uaic.info.components.ExplorationMap;
import uaic.info.components.SharedMemory;
import uaic.info.robot.Robot;
import uaic.info.robot.Supervisor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exploration {
    static private final int MAP_DIMENSION = 10;
    private final SharedMemory memory = new SharedMemory(MAP_DIMENSION);
    private final ExplorationMap map = new ExplorationMap(MAP_DIMENSION);
    private final List<Robot> robots = new ArrayList<>();
    private final Supervisor supervisor = new Supervisor(this);

    public int getMapDimension()
    {
        return MAP_DIMENSION;
    }

    public SharedMemory getMemory() {
        return memory;
    }

    public ExplorationMap getMap() {
        return map;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    private void addRobot(Robot robot)
    {
        robots.add(robot);
        int row = new Random().nextInt(MAP_DIMENSION);
        int col = new Random().nextInt(MAP_DIMENSION);
        while(map.isOccupied(row,col))
        {
            row = new Random().nextInt(MAP_DIMENSION);
            col = new Random().nextInt(MAP_DIMENSION);
        }
        robot.setExploration(this);
        map.setVisited(row,col,robot);
        robot.setCurrentRow(row);
        robot.setCurrentCol(col);
    }
    private void start() {
        List<Thread> threads = new ArrayList<>();
        new Thread(supervisor).start();
        for(Robot robot : robots)
        {
            Thread t = new Thread(robot);
            threads.add(t);
            t.start();
        }

    }

    public static void main(String args[])
    {
        var explore = new Exploration();
        explore.addRobot(new Robot("robot1"));

        explore.start();

    }

}
