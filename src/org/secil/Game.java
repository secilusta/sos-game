package org.secil;

import com.sun.tools.corba.se.idl.InvalidArgument;

import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private IPlayer[] players;
    private Scanner in = new Scanner(System.in);
    private int currentPlayer;

    public Game() {
        players = new IPlayer[2];
    }

    public void init() {
        boolean isInputValid = false;
        int boardSize;

        do {
            System.out.print("Enter the board size (3-7): ");
            boardSize = in.nextInt();
            try {
                gameBoard = new Board(boardSize);
                isInputValid = true;
            } catch (InvalidArgument invalidArgument) {
                isInputValid=false;
                System.out.print(boardSize + " is invalid. ");
            }
        } while (!isInputValid);

        int randomCellValue = (int)(Math.random()*2);
        if (randomCellValue == 0){
            players[0] = new PlayerAI(CellValue.S);
            players[1] = new PlayerUser(CellValue.O);
        }
        else{
            players[0] = new PlayerAI(CellValue.O);
            players[1] = new PlayerUser(CellValue.S);
        }
    }

    public void play(){
        currentPlayer = (int)(Math.random()*2);
        do {
            gameBoard.printBoard();
            playerMove(players[currentPlayer]);
            if (setScores()){ //if there is score, don't change player
                currentPlayer = (currentPlayer == 0) ? 1 : 0;
            }
            printScores();
        } while (gameBoard.isThereEmptyCells());
        endGame();
    }

    public boolean setScores(){
        int totalScore = gameBoard.getScoreOfBoard();
        int newScore = totalScore - (players[0].getScore() + players[1].getScore());
        players[currentPlayer].addScore(newScore);
        return newScore == 0;
    }
    public void endGame(){
        gameBoard.printBoard();
        if (players[0].getScore() > players[1].getScore()){
            System.out.println("Player1 (Computer) has won!");
        }
        else if (players[0].getScore() == players[1].getScore()) {
            System.out.println("Drawn!");
        }
        else {
            System.out.println("Player2 (User) has won!");
        }
    }

    public void printScores(){
        System.out.print("Scores: ");
        System.out.print("Player1 (Computer):" + players[0].getScore());
        System.out.println(" - Player2 (User):" + players[1].getScore());
    }

    public void playerMove(IPlayer player) {
        if (player instanceof PlayerAI){
            ((PlayerAI) player).playRandomMove(gameBoard);
        }
        else {
            ((PlayerUser) player).playFromConsole(gameBoard);
        }
    }
}
