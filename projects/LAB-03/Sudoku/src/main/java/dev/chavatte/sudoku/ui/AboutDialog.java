package dev.chavatte.sudoku.ui;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "Sobre o Jogo Sudoku", true);
        // ImageIcon icon = new ImageIcon("src/assets/img/sudoku.png");
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/img/sudoku.png")); 
        double scaleFactor = 0.4;
        Image image = icon.getImage().getScaledInstance(
                (int) (icon.getIconWidth() * scaleFactor),
                (int) (icon.getIconHeight() * scaleFactor),
                Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        JLabel titleLabel = new JLabel("Jogo Sudoku", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel infoLabel = new JLabel("<html>" +
                "<p><b>Descrição:</b> Jogo criado no Bootcamp Bradesco - Java Cloud Native.</p>" +
                "<p><b>Técnologia:</b> Feito em Java com interface gráfica Swing.</p>" +
                "<p><b>Versão:</b> 1.0 [Beta]</p>" +
                "<h3 style='margin-top:10px;'><b>Desenvolvido por:</b> João Carlos Chavatte</h3>" +
                "<p><b>Email:</b> <a href='mailto:dev.chavatte@gmail.com'>dev.chavatte@gmail.com</a></p>" +
                "<p><b>Site:</b> <a href='https://chavatte.42web.io/'>https://chavatte.42web.io/</a></p>" +
                "</html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(titleLabel);
        contentPanel.add(infoLabel);
        contentPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(closeButton);
        contentPanel.add(buttonPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(imageLabel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        getContentPane().setBackground(new Color(220, 235, 255));
        pack();
        setLocationRelativeTo(parent);
    }
}