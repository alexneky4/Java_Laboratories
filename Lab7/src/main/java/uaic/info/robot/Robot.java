package uaic.info.robot;

import uaic.info.components.Token;
import uaic.info.exploration.Exploration;

import java.util.*;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Robot implements Runnable{

    private String name;
    private boolean running = true;
    private Exploration exploration;

    private int currentRow;
    private int currentCol;
    private final int[] directions = {-1, 0, 1};

    private int numberOfColumnVisited = 0;
    public volatile boolean isSleeping = false;

    public Robot(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setExploration(Exploration exploration) {
        this.exploration = exploration;
    }
    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public int getNumberOfColumnVisited() {
        return numberOfColumnVisited;
    }

    public void run() {
        while (running) {

            synchronized (this)
            {
                while(isSleeping)
                {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            if (exploration.getMap().allVisited()) {
                running = false;
                continue;
            }
            /*List<Integer> possibleRows = new ArrayList<>();
            List<Integer> possibleCols = new ArrayList<>();
            for (int i = 1; i <= 8; i++) {
                int row = currentRow + directions[new Random().nextInt(3)];
                int col = currentCol + directions[new Random().nextInt(3)];

                if (row >= 0 && row < exploration.getMapDimension() && col >= 0 && col < exploration.getMapDimension()
                        && !exploration.getMap().isOccupied(row, col)) {
                    possibleRows.add(row);
                    possibleCols.add(col);
                }
            }
            if (possibleRows.size() > 0) {
                exploration.getMap().moveRobot(currentRow, currentCol);
                int random = new Random().nextInt(possibleRows.size());
                currentRow = possibleRows.get(random);
                currentCol = possibleCols.get(random);

                if (!exploration.getMap().isVisited(currentRow, currentCol)) {
                    exploration.getMap().setVisited(currentRow, currentCol, this);
                }
            }*/
            System.out.println("In run");
            int[] nextCell = exploration.getMap().findNearestEmptyCell(currentRow,currentCol);
            exploration.getMap().moveRobot(currentRow,currentCol);
            currentRow = nextCell[0];
            currentCol = nextCell[1];
            if (!exploration.getMap().isVisited(currentRow, currentCol)) {
                exploration.getMap().setVisited(currentRow, currentCol, this);
                numberOfColumnVisited++;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Token> extractTokens(int howMany)
    {
        return exploration.getMemory().extractTokens(howMany);
    }


    public void pause() {
        isSleeping = true;
    }
    public void  pauseWithTime(long duration) {
        isSleeping = true;
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        isSleeping = false;
    }

    public void resume() {
        synchronized (this) {
            isSleeping = false;
        }
    }

}
