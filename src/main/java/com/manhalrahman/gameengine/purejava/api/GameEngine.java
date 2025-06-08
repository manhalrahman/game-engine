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
            board.move(move);
        } else {
            throw new IllegalArgumentException("Unsupported board type");
        }
    }
}
