package uaic.info.components;

import uaic.info.robot.Robot;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public synchronized boolean allVisited()
    {
        for(Cell[] row : matrix)
            for(Cell cell : row)
                if(cell == null)
                    return false;
        return true;
    }
    public Cell[][] getMatrix() {
        return matrix;
    }

    public int getDimension() {
        return dimension;
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
        System.out.println("Cell [" + row + "][" + column + "] has been visited by " + robot.getName());
    }

    public synchronized int[] findNearestEmptyCell(int row, int column)
    {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row,column});
        boolean visited[][] = new boolean[dimension][dimension];
        for(boolean[] rowMatrix : visited)
            Arrays.fill(rowMatrix,false);

        while(!queue.isEmpty())
        {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
                if(matrix[r][c] == null)
                    return cell;
            if(visited[r][c] == true)
                continue;
            visited[r][c] = true;
            if (r > 0 && visited[r-1][c] == false ) {
                queue.offer(new int[]{r-1, c});
            }
            if (r < dimension-1 && visited[r+1][c] == false) {
                queue.offer(new int[]{r+1, c});
            }
            if (c > 0 && visited[r][c-1] == false) {
                queue.offer(new int[]{r, c-1});
            }
            if (c < dimension-1 && visited[r][c+1] == false) {
                queue.offer(new int[]{r, c+1});
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ExplorationMap{" +
                "matrix=" + Arrays.toString(matrix) +
                '}';
    }
}
