package dev.chavatte.sudoku.core;

public class Game {
    private Board board;

    public Game() {
        board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public boolean isGameOver() {
        int[][] boardArray = board.getBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (boardArray[i][j] == 0) {
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
        int[][] boardArray = board.getBoard();
        for (int col = 0; col < 9; col++) {
            int num = boardArray[row][col];
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
        int[][] boardArray = board.getBoard();
        for (int row = 0; row < 9; row++) {
            int num = boardArray[row][col];
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
        int[][] boardArray = board.getBoard();
        for (int row = blockRow; row < blockRow + 3; row++) {
            for (int col = blockCol; col < blockCol + 3; col++) {
                int num = boardArray[row][col];
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