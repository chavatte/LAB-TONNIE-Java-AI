package dev.chavatte.model;

public enum CardStatus {
  BLOQUEADO("Bloqueado"),
  EM_ANDAMENTO("Em andamento"),
  CONCLUIDO("Concluído");

  private String descricao;

  CardStatus(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}