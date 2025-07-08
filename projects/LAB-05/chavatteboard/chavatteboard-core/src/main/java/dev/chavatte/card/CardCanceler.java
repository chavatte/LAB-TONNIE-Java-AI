package dev.chavatte.card;

import dev.chavatte.column.ColumnManager;
import dev.chavatte.database.Database;
import dev.chavatte.model.Board;
import dev.chavatte.model.Card;
import dev.chavatte.model.Column;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CardCanceler {

  private Scanner scanner;
  private CardHelper cardHelper;
  private ColumnManager columnManager;

  public CardCanceler(Scanner scanner, CardHelper cardHelper) {
    this.scanner = scanner;
    this.cardHelper = cardHelper;
    this.columnManager = new ColumnManager(scanner);
  }

  public void cancelCard(Board board, Column column) {
    List<Card> cards = cardHelper.getCardsByColumn(column);

    if (cards.isEmpty()) {
      System.out.println("Não há cards nesta coluna.");
      return;
    }

    System.out.println("\nCards na coluna " + column.getName() + ": ");
    for (int i = 0; i < cards.size(); i++) {
      System.out.println((i + 1) + " - " + cards.get(i).getTitle());
    }

    System.out.print("Escolha um card para cancelar: ");
    int cardIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (cardIndex >= 0 && cardIndex < cards.size()) {
      Card cardToCancel = cards.get(cardIndex);

      Column cancelColumn = columnManager.getCancelColumn(board);

      if (cancelColumn != null) {
        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE card SET column_id =? WHERE id =?")) {

          statement.setInt(1, cancelColumn.getId());
          statement.setInt(2, cardToCancel.getId());
          statement.executeUpdate();

          cardHelper.registerCardMovement(cardToCancel, cancelColumn);
          System.out.println("Card cancelado com sucesso!");

        } catch (SQLException e) {
          System.err.println("Erro ao cancelar card: " + e.getMessage());
        }
      } else {
        System.out.println("Não foi encontrada a coluna de cancelamento no board.");
      }

    } else {
      System.out.println("Opção inválida!");
    }
  }
}