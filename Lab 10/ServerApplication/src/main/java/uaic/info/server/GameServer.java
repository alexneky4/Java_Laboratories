package uaic.info.server;

import uaic.info.client.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    public static final int PORT = 8100;

    public GameServer() throws IOException
    {
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(PORT);
            while(true)
            {
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            serverSocket.close();
        }
    }
    public static void main(String[] args) throws IOException{
        GameServer server = new GameServer();
    }
}
