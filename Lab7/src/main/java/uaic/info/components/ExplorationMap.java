package uaic.info.components;

import uaic.info.robot.Robot;

import java.util.Arrays;
import java.util.List;

public class ExplorationMap {

    private final Cell[][] matrix;
    private final int dimension;

    public ExplorationMap(int n)
    {
        matrix = new Cell[n][n];
        dimension = n;
        for(Cell[] row : matrix)
            Arrays.fill(row,null);
    }

    public boolean allVisited()
    {
        for(Cell[] row : matrix)
            for(Cell cell : row)
                if(cell == null)
                    return false;
        return true;
    }

    public synchronized boolean isOccupied(int row, int column)
    {
        if(matrix[row][column] == null) return false;
        return  matrix[row][column].getRobotOccupant() != null;
    }

    public synchronized boolean isVisited(int row,int column)
    {
        return matrix[row][column] != null;
    }

    public synchronized void moveRobot(int row,int column)
    {
        matrix[row][column].setRobotOccupant(null);
    }

    public synchronized void setVisited(int row, int column, Robot robot)
    {

        List<Token> extractedTokens= robot.extractTokens(dimension);
        Cell cell = new Cell(extractedTokens);
        cell.setRobotOccupant(robot);
        matrix[row][column] = cell;
        System.out.println("Celula [" + row + "][" + column + "] a fost vizitata de catre robotul " + robot.getName());
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        return "ExplorationMap{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
}
