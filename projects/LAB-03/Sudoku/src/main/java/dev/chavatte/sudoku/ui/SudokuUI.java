package dev.chavatte.sudoku.ui;

import dev.chavatte.sudoku.core.SudokuGame;

import javax.swing.*;
import java.awt.*;

public class SudokuUI extends JFrame {

    private SudokuGame game;
    private BoardPanel boardPanel;
    private ButtonPanel buttonPanel;
    private JButton newGameButton;
    private JButton resetButton;
    private JButton hintButton;
    private JButton solveButton;
    private GameController gameController;
    private JMenuBar menuBar;

    public SudokuUI() {
        setTitle("[ D E V  C h a v a t t e ]  Sudoku Game");
        // ImageIcon icon = new ImageIcon("src/assets/img/sudoku.png");
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/img/sudoku.png")); 
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        game = new SudokuGame();
        boardPanel = new BoardPanel(this);
        buttonPanel = new ButtonPanel(this);
        gameController = new GameController(this);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        menuBar = new JMenuBar();

        JMenu themeMenu = new JMenu("Tema");
        ButtonGroup themeGroup = new ButtonGroup();
        JRadioButtonMenuItem themeDefault = new JRadioButtonMenuItem("Padrão", true);
        JRadioButtonMenuItem themeClaro = new JRadioButtonMenuItem("Claro");
        JRadioButtonMenuItem themeEscuro = new JRadioButtonMenuItem("Escuro");
        themeGroup.add(themeDefault);
        themeGroup.add(themeClaro);
        themeGroup.add(themeEscuro);
        themeMenu.add(themeDefault);
        themeMenu.add(themeClaro);
        themeMenu.add(themeEscuro);
        menuBar.add(themeMenu);

        JMenu difficultyMenu = new JMenu("Dificuldade");
        ButtonGroup difficultyGroup = new ButtonGroup();
        JRadioButtonMenuItem difficultyEasy = new JRadioButtonMenuItem("Fácil");
        JRadioButtonMenuItem difficultyMedium = new JRadioButtonMenuItem("Médio", true); // Médio como padrão
        JRadioButtonMenuItem difficultyHard = new JRadioButtonMenuItem("Difícil");
        JRadioButtonMenuItem difficultyExpert = new JRadioButtonMenuItem("Expert");
        difficultyGroup.add(difficultyEasy);
        difficultyGroup.add(difficultyMedium);
        difficultyGroup.add(difficultyHard);
        difficultyGroup.add(difficultyExpert);
        difficultyMenu.add(difficultyEasy);
        difficultyMenu.add(difficultyMedium);
        difficultyMenu.add(difficultyHard);
        difficultyMenu.add(difficultyExpert);
        menuBar.add(Box.createHorizontalStrut(20));
        menuBar.add(difficultyMenu);

        JMenu aboutMenu = new JMenu("Sobre");
        JMenuItem aboutMenuItem = new JMenuItem("Sobre o Jogo");
        aboutMenu.add(aboutMenuItem);
        menuBar.add(Box.createHorizontalStrut(20));
        menuBar.add(aboutMenu);

        setJMenuBar(menuBar);

        themeClaro.addActionListener(e -> {
            boardPanel.applyLightTheme();
            buttonPanel.applyLightTheme();
        });

        themeEscuro.addActionListener(e -> {
            boardPanel.applyDarkTheme();
            buttonPanel.applyDarkTheme();
        });

        themeDefault.addActionListener(e -> {
            boardPanel.applyDefaultTheme();
            buttonPanel.applyDefaultTheme();
        });

        difficultyEasy.addActionListener(e -> {
            game.newGame(1);
            boardPanel.updateBoardUI();
        });

        difficultyMedium.addActionListener(e -> {
            game.newGame(2);
            boardPanel.updateBoardUI();
        });

        difficultyHard.addActionListener(e -> {
            game.newGame(3);
            boardPanel.updateBoardUI();
        });

        difficultyExpert.addActionListener(e -> {
            game.newGame(4);
            boardPanel.updateBoardUI();
        });

        aboutMenuItem.addActionListener(e -> {
            new AboutDialog(this).setVisible(true);
        });

        JPanel bottomPanel = new JPanel();
        newGameButton = new JButton("Novo Jogo");
        resetButton = new JButton("Reiniciar Jogo");
        hintButton = new JButton("Dica");
        solveButton = new JButton("Resolver");
        bottomPanel.add(newGameButton);
        bottomPanel.add(resetButton);
        bottomPanel.add(hintButton);
        bottomPanel.add(solveButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        newGameButton.addActionListener(e -> {
            int difficulty = 1;
            if (difficultyMedium.isSelected()) {
                difficulty = 2;
            } else if (difficultyHard.isSelected()) {
                difficulty = 3;
            } else if (difficultyExpert.isSelected()) {
                difficulty = 4;
            }
            game.newGame(difficulty);
            boardPanel.updateBoardUI();
        });

        resetButton.addActionListener(e -> {
            this.getGame().resetGame();
            this.getBoardPanel().updateBoardUI();
        });

        hintButton.addActionListener(e -> this.requestHint());
        solveButton.addActionListener(e -> this.solveSudoku());
        add(mainPanel);
        themeDefault.doClick();
        setVisible(true);
    }

    public SudokuGame getGame() {
        return game;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public void requestHint() {
        gameController.requestHint();
    }

    public void solveSudoku() {
        gameController.solveSudoku();
    }

    public static void main(String args) {
        SwingUtilities.invokeLater(() -> new SudokuUI());
    }
}