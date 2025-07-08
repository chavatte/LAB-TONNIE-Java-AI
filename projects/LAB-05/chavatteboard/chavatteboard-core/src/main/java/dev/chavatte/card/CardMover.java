package dev.chavatte.card;

import dev.chavatte.column.ColumnManager;
import dev.chavatte.column.ColumnRetriever;
import dev.chavatte.database.Database;
import dev.chavatte.model.Board;
import dev.chavatte.model.Card;
import dev.chavatte.model.Column;
import dev.chavatte.model.ColumnOrder;
import dev.chavatte.model.CardStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardMover {

  private Scanner scanner;
  private CardHelper cardHelper;
  private ColumnManager columnManager;
  private ColumnRetriever columnRetriever;

  public CardMover(Scanner scanner, CardHelper cardHelper, ColumnRetriever columnRetriever) {
    this.scanner = scanner;
    this.cardHelper = cardHelper;
    this.columnRetriever = columnRetriever;
    this.columnManager = new ColumnManager(scanner);
    this.columnRetriever = columnRetriever;
  }

  public void moveCard(Board board, Column column) {
    List<Card> cards = cardHelper.getCardsByColumn(column);

    if (cards.isEmpty()) {
      System.out.println("Não há cards nesta coluna.");
      return;
    }

    System.out.println("\nCards na coluna " + column.getName() + ": ");
    for (int i = 0; i < cards.size(); i++) {
      System.out.println((i + 1) + " - " + cards.get(i).getTitle());
    }

    System.out.print("Escolha um card para mover: ");
    int cardIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (cardIndex >= 0 && cardIndex < cards.size()) {
      Card cardToMove = cards.get(cardIndex);

      if (cardToMove.getStatus() == CardStatus.BLOQUEADO) {
        System.out.println("O card está bloqueado e não pode ser movido.");
        return;
      }

      List<Column> possibleColumns = new ArrayList<>();

      if (column.getOrder() != ColumnOrder.FINAL) {
        possibleColumns.addAll(columnRetriever.getColumnsByBoard(board));
      } else {
        possibleColumns.add(columnManager.getNextColumn(board, column));
      }

      System.out.println("\nColunas possíveis para mover o card:");
      for (int i = 0; i < possibleColumns.size(); i++) {
        System.out.println((i + 1) + " - " + possibleColumns.get(i).getName());
      }

      System.out.print("Escolha a coluna de destino: ");
      int columnIndex = scanner.nextInt() - 1;
      scanner.nextLine();

      if (columnIndex >= 0 && columnIndex < possibleColumns.size()) {
        Column nextColumn = possibleColumns.get(columnIndex);

        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE card SET column_id =? WHERE id =?")) {

          statement.setInt(1, nextColumn.getId());
          statement.setInt(2, cardToMove.getId());
          statement.executeUpdate();

          cardHelper.registerCardMovement(cardToMove, nextColumn);
          System.out.println("Card movido para a coluna " + nextColumn.getName() + " com sucesso!");

        } catch (SQLException e) {
          System.err.println("Erro ao mover card: " + e.getMessage());
        }

      } else {
        System.out.println("Opção inválida!");
      }
    } else {
      System.out.println("Opção inválida!");
    }
  }
}