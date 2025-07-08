package dev.chavatte.sudoku.ui;

import dev.chavatte.sudoku.util.HintGenerator;
import dev.chavatte.sudoku.util.SudokuResolver;

import javax.swing.*;
import java.awt.Color;

public class GameController {

    private SudokuUI ui;

    public GameController(SudokuUI ui) {
        this.ui = ui;
    }

    public void requestHint() {
        int[][] hintBoard = HintGenerator.generateHint(ui.getGame().getBoard().getBoard());
        if (hintBoard != null) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (ui.getGame().getBoard().getBoard()[i][j] == 0) {
                        ui.getBoardPanel().getCell(i, j).setText(String.valueOf(hintBoard[i][j]));
                        ui.getGame().getBoard().getBoard()[i][j] = hintBoard[i][j];
                        ui.getBoardPanel().getCell(i, j).setForeground(Color.RED);
                        return;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(ui,
                    "Não foi possível encontrar uma dica.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void solveSudoku() {
        if (SudokuResolver.solveSudoku(ui.getGame().getBoard().getBoard())) {
            ui.getBoardPanel().updateBoardUI();
        } else {
            JOptionPane.showMessageDialog(ui,
                    "Não foi possível resolver o Sudoku.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
