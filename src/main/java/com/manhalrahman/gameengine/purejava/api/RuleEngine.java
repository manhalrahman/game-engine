package com.manhalrahman.gameengine.purejava.api;

import com.manhalrahman.gameengine.purejava.game.Board;
import com.manhalrahman.gameengine.purejava.game.GameResult;
import com.manhalrahman.gameengine.purejava.board.TicTacToeBoard;

public class RuleEngine {
    public GameResult getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            String firstCharacter = TicTacToeBoard.getDefaultEmptyCell();
            boolean rowComplete = true;
            for (int i = 0; i < 3; i++) {
                rowComplete = true;
                firstCharacter = ticTacToeBoard.getCell(i, 0);
                for (int j = 0; j < 3; j++) {
                    if (firstCharacter.equals(TicTacToeBoard.getDefaultEmptyCell())
                            || !firstCharacter.equals(ticTacToeBoard.getCell(i, j))) {
                        rowComplete = false;
                        break;
                    }
                }
                if (rowComplete) {
                    break;
                }
            }

            if (rowComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean columnComplete = false;
            for (int i = 0; i < 3; i++) {
                columnComplete = true;
                firstCharacter = ticTacToeBoard.getCell(0, i);
                for (int j = 0; j < 3; j++) {
                    if (firstCharacter.equals(TicTacToeBoard.getDefaultEmptyCell())
                            || !firstCharacter.equals(ticTacToeBoard.getCell(j, i))) {
                        columnComplete = false;
                        break;
                    }
                }
                if (columnComplete) {
                    break;
                }
            }

            if (columnComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean diagonalComplete = false;
            for (int i = 0; i < 3; i++) {
                diagonalComplete = true;
                firstCharacter = ticTacToeBoard.getCell(0, 0);
                for (int j = 0; j < 3; j++) {
                    if (firstCharacter.equals(TicTacToeBoard.getDefaultEmptyCell())
                            || !firstCharacter.equals(ticTacToeBoard.getCell(j, j))) {
                        diagonalComplete = false;
                        break;
                    }
                }
                if (diagonalComplete) {
                    break;
                }
            }

            if (diagonalComplete) {
                return new GameResult(true, firstCharacter);
            }

            boolean reverseDiagonalComplete = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = ticTacToeBoard.getCell(0, 2);
                for (int j = 0; j < 3; j++) {
                    if (firstCharacter.equals(TicTacToeBoard.getDefaultEmptyCell())
                            || !firstCharacter.equals(ticTacToeBoard.getCell(j, 2 - j))) {
                        reverseDiagonalComplete = false;
                        break;
                    }
                }
            }

            if (reverseDiagonalComplete) {
                return new GameResult(true, firstCharacter);
            }

            int countOfFilledCells = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!ticTacToeBoard.getCell(i, j).equals(TicTacToeBoard.getDefaultEmptyCell())) {
                        countOfFilledCells++;
                    }
                }
            }

            if (countOfFilledCells == 9) {
                return new GameResult(true, "-");
            }

            return new GameResult(false, "-");
        } else {
            throw new IllegalArgumentException("Unsupported board type");
        }
    }
}
