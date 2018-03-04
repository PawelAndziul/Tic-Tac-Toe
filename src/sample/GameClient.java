package sample;

import java.io.DataOutputStream;
import java.net.Socket;

public class GameClient {

    private Socket clientSocket;

    public GameClient(String serverIp, int serverPort) {
        try {
            clientSocket = new Socket(serverIp, serverPort);
            System.out.println("Connected to server at: " + serverIp + ":" + serverPort);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void sendMessage(String sentence) {
        try {
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            outputStream.writeBytes(sentence);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
