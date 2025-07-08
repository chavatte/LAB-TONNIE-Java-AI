package dev.chavatte.sudoku.ui;

import java.awt.Component;
import javax.swing.*;

public class ButtonPanel extends JPanel {

  private SudokuUI ui;

  public ButtonPanel(SudokuUI ui) {
    this.ui = ui;

    JButton resetButton = new JButton("Reiniciar Jogo");
    JButton hintButton = new JButton("Dica");
    JButton solveButton = new JButton("Resolver");

    add(resetButton);
    add(hintButton);
    add(solveButton);

    resetButton.addActionListener(e -> {
      ui.getGame().resetGame();
      ui.getBoardPanel().updateBoardUI();
    });
    hintButton.addActionListener(e -> ui.requestHint());
    solveButton.addActionListener(e -> ui.solveSudoku());
  }

  public void applyLightTheme() {
    for (Component component : getComponents()) {
      if (component instanceof JButton) {
        JButton button = (JButton) component;
        button.setBackground(Themes.LIGHT_BUTTON_COLOR);
        button.setForeground(Themes.LIGHT_BUTTON_TEXT_COLOR);
      }
    }
  }

  public void applyDarkTheme() {
    for (Component component : getComponents()) {
      if (component instanceof JButton) {
        JButton button = (JButton) component;
        button.setBackground(Themes.DARK_BUTTON_COLOR);
        button.setForeground(Themes.DARK_BUTTON_TEXT_COLOR);
      }
    }
  }

  public void applyDefaultTheme() {
    for (Component component : getComponents()) {
      if (component instanceof JButton) {
        JButton button = (JButton) component;
        button.setBackground(Themes.DEFAULT_BUTTON_COLOR);
        button.setForeground(Themes.DEFAULT_BUTTON_TEXT_COLOR);
      }
    }
  }
}