package com.manhalrahman.gameengine;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.manhalrahman.gameengine.purejava.api.GameEngine;
import com.manhalrahman.gameengine.purejava.api.RuleEngine;
import com.manhalrahman.gameengine.purejava.api.AIEngine;
import com.manhalrahman.gameengine.purejava.game.Board;
import com.manhalrahman.gameengine.purejava.game.Player;
import com.manhalrahman.gameengine.purejava.game.Move;
import com.manhalrahman.gameengine.purejava.game.Cell;

public class GamePlayTest {
    private GameEngine gameEngine;
    private RuleEngine ruleEngine;
    private AIEngine aiEngine;

    @BeforeEach
    public void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        aiEngine = new AIEngine();
    }


    private void playGame(Board board, int[][] moves) {
        int next = 0;
        int row, col;
        while (!ruleEngine.getState(board).isOver()) {

            row = moves[next][0];
            col = moves[next][1];
            next++;

            Player computer = new Player("X");
            Player human = new Player("O");

            Move humanMove = new Move(new Cell(row, col), human);
            Move computerMove = aiEngine.suggestMove(computer, board);

            gameEngine.move(board, computer, computerMove);
            gameEngine.move(board, human, humanMove);

            if (!ruleEngine.getState(board).isOver()) {
                Move newComputerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computer, newComputerMove);

            }

        }
    }
    

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{0, 0}, {1, 0}, {2, 0}};

        playGame(board, moves);

        Assertions.assertThat(ruleEngine.getState(board).isOver()).isTrue();
        Assertions.assertThat(ruleEngine.getState(board).getWinner()).isEqualTo("X");

    }

    @Test
    public void checkForColumnWin() {
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{0, 0}, {0, 1}, {0, 2}};

        playGame(board, moves);

        Assertions.assertThat(ruleEngine.getState(board).isOver()).isTrue();
        Assertions.assertThat(ruleEngine.getState(board).getWinner()).isEqualTo("X");
    }


    @Test
    public void checkForDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{0, 0}, {1, 1}, {2, 2}};

        playGame(board, moves);

        Assertions.assertThat(ruleEngine.getState(board).isOver()).isTrue();
        Assertions.assertThat(ruleEngine.getState(board).getWinner()).isEqualTo("X");
    }
    

    @Test
    public void checkForAntiDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");

        int[][] moves = new int[][]{{0, 2}, {1, 1}, {2, 0}};

        playGame(board, moves);

        Assertions.assertThat(ruleEngine.getState(board).isOver()).isTrue();
        Assertions.assertThat(ruleEngine.getState(board).getWinner()).isEqualTo("X");
    }

}
