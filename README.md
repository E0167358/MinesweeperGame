# MinesweeperGame

This is a simple command-line Minesweeper game implemented in Java for my job assessment test.

## Features
- Prompt the user for the grid size and the number of mines.
- Generate the grid and randomly place the specified number of mines.
- Allow the user to select a square to uncover.
  - If the selected square contains a mine, the game is over and the user loses.
  - Otherwise, the selected square is uncovered and reveals a number indicating how many of its adjacent squares contain mines.
  - If an uncovered square has no adjacent mines, the program automatically uncovers all adjacent squares until it reaches squares that do have adjacent mines.
- The game is won when all non-mine squares have been uncovered.
- Display the game grid and allow the user to input their choices through a command line interface.
- Track the user's progress throughout the game, displaying the minefield after each user input.

## How to Run
1. **Clone the Repository**:
   ```sh
   git clone https://github.com/E0167358/MinesweeperGame.git
   cd MinesweeperGame
2. Compile the Java Files:
javac src/com/assessment/minesweeper/*.java
3. Run the Game:
java -cp src com.assessment.minesweeper.Minesweeper

Classes
Cell.java: Represents each cell in the grid, tracking whether it's a mine, revealed, and the number of adjacent mines.
Grid.java: Manages the game grid, including mine placement, calculating adjacent mines, and handling cell reveals.
Minesweeper.java: The main class that handles user input and runs the game loop.


Example Gameplay
Welcome to Minesweeper!

Enter the size of the grid (e.g., 4 for a 4x4 grid): 
4
Enter the number of mines to place on the grid (maximum is 35% of the total squares): 
3

Here is your minefield:
  1 2 3 4
A _ _ _ _
B _ _ _ _
C _ _ _ _
D _ _ _ _

Select a square to reveal (e.g., A1): D4
This square contains 0 adjacent mines. 

Here is your updated minefield:
  1 2 3 4
A _ _ 2 0
B _ _ 2 0
C _ 2 1 0
D _ 1 0 0

