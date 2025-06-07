package com.manhalrahman.gameengine;

import com.manhalrahman.gameengine.purejava.api.GameEngine;
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
		// SpringApplication.run(GameengineApplication.class, args);
		System.out.println("Entered - before taking input");
		Scanner scanner = new Scanner(System.in);

		System.out.println("About to make board");

		Board board = gameEngine.start("TicTacToe");
		boolean gameState =  gameEngine.isComplete(board).isOver();
		System.out.println("About to start game. Game state: " + gameState);
		
		while(!gameEngine.isComplete(board).isOver()) {
			System.out.println("Enter the row and column for the move");

			System.out.println(board.toString());

			int row = scanner.nextInt();
			int column = scanner.nextInt();

			Player computer = new Player("X");
			Player opponent = new Player("O");

			Move opponentMove = new Move(new Cell(row, column));
			Move computerMove = gameEngine.suggestMove(computer, board);

			gameEngine.move(board, computer, computerMove);
			gameEngine.move(board, opponent, opponentMove);

			if(!gameEngine.isComplete(board).isOver()) {
				Move newComputerMove = gameEngine.suggestMove(computer, board);
				gameEngine.move(board, computer, newComputerMove);

			}
			
			System.out.println(board.toString());	

		}
		System.out.println("Game over, winner: " + gameEngine.isComplete(board).toString());
		scanner.close();
	}

}
