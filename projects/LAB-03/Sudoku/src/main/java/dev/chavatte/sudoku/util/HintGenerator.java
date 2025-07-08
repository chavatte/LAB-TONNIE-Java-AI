package dev.chavatte.sudoku.util;

public class HintGenerator {

  public static int[][] generateHint(int[][] board) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == 0) {
          int[][] boardCopy = new int[9][9];
          for (int k = 0; k < 9; k++) {
            System.arraycopy(board[k], 0, boardCopy[k], 0, 9);
          }
          if (SudokuResolver.solveSudoku(boardCopy)) {
            return boardCopy;
          } else {
            return null;
          }
        }
      }
    }
    return null;
  }
}
