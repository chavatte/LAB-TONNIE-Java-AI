package dev.chavatte.card;

import dev.chavatte.model.Board;
import dev.chavatte.model.Card;
import dev.chavatte.model.Column;

import java.util.List;

public class CardDisplayer {

    private CardHelper cardHelper;

    public CardDisplayer(CardHelper cardHelper) {
        this.cardHelper = cardHelper;
    }

    public void displayCards(Board board, Column column) {
        List<Card> cards = cardHelper.getCardsByColumn(column);

        if (cards.isEmpty()) {
            System.out.println("Não há cards nesta coluna.");
            return;
        }

        System.out.println("\nCards na coluna " + column.getName() + ":");
        for (Card card : cards) {
            System.out.println("Título: " + card.getTitle());
            System.out.println("Descrição: " + card.getDescription());
            System.out.println("Status: " + card.getStatus().getDescricao());
            System.out.println("--------------------");
        }
    }
}