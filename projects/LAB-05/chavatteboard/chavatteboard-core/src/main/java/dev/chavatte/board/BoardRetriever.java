package dev.chavatte.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.chavatte.database.Database;
import dev.chavatte.model.Board;

public class BoardRetriever {
  public List<Board> getAllBoards() {
    List<Board> boards = new ArrayList<>();

    try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM board");
        ResultSet resultSet = statement.executeQuery()) {

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        boards.add(new Board(id, name));
      }

    } catch (SQLException e) {
      System.err.println("Erro ao buscar boards: " + e.getMessage());
    }

    return boards;
  }
}
