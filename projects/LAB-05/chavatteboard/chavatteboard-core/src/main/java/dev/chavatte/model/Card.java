package dev.chavatte.model;

import java.time.LocalDateTime;

public class Card {

  private int id;
  private int columnId;
  private String title;
  private String description;
  private CardStatus status;
  private String blockReason;
  private String unblockReason;
  private LocalDateTime createdAt;

  public Card(int id, int columnId, String title, String description, CardStatus status, String blockReason,
      String unblockReason, LocalDateTime createdAt) {
    this.id = id;
    this.columnId = columnId;
    this.title = title;
    this.description = description;
    this.status = status;
    this.blockReason = blockReason;
    this.unblockReason = unblockReason;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getColumnId() {
    return columnId;
  }

  public void setColumnId(int columnId) {
    this.columnId = columnId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CardStatus getStatus() {
    return status;
  }

  public void setStatus(CardStatus status) {
    this.status = status;
  }

  public String getBlockReason() {
    return blockReason;
  }

  public void setBlockReason(String blockReason) {
    this.blockReason = blockReason;
  }

  public String getUnblockReason() {
    return unblockReason;
  }

  public void setUnblockReason(String unblockReason) {
    this.unblockReason = unblockReason;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}