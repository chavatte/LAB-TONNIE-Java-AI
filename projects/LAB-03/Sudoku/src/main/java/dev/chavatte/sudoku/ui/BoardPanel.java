package dev.chavatte.sudoku.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoardPanel extends JPanel {

  private JTextField[][] cells;
  private SudokuUI ui;

  public BoardPanel(SudokuUI ui) {
    this.ui = ui;
    setLayout(new GridLayout(9, 9));
    cells = new JTextField[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        JTextField cell = new JTextField();
        cell.setHorizontalAlignment(JTextField.CENTER);
        cell.setFont(new Font("Arial", Font.BOLD, 20));
        int colorIndex = (i / 3 + j / 3) % 2;
        cell.setBackground(colorIndex == 0 ? Themes.DEFAULT_CELL_COLOR1 : Themes.DEFAULT_CELL_COLOR2);
        CellBorder.setCellBorder(cell, Themes.DEFAULT_BORDER_COLOR);
        if (ui.getGame().getBoard().getBoard()[i][j] != 0) {
          cell.setText(String.valueOf(ui.getGame().getBoard().getBoard()[i][j]));
          cell.setEditable(false);
          cell.setForeground(Themes.DEFAULT_NUMBER_COLOR);
        }
        cell.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
            char inputChar = e.getKeyChar();
            if (inputChar >= '1' && inputChar <= '9') {
              int row = getCellRow(cell);
              int col = getCellCol(cell);
              int num = Character.getNumericValue(inputChar);
              cell.setText("");
              ui.getGame().setNumber(row, col, num);
              cell.setForeground(Color.BLUE);

              if (ui.getGame().isGameOver()) {
                String message;
                int difficulty = ui.getGame().getDifficulty();

                switch (difficulty) {
                  case 1:
                    message = "Parabéns! Você completou o Sudoku no nível fácil!\nQue tal tentar o nível Médio agora?";
                    break;
                  case 2:
                    message = "Parabéns! Você completou o Sudoku no nível médio!\nAgora, prepare-se para o nível Difícil!";
                    break;
                  case 3:
                    message = "Parabéns! Você completou o Sudoku no nível dificil!\nVocê é bom! Que tal enfrentar o nível Expert?";
                    break;
                  case 4:
                    message = "Parabéns! Você completou o Sudoku no nível Expert!\nVocê é um mestre do Sudoku!";
                    break;
                  default:
                    message = "Parabéns! Você completou o Sudoku!\nContinue assim!";
                }

                JOptionPane.showMessageDialog(ui, message, "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
              }
            } else {
              e.consume();
            }
          }
        });
        cells[i][j] = cell;
        add(cell);
      }
    }
  }

  public void updateBoardUI() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        cells[i][j].setForeground(Color.BLACK);
        cells[i][j].setText(
            ui.getGame().getBoard().getBoard()[i][j] != 0 ? String.valueOf(ui.getGame().getBoard().getBoard()[i][j])
                : "");
        cells[i][j].setEditable(ui.getGame().getBoard().isCellEditable(i, j));
      }
    }
  }

  public JTextField getCell(int row, int col) {
    return cells[row][col];
  }

  private int getCellRow(JTextField cell) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (cells[i][j] == cell) {
          return i;
        }
      }
    }
    return -1;
  }

  private int getCellCol(JTextField cell) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (cells[i][j] == cell) {
          return j;
        }
      }
    }
    return -1;
  }

  public void applyLightTheme() {
    setBackground(Themes.LIGHT_BACKGROUND_COLOR);
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        JTextField cell = getCell(i, j);
        int colorIndex = (i / 3 + j / 3) % 2;
        cell.setBackground(colorIndex == 0 ? Themes.LIGHT_CELL_COLOR1 : Themes.LIGHT_CELL_COLOR2);
        cell.setForeground(Themes.LIGHT_NUMBER_COLOR);
        CellBorder.setCellBorder(cell, Themes.LIGHT_BORDER_COLOR);
      }
    }
  }

  public void applyDarkTheme() {
    setBackground(Themes.DARK_BACKGROUND_COLOR);
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        JTextField cell = getCell(i, j);
        int colorIndex = (i / 3 + j / 3) % 2;
        cell.setBackground(colorIndex == 0 ? Themes.DARK_CELL_COLOR1 : Themes.DARK_CELL_COLOR2);
        cell.setForeground(Themes.DARK_NUMBER_COLOR);
        CellBorder.setCellBorder(cell, Themes.DARK_BORDER_COLOR);
      }
    }
  }

  public void applyDefaultTheme() {
    setBackground(Themes.DEFAULT_BACKGROUND_COLOR);
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        JTextField cell = getCell(i, j);
        int colorIndex = (i / 3 + j / 3) % 2;
        cell.setBackground(colorIndex == 0 ? Themes.DEFAULT_CELL_COLOR1 : Themes.DEFAULT_CELL_COLOR2);
        cell.setForeground(Themes.DEFAULT_NUMBER_COLOR);
        CellBorder.setCellBorder(cell, Themes.LIGHT_BORDER_COLOR);
      }
    }
  }
}
