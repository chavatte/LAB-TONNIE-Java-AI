package dev.chavatte.menu;

import dev.chavatte.menu.option.MainMenuOption;
import dev.chavatte.menu.option.BoardMenuOption;
import dev.chavatte.menu.option.ColumnMenuOption;

import java.util.Scanner;

public class MenuManager {

  private MainMenu mainMenu;
  private BoardMenu boardMenu;
  private ColumnMenu columnMenu;

  public MenuManager() {
    this.mainMenu = new MainMenu();
    this.boardMenu = new BoardMenu();
    this.columnMenu = new ColumnMenu();
  }

  public MainMenuOption showMainMenu(Scanner scanner) {
    return mainMenu.showMainMenu(scanner);
  }

  public BoardMenuOption showBoardMenu(Scanner scanner) {
    return boardMenu.showBoardMenu(scanner);
  }

  public ColumnMenuOption showColumnMenu(Scanner scanner) {
    return columnMenu.showColumnMenu(scanner);
  }
}