package dev.chavatte.sudoku.core;

public class Board {
    private int[][] board;
    private int[][] initialBoard;

    public Board() {
        board = new int[9][9];
        initialBoard = new int[9][9];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, initialBoard[i], 0, 9);
        }
    }

    public boolean isCellEditable(int row, int col) {
        return board[row][col] == 0;
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != initialBoard[i][j]) {
                    board[i][j] = 0;
                }
            }
        }
    }

    public boolean isValidRow(int row) {
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

    public boolean isValidColumn(int col) {
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

    public boolean isValidBlock(int blockRow, int blockCol) {
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
}