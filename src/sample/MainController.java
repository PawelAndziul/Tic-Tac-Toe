package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    private String turn = "player1";
    private Boolean gameLocked = false;

    @FXML
    private void buttonA1Clicked() {
        if (click(turn, buttonA1))
            changeTurn();
    }

    @FXML
    private void buttonA2Clicked() {
        if (click(turn, buttonA2))
            changeTurn();
    }

    @FXML
    private void buttonA3Clicked() {
        if (click(turn, buttonA3))
            changeTurn();
    }

    @FXML
    private void buttonB1Clicked() {
        if (click(turn, buttonB1))
            changeTurn();
    }

    @FXML
    private void buttonB2Clicked() {
        if (click(turn, buttonB2))
            changeTurn();
    }

    @FXML
    private void buttonB3Clicked() {
        if (click(turn, buttonB3))
            changeTurn();
    }

    @FXML
    private void buttonC1Clicked() {
        if (click(turn, buttonC1))
            changeTurn();
    }

    @FXML
    private void buttonC2Clicked() {
        if (click(turn, buttonC2))
            changeTurn();
    }

    @FXML
    private void buttonC3Clicked() {
        if (click(turn, buttonC3))
            changeTurn();
    }

    @FXML
    private void buttonConnectClicked() {
        throw new NotImplementedException();
    }

    @FXML
    private void buttonRestartClicked() {
        setAllButtonsText("");
        turn = "player1";
        gameLocked = false;
    }

    private boolean click(String turn, Button button) {
        if (gameLocked == false && button.getText().equals("")) {
            if (turn.equals("player1")) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            checkWinner();
            return true;
        }
        return false;
    }

    private void changeTurn() {
        if (turn.equals("player1"))
            turn = "player2";
        else
            turn = "player1";
    }

    private void checkWinner() {
        String[][] game = new String[3][3];
        game[0][0] = buttonA1.getText();
        game[0][1] = buttonA2.getText();
        game[0][2] = buttonA3.getText();

        game[1][0] = buttonB1.getText();
        game[1][1] = buttonB2.getText();
        game[1][2] = buttonB3.getText();

        game[2][0] = buttonC1.getText();
        game[2][1] = buttonC2.getText();
        game[2][2] = buttonC3.getText();

        checkCombination(game);
    }

    private void checkCombination(String[][] game) {
        for (int row = 0; row < 3; row++) {
            if (game[row][0].equals(game[row][1]) && game[row][1].equals(game[row][2]) && !game[row][0].equals(""))
                endGame();
        }

        for (int col = 0; col < 3; col++) {
            if (game[0][col].equals(game[1][col]) && game[1][col].equals(game[2][col]) && !game[0][col].equals(""))
                endGame();
        }

        if (!game[1][1].equals("")) {
            if (game[0][0].equals(game[1][1]) && game[1][1].equals(game[2][2]))
                endGame();
            if (game[2][0].equals(game[1][1]) && game[1][1].equals(game[0][2]))
                endGame();
        }
    }

    private void endGame() {
        buttonA1.setText("");
        buttonA2.setText("");
        buttonA3.setText("");

        buttonB1.setText("");
        buttonB2.setText(turn + " win");
        buttonB3.setText("");

        buttonC1.setText("");
        buttonC2.setText("");
        buttonC3.setText("");

        gameLocked = true;
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
