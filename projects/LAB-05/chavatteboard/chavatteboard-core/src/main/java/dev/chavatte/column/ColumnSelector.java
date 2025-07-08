package dev.chavatte.column;

import dev.chavatte.model.Board;
import dev.chavatte.model.Column;

import java.util.List;
import java.util.Scanner;

public class ColumnSelector {

    private Scanner scanner;

    public ColumnSelector(Scanner scanner) {
        this.scanner = scanner;
    }

    public Column selectColumn(Board board, ColumnRetriever columnRetriever) {
        List<Column> columns = columnRetriever.getColumnsByBoard(board);

        if (columns.isEmpty()) {
            System.out.println("Não há colunas cadastradas neste board.");
            return null;
        }

        System.out.println("\nColunas existentes no board " + board.getName() + ":");
        for (int i = 0; i < columns.size(); i++) {
            System.out.println((i + 1) + " - " + columns.get(i).getName());
        }

        System.out.print("Escolha uma coluna: ");
        int columnIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (columnIndex >= 0 && columnIndex < columns.size()) {
            Column selectedColumn = columns.get(columnIndex);
            System.out.println("Coluna selecionada: " + selectedColumn.getName());
            return selectedColumn;
        } else {
            System.out.println("Opção inválida!");
            return null;
        }
    }
}
