package dev.chavatte.sudoku;

public class Sudoku {

    public int[][] board;

    public Sudoku() {
        board = new int[9][9];
    }

    public boolean isGameOver() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }

        for (int row = 0; row < 9; row++) {
            if (!isValidRow(row)) {
                return false;
            }
        }

        for (int col = 0; col < 9; col++) {
            if (!isValidColumn(col)) {
                return false;
            }
        }

        for (int blockRow = 0; blockRow < 9; blockRow += 3) {
            for (int blockCol = 0; blockCol < 9; blockCol += 3) {
                if (!isValidBlock(blockRow, blockCol)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidRow(int row) {
        boolean[] seen = new boolean[10];
        for (int col = 0; col < 9; col++) {
            int num = board[row][col];
            if (num != 0) {
                if (seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        return true;
    }

    private boolean isValidColumn(int col) {
        boolean[] seen = new boolean[10];
        for (int row = 0; row < 9; row++) {
            int num = board[row][col];
            if (num != 0) {
                if (seen[num]) {
                    return false;
                }
                seen[num] = true;
            }
        }
        return true;
    }

    private boolean isValidBlock(int blockRow, int blockCol) {
        boolean[] seen = new boolean[10];
        for (int row = blockRow; row < blockRow + 3; row++) {
            for (int col = blockCol; col < blockCol + 3; col++) {
                int num = board[row][col];
                if (num != 0) {
                    if (seen[num]) {
                        return false;
                    }
                    seen[num] = true;
                }
            }
        }
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (isCellEditable(i, j)) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private boolean isCellEditable(int row, int col) {
        return board[row][col] == 0;
    }
}