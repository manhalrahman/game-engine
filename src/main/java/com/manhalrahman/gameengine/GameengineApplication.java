package com.manhalrahman.gameengine;

import com.manhalrahman.gameengine.purejava.api.GameEngine;
import com.manhalrahman.gameengine.purejava.api.AIEngine;
import com.manhalrahman.gameengine.purejava.api.RuleEngine;
import com.manhalrahman.gameengine.purejava.game.Board;
import com.manhalrahman.gameengine.purejava.game.Player;
import com.manhalrahman.gameengine.purejava.game.Move;
import com.manhalrahman.gameengine.purejava.game.Cell;
import com.manhalrahman.gameengine.purejava.game.GameResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

// @SpringBootApplication
public class GameengineApplication {

	public static void main(String[] args) {
		GameEngine gameEngine = new GameEngine();
		RuleEngine ruleEngine = new RuleEngine();
		AIEngine aiEngine = new AIEngine();
		// SpringApplication.run(GameengineApplication.class, args);
		System.out.println("Entered - before taking input");
		Scanner scanner = new Scanner(System.in);

		Board board = gameEngine.start("TicTacToe");

		while (!ruleEngine.getState(board).isOver()) {
			System.out.println("Current board state: ");
			System.out.println(board.toString());


			System.out.println("Enter the row and column for the move");

			int row = scanner.nextInt();
			int column = scanner.nextInt();

			Player computer = new Player("X");
			Player human = new Player("O");

			Move humanMove = new Move(new Cell(row, column), human);
			Move computerMove = aiEngine.suggestMove(computer, board);

			gameEngine.move(board, computer, computerMove);
			gameEngine.move(board, human, humanMove);

			if (!ruleEngine.getState(board).isOver()) {
				Move newComputerMove = aiEngine.suggestMove(computer, board);
				gameEngine.move(board, computer, newComputerMove);

			}

		}
		System.out.println("Game over, winner: " + ruleEngine.getState(board).toString());
		scanner.close();
	}

}
