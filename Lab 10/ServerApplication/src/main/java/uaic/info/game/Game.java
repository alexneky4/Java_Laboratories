package uaic.info.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;

    private boolean firstTurn;

    public Game(Player player1) {
        this.board = new Board(19);
        this.player1 = player1;
        firstTurn = true;
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(19);
        firstTurn = true;
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

    public boolean makeMove(int row, int col)
    {
        if(board.validMove(row,col) == false)
            return false;
        if(firstTurn == true)
            board.makeMove(1,row,col);
        else board.makeMove(2,row,col);
        return true;
    }

    public void startGame() {
        try {
            BufferedReader[] inPlayers = new BufferedReader[2];
            inPlayers[0] = new BufferedReader(
                    new InputStreamReader(player1.getSocket().getInputStream()));
            inPlayers[1] = new BufferedReader(
                    new InputStreamReader(player2.getSocket().getInputStream()));

            PrintWriter[] outPlayers = new PrintWriter[2];
            outPlayers[0] = new PrintWriter(player1.getSocket().getOutputStream());
            outPlayers[1] = new PrintWriter(player2.getSocket().getOutputStream());

            while(true)
            {
                int index;
                board.displayBoard(outPlayers[0]);
                board.displayBoard(outPlayers[1]);
                if(firstTurn == true)
                    index = 0;
                else index = 1;
                outPlayers[index].println("Please give the row and column number where you want to put the next piece");
                String response = inPlayers[index].readLine();
                int row = -1;
                int col = -1;
                while(response == null)
                    response = inPlayers[index].readLine();
                String[] coord = response.split(" ");
                while(row == -1 || col == -1)
                {
                    try
                    {
                        row = Integer.parseInt(coord[0]);
                        col = Integer.parseInt(coord[1]);
                        if(board.validMove(row,col) == false)
                        {
                            outPlayers[index].println("This move is not valid! Please insert valid row and column numbers");
                            row = -1;
                            col = -1;
                        }

                    }
                    catch(NumberFormatException e)
                    {
                        outPlayers[index].println("This move is not valid! Please insert valid row and column numbers");
                        row = -1;
                        col = -1;
                    }
                }

                board.makeMove(1,row,col);
                outPlayers[index % 2].println("Wait for the other player to move");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
