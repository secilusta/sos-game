package org.secil;

import java.util.Scanner;

public class PlayerUser extends IPlayer {

    private Scanner in = new Scanner(System.in);

    public PlayerUser(CellValue playerValue) {
        super(playerValue);
    }

    public void playFromConsole(Board gameBoard) {
        boolean isInputValid = false;
        int row,col;
        do {
            System.out.printf("Player '%s', enter your move (row[1-%d] column[1-%d]): ",
                    getPlayerCellValue(),
                    gameBoard.getSize(),gameBoard.getSize());

            row = in.nextInt() - 1;
            col = in.nextInt() - 1;

            if (gameBoard.isCellValid(row,col) && gameBoard.isCellEmpty(row,col)) {
                isInputValid = true;
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...");
            }
        } while (!isInputValid);

        gameBoard.play(row,col,getPlayerCellValue());
    }

}
