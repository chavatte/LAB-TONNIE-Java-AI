package dev.chavatte.sudoku.ui;

import javax.swing.*;
import java.awt.*;

public class CellBorder {

    public static void setCellBorder(JTextField cell, Color borderColor) {
        int top = 1, left = 1, bottom = 1, right = 1;
        int i = getCellRow(cell);
        int j = getCellCol(cell);
        if (i % 3 == 0)
            top = 3;
        if (i % 3 == 2)
            bottom = 3;
        if (j % 3 == 0)
            left = 3;
        if (j % 3 == 2)
            right = 3;
        cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, borderColor));
    }

    private static int getCellRow(JTextField cell) {
        Container parent = cell.getParent();
        if (parent instanceof JPanel) {
            JPanel panel = (JPanel) parent;
            Component[] components = panel.getComponents();
            for (int i = 0; i < components.length; i++) {
                if (components[i] == cell) {
                    return i / 9;
                }
            }
        }
        return -1;
    }

    private static int getCellCol(JTextField cell) {
        Container parent = cell.getParent();
        if (parent instanceof JPanel) {
            JPanel panel = (JPanel) parent;
            Component[] components = panel.getComponents();
            for (int i = 0; i < components.length; i++) {
                if (components[i] == cell) {
                    return i % 9;
                }
            }
        }
        return -1;
    }
}