package com.minesweeper;
import java.util.Scanner;

/**
 * @author by Hema created on 16082025
 * Entry point for the Minesweeper game.
 */
public class MineSweeperApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter grid size (n for n x n): ");
            int size = scanner.nextInt();

            System.out.print("Enter number of mines: ");
            int mineCount = scanner.nextInt();

            MineSweeperGame game = new MineSweeperGame(size, mineCount);

            while (!game.isGameOver()) {
                game.printBoard();
                System.out.print("Enter row and column (e.g. 1 3): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (!game.uncover(row, col)) {
                    System.out.println("Invalid input or cell already uncovered. Try again.");
                }
            }

            game.printBoard();
            System.out.println(game.isWin() ? "Congratulations! You win!" : "Boom! Game over.");
        }
    }
}
