package dev.chavatte.card;

import dev.chavatte.database.Database;
import dev.chavatte.model.Board;
import dev.chavatte.model.Column;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CardCreator {

  private Scanner scanner;

  public CardCreator(Scanner scanner) {
    this.scanner = scanner;
  }

  public void createCard(Board board, Column column) {
    System.out.println("\nCriar novo card na coluna " + column.getName() + " do board " + board.getName() + ":");

    System.out.print("Título do card: ");
    String cardTitle = scanner.nextLine();

    System.out.print("Descrição do card: ");
    String cardDescription = scanner.nextLine();

    try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection
            .prepareStatement("INSERT INTO card (column_id, title, description) VALUES (?,?,?)")) {

      statement.setInt(1, column.getId());
      statement.setString(2, cardTitle);
      statement.setString(3, cardDescription);
      statement.executeUpdate();
      System.out.println("Card criado com sucesso!");

    } catch (SQLException e) {
      System.err.println("Erro ao criar card: " + e.getMessage());
    }
  }
}