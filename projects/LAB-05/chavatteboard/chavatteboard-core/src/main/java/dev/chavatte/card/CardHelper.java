package dev.chavatte.card;

import dev.chavatte.database.Database;
import dev.chavatte.model.Card;
import dev.chavatte.model.CardStatus;
import dev.chavatte.model.Column;
import dev.chavatte.model.ColumnOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CardHelper {

  public List<Card> getCardsByColumn(Column column) {
    List<Card> cards = new ArrayList<>();

    try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM card WHERE column_id =?")) {

      statement.setInt(1, column.getId());
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          int id = resultSet.getInt("id");
          String title = resultSet.getString("title");
          String description = resultSet.getString("description");
          CardStatus status = CardStatus.valueOf(resultSet.getString("status"));
          LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
          cards.add(new Card(id, column.getId(), title, description, status,
              resultSet.getString("block_reason"), resultSet.getString("unblock_reason"), createdAt));
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar cards: " + e.getMessage());
    }

    return cards;
  }

  public void registerCardMovement(Card card, Column newColumn) {
    try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection
            .prepareStatement("INSERT INTO card_history (card_id, column_id, status, moved_at) VALUES (?,?,?,?)")) {

      statement.setInt(1, card.getId());
      statement.setInt(2, newColumn.getId());
      statement.setString(3, card.getStatus().name());
      statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      statement.executeUpdate();

    } catch (SQLException e) {
      System.err.println("Erro ao registrar movimentação do card: " + e.getMessage());
    }
  }

  public List<CardMovement> getCardMovements(int cardId) {
    List<CardMovement> movements = new ArrayList<>();
    try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(
            "SELECT ch.moved_at, c.name, c.\"order\" " +
                "FROM card_history ch " +
                "JOIN \"column\" c ON ch.column_id = c.id " +
                "WHERE ch.card_id =? " +
                "ORDER BY ch.moved_at")) {
      statement.setInt(1, cardId);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          LocalDateTime movedAt = resultSet.getTimestamp("moved_at").toLocalDateTime();
          String columnName = resultSet.getString("name");
          ColumnOrder columnOrder = ColumnOrder.valueOf(resultSet.getString("order"));
          movements.add(new CardMovement(movedAt, columnName, columnOrder));
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar movimentações do card: " + e.getMessage());
    }
    return movements;
  }

  public Duration calculateTimeInColumn(List<CardMovement> movements, ColumnOrder columnOrder) {
    Duration totalDuration = Duration.ZERO;
    LocalDateTime entryTime = null;
    for (int i = 0; i < movements.size(); i++) {
      CardMovement movement = movements.get(i);
      if (movement.getColumnOrder() == columnOrder) {
        entryTime = movement.getMovedAt();
      } else if (entryTime != null) {
        totalDuration = totalDuration.plus(Duration.between(entryTime, movement.getMovedAt()));
        entryTime = null;
      }
    }
    return totalDuration;
  }

  public static class CardMovement {
    private LocalDateTime movedAt;
    private String columnName;
    private ColumnOrder columnOrder;

    public CardMovement(LocalDateTime movedAt, String columnName, ColumnOrder columnOrder) {
      this.movedAt = movedAt;
      this.columnName = columnName;
      this.columnOrder = columnOrder;
    }

    public LocalDateTime getMovedAt() {
      return movedAt;
    }

    public String getColumnName() {
      return columnName;
    }

    public ColumnOrder getColumnOrder() {
      return columnOrder;
    }
  }
}