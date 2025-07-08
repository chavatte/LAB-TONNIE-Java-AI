package dev.chavatte.menu;

import dev.chavatte.menu.option.ColumnMenuOption;

import java.util.Scanner;

public class ColumnMenu {

  public ColumnMenuOption showColumnMenu(Scanner scanner) {
    ColumnMenuOption option = null;
    do {
      System.out.println("\nMenu da Coluna:");
      System.out.println("1 - Criar card");
      System.out.println("2 - Mover card");
      System.out.println("3 - Cancelar card");
      System.out.println("4 - Bloquear card");
      System.out.println("5 - Desbloquear card");
      System.out.println("6 - Exibir cards");
      System.out.println("7 - Voltar ao menu do board");
      System.out.print("Escolha uma opção: ");

      int choice;
      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            option = ColumnMenuOption.CREATE_CARD;
            break;
          case 2:
            option = ColumnMenuOption.MOVE_CARD;
            break;
          case 3:
            option = ColumnMenuOption.CANCEL_CARD;
            break;
          case 4:
            option = ColumnMenuOption.BLOCK_CARD;
            break;
          case 5:
            option = ColumnMenuOption.UNBLOCK_CARD;
            break;
          case 6:
            option = ColumnMenuOption.DISPLAY_CARDS;
            break;
          case 7:
            option = ColumnMenuOption.BACK_TO_BOARD_MENU;
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