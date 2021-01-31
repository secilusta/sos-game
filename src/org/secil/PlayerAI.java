package org.secil;

public class PlayerAI extends IPlayer {

    public PlayerAI(CellValue playerValue) {
        super(playerValue);
    }

    public void playRandomMove(Board gameBoard){
        boolean isInputValid = false;
        int row,col;
        do {
            row = (int)(Math.random() * gameBoard.getSize());
            col = (int)(Math.random() * gameBoard.getSize());
            if (gameBoard.isCellEmpty(row,col)) {
                isInputValid = true;
            }
        } while (!isInputValid);

        gameBoard.play(row,col,getPlayerCellValue());
    }
}
