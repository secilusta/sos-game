package org.secil;

public abstract class IPlayer {
     private int score;
     private CellValue playerValue;

    public IPlayer(final CellValue playerValue){
        score = 0;
        this.playerValue = playerValue;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int newScore){
        score+=newScore;
    }

    public CellValue getPlayerCellValue(){
        return playerValue;
    }
}
