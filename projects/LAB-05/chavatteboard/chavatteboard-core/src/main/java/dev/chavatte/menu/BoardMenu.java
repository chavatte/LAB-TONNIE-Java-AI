package dev.chavatte.menu;

import dev.chavatte.menu.option.BoardMenuOption;

import java.util.Scanner;

public class BoardMenu {

  public BoardMenuOption showBoardMenu(Scanner scanner) {
    BoardMenuOption option = null;
    do {
      System.out.println("\nMenu do Board:");
      System.out.println("1 - Criar coluna");
      System.out.println("2 - Selecionar coluna");
      System.out.println("3 - Gerar relatório de tempo de conclusão");
      System.out.println("4 - Gerar relatório de bloqueios");
      System.out.println("5 - Voltar ao menu principal");
      System.out.print("Escolha uma opção: ");

      int choice;
      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            option = BoardMenuOption.CREATE_COLUMN;
            break;
          case 2:
            option = BoardMenuOption.SELECT_COLUMN;
            break;
          case 3:
            option = BoardMenuOption.GENERATE_TIME_REPORT;
            break;
          case 4:
            option = BoardMenuOption.GENERATE_BLOCK_REPORT;
            break;
          case 5:
            option = BoardMenuOption.BACK_TO_MAIN_MENU;
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