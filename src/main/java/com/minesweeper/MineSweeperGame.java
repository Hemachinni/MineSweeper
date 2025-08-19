package com.minesweeper;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Hema created on 16082025
 */
public class MineSweeperGame {

    private final int size;
    private final int mineCount;
    private final char[][] board;
    private final boolean[][] mines;
    private int cellsLeft;
    private boolean gameOver;

    private static final char COVERED = '#';
    private static final char MINE = 'M';
    private static final char EMPTY = ' ';

    /**
     * Initializes a new Minesweeper game.
     * @param size size of the grid (n x n)
     * @param mineCount number of mines
     */
    public MineSweeperGame(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.board = new char[size][size];
        this.mines = new boolean[size][size];
        this.cellsLeft = size * size - mineCount;
        this.gameOver = false;

        for (char[] row : board) {
            Arrays.fill(row, COVERED);
        }

        placeMines();
    }

    /** Places mines randomly on the board. */
    private void placeMines() {
        Random random = new Random();
        int placed = 0;
        while (placed < mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!mines[row][col]) {
                mines[row][col] = true;
                placed++;
            }
        }
    }

    /**
     * Uncovers the cell at (row, col).
     * @param row the row index
     * @param col the column index
     * @return true if valid uncover, false if invalid/out-of-bounds/already uncovered
     */
    public boolean uncover(int row, int col) {
        if (outOfBounds(row, col) || board[row][col] != COVERED) {
            return false;
        }

        if (mines[row][col]) {
            board[row][col] = MINE;
            gameOver = true;
            return true;
        }

        int adjacentMines = countAdjacentMines(row, col);
        board[row][col] = (adjacentMines == 0) ? EMPTY : (char) ('0' + adjacentMines);
        cellsLeft--;

        if (adjacentMines == 0) {
            // Recursively uncover neighbors
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr != 0 || dc != 0) {
                        uncover(row + dr, col + dc);
                    }
                }
            }
        }

        if (cellsLeft == 0) {
            gameOver = true;
        }

        return true;
    }

    private boolean outOfBounds(int row, int col) {
        return row < 0 || col < 0 || row >= size || col >= size;
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int nr = row + dr;
                int nc = col + dc;
                if (!outOfBounds(nr, nc) && mines[nr][nc]) {
                    count++;
                }
            }
        }
        return count;
    }

    /** Checks if the game is over (mine hit or all safe cells uncovered). */
    public boolean isGameOver() {
        return gameOver;
    }

    /** Checks if the player has won (all safe cells uncovered). */
    public boolean isWin() {
        return cellsLeft == 0 && !anyMineUncovered();
    }

    private boolean anyMineUncovered() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == MINE) return true;
            }
        }
        return false;
    }

    /** Prints the current state of the board. */
    public void printBoard() {
        System.out.print("  ");
        for (int c = 0; c < size; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int r = 0; r < size; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < size; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
