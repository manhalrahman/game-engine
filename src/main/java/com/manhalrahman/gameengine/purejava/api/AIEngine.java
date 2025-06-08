package com.manhalrahman.gameengine.purejava.api;

import com.manhalrahman.gameengine.purejava.game.Board;
import com.manhalrahman.gameengine.purejava.game.Move;
import com.manhalrahman.gameengine.purejava.game.Player;
import com.manhalrahman.gameengine.purejava.board.TicTacToeBoard;
import com.manhalrahman.gameengine.purejava.game.Cell;

public class AIEngine {
    
    public Move suggestMove(Player computer, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacToeBoard.getCell(i, j).equals(TicTacToeBoard.getDefaultEmptyCell())) {
                        return new Move(new Cell(i, j), computer);
                    }
                }
            }

            throw new IllegalStateException("No move found");
        } else {
            throw new IllegalArgumentException("Unsupported board type");
        }
    }
}