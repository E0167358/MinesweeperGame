package com.assessment.minesweeper;
import java.util.Scanner;

public class Minesweeper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");

        int gridSize = getValidInput(scanner, "Enter the size of the grid (e.g., 4 for a 4x4 grid): ", 2, 10);
        int maxMines = (int) (gridSize * gridSize * 0.35);
        int totalMines = getValidInput(scanner, "Enter the number of mines to place on the grid (maximum is " + maxMines + "): ", 1, maxMines);

        Grid grid = new Grid(gridSize, totalMines);

        boolean gameOver = false;
        while (!gameOver) {
            grid.displayGrid();
            System.out.print("Select a square to reveal (e.g., A1): ");
            String input = scanner.next().toUpperCase();

            if (input.length() != 2) {
                System.out.println("Incorrect input.");
                continue;
            }

            char rowChar = input.charAt(0);
            char colChar = input.charAt(1);

            if (rowChar < 'A' || rowChar >= 'A' + gridSize || colChar < '1' || colChar >= '1' + gridSize) {
                System.out.println("Incorrect input.");
                continue;
            }

            int row = rowChar - 'A';
            int col = colChar - '1';

            gameOver = grid.revealCell(row, col);
            if (gameOver) {
                System.out.println("Oh no, you detonated a mine! Game over.");
            } else if (grid.isWin()) {
                System.out.println("Congratulations, you have won the game!");
                gameOver = true;
            }
        }

        grid.displayGrid();
        System.out.println("Press any key to play again...");
        scanner.next();
    }

    /**
     * Prompts the user for an integer input within a specified range and validates the input.
     *
     * @param used to read user input
     * @param prompt the message to display from user input
     * @param min the minimum acceptable value 
     * @param max the maximum acceptable value 
     * @return a valid integer input within the specified range
     */
    private static int getValidInput(Scanner scanner, String prompt, int min, int max) {
        int input = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    valid = true;
                } else {
                    System.out.println("Input must be between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Incorrect input.");
                scanner.next(); // Consume invalid input
            }
        }
        return input;
    }
}
