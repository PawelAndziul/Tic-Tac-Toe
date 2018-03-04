package sample;

public class TicTacToe {
    private String turn;
    private Boolean gameOver;
    private String winner;

    private String[][] gameState;

    public TicTacToe() {
        gameState = new String[3][3];
        turn = "player1";
        gameOver = false;
        winner = "N/A";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameState[i][j] = "";
            }
        }
    }

    public void changeTurn() {
        if (turn.equals("player1"))
            turn = "player2";
        else
            turn = "player1";
    }

    public boolean clickArea(int row, int col) {
        if (gameOver)
            return false;

        if (!gameState[row][col].equals("")) {
            return false;
        } else {
            if (turn.equals("player1")) {
                gameState[row][col] = "X";
            } else {
                gameState[row][col] = "O";
            }
        }

        checkWinner();

        if (gameOver == false)
            changeTurn();

        return true;
    }

    public void checkWinner() {
        for (int row = 0; row < 3; row++) {
            if (gameState[row][0].equals(gameState[row][1]) && gameState[row][1].equals(gameState[row][2]) && !gameState[row][0].equals(""))
                endGame();
        }

        for (int col = 0; col < 3; col++) {
            if (gameState[0][col].equals(gameState[1][col]) && gameState[1][col].equals(gameState[2][col]) && !gameState[0][col].equals(""))
                endGame();
        }

        if (!gameState[1][1].equals("")) {
            if (gameState[0][0].equals(gameState[1][1]) && gameState[1][1].equals(gameState[2][2]))
                endGame();
            if (gameState[2][0].equals(gameState[1][1]) && gameState[1][1].equals(gameState[0][2]))
                endGame();
        }
    }

    private void endGame() {
        gameOver = true;
        winner = turn;
    }

    public String getWinner() {
        return winner;
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public void printState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState[i][j].equals(""))
                    System.out.print("*");
                else
                    System.out.print(gameState[i][j]);
            }
            System.out.println("");
        }
    }

    public String getAreaValue(int row, int col) {
        if (row < 0 || row > 3 || col < 0 || col > 3)
            return null;

        return gameState[row][col];
    }
}
