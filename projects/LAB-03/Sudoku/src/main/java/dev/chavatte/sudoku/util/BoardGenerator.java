package dev.chavatte.sudoku.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BoardGenerator {

    public static int[][] generateBoard(int difficulty) {
        int[][] board = new int[9][9];
        fillDiagonalBlocks(board);
        fillRemaining(0, 3, board);
        removeNumbers(board, difficulty);
        return board;
    }

    private static void fillDiagonalBlocks(int[][] board) {
        for (int block = 0; block < 9; block += 3) {
            fillBlock(block, block, board);
        }
    }

    private static void fillBlock(int row, int col, int[][] board) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        int index = 0;
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                board[i][j] = numbers.get(index++);
            }
        }
    }

    private static boolean fillRemaining(int row, int col, int[][] board) {
        if (col >= 9 && row < 8) {
            row += 1;
            col = 0;
        }
        if (row >= 9 && col >= 9) {
            return true;
        }
        if (row < 3) {
            if (col < 3) {
                col = 3;
            }
        } else if (row < 6) {
            if (col == (int) (row / 3) * 3) {
                col += 3;
            }
        } else {
            if (col == 6) {
                row += 1;
                col = 0;
                if (row >= 9) {
                    return true;
                }
            }
        }
        for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num, board)) {
                board[row][col] = num;
                if (fillRemaining(row, col + 1, board)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int row, int col, int num, int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void removeNumbers(int[][] board, int difficulty) {
        Random random = new Random();
        int count;
        switch (difficulty) {
            case 1:
                count = 30;
                break;
            case 2:
                count = 40;
                break;
            case 3:
                count = 50;
                break;
            case 4:
                count = 60;
                break;
            default:
                count = 40;
        }

        while (count > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                count--;
            }
        }
    }
}