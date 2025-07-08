package dev.chavatte.board;

import java.util.List;
import java.util.Scanner;

import dev.chavatte.model.Board;

public class BoardSelector {

  private Scanner scanner;

  public BoardSelector(Scanner scanner) {
    this.scanner = scanner;
  }

  public Board selectBoard() {
    BoardRetriever boardRetriever = new BoardRetriever();
    List<Board> boards = boardRetriever.getAllBoards();

    if (boards.isEmpty()) {
      System.out.println("Não há boards cadastrados.");
      return null;
    }

    System.out.println("\nBoards existentes:");
    for (int i = 0; i < boards.size(); i++) {
      System.out.println((i + 1) + " - " + boards.get(i).getName());
    }

    System.out.print("Escolha um board: ");
    int boardIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (boardIndex >= 0 && boardIndex < boards.size()) {
      Board selectedBoard = boards.get(boardIndex);
      System.out.println("Board selecionado: " + selectedBoard.getName());
      return selectedBoard;
    } else {
      System.out.println("Opção inválida!");
      return null;
    }
  }
}
