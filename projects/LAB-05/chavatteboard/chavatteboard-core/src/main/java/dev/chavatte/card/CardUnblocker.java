package dev.chavatte.card;

import dev.chavatte.database.Database;
import dev.chavatte.model.Board;
import dev.chavatte.model.Card;
import dev.chavatte.model.CardStatus;
import dev.chavatte.model.Column;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CardUnblocker {

  private Scanner scanner;
  private CardHelper cardHelper;

  public CardUnblocker(Scanner scanner, CardHelper cardHelper) {
    this.scanner = scanner;
    this.cardHelper = cardHelper;
  }

  public void unblockCard(Board board, Column column) {
    List<Card> cards = cardHelper.getCardsByColumn(column);

    if (cards.isEmpty()) {
      System.out.println("Não há cards nesta coluna.");
      return;
    }

    System.out.println("\nCards na coluna " + column.getName() + ":");
    for (int i = 0; i < cards.size(); i++) {
      System.out.println((i + 1) + " - " + cards.get(i).getTitle());
    }

    System.out.print("Escolha um card para desbloquear: ");
    int cardIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (cardIndex >= 0 && cardIndex < cards.size()) {
      Card cardToUnblock = cards.get(cardIndex);

      System.out.print("Motivo do desbloqueio: ");
      String unblockReason = scanner.nextLine();

      try (Connection connection = Database.getConnection();
          PreparedStatement statement = connection
              .prepareStatement("UPDATE card SET status =?, unblock_reason =? WHERE id =?")) {

        statement.setString(1, CardStatus.EM_ANDAMENTO.name());
        statement.setString(2, unblockReason);
        statement.setInt(3, cardToUnblock.getId());
        statement.executeUpdate();
        System.out.println("Card desbloqueado com sucesso!");

      } catch (SQLException e) {
        System.err.println("Erro ao desbloquear card: " + e.getMessage());
      }

    } else {
      System.out.println("Opção inválida!");
    }
  }
}