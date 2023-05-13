package uaic.info.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.exit;

public class GameClient {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            AtomicBoolean serverClosed = new AtomicBoolean(false);
            Thread inputThread = new Thread(() -> {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                        System.out.flush();
                    }
                } catch (IOException e) {
                    System.out.println("The server has closed!");
                }
                serverClosed.set(true);
            });

            inputThread.start();

            while (!serverClosed.get()) {
                String command = reader.readLine();
                if (command.equals("exit")) {
                    socket.close();
                    System.out.println("Client exited");
                    exit(0);
                }

                out.println(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




