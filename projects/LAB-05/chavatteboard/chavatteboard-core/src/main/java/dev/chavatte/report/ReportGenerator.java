package dev.chavatte.report;

import dev.chavatte.card.CardHelper;
import dev.chavatte.column.ColumnRetriever;
import dev.chavatte.model.Board;
import dev.chavatte.model.Card;
import dev.chavatte.model.Column;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator {

  private final ColumnRetriever columnRetriever;
  private final CardHelper cardHelper;

  public ReportGenerator(ColumnRetriever columnRetriever, CardHelper cardHelper) {
    this.columnRetriever = columnRetriever;
    this.cardHelper = cardHelper;
  }

  public void generateTimeReport(Board board) {
    List<Column> columns = columnRetriever.getColumnsByBoard(board);
    List<Card> cards = new ArrayList<>();
    for (Column column : columns) {
      cards.addAll(cardHelper.getCardsByColumn(column));
    }

    System.out.println("\nRelatório de Tempo do Board: " + board.getName());
    for (Card card : cards) {
      System.out.println("\nCard: " + card.getTitle());
      List<CardHelper.CardMovement> movements = cardHelper.getCardMovements(card.getId());
      for (Column column : columns) {
        Duration timeInColumn = cardHelper.calculateTimeInColumn(movements, column.getOrder());
        System.out.println("Tempo na coluna " + column.getName() + ": " + formatDuration(timeInColumn));
      }
      Duration totalTime = Duration.between(card.getCreatedAt(), LocalDateTime.now());
      System.out.println("Tempo total: " + formatDuration(totalTime));
    }
  }

  private String formatDuration(Duration duration) {
    long days = duration.toDaysPart();
    long hours = duration.toHoursPart();
    long minutes = duration.toMinutesPart();
    long seconds = duration.toSecondsPart();
    return String.format("%02dd %02dh %02dm %02ds", days, hours, minutes, seconds);
  }

  public void generateBlockReport(Board board) {
    List<Card> cards = new ArrayList<>();
    for (Column column : columnRetriever.getColumnsByBoard(board)) {
      cards.addAll(cardHelper.getCardsByColumn(column));
    }

    System.out.println("\nRelatório de Bloqueios do Board: " + board.getName());
    for (Card card : cards) {
      List<CardHelper.CardMovement> movements = cardHelper.getCardMovements(card.getId());
      List<CardHelper.CardMovement> blockedMovements = movements.stream()
          .filter(m -> card.getBlockReason() != null || card.getUnblockReason() != null)
          .collect(Collectors.toList());

      if (!blockedMovements.isEmpty()) {
        System.out.println("\nCard: " + card.getTitle());
        for (CardHelper.CardMovement movement : blockedMovements) {
          if (card.getBlockReason() != null) {
            System.out.println(
                "Bloqueado em: " + movement.getMovedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            System.out.println("Motivo do bloqueio: " + card.getBlockReason());
          }
          if (card.getUnblockReason() != null) {
            System.out.println(
                "Desbloqueado em: " + movement.getMovedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            System.out.println("Motivo do desbloqueio: " + card.getUnblockReason());
          }
          System.out.println("--------------------");
        }
      }
    }
  }
}
