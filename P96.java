package p96;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class P96 {

    public static void main(String args[]) throws Exception {
        Scanner S = new Scanner(new BufferedReader(new FileReader("/Users/Icey/Desktop/Sudoku/sudoku.txt")));
        int rows = 9;
        int columns = 9;
        int TotalSum = 0;
        int[][] SudokuGrid = new int[rows][columns];

        for (int i = 0; i <= 50; i++) {
            while (S.hasNextLine()) {
                for (int j = 0; j < SudokuGrid.length; j++) {
                    String[] line = S.nextLine().trim().split("");
                    for (int k = 0; k < line.length; k++) {
                        SudokuGrid[j][k] = Integer.parseInt(line[k]);
                    }
                }

                P96 sudoku = new P96(SudokuGrid);
                System.out.println("Sudoku Board [Initial]");
                sudoku.DisplayBoard();

                if (sudoku.solve() == true) {
                    System.out.println("Sudoku Board [Solution]");
                    sudoku.DisplayBoard();
                    sudoku.Sum();
                } else {
                    System.out.println("OOPS, SUDOKU BOARD CANNOT BE SOLVED.");
                }
            }
        }
    }

    private int[][] SudokuBoard;

    public static final int BLANK = 0;
    public static final int SUDOKU_SIZE = 9;
    public static final int SUDOKU_THIRDS = 3;

    public P96(int[][] board) {
        this.SudokuBoard = new int[SUDOKU_SIZE][SUDOKU_SIZE];

        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                this.SudokuBoard[i][j] = board[i][j];
            }
        }
    }

    // Checks if Column has Same Number Repeated
    public boolean CheckColumn(int column, int number) {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (SudokuBoard[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    // Checks if Row has Same Number Repeated
    public boolean CheckRow(int row, int number) {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            if (SudokuBoard[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    // Checks if Box has Same Number Repeated
    public boolean CheckBox(int row, int column, int number) {
        int Row = (row - row % SUDOKU_THIRDS);
        int Column = (column - column % SUDOKU_THIRDS);

        for (int i = Row; i < (Row + SUDOKU_THIRDS); i++) {
            for (int j = Column; j < (Column + SUDOKU_THIRDS); j++) {
                if (SudokuBoard[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // Confirms if Number is Valid for a Position
    public boolean Valid(int row, int column, int number) {
        return (!CheckColumn(column, number) && !CheckRow(row, number) && !CheckBox(row, column, number));
    }

    // Backtracking Algorithm to Solve the Sudoku Puzzle
    public boolean solve() {
        for (int row = 0; row < SUDOKU_SIZE; row++) {
            for (int column = 0; column < SUDOKU_SIZE; column++) {
                if (SudokuBoard[row][column] == BLANK) {
                    for (int number = 1; number <= SUDOKU_SIZE; number++) {
                        if (Valid(row, column, number) == true) {
                            SudokuBoard[row][column] = number;

                            if (solve() == true) {
                                return true;
                            } else {
                                SudokuBoard[row][column] = BLANK;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Prints the Sudoku Board 2D Array onto the Screen.
    public void DisplayBoard() {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                System.out.print(" " + SudokuBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void Sum() {
        int sum = 0;
        //int TotalSum = 0;
        sum = sum + (SudokuBoard[0][0] + (SudokuBoard[0][1]) + (SudokuBoard[0][2]));
        System.out.println("The sum of the top left 3 digits is: " + sum);
        //System.out.println("The total sum of these digits is: " + TotalSum);
        
        //int sums [] = new int [50];
        //for (int i = 0; i < 50; i++) {
        //    TotalSum = TotalSum + sums[i];
        //}
        //System.out.println("The total sum of all the top left 3 digits so far is: " + TotalSum);
    }
}
