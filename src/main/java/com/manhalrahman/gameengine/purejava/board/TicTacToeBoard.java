package com.manhalrahman.gameengine.purejava.board;

import com.manhalrahman.gameengine.purejava.game.Board;
import com.manhalrahman.gameengine.purejava.game.Cell;

public class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];

    private static final String EMPTY_CELL = "-";

    public TicTacToeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = EMPTY_CELL;
            }
        }
    }

    public static String getDefaultEmptyCell() {
        return EMPTY_CELL;
    }

    public String getCell(int row, int column) {
        return cells[row][column];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getColumn()] = symbol;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                result += cells[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getSymbol());
    }
}
