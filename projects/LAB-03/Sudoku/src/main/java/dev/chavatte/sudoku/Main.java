package dev.chavatte.sudoku;

import dev.chavatte.sudoku.ui.SudokuUI;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        SudokuUI ui = new SudokuUI();
        ui.setVisible(true);
    }
}