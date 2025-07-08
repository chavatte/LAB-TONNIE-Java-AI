package dev.chavatte.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import dev.chavatte.database.Database;

public class BoardCreator {
  private Scanner scanner;

  public BoardCreator(Scanner scanner) {
    this.scanner = scanner;
  }

  public void createBoard() {
    System.out.println("Digite o nome do novo board:");
    String boardName = scanner.nextLine();

    try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO board (name) VALUES (?)")) {

      statement.setString(1, boardName);
      statement.executeUpdate();
      System.out.println("Board criado com sucesso!");

    } catch (SQLException e) {
      System.err.println("Erro ao criar board: " + e.getMessage());
    }
  }
}
