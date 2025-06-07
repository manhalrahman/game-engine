package com.manhalrahman.gameengine.purejava.api;

import com.manhalrahman.gameengine.purejava.board.TicTacToeBoard;
import com.manhalrahman.gameengine.purejava.game.Board;
import com.manhalrahman.gameengine.purejava.game.GameResult;
import com.manhalrahman.gameengine.purejava.game.Move;
import com.manhalrahman.gameengine.purejava.game.Player;
import com.manhalrahman.gameengine.purejava.game.Cell;

public class GameEngine {

    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException("Unsupported board type");
        }
    }

    public void move(Board board, Player player, Move move) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            ticTacToeBoard.setCell(move.getCell(), player.getSymbol());
        } else {
            throw new IllegalArgumentException("Unsupported board type");
        }
    }

    public GameResult isComplete(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            String firstCharacter = TicTacToeBoard.getDefaultEmptyCell();
            boolean rowComplete = true;
            for (int i = 0; i < 3; i++) {
                rowComplete = true;
                firstCharacter = ticTacToeBoard.getCell(i, 0);
                for (int j = 0; j < 3; j++) {
                    if (firstCharacter.equals(TicTacToeBoard.getDefaultEmptyCell()) || !firstCharacter.equals(ticTacToeBoard.getCell(i, j))) {
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
                    if (firstCharacter.equals(TicTacToeBoard.getDefaultEmptyCell()) || !firstCharacter.equals(ticTacToeBoard.getCell(j, i))) {
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
                    if (firstCharacter.equals(TicTacToeBoard.getDefaultEmptyCell()) || !firstCharacter.equals(ticTacToeBoard.getCell(j, j))) {
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
                    if (firstCharacter.equals(TicTacToeBoard.getDefaultEmptyCell()) || !firstCharacter.equals(ticTacToeBoard.getCell(j, 2 - j))) {
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

    public Move suggestMove(Player computer, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getCell(i, j).equals(TicTacToeBoard.getDefaultEmptyCell())) {
                        return new Move(new Cell(i, j));
                    }
                }
            }

            throw new IllegalStateException("No move found");
        } else {
            throw new IllegalArgumentException("Unsupported board type");
        }
    }
}
