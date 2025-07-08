package dev.chavatte.menu;

import dev.chavatte.menu.option.MainMenuOption;

import java.util.Scanner;

public class MainMenu {

  public MainMenuOption showMainMenu(Scanner scanner) {
    MainMenuOption option = null;
    do {
      System.out.println("\nMenu Principal:");
      System.out.println("1 - Criar novo board");
      System.out.println("2 - Selecionar board");
      System.out.println("3 - Excluir boards");
      System.out.println("4 - Sair");
      System.out.print("Escolha uma opção: ");

      int choice;
      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            option = MainMenuOption.CREATE_BOARD;
            break;
          case 2:
            option = MainMenuOption.SELECT_BOARD;
            break;
          case 3:
            option = MainMenuOption.DELETE_BOARD;
            break;
          case 4:
            option = MainMenuOption.EXIT;
            break;
          default:
            System.out.println("Opção inválida!");
        }
      } else {
        System.out.println("Opção inválida!");
        scanner.nextLine();
      }
    } while (option == null);

    return option;
  }
}