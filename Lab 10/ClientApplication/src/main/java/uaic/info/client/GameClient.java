package uaic.info.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        while(true)
        {
            System.out.println("Alegeti una dintre optiuni:");
            System.out.println("1.Create game");
            System.out.println("2.Join game");
            System.out.println("3.Submit move");
            System.out.println("4.Stop");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            try (
                    Socket socket = new Socket(serverAddress, PORT);
                    PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader (
                            new InputStreamReader(socket.getInputStream())) ) {
                String request = null;
                switch (option)
                {
                    case 1: request = "create game"; break;
                    case 2: request = "join game"; break;
                    case 3: request = "submit move"; break;
                    case 4: request = "stop"; break;
                }
                out.println(request);
                String response = in.readLine ();
                System.out.println(response);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            if(option == 4)
                break;
        }

    }
}
