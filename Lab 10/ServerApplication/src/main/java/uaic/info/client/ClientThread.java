package uaic.info.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{

    private Socket socket = null;
    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    public void run()
    {
        while(true)
        {
            try{
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String response = "Server received the command: ";
                String request = in.readLine();
                while(request == null)
                    request = in.readLine();
                response += request;
                if(request.equals("stop"))
                {
                    response = "Server stopped";
                    out.println(response);
                    out.flush();
                    break;
                }
                out.println(response);
                out.flush();

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
