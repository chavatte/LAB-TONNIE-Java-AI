package dev.chavatte.card;

import dev.chavatte.column.ColumnRetriever;
import dev.chavatte.model.Board;
import dev.chavatte.model.Column;
import dev.chavatte.report.ReportGenerator;

import java.util.Scanner;

public class CardManager {

  private CardCreator cardCreator;
  private CardMover cardMover;
  private CardCanceler cardCanceler;
  private CardBlocker cardBlocker;
  private CardUnblocker cardUnblocker;
  private CardDisplayer cardDisplayer;
  private CardHelper cardHelper;
  private ColumnRetriever columnRetriever;
  private final ReportGenerator reportGenerator;

  public CardManager(Scanner scanner) {
    this.cardHelper = new CardHelper();
    this.cardCreator = new CardCreator(scanner);
    this.columnRetriever = new ColumnRetriever();
    this.cardMover = new CardMover(scanner, cardHelper, columnRetriever);
    this.cardCanceler = new CardCanceler(scanner, cardHelper);
    this.cardBlocker = new CardBlocker(scanner, cardHelper);
    this.cardUnblocker = new CardUnblocker(scanner, cardHelper);
    this.cardDisplayer = new CardDisplayer(cardHelper);
    this.reportGenerator = new ReportGenerator(columnRetriever, cardHelper);
  }

  public void createCard(Board board, Column column) {
    cardCreator.createCard(board, column);
  }

  public void moveCard(Board board, Column column) {
    cardMover.moveCard(board, column);
  }

  public void cancelCard(Board board, Column column) {
    cardCanceler.cancelCard(board, column);
  }

  public void blockCard(Board board, Column column) {
    cardBlocker.blockCard(board, column);
  }

  public void unblockCard(Board board, Column column) {
    cardUnblocker.unblockCard(board, column);
  }

  public void displayCards(Board board, Column column) {
    cardDisplayer.displayCards(board, column);
  }

  public void generateTimeReport(Board board) {
    reportGenerator.generateTimeReport(board);
  }

  public void generateBlockReport(Board board) {
    reportGenerator.generateBlockReport(board);
  }
}