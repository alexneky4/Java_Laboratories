package uaic.info.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;

    private AtomicBoolean firstTurn = new AtomicBoolean(true);
    private AtomicBoolean isFinished = new AtomicBoolean(false);
    private Timer timer = new Timer(this);

    public Game(Player player1) {
        this.board = new Board(19);
        this.player1 = player1;
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(19);
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void makeMove(int row, int col)
    {
        if(firstTurn.get() == true)
            board.makeMove(1,row,col);
        else board.makeMove(2,row,col);
        if(gameFinished(row,col))
        {
            isFinished.set(true);
        }
        firstTurn.set(!firstTurn.get());
    }

    public boolean isFirstTurn() {
        return firstTurn.get();
    }

    public boolean isGameFinished()
    {
        return isFinished.get();
    }
    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn.set(firstTurn);
    }

    public void startGame() {
        timer.start();
    }

    public boolean gameFinished(int row, int col)
    {
        if(board.isFull())
            return true;
        int player;
        if(firstTurn.get() == true)
            player = 1;
        else
            player = 2;
        //Check horizontally
        int count = 1;
        int left = col - 1;
        int right = col + 1;
        while(left >= 0 && board.getBoard()[row][left] == player)
        {
            count++;
            left--;
        }
        while(right < board.getDimension() && board.getBoard()[row][right] == player)
        {
            count++;
            right++;
        }
        if(count >= 5)
            return true;
        //Check vertically
        count = 1;
        int up = row - 1;
        int down = row + 1;
        while(up >= 0 && board.getBoard()[up][col] == player)
        {
            count++;
            up--;
        }
        while(down < board.getDimension() && board.getBoard()[down][col] == player)
        {
            count++;
            down++;
        }
        if(count >= 5)
            return true;

        count = 1;
        int diagUpLeftRow = row - 1;
        int diagUpLeftCol = col - 1;
        while (diagUpLeftRow >= 0 && diagUpLeftCol >= 0 && board.getBoard()[diagUpLeftRow][diagUpLeftCol] == player) {
            count++;
            diagUpLeftRow--;
            diagUpLeftCol--;
        }
        int diagDownRightRow = row + 1;
        int diagDownRightCol = col + 1;
        while (diagDownRightRow < board.getDimension() && diagDownRightCol < board.getDimension()
                && board.getBoard()[diagDownRightRow][diagDownRightCol] == player) {
            count++;
            diagDownRightRow++;
            diagDownRightCol++;
        }
        if (count >= 5) {
            return true;
        }

        // Check diagonal (down-left to up-right)
        count = 1;
        int diagDownLeftRow = row + 1;
        int diagDownLeftCol = col - 1;
        while (diagDownLeftRow <  board.getDimension() && diagDownLeftCol >= 0 &&
                board.getBoard()[diagDownLeftRow][diagDownLeftCol] == player) {
            count++;
            diagDownLeftRow++;
            diagDownLeftCol--;
        }
        int diagUpRightRow = row - 1;
        int diagUpRightCol = col + 1;
        while (diagUpRightRow >= 0 && diagUpRightCol <  board.getDimension() &&
                board.getBoard()[diagUpRightRow][diagUpRightCol] == player) {
            count++;
            diagUpRightRow--;
            diagUpRightCol++;
        }

        return count >= 5;
    }

    public synchronized void displayBoard(PrintWriter out)
    {
        board.displayBoard(out);
    }

    public Timer getTimer() {
        return timer;
    }
    public void outOfTime(int playerWhoLost) {
        isFinished.set(true);
        try {

            PrintWriter outPlayer1 = new PrintWriter(player1.getSocket().getOutputStream());
            PrintWriter outPlayer2 = new PrintWriter(player2.getSocket().getOutputStream());
            if (playerWhoLost == 1) {
                outPlayer1.println("You lost because you ran out of time!");
                outPlayer1.flush();
                outPlayer2.println("You won because your opponent ran out of time");
                outPlayer2.flush();
            } else {
                outPlayer2.println("You lost because you ran out of time!");
                outPlayer2.flush();
                outPlayer1.println("You won because your opponent ran out of time");
                outPlayer1.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
