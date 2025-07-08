package dev.chavatte.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dev.chavatte.database.Database;
import dev.chavatte.model.Board;

public class BoardDeleter {

  private Scanner scanner;

  public BoardDeleter(Scanner scanner) {
    this.scanner = scanner;
  }

  public void deleteBoard() {
    BoardRetriever boardRetriever = new BoardRetriever();
    List<Board> boards = boardRetriever.getAllBoards();

    if (boards.isEmpty()) {
      System.out.println("Não há boards cadastrados.");
      return;
    }

    System.out.println("\nBoards existentes:");
    for (int i = 0; i < boards.size(); i++) {
      System.out.println((i + 1) + " - " + boards.get(i).getName());
    }

    System.out.print("Escolha um board para excluir: ");
    int boardIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (boardIndex >= 0 && boardIndex < boards.size()) {
      Board boardToDelete = boards.get(boardIndex);

      try (Connection connection = Database.getConnection();
          PreparedStatement statement = connection.prepareStatement("DELETE FROM board WHERE id =?")) {

        statement.setInt(1, boardToDelete.getId());
        statement.executeUpdate();
        System.out.println("Board excluído com sucesso!");

      } catch (SQLException e) {
        System.err.println("Erro ao excluir board: " + e.getMessage());
      }
    } else {
      System.out.println("Opção inválida!");
    }
  }
}