package uaic.info.client;

import uaic.info.game.Game;
import uaic.info.game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientThread extends Thread{

    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;

    private Game game;
    private Player player;
    private static List<Game> availableGames = new LinkedList<>();
    public ClientThread(Socket socket)
    {
        this.socket = socket;
        game = null;
    }

    public void run()
    {
        while(true)
        {
            try{
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
//                String response = "Server received the command: ";
                String request = in.readLine();
                if(request.equals("stop"))
                {
                    out.println("Server stopped");
                    out.flush();
                    break;
                }
                commandExecutor(request);

            } catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
        try
        {
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("finished");
    }

    private synchronized Game connectToGame()
    {

        return availableGames.isEmpty() ? null : availableGames.remove(0);
    }

    public void commandExecutor(String response)
    {
        response = response.trim().toLowerCase();
        if(game != null && game.isGameFinished() == true)
            game = null;
        if(response.equals("create game")) {
            createGame();
        }
        else if(response.equals("join game")) {
             joinGame();
        }
        else if(response.equals("place piece")) {
             placePiece();
        }
        else if(response.equals("show board"))
        {
            game.displayBoard(out);
        }
        else
        {
            out.println("Not a valid command");
            out.flush();
        }
    }

    private synchronized void createGame()
    {
        if(game != null && game.isGameFinished() == false)
        {
            out.println("You are still inside a game");
            out.flush();
            return;
        }
        try {
            out.println("Give the name of the player:");
            out.flush();
            String name = in.readLine();
            this.player = new Player(name, this.socket);
            this.game = new Game(player);
            availableGames.add(game);
            out.println("Waiting for the second player to connect");
            out.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private synchronized void joinGame()
    {
        if(game != null && game.isGameFinished() == false)
        {
            out.println("You are still inside a game");
            out.flush();
            return;
        }
        try{
            this.game = connectToGame();
            if(game == null)
            {
                out.println("There is no game you can join");
                out.flush();
                return;
            }
            out.println("Give the name of the player:");
            out.flush();
            String name = in.readLine();
            this.player = new Player(name, this.socket);

            this.game.setPlayer2(player);
            out.println("You connected successfully. The game will start");
            out.flush();
            PrintWriter outOtherPlayer =  new PrintWriter(game.getPlayer1().getSocket().getOutputStream());
            outOtherPlayer.println("The game will start");
            outOtherPlayer.flush();
            game.startGame();
            game.displayBoard(out);
            game.displayBoard(outOtherPlayer);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private synchronized void placePiece()
    {
        if(game == null)
        {
            out.println("You are not inside a game");
            out.flush();
            return;
        }
        if(game.isFirstTurn() == false && game.getPlayer1().equals(player))
        {
            out.println("Not your turn");
            out.flush();
            return;
        }
        if(game.isFirstTurn() == true && game.getPlayer2().equals(player))
        {
            out.println("Not your turn");
            out.flush();
            return;
        }
        try {
            int currentPlayer;
            out.println("Give the row and column number of where you want to place the piece");
            out.flush();
            String response = in.readLine();
            String[] coord = response.trim().split(" ");
            int row,col;
            try {
                row = Integer.parseInt(coord[0]);
                col = Integer.parseInt(coord[1]);
                if (game.getBoard().validMove(row, col) == false) {
                    out.println("This move is not valid! Please insert valid row and column numbers");
                    out.flush();
                }
                else {
                    game.makeMove(row,col);
                    PrintWriter outOtherPlayer;
                    if(game.getPlayer1().equals(player)) {
                       outOtherPlayer =  new PrintWriter(game.getPlayer2().getSocket().getOutputStream());
                       currentPlayer = 1;
                    }
                    else
                    {
                       outOtherPlayer =  new PrintWriter(game.getPlayer1().getSocket().getOutputStream());
                       currentPlayer = 0;
                    }
                    if(game.isGameFinished() == true)
                    {
                        out.println("Game is Finished! You won");
                        out.flush();
                        outOtherPlayer.println("Game is Finished! You lost");
                        outOtherPlayer.flush();
                        return;
                    }
                    game.getTimer().changeCurrentPlayer(currentPlayer);
                    game.displayBoard(out);
                    outOtherPlayer.println("Now it's your turn");
                    outOtherPlayer.flush();
                    game.displayBoard(outOtherPlayer);
                }
            } catch (NumberFormatException e)
            {
                out.println("This move is not valid! Please insert valid row and column numbers");
                out.flush();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
