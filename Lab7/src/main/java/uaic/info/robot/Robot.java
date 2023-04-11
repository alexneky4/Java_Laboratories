package uaic.info.robot;

import uaic.info.components.Token;
import uaic.info.exploration.Exploration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Robot implements Runnable{

    private String name;
    private boolean running = true;
    private Exploration exploration;

    private int currentRow;
    private int currentCol;
    private final int[] directions = {-1, 0, 1};

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

    public void run(){
        while(running)
        {
            if(exploration.getMap().allVisited())
            {
                running = false;
                continue;
            }
            List<Integer> possibleRows = new ArrayList<>();
            List<Integer> possibleCols = new ArrayList<>();
            for(int i = 1; i <= 8; i++)
            {
                int row = currentRow + directions[new Random().nextInt(3)];
                int col = currentCol + directions[new Random().nextInt(3)];

                if(row >= 0 && row < exploration.getMapDimension() && col >= 0 && col < exploration.getMapDimension()
                    && exploration.getMap().isOccupied(row,col) == false)
                {
                    possibleRows.add(row);
                    possibleCols.add(col);
                }
            }
                if(possibleRows.size() > 0)
                {
                    exploration.getMap().moveRobot(currentRow,currentCol);
                    int random = new Random().nextInt(possibleRows.size());
                    currentRow = possibleRows.get(random);
                    currentCol = possibleCols.get(random);

                    if(exploration.getMap().isVisited(currentRow,currentCol) == false)
                    {

                        exploration.getMap().setVisited(currentRow,currentCol,this);
                    }
                }
                try
                {
                    sleep(500);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
        }
    }

    public List<Token> extractTokens(int howMany)
    {
        return exploration.getMemory().extractTokens(howMany);
    }
}
