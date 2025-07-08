package dev.chavatte.board;

import java.util.Scanner;

import dev.chavatte.model.Board;

public class BoardManager {

    private BoardCreator boardCreator;
    private BoardSelector boardSelector;
    private BoardDeleter boardDeleter;

    public BoardManager(Scanner scanner) {
        this.boardCreator = new BoardCreator(scanner);
        this.boardSelector = new BoardSelector(scanner);
        this.boardDeleter = new BoardDeleter(scanner);
    }

    public void createBoard() {
        boardCreator.createBoard();
    }

    public Board selectBoard() {
        return boardSelector.selectBoard();
    }

    public void deleteBoard() {
        boardDeleter.deleteBoard();
    }
}