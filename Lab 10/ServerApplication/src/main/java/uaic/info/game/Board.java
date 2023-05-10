package uaic.info.game;

import java.io.PrintWriter;
import java.util.Arrays;

public class Board {

    private int[][] board;

    public Board(int dimension) {
        board = new int[dimension][dimension];
        for (int[] row : board)
            Arrays.fill(row, 0);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void makeMove(int piece,int row, int col)
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
            for(int j = 0; j < board[0].length; j++)
                out.print(board[i][j] + " ");
            out.println();
        }
    }
}
