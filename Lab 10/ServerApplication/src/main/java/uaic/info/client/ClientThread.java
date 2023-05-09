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

public class ClientThread extends Thread{

    private Socket socket = null;

    private Game game;
    private Player player;
    private static final List<Game> availableGames = new LinkedList<>();
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
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());
//                String response = "Server received the command: ";
                String request = in.readLine();
                while(request == null)
                    request = in.readLine();
//                response += request;
                switch (request)
                {
                    case "create game" :
                    {
                        out.println("Give the name of the player:");
                        out.flush();
                        String name = in.readLine();
                        in.readLine();
                        this.player = new Player(name);
                        this.game = new Game(player);
                        availableGames.add(game);
                        out.println("Waiting for the second player to connect");
                        while(true)
                        {
                            if(game.getPlayer2() != null)
                                break;
                        }
                        out.println("The game can start");
//                        game.startGame();
                    }
                }
//                if(request.equals("stop"))
//                {
//                    response = "Server stopped";
//                    out.println(response);
//                    out.flush();
//                    break;
//                }
//                out.println(response);
//                out.flush();
                    break;
            } catch (IOException e){
                e.printStackTrace();
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
}
