# Design Overview

This is a Maven-based Java application implementation of the Minesweeper game that satisfies all the given rules.

## Structure & Object-Oriented Design

### About 'MineSweeperApp.Class'

- The entry point ('main' method) that handles user input, initializes the game, and controls the main game loop.

### About 'MineSweeperGame.Class'

which will describe the 'game state':

- 'board' – what the player sees ('#', number, or blank)
- 'mines' – internal mine placement
- Logic for uncovering cells, counting adjacent mines, and tracking win/lose conditions.

## Key Design Choices & Assumptions

- The grid is always 'square' (n × n).
- Rows and columns are '0-indexed' when selecting a cell.
- The game ends immediately when a mine is uncovered or when all safe cells are revealed.
- Uses 'recursive' to uncover adjacent empty cells if there are no neighboring mines.
- Mines are placed randomly using 'java.util.Random'.
- Input and output are via console for simplicity.
- 'No external libraries' are used.

# Environment Requirements

- Java Version: JDK 8 or higher
- OS: Windows / Linux
- Build Tool: Not required – plain 'javac' is enough or (if we are running from IDE like STS or Eclipse or Intellij directly run as Java application)
- Unit tests can be written using JUnit 5.

# How to Compile and Run

## Compile

Open terminal/command prompt in the '/minesweeper' folder:

javac MineSweeperApp.java

or

Build Project:

From the project root (where pom.xml is located), run:

mvn clean compile

## Run

java MineSweeperApp

Or

mvn exec:java -Dexec.mainClass="MineSweeperApp"

Or

if your IDE supports Maven, you can right-click MineSweeperApp.java → Run as Java Application.

## Game Input Format

- First input → Grid size (e.g., enter '5' for a 5x5 grid)
- Second input → Number of mines
- During the game → Enter 'row col' (e.g., 2 3) to uncover that cell

# Example Gameplay Session

Enter grid size (n for n x n): 4
Enter number of mines: 3
0 1 2 3
0 # # # #
1 # # # #
2 # # # #
3 # # # #

Enter row and column (e.g. 1 3): 1 1
