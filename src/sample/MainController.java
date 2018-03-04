package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    Button buttonA1;
    @FXML
    Button buttonA2;
    @FXML
    Button buttonA3;
    @FXML
    Button buttonB1;
    @FXML
    Button buttonB2;
    @FXML
    Button buttonB3;
    @FXML
    Button buttonC1;
    @FXML
    Button buttonC2;
    @FXML
    Button buttonC3;
    @FXML
    Button buttonConnect;
    @FXML
    Button buttonRestart;
    @FXML
    Button buttonStartServer;
    @FXML
    TextField textClientIP;
    @FXML
    TextField textServerPort;

    private TicTacToe game = new TicTacToe();
    private GameClient client;
    private GameServer gameServer;

    @FXML
    private void buttonA1Clicked() {
        buttonClicked(0, 0, "A1");
    }

    @FXML
    private void buttonA2Clicked() {
        buttonClicked(0, 1, "A2");

    }

    @FXML
    private void buttonA3Clicked() {
        buttonClicked(0, 2, "A3");
    }

    @FXML
    private void buttonB1Clicked() {
        buttonClicked(1, 0, "B1");
    }

    @FXML
    private void buttonB2Clicked() {
        buttonClicked(1, 1, "B2");
    }

    @FXML
    private void buttonB3Clicked() {
        buttonClicked(1, 2, "B3");
    }

    @FXML
    private void buttonC1Clicked() {
        buttonClicked(2, 0, "C1");
    }

    @FXML
    private void buttonC2Clicked() {
        buttonClicked(2, 1, "C2");
    }

    @FXML
    private void buttonC3Clicked() {
        buttonClicked(2, 2, "C3");
    }

    @FXML
    private void buttonConnectClicked() {
        String[] serverData = textClientIP.getText().split(":");
        if (serverData.length != 2)
            return;

        client = new GameClient(serverData[0], Integer.valueOf(serverData[1]));
    }

    @FXML
    private void buttonRestartClicked() {
        game = new TicTacToe();
        setAllButtonsText("");
    }

    @FXML
    private void buttonStartServerClicked() {
        startServer();
    }

    private void buttonClicked(int row, int col, String message) {
        if (game.clickArea(row, col)) {
            Button button = null;
            int buttonId = row * 10 + col;
            switch (buttonId) {
                case 0:
                    button = buttonA1;
                    break;
                case 1:
                    button = buttonA2;
                    break;
                case 2:
                    button = buttonA3;
                    break;
                case 10:
                    button = buttonB1;
                    break;
                case 11:
                    button = buttonB2;
                    break;
                case 12:
                    button = buttonB3;
                    break;
                case 20:
                    button = buttonC1;
                    break;
                case 21:
                    button = buttonC2;
                    break;
                case 22:
                    button = buttonC3;
                    break;
            }

            button.setText(game.getAreaValue(row, col));

            if (client != null)
                client.sendMessage(message);

            if (game.isGameOver()) {
                announceWinner(game.getWinner());
            }
        }
    }

    private void announceWinner(String winner) {
        setAllButtonsText("");
        buttonB2.setText("Winner:\n" + winner);
    }

    private void startServer() {
        int port;
        try {
            port = Integer.valueOf(textServerPort.getText());
        } catch (Exception ex) {
            System.out.println("Not a valid port!");
            return;
        }

        gameServer = new GameServer(port);
        gameServer.start();
    }


    private void setAllButtonsText(String textToSet) {
        buttonA1.setText(textToSet);
        buttonA2.setText(textToSet);
        buttonA3.setText(textToSet);

        buttonB1.setText(textToSet);
        buttonB2.setText(textToSet);
        buttonB3.setText(textToSet);

        buttonC1.setText(textToSet);
        buttonC2.setText(textToSet);
        buttonC3.setText(textToSet);
    }

}
