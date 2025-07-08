package dev.chavatte.column;

import dev.chavatte.database.Database;
import dev.chavatte.model.Board;
import dev.chavatte.model.Column;
import dev.chavatte.model.ColumnOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColumnRetriever {

  public List<Column> getColumnsByBoard(Board board) {
    List<Column> columns = new ArrayList<>();

    try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection
            .prepareStatement("SELECT * FROM \"column\" WHERE board_id =? ORDER BY \"order\"")) {

      statement.setInt(1, board.getId());
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          int id = resultSet.getInt("id");
          String name = resultSet.getString("name");
          ColumnOrder order = ColumnOrder.valueOf(resultSet.getString("order"));
          String type = resultSet.getString("type");
          columns.add(new Column(id, board.getId(), name, order, type));
        }
      }
    } catch (SQLException e) {
      System.err.println("Erro ao buscar colunas: " + e.getMessage());
    }

    return columns;
  }

  public Column getCancelColumn(Board board) {
    List<Column> columns = getColumnsByBoard(board);
    for (Column column : columns) {
      if (column.getOrder() == ColumnOrder.CANCELED) {
        return column;
      }
    }
    return null;
  }

  public Column getNextColumn(Board board, Column currentColumn) {
    List<Column> columns = getColumnsByBoard(board);
    ColumnOrder currentOrder = currentColumn.getOrder();

    switch (currentOrder) {
      case INITIAL:
        for (Column column : columns) {
          if (column.getOrder() == ColumnOrder.PENDING) {
            return column;
          }
        }
        break;
      case PENDING:
        for (Column column : columns) {
          if (column.getOrder() == ColumnOrder.FINAL) {
            return column;
          }
        }
        break;
      case FINAL:
        for (Column column : columns) {
          if (column.getOrder() == ColumnOrder.CANCELED) {
            return column;
          }
        }
        break;
      case CANCELED:
        return null;
    }

    if (currentOrder != ColumnOrder.FINAL) {
      for (Column column : columns) {
        if (column.getOrder() == ColumnOrder.CANCELED) {
          return column;
        }
      }
    }
    return null;
  }
}