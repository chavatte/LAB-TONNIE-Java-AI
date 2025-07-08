package dev.chavatte.sudoku.core;

import dev.chavatte.sudoku.util.BoardGenerator;
import dev.chavatte.sudoku.util.MoveValidator;

import javax.swing.JOptionPane;

public class SudokuGame {

    private Board board;
    private int difficulty;

    public SudokuGame() {
        board = new Board();
        board.setBoard(BoardGenerator.generateBoard(1));
        this.difficulty = 1;
    }

    public Board getBoard() {
        return board;
    }

    public void setNumber(int row, int col, int num) {
        if (MoveValidator.isValidMove(board.getBoard(), row, col, num)) {
            board.getBoard()[row][col] = num;
        } else {
            System.out.println("Movimento inválido!");
            JOptionPane.showMessageDialog(null, "Movimento inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetGame() {
        board.resetBoard();
    }

    public void newGame(int difficulty) {
        this.difficulty = difficulty;
        board.setBoard(BoardGenerator.generateBoard(difficulty));
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isGameOver() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getBoard()[i][j] == 0) {
                    return false;
                }
            }
        }

        for (int row = 0; row < 9; row++) {
            if (!board.isValidRow(row)) {
                return false;
            }
        }

        for (int col = 0; col < 9; col++) {
            if (!board.isValidColumn(col)) {
                return false;
            }
        }

        for (int blockRow = 0; blockRow < 9; blockRow += 3) {
            for (int blockCol = 0; blockCol < 9; blockCol += 3) {
                if (!board.isValidBlock(blockRow, blockCol)) {
                    return false;
                }
            }
        }
        return true;
    }
}