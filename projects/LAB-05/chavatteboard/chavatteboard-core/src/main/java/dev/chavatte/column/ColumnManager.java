package dev.chavatte.column;

import dev.chavatte.model.Board;
import dev.chavatte.model.Column;

import java.util.Scanner;

public class ColumnManager {

  private ColumnCreator columnCreator;
  private ColumnSelector columnSelector;
  private ColumnRetriever columnRetriever;

  public ColumnManager(Scanner scanner) {
    this.columnCreator = new ColumnCreator(scanner);
    this.columnSelector = new ColumnSelector(scanner);
    this.columnRetriever = new ColumnRetriever();
  }

  public void createColumn(Board board) {
    columnCreator.createColumn(board);
  }

  public Column selectColumn(Board board) {
    return columnSelector.selectColumn(board, columnRetriever);
  }

  public Column getCancelColumn(Board board) {
    return columnRetriever.getCancelColumn(board);
  }

  public Column getNextColumn(Board board, Column currentColumn) {
    return columnRetriever.getNextColumn(board, currentColumn);
  }
}