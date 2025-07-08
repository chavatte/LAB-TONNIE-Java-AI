package dev.chavatte.model;

public class Column {

  private int id;
  private int boardId;
  private String name;
  private ColumnOrder order;
  private String type;

  public Column(int id, int boardId, String name, ColumnOrder order, String type) {
    this.id = id;
    this.boardId = boardId;
    this.name = name;
    this.order = order;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBoardId() {
    return boardId;
  }

  public void setBoardId(int boardId) {
    this.boardId = boardId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ColumnOrder getOrder() {
    return order;
  }

  public void setOrder(ColumnOrder order) {
    this.order = order;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}