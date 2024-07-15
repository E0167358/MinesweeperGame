package com.assessment.minesweeper;
import java.util.Random;

public class Grid {
    private final Cell[][] grid;
    private final int size;
    private final int totalMines;

    public Grid(int size, int totalMines) {
        this.size = size;
        this.totalMines = totalMines;
        this.grid = new Cell[size][size];
        initializeGrid();
        placeMines();
        calculateAdjacentMines();
    }

    private void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    /**
     * Places mines randomly on the grid.
     */
    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    /**
     * Calculates the number of adjacent mines for each cell on the grid.
     */
    private void calculateAdjacentMines() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!grid[i][j].isMine()) {
                    int count = countAdjacentMines(i, j);
                    grid[i][j].setAdjacentMines(count);
                }
            }
        }
    }

    /**
     * Counts the number of mines adjacent to a given cell.
     *
     * @param row the row of the cell
     * @param col the column of the cell
     * @return the number of adjacent mines
     */
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size && grid[i][j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Displays the current state of the grid.
     */
    public void displayGrid() {
        System.out.print("  ");
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                if (grid[i][j].isRevealed()) {
                    if (grid[i][j].isMine()) {
                        System.out.print("* ");
                    } else {
                        System.out.print(grid[i][j].getAdjacentMines() + " ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Reveals the cell at the specified position.
     *
     * @param row the row of the cell to reveal
     * @param col the column of the cell to reveal
     * @return true if the cell contains a mine, false otherwise
     */
    public boolean revealCell(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size || grid[row][col].isRevealed()) {
            return false;
        }

        grid[row][col].reveal();

        if (grid[row][col].isMine()) {
            return true;
        }

        if (grid[row][col].getAdjacentMines() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < size && j >= 0 && j < size) {
                        revealCell(i, j);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the player has won the game.
     *
     * @return true if all non-mine cells have been revealed, false otherwise
     */
    public boolean isWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!grid[i][j].isMine() && !grid[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }
}
