package uaic.info.game;

import java.io.PrintWriter;
import java.util.Arrays;

public class Board {

    private int[][] board;
    private int dimension;

    public Board(int dimension) {
        board = new int[dimension][dimension];
        for (int[] row : board)
            Arrays.fill(row, 0);
        this.dimension = dimension;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void makeMove(int piece, int row, int col)
    {
        board[row][col] = piece;
    }

    public boolean validMove(int row, int col)
    {
        if(row < 0 || row >= board.length)
            return false;
        if(col < 0 || col >= board[0].length)
            return true;
        return board[row][col] == 0;
    }

    public void displayBoard(PrintWriter out)
    {

        for(int i = 0; i < board.length; i++)
        {
            String line = "";
            for(int j = 0; j < board[i].length; j++)
               line = line + board[i][j] + " ";
            out.println(line);
            out.flush();
        }
    }

    public boolean isFull()
    {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                if(board[i][j] == 0)
                    return false;
        return true;
    }
}
