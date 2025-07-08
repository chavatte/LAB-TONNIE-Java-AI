package dev.chavatte;

import dev.chavatte.menu.MenuManager;
import dev.chavatte.menu.option.BoardMenuOption;
import dev.chavatte.menu.option.ColumnMenuOption;
import dev.chavatte.menu.option.MainMenuOption;
import dev.chavatte.board.BoardManager;
import dev.chavatte.card.CardManager;
import dev.chavatte.column.ColumnManager;
import dev.chavatte.database.DatabaseSetup;
import dev.chavatte.model.Board;
import dev.chavatte.model.Column;

import java.util.Scanner;

public class App {

  public static void run() {
    DatabaseSetup.createTables();
    Scanner scanner = new Scanner(System.in);
    BoardManager boardManager = new BoardManager(scanner);
    ColumnManager columnManager = new ColumnManager(scanner);
    CardManager cardManager = new CardManager(scanner);
    MenuManager menuManager = new MenuManager();

    MainMenuOption mainMenuOption;
    do {
      mainMenuOption = menuManager.showMainMenu(scanner);

      switch (mainMenuOption) {
        case CREATE_BOARD:
          boardManager.createBoard();
          break;
        case SELECT_BOARD:
          Board selectedBoard = boardManager.selectBoard();
          if (selectedBoard != null) {
            BoardMenuOption boardMenuOption;
            do {
              boardMenuOption = menuManager.showBoardMenu(scanner);

              switch (boardMenuOption) {
                case CREATE_COLUMN:
                  columnManager.createColumn(selectedBoard);
                  break;
                case SELECT_COLUMN:
                  Column selectedColumn = columnManager.selectColumn(selectedBoard);
                  if (selectedColumn != null) {
                    ColumnMenuOption columnMenuOption;
                    do {
                      columnMenuOption = menuManager.showColumnMenu(scanner);

                      switch (columnMenuOption) {
                        case CREATE_CARD:
                          cardManager.createCard(selectedBoard, selectedColumn);
                          break;
                        case MOVE_CARD:
                          cardManager.moveCard(selectedBoard, selectedColumn);
                          break;
                        case CANCEL_CARD:
                          cardManager.cancelCard(selectedBoard, selectedColumn);
                          break;
                        case BLOCK_CARD:
                          cardManager.blockCard(selectedBoard, selectedColumn);
                          break;
                        case UNBLOCK_CARD:
                          cardManager.unblockCard(selectedBoard, selectedColumn);
                          break;
                        case DISPLAY_CARDS:
                          cardManager.displayCards(selectedBoard, selectedColumn);
                          break;
                        case BACK_TO_BOARD_MENU:
                          System.out.println("Voltando ao menu do board...");
                          break;
                      }
                    } while (columnMenuOption != ColumnMenuOption.BACK_TO_BOARD_MENU);
                  }
                  break;
                case GENERATE_TIME_REPORT:
                  cardManager.generateTimeReport(selectedBoard);
                  break;
                case GENERATE_BLOCK_REPORT:
                  cardManager.generateBlockReport(selectedBoard);
                  break;
                case BACK_TO_MAIN_MENU:
                  System.out.println("Voltando ao menu principal...");
                  break;
              }
            } while (boardMenuOption != BoardMenuOption.BACK_TO_MAIN_MENU);
          }
          break;
        case DELETE_BOARD:
          boardManager.deleteBoard();
          break;
        case EXIT:
          System.out.println("Saindo...");
          break;
      }
    } while (mainMenuOption != MainMenuOption.EXIT);

    scanner.close();
  }
}