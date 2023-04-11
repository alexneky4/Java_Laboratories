package uaic.info.components;

import uaic.info.robot.Robot;

import java.util.List;

public class Cell {

    private final List<Token> tokenList;
    private Robot robotOccupant = null;

    public Cell(List<Token> tokenList)
    {
        this.tokenList = tokenList;
    }

    public Robot getRobotOccupant() {
        return robotOccupant;
    }

    public void setRobotOccupant(Robot robotOccupant) {
        this.robotOccupant = robotOccupant;
    }
}
