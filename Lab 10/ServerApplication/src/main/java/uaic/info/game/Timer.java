package uaic.info.game;

import java.io.IOException;
import java.io.PrintWriter;

public class Timer extends Thread {
    private final static long MAX_TIME = 1000 * 60;
    private volatile int currentPlayer;
    private long timeLeft[] = new long[2];
    private Game game;

    Timer(Game game) {
        this.game = game;
        timeLeft[0] = MAX_TIME;
        timeLeft[1] = MAX_TIME;
    }

    public void run() {
        try {
            currentPlayer = 0;
            PrintWriter outPlayer1 = new PrintWriter(game.getPlayer1().getSocket().getOutputStream());
            PrintWriter outPlayer2 = new PrintWriter(game.getPlayer2().getSocket().getOutputStream());
            while(timeLeft[currentPlayer] > 0) {
                if(timeLeft[currentPlayer] % (60 * 1000) == 0) {
                    if(currentPlayer == 0) {
                        outPlayer1.println("You have " + timeLeft[currentPlayer] / (1000 * 60) + " minutes left!");
                        outPlayer1.flush();
                    }
                    else if(currentPlayer == 1)
                    {
                        outPlayer2.println("You have " + timeLeft[currentPlayer] / (1000 * 60) + " minutes left!");
                        outPlayer2.flush();
                    }
                }
                if(timeLeft[currentPlayer] < 1000 * 60 && timeLeft[currentPlayer] % 5000 == 0) {
                    if(currentPlayer == 0) {
                        outPlayer1.println("You have " + timeLeft[currentPlayer] / 1000 + " seconds left!");
                        outPlayer1.flush();
                    }
                    else if(currentPlayer == 1)
                    {
                        outPlayer2.println("You have " + timeLeft[currentPlayer] / 1000 + " seconds left!");
                        outPlayer2.flush();
                    }
                }
                timeLeft[currentPlayer] = timeLeft[currentPlayer] - 100;
                synchronized (this) {
                    wait(100);
                }
            }
            game.outOfTime(currentPlayer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public long getTimeLeft(int player) {
        return timeLeft[player] / 1000;
    }

    public void changeCurrentPlayer(int player) {
        currentPlayer = player;
    }
}