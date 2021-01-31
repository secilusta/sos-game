package org.secil;

import com.sun.tools.corba.se.idl.InvalidArgument;

public class Board {

    private Cell[][] board;
    private final StringBuilder horizontal = new StringBuilder("-----------------");
    private final String vertical = "|";
    private int size;

    public Board(final int n) throws InvalidArgument {
        if (n < 3 || n > 7)
        {
            throw new InvalidArgument("Board size should be between [3-7]");
        }

        for (int i=3; i<n; i++) horizontal.append("----");

        board = new Cell[n][n];
        size = n;

        for (int i=0; i<n; i++)
        {
            for (int y=0; y<n; y++)
            {
                board[i][y] = new Cell();
            }
        }
    }

    public int getSize(){
        return size;
    }

    public void printBoard(){
        System.out.println(horizontal);
        for (int i=0; i<=board.length; i++){
            for (int j=0; j<=board[0].length; j++){
                System.out.print(vertical);
                if (i==0 && j!=0){
                    Cell.printCellValue(Integer.toString(j));
                }
                else if (i==0){
                    Cell.printCellValue(" ");
                }
                else if (j==0 && i!=0){
                    Cell.printCellValue(Integer.toString(i));
                }
                else{
                    board[i-1][j-1].printValue();
                }
            }
            System.out.println(vertical);
            System.out.println(horizontal);
        }
    }

    public int getScoreOfBoard()
    {
        int score = 0;

        for (int row=0; row<board.length; row++) //rows
        {
            for (int column=0; column<board[row].length; column++) // columns
            {
                if (row+2 < board.length)
                if (board[row][column].getCellValue() == CellValue.S
                        &&
                        board[row+1][column].getCellValue() == CellValue.O
                        &&
                        board[row+2][column].getCellValue() == CellValue.S)
                {
                    score++;
                }
                if (column+2 < board[row].length)
                if (board[row][column].getCellValue() == CellValue.S
                        &&
                        board[row][column+1].getCellValue() == CellValue.O
                        &&
                        board[row][column+2].getCellValue() == CellValue.S)
                {
                    score++;
                }
                if (row+2 < board.length && column+2 < board[row].length)
                    if (board[row][column].getCellValue() == CellValue.S
                            &&
                            board[row+1][column+1].getCellValue() == CellValue.O
                            &&
                            board[row+2][column+2].getCellValue() == CellValue.S)
                    {
                        score++;
                    }
                if (row+2 < board.length && column-2 >= 0)
                    if (board[row][column].getCellValue() == CellValue.S
                            &&
                            board[row+1][column-1].getCellValue() == CellValue.O
                            &&
                            board[row+2][column-2].getCellValue() == CellValue.S)
                    {
                        score++;
                    }
            }
        }
        return score;
    }

    public boolean isThereEmptyCells(){
        for (Cell[] line: board) {
            for (Cell cell: line) {
                if (cell.getCellValue() == CellValue.EMPTY)
                    return true;
            }
        }
        return false;
    }
    public boolean isCellEmpty(int x, int y){
        return board[x][y].getCellValue() == CellValue.EMPTY;
    }

    public boolean isCellValid(int x, int y){
        return x<size && y<size;
    }

    public void play(int x, int y, CellValue value){
        board[x][y].setCellValue(value);
    }
}