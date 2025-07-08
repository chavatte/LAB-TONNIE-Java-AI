package dev.chavatte.sudoku.util;

public class SudokuResolver {

  public static boolean solveSudoku(int[][] board) {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (board[row][col] == 0) {
          for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
              board[row][col] = num;
              if (solveSudoku(board)) {
                return true;
              } else {
                board[row][col] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  private static boolean isSafe(int[][] board, int row, int col, int num) {
    for (int x = 0; x < 9; x++) {
      if (board[row][x] == num) {
        return false;
      }
    }

    for (int x = 0; x < 9; x++) {
      if (board[x][col] == num) {
        return false;
      }
    }

    int startRow = row - row % 3;
    int startCol = col - col % 3;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i + startRow][j + startCol] == num) {
          return false;
        }
      }
    }
    return true;
  }
}
