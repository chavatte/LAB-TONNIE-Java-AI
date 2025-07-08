package dev.chavatte.column;

import dev.chavatte.database.Database;
import dev.chavatte.model.Board;
import dev.chavatte.model.ColumnOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ColumnCreator {

  private Scanner scanner;

  public ColumnCreator(Scanner scanner) {
    this.scanner = scanner;
  }

  public void createColumn(Board board) {
    System.out.println("\nCriar nova coluna no board " + board.getName() + ":");

    System.out.print("Nome da coluna: ");
    String columnName = scanner.nextLine();

    String columnType;
    while (true) {
      System.out.print("Tipo da coluna (INITIAL, PENDING, FINAL, CANCELED): ");
      columnType = scanner.nextLine().toUpperCase();

      if (columnType.equals("INITIAL") || columnType.equals("PENDING") || columnType.equals("FINAL")
          || columnType.equals("CANCELED")) {
        break;
      } else {
        System.out.println("Tipo de coluna inválido! Tente novamente.");
      }
    }

    ColumnOrder columnOrder = ColumnOrder.valueOf(columnType);

    try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection
            .prepareStatement("INSERT INTO \"column\" (board_id, name, \"order\", type) VALUES (?,?,?,?)")) {

      statement.setInt(1, board.getId());
      statement.setString(2, columnName);
      statement.setString(3, columnOrder.name());
      statement.setString(4, columnType);
      statement.executeUpdate();
      System.out.println("Coluna criada com sucesso!");

    } catch (SQLException e) {
      System.err.println("Erro ao criar coluna: " + e.getMessage());
    }
  }
}