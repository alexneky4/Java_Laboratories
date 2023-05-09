package uaic.info.game;

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
        return board[row][col] == 0;
    }
}
