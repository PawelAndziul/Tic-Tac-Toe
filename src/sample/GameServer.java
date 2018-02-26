package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {
    ServerSocket serverSocket;

    public GameServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port: " + port);
        } catch (Exception ex) {
            System.out.println("Could not start a server");
        }
    }

    public void run() {
        if (serverSocket == null)
            return;

        waitForClientConnection();
    }

    private void waitForClientConnection() {
        Socket client = null;
        try {
            client = serverSocket.accept();
            System.out.println("Client connected");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            while (!client.isClosed()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String sentence = reader.readLine();
                System.out.println("Client: " + sentence);

                wait(200);
            }
        } catch (Exception ex) {
            System.out.println("Client disconnected");
        }
    }
}
